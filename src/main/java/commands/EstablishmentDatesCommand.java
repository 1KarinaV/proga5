package commands;

import db.Database;
import io.CheckedReader;
import exceptions.CommandException;

import java.io.IOException;

/**
 * Команда "print_field_descending_establishment_date".
 * Не имеет аргументов.
 * Выводит значения дат основания всех групп в порядке убывания.
 */
public class EstablishmentDatesCommand extends AbstractCommand {
    /**
     * Создание команды "print_field_descending_establishment_date"
     * @param db база данных
     */
    public EstablishmentDatesCommand(Database db) {
        super("print_field_descending_establishment_date",
                "print_field_descending_establishment_date - " +
                        "вывести значения поля establishmentDate всех элементов в порядке убывания", db);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(CheckedReader reader, String... args) throws IOException {
        if (args.length > 0)
            throw new CommandException("Ошибка: команда '" + name() + "' не имеет аргументов");
        database.showEstablishmentDates();
    }
}
