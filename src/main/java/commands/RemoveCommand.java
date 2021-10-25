package commands;

import db.Database;
import io.CheckedReader;
import exceptions.CommandException;

import java.io.IOException;

/**
 * Команда "remove_by_id id".
 * Имеет один аргумент - id группы.
 * Удаление группы с заданным id.
 */
public class RemoveCommand extends AbstractCommand {
    /**
     * Создание команды "remove_by_id"
     * @param db база данных
     */
    public RemoveCommand(Database db) {
        super("remove_by_id", "remove_by_id id - удалить элемент из коллекции по его id", db);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CheckedReader reader, String... args) throws IOException {
        if (args.length != 1)
            throw new CommandException("Ошибка: команда '" + name() + "' должна иметь один аргумент.");

        Integer id = Integer.valueOf(args[0]);
        if (database.remove(id))
            System.out.println("Элемент успешно удален.");
        else
            System.out.println("Элемент с id=" + id + " отсутствует в коллекции.");
    }
}
