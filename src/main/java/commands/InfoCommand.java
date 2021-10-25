package commands;

import db.Database;
import io.CheckedReader;
import exceptions.CommandException;

import java.io.IOException;

/**
 * Команда "info".
 * Не имеет аргументов.
 * Команда вывода информации о коллекции и данных.
 */
public class InfoCommand extends AbstractCommand {
    /**
     * Создание команды "info"
     * @param db база данных
     */
    public InfoCommand(Database db) {
        super("info", "info - вывести в стандартный поток вывода информацию о коллекции", db);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CheckedReader reader, String... args) throws IOException {
        if (args.length > 0)
            throw new CommandException("Ошибка: команда '" + name() + "' не имеет аргументов");
        System.out.println(database);
    }
}
