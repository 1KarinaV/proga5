package commands;

import db.CommandInvoker;
import db.Database;
import io.CheckedReader;
import exceptions.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Команда "execute_script file_name".
 * Имеет один аргумент file_name - имя файла со скриптом.
 * Считывает и исполняет скрипт из указанного файла.
 */
public class ExecuteCommand extends AbstractCommand {
    /**
     * Создание команды "execute_script"
     * @param db база данных
     */
    public ExecuteCommand(Database db) {
        super("execute_script", "execute_script file_name - считать и исполнить скрипт из указанного файла", db);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CheckedReader reader, String... args) throws IOException {
        if (args.length != 1)
            throw new CommandException("Ошибка: Команда '" + name() + "' должна иметь один аргумент.");

        Path path = Paths.get(args[0]);
        if (Files.notExists(path)) {
            System.out.println("Ошибка: Файл не существует: " + args[0]);
        }
        else if (Files.isReadable(path)) {
            try (CommandInvoker ci = CommandInvoker.getGlobal().createFromThis(path)) {
                ci.run();
            } catch (ValueException e) {
                System.out.println("Ошибка: Выполнение скрипта прервано: " + path);
            }
        }

        else {
            System.out.println("Ошибка: Невозможно прочитать файл: " + args[0]);
        }

    }
    }

