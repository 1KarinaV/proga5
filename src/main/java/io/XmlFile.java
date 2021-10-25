package io;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.nio.file.Path;

/**
 * Класс предоставляет методы сохранения в файл и загрузки из файла
 * данных с помощью JAXB.
 */
public class XmlFile {
    /**
     * Сохранение свойств указанного объекта в файл.
     * @param obj объект сериализации
     * @param path путь к файлу
     * @param <T> тип объекта
     * @throws JAXBException если классы данных с неверными JAXB аннотациями
     *          или другие неожиданные ошибки при работе JAXB
     */
    public static <T> void marshall(T obj, Path path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(obj, path.toFile());
    }

    /**
     * Загрузка свойств из указанного файла в объект.
     * @param tClass объект класса возвращаемого типа
     * @param path путь к файлу
     * @param <T> тип возвращаемого объекта
     * @return загруженный объект
     * @throws JAXBException если классы данных с неверными JAXB аннотациями
     *          или другие неожиданные ошибки при работе JAXB
     */
    @SuppressWarnings("unchecked")
    public static <T> T unmarshall(Class<T> tClass, Path path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(tClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(path.toFile());
    }
}
