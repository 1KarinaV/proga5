package commands;

import db.Database;
import io.CheckedReader;
import types.MusicBand;
import exceptions.CommandException;

import java.io.IOException;

/**
 * Команда "add".
 * Не имеет аргументов.
 * Добавляет музыкальную группу в базу данных групп.
 */
public class AddCommand extends AbstractCommand {
    /**
     * Создание команды "add"
     * @param db база данных
     */
    public AddCommand(Database db) {
        super("add", "add - добавить новый элемент в коллекцию", db);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CheckedReader reader, String... args) throws IOException {
        if (args.length > 0)
            throw new CommandException("Ошибка: команда '" + name() + "' не имеет аргументов");

        MusicBand mb = MusicBand.fromReader(reader);
        database.add(mb);
        System.out.println("Добавлен элемент:\n" + mb);
    }
}
