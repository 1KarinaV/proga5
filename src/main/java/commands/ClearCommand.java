package commands;

import db.Database;
import io.CheckedReader;
import exceptions.CommandException;

import java.io.IOException;

/**
 * Команда "clear".
 * Не имеет аргументов.
 * Удаляет все группы из базы данных.
 */
public class ClearCommand extends AbstractCommand {
    /**
     * Создание команды "clear"
     * @param db база данных
     */
    public ClearCommand(Database db) {
        super("clear", "clear - очистить коллекцию", db);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CheckedReader reader, String... args) throws IOException {
        if (args.length > 0)
            throw new CommandException("Ошибка: команда '" + name() + "' не имеет аргументов");
        database.clear();
    }
}
