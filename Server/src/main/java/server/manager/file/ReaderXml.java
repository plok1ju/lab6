package server.manager.file;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.collection.HashTableCollection;
import server.deserializers.HashTableCollectDeserializer;
import server.exceptions.CollectionException;
import server.manager.FileManager;
import server.model.Dragon;

import java.io.File;

/**
 * Этот класс считывает информацию из xml файла и вызывает deserializer
 */
public class ReaderXml {

    Logger log = LoggerFactory.getLogger(ReaderXml.class);
    /**
     * Метод отвечает за создание коллекции по данным файла xml
     *
     * @return - коллекция
     */
    public HashTableCollection<Integer, Dragon> returnCollect() throws Exception {
        FileManager fileManager = new FileManager();
        File file = fileManager.getFile();

        if (!file.exists()){
            log.error("Файла " + file.getName()+ " не существует");
            throw new CollectionException("Файла " + file.getName() + " не существует");
        }
        if (!file.canRead()) {
            log.error("Файл закрыт для чтения");
            throw new CollectionException("Похоже файл закрыт для чтения");
        }

        XmlMapper xmlMapper = new XmlMapper(); // будет сам записывать дракона
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT); // красивый вывод
        xmlMapper.registerModule(new JavaTimeModule()); // сериализация время
        HashTableCollectDeserializer deserializer = xmlMapper.readValue(file, HashTableCollectDeserializer.class); // десериализация в класс Dragon
        HashTableCollection<Integer, Dragon> collection = deserializer.getCollection();
        return collection;
    }


}
