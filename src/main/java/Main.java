import db.CommandInvoker;
import db.Database;
import commands.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1)
            printUsage();

        try {
            Database db = new Database(args[0]);
            CommandInvoker ci = CommandInvoker.getGlobal();
            ci.register(new AddCommand(db));
            ci.register(new AddIfMaxCommand(db));
            ci.register(new ClearCommand(db));
            ci.register(new EstablishmentDatesCommand(db));
            ci.register(new ExecuteCommand(db));
            ci.register(new ExitCommand(db));
            ci.register(new InfoCommand(db));
            ci.register(new ParticipantsAvgCommand(db));
            ci.register(new ParticipantsSumCommand(db));
            ci.register(new RemoveCommand(db));
            ci.register(new RemoveGreaterCommand(db));
            ci.register(new SaveCommand(db));
            ci.register(new ShowCommand(db));
            ci.register(new UpdateCommand(db));
            ci.run();
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (JAXBException e) {
            System.out.println("Внутренняя ошибка работы с JAXB");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void printUsage()
    {
        System.out.println("Программа принимает на вход ровно один аргумент - путь до файла.");
        System.out.println("Пожалуйста, проверьте верность аргументов и повторите запуск.");
        System.exit(1);
    }
}

