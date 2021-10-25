package db;

import commands.*;
import io.CheckedReader;
import exceptions.CommandException;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Класс регистрации и выполнения команд над базой данных.
 * Команды регистрируются с помощью {@link #register(Command) registre}.
 * Цикл обработки и выполнения команд запускается методом {@link #run() run}.
 * Команды считываются из входного потока с помощью
 * {@link CheckedReader}.
 */
public class CommandInvoker implements Closeable {
    private final TreeMap<String, Command> commands;
    private final LinkedList<String> history;
    private final CheckedReader reader;

    private static final CommandInvoker global = new CommandInvoker(System.in);
    private static final Stack<Path> scriptTrace = new Stack<>();

    private static final String CMD_HINT = "Введите 'help' для справки о доступных командах\n";
    private static final int HISTORY_ENTRIES = 14;

    // Команды help и history существуют только в контексте CommandInvoker

    // help
    private class HelpCommand extends AbstractCommand {
        HelpCommand() {
            super("help", "help - вывести последние 14 команд (без их аргументов)", null);
        }

        @Override
        public void execute(CheckedReader reader, String... args) {
            commands.forEach((k, v) -> System.out.println(v.description()));
        }
    }

    // history
    private class HistoryCommand extends AbstractCommand {
        HistoryCommand() {
            super("history", "history - вывести справку по доступным командам", null);
        }

        @Override
        public void execute(CheckedReader reader, String... args) {
            history.forEach(System.out::println);
        }
    }

    // Создание нового CommandInvoker, читающего и исполняющего команды
    // из указанного потока./
    private CommandInvoker(InputStream in) {
        commands = new TreeMap<>();
        history = new LinkedList<>();
        reader = new CheckedReader(in);

        register(new HelpCommand());
        register(new HistoryCommand());
    }

    /**
     * Глобальный объект CommandInvoker.
     * Работает с потоком {@code System.in}
     * @return глобальный CommandInvoker
     */
    public static CommandInvoker getGlobal() {
        return global;
    }

    /**
     * Создание нового CommandInvoker из текущего.
     * Созданный объект имеет такой же набор зарегистрированных команд.
     * Входной поток получения команд связывается с указанным файлом.
     * @param path путь к файлу
     * @return новый CommandInvoker
     * @throws FileNotFoundException если файл по указанному пути недоступен.
     * @throws CommandException при ошибках исполнения команд
     */
    public CommandInvoker createFromThis(Path path) throws FileNotFoundException, CommandException {
        path = path.normalize().toAbsolutePath();
        if (scriptTrace.search(path) < 0) {
            scriptTrace.push(path);
            CommandInvoker result = new CommandInvoker(new FileInputStream(path.toFile()));
            commands.forEach((k, v) -> result.register(v));
            return result;
        }
        throw new CommandException("Ошибка: рекурсивное выполнение скриптов не поддерживается: " + path);
    }

    /**
     * Регистрация новой команды.
     * @param cmd команда
     */
    public void register(Command cmd) {
        commands.putIfAbsent(cmd.name(), cmd);
    }

    /**
     * Запуск цикла ввода и обработки команд.
     * @throws IOException при ошибках ввода/вывода
     */
    public void run() throws IOException {
        reader.printPrompt(CMD_HINT);

        while (true) {
            reader.printPrompt("> ");

            String[] args = readCommand();
            if (args == null)
                break; // EOF
            if (args.length == 0) {
                reader.printPrompt(CMD_HINT);
                continue;
            }

            Command cmd = commands.get(args[0]);
            if (cmd == null) {
                System.out.println("Ошибка: Неизвестная команда: " + args[0]);
                reader.printPrompt(CMD_HINT);
                continue;
            }

            if (history.size() == HISTORY_ENTRIES)
                history.removeFirst();
            history.addLast(cmd.name());

            try {
                cmd.execute(reader, Arrays.copyOfRange(args, 1, args.length));
            }
            catch (CommandException e) {
                System.out.println("Ошибка: " + e.getMessage());
                reader.printPrompt(CMD_HINT);
            }
        }
    }

    // Читает команду из потока ввода и возвращает массив
    // строк, первым элементом которого будет название команды,
    // остальные - аргументы команды.
    private String[] readCommand() throws IOException {
        String line = reader.readLine();
        if (line == null)
            return null;
        return Arrays.stream(line.split("\\s"))
                .filter(s -> s.length() > 0)
                .toArray(String[]::new);
    }

    /**
     * Закрытие связанного с данным объектом потока ввода.
     * @throws IOException при ошибках закрытия потока
     */
    @Override
    public void close() throws IOException {
        reader.close();
        scriptTrace.pop();
    }
}
