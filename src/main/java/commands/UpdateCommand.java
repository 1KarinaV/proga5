package commands;

import db.Database;
import io.CheckedReader;
import types.MusicBand;
import exceptions.CommandException;

import java.io.IOException;

/**
 * Команда "update id".
 * Имеет один аргумент - id группы.
 * Обновление группы с заданным id.
 */
public class UpdateCommand extends AbstractCommand {
    /**
     * Создание команды "update"
     * @param db база данных
     */
    public UpdateCommand(Database db) {
        super("update", "update id - обновить значение элемента коллекции по заданному id", db);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CheckedReader reader, String... args) throws IOException {
        if (args.length != 1)
            throw new CommandException("Ошибка: команда '" + name() + "' должна иметь один аргумент.");

        Integer id = Integer.valueOf(args[0]);
        MusicBand found = database.find(id);
        if (found != null) {
            MusicBand tmp = MusicBand.fromReader(reader);
            found.setBestAlbum(tmp.getBestAlbum());
            found.setCoordinates(tmp.getCoordinates());
            found.setEstablishmentDate(tmp.getEstablishmentDate());
            found.setGenre(tmp.getGenre());
            found.setName(tmp.getName());
            found.setNumberOfParticipants(tmp.getNumberOfParticipants());
            System.out.println("Обновлен элемент:\n" + found);
        }
        else {
            System.out.println("Элемент с id=" + id + " отсутствует в коллекции.");
        }
    }
}
