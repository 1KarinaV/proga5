package commands;

import db.Database;
import io.CheckedReader;
import exceptions.CommandException;

import java.io.IOException;

/**
 * Команда "average_of_number_of_participants".
 * Не имеет аргументов.
 * Выводит среднее число участников во всех группах.
 */
public class ParticipantsAvgCommand extends AbstractCommand {
    /**
     * Создание команды "average_of_number_of_participants"
     * @param db база данных
     */
    public ParticipantsAvgCommand(Database db) {
        super("average_of_number_of_participants",
                "average_of_number_of_participants - " +
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

        System.out.println("Среднее: " + database.getParticipantsAvg());
    }
}
