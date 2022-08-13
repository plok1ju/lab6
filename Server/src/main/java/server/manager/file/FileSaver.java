package server.manager.file;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.Main;
import server.collection.HashTableCollection;
import server.exceptions.CollectionException;
import server.manager.FileManager;
import server.model.Dragon;
import server.serializer.HashTableCollectSerializer;

import java.io.File;

/**
 * Этот класс записывает коллекцию в файл
 */
public class FileSaver {
    /**
     * Метод отвечает за сохранение коллекции в файл xml
     *
     * @param dragons
     */
    public void saveInfo(HashTableCollection<Integer, Dragon> dragons) throws Exception {
        FileManager fileManager = new FileManager();
        File file = fileManager.getFile();
        if (!file.exists()){
            file.createNewFile();
        }
        if (!file.canWrite()) {
            Main.log.error("Файл закрыт для чтения");
            throw new CollectionException("Похоже файл закрыт для записи");
        }
        HashTableCollectSerializer serializer = new HashTableCollectSerializer(dragons);

        XmlMapper xmlMapper = new XmlMapper(); // будет сам записывать дракона
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT); // красивый вывод
        xmlMapper.registerModule(new JavaTimeModule()); // сериализация время

        xmlMapper.writeValue(file, serializer);


    }
}

