package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.exceptions.CollectionException;
import itmo.exceptions.ServerException;
import itmo.io.FileScan;
import itmo.io.Printable;
import itmo.io.Scannable;
import itmo.io.StringReader;
import itmo.manager.CommandsManager;
import itmo.manager.FilesHistory;
import itmo.model.Dragon;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс отвечает за исполнение команд из файла
 */
public class ExecuteScript implements Command {

    /**
     * Поле fileContent
     */
    private final String fileContent;
    private final String fileName;

    /**
     * Поле collection
     * {@link HashTableCollection}
     */
    private final HashTableCollection<Integer, Dragon> collection;
    private final Scannable scannable;
    private final Printable printable;

    /**
     * Конструктор класса ExecuteScript
     *
     * @param fileContent   - Поле fileName
     * @param collection - Поле collection
     * @param scannable
     * @param printable
     */
    public ExecuteScript(String filename, String fileContent, HashTableCollection<Integer, Dragon> collection, Scannable scannable, Printable printable) {
        this.fileName = filename;
        this.fileContent = fileContent;
        this.collection = collection;
        this.scannable = scannable;
        this.printable = printable;
    }

    /**
     * Переопределение метода execute
     */
    @Override
    public void execute() throws Exception {

        Scannable scannable = new StringReader(fileContent);
        CommandsManager commandsManager = new CommandsManager(collection);

        if (FilesHistory.getInstance().containsFile(new File(fileName))) {
            throw new CollectionException("Чуть рекурсию не вызвал");
        }
        FilesHistory.getInstance().addHistory(new File(fileName));

        while (scannable.hasNextLine()) {
            try {
                commandsManager.getCommandFromFile(scannable, printable).execute();
            } catch (ServerException serverException){
                FilesHistory.getInstance().removeFile(new File(fileName));
                throw serverException;
            } catch (Exception e) {
                printable.printLine(fileName + ": " + e.getMessage());
//            throw new CollectionException(fileName + ": " + e.getMessage() + "\n");
            }
        }
        FilesHistory.getInstance().removeFile(new File(fileName));
    }
}
