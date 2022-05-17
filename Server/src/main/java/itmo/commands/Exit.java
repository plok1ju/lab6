package itmo.commands;

import itmo.io.Printable;

/**
 * Класс отвечает за завершение программы
 */
public class Exit implements Command {

    private final Printable printable;

    public Exit(Printable printable) {
        this.printable = printable;
    }

    /**
     * Переопределение метода execute
     * Завершение программы
     */
    @Override
    public void execute() throws Exception {
        printable.printLine("/exit/");
    }

}
