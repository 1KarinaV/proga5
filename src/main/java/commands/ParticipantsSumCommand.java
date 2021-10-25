package commands;

import db.Database;
import io.CheckedReader;
import exceptions.CommandException;

import java.io.IOException;

/**
 * Команда "sum_of_number_of_participants".
 * Не имеет аргументов.
 * Вывести сумму участников всех групп.
 */
public class ParticipantsSumCommand extends AbstractCommand {
    /**
     * Создание команды "sum_of_number_of_participants"
     * @param db база данных
     */
    public ParticipantsSumCommand(Database db) {
        super("sum_of_number_of_participants",
                "sum_of_number_of_participants - " +
                        "вывести сумму значений поля numberOfParticipants для всех элементов коллекции",
                db);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CheckedReader reader, String... args) throws IOException {
        if (args.length > 0)
            throw new CommandException("Ошибка: команда '" + name() + "' не имеет аргументов");

        System.out.println("Сумма: " + database.getParticipantsSum());
    }
}
