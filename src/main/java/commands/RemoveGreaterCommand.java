package commands;

import db.Database;
import io.CheckedReader;
import types.MusicBand;
import exceptions.CommandException;

import java.io.IOException;

/**
 * Команда "remove_greater".
 * Не имеет аргументов.
 * Удалить из коллекции все группы, превышающие заданную.
 */
public class RemoveGreaterCommand extends AbstractCommand {
    /**
     * Создание команды "remove_greater"
     * @param db база данных
     */
    public RemoveGreaterCommand(Database db) {
        super("remove_greater",
                "remove_greater - удалить из коллекции все элементы, превышающие заданный",
                db);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CheckedReader reader, String... args) throws IOException {
        if (args.length > 0)
            throw new CommandException("Ошибка: команда '" + name() + "' не имеет аргументов");

        MusicBand mb = MusicBand.fromReader(reader);
        System.out.println("Удалено элементов: " + database.removeGreater(mb));
    }
}
