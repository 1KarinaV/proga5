package commands;

import db.Database;
import io.CheckedReader;
import types.MusicBand;
import exceptions.*;

import java.io.IOException;

/**
 * Команда "add_if_max {element}".
 * Не имеет аргументов.
 * Добавляет музыкальную группу в базу данных групп, если
 * введенное значение превышает значение наибольшего элемента.
 */
public class AddIfMaxCommand extends AbstractCommand {
    /**
     * Создание команды "add_if_max"
     * @param db база данных
     */
    public AddIfMaxCommand(Database db) {
        super("add_if_max",
                "add_if_max - добавить новый элемент в коллекцию, если его значение\n" +
                        "             превышает значение наибольшего элемента этой коллекции",
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
        if (database.addIfMax(mb))
            System.out.println("Добавлен элемент:\n" + mb);
        else
            System.out.println("Ничего не добавлено");
    }
}
