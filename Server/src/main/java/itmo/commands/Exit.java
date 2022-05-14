package itmo.commands;

/**
 * Класс отвечает за завершение программы
 */
public class Exit implements Command {

    /**
     * Пустой конструктор класса Exit
     */
    public Exit() {
    }

    /**
     * Переопределение метода execute
     * Завершение программы
     */
    @Override
    public void execute() {
        System.exit(0);
    }
}
