package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.exceptions.CollectionException;
import itmo.io.FileScan;
import itmo.io.Printable;
import itmo.io.Scannable;
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
     * Поле fileName
     */
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
     * @param fileName   - Поле fileName
     * @param collection - Поле collection
     * @param scannable
     * @param printable
     */
    public ExecuteScript(String fileName, HashTableCollection<Integer, Dragon> collection, Scannable scannable, Printable printable) {
        this.fileName = fileName;
        this.collection = collection;
        this.scannable = scannable;
        this.printable = printable;
    }

    /**
     * Переопределение метода execute
     */
    @Override
    public void execute() throws Exception {
        if (!Files.isReadable(Paths.get(fileName))) {
            throw new CollectionException("Невозможно считать файл");
        }
        Scannable scannable = new FileScan(fileName);
        CommandsManager commandsManager = new CommandsManager(collection);

        if (FilesHistory.getInstance().containsFile(new File(fileName))) {
            throw new CollectionException("Чуть рекурсию не вызвал");
        }
        FilesHistory.getInstance().addHistory(new File(fileName));

        try {

            //String commandLine = scannable.scanString();
            while (true) {

                //Command command = commandsManager.getCommand(commandLine, scannable, false);
                commandsManager.sendCommandInfo(scannable, printable, false);
                //Thread.sleep(100);
                //commandLine = scannable.scanString();
            }
        } catch (Exception e) {
            System.out.println(fileName + ": " + e.getMessage());
//            throw new CollectionException(fileName + ": " + e.getMessage() + "\n");

        }
        FilesHistory.getInstance().removeFile(new File(fileName));
    }
}
