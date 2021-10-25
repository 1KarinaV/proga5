package commands;

import db.Database;
import exceptions.*;
import io.CheckedReader;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Команда "save".
 * Не имеет аргументов.
 * Сохраняет данные коллекции в файл.
 */
public class SaveCommand extends AbstractCommand {
    /**
     * Создание команды "save"
     * @param db база данных
     */
    public SaveCommand(Database db) {
        super("save", "save - сохранить коллекцию в файл", db);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CheckedReader reader, String... args) throws IOException {
        if (args.length > 0)
            throw new CommandException("Ошибка: команда '" + name() + "' не имеет аргументов");

        try {
            database.save();
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
        }
        catch (JAXBException e) {
            System.out.println("Внутренняя ошибка работы с JAXB");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
