package commands;

import db.Database;

/**
 * Абстрактный класс команд, выполняемых над базой данных.
 */
public abstract class AbstractCommand implements Command {
    private final String name;
    private final String description;
    final Database database;

    /**
     * Создание новой команды
     * @param name имя команды
     * @param description описание команды
     * @param db база данных
     */
    public AbstractCommand(String name, String description, Database db) {
        this.name = name;
        this.description = description;
        database = db;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String name() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String description() {
        return description;
    }

}
