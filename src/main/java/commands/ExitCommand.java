package commands;

import db.Database;
import io.CheckedReader;

/**
 * Команда "exit".
 * Не имеет аргументов.
 * Команда выхода из приложения без сохранения данных.
 */
public class ExitCommand extends AbstractCommand {
    /**
     * Создание команды "exit"
     * @param db база данных
     */
    public ExitCommand(Database db) {
        super("exit", "exit - завершить программу (без сохранения в файл)", db);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CheckedReader reader, String... args) {
        System.exit(0);
    }
}
