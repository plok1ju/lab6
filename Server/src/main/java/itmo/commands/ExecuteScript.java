package itmo.commands;

import itmo.collection.HashTableCollection;
import itmo.model.Dragon;

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
    HashTableCollection<Integer, Dragon> collection;

    /**
     * Конструктор класса ExecuteScript
     *
     * @param fileName   - Поле fileName
     * @param collection - Поле collection
     */
    public ExecuteScript(String fileName, HashTableCollection<Integer, Dragon> collection) {
        this.fileName = fileName;
        this.collection = collection;
    }

    /**
     * Переопределение метода execute
     */
    @Override
    public void execute() throws Exception {
//        if (!Files.isReadable(Paths.get(fileName))) {
//            throw new CollectionException("Невозможно считать файл");
//        }
//        Scannable scannable = new FileScan(fileName);
//        CommandsManager commandsManager = new CommandsManager(collection);
//
//        if (FilesHistory.getInstance().containsFile(new File(fileName))) {
//            throw new CollectionException("Чуть рекурсию не вызвал");
//        }
//        FilesHistory.getInstance().addHistory(new File(fileName));
//
//        try {
//
//            String commandLine = scannable.scanString();
//            while (commandLine != null) {
//
//                Command command = commandsManager.getCommand(commandLine, scannable, false);
//                command.execute();
//                commandLine = scannable.scanString();
//            }
//        } catch (Exception e) {
//            System.out.println(fileName + ": " + e.getMessage());
////            throw new CollectionException(fileName + ": " + e.getMessage() + "\n");
//
//        }
//        FilesHistory.getInstance().removeFile(new File(fileName));
    }
}
