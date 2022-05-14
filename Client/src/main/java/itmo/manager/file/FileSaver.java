package itmo.manager.file;


import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import itmo.collection.HashTableCollection;
import itmo.exceptions.CollectionException;
import itmo.manager.FileManager;
import itmo.model.Dragon;
import itmo.serializer.HashTableCollectSerializer;

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
        if (!file.canWrite()) {
            throw new CollectionException("Похоже файл закрыт для записи");
        }
        HashTableCollectSerializer serializer = new HashTableCollectSerializer(dragons);

        XmlMapper xmlMapper = new XmlMapper(); // будет сам записывать дракона
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT); // красивый вывод
        xmlMapper.registerModule(new JavaTimeModule()); // сериализация время

        xmlMapper.writeValue(file, serializer);


    }
}

