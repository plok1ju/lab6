package itmo.commands;

/**
 * Интерфейс объекта, который отвечает за выполнение команд
 */
public interface Command {
    /**
     * Обеспечивает выполнение команд
     */
    void execute() throws Exception;
}
