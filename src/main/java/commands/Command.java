package commands;

import io.CheckedReader;

import java.io.IOException;

/**
 * Интерфейс команд
 */
public interface Command {
    /**
     * Название команды
     * @return название команды
     */
    String name();

    /**
     * Имя команды и ее использование
     * @return описание команды
     */
    String description();

    /**
     * Выполняет команду.
     * @param reader объект, с помощью которого команды читаются
     * @param args аргументы команды
     * @throws IOException при ошибках ввода команды
     */
    void execute(CheckedReader reader, String... args) throws IOException;
}
