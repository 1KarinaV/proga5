package commands;

import db.Database;
import io.CheckedReader;
import exceptions.CommandException;

import java.io.IOException;

/**
 * Команда "show".
 * Не имеет аргументов.
 * Вывод на экран всех групп.
 */
public class ShowCommand extends AbstractCommand {
    /**
     * Создание команды "show"
     * @param db база данных
     */
    public ShowCommand(Database db) {
        super("show", "show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении", db);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CheckedReader reader, String... args) throws IOException {
        if (args.length > 0)
            throw new CommandException("Ошибка: команда '" + name() + "' не имеет аргументов");
        database.show();
    }
}
