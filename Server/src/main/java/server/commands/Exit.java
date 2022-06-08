package server.commands;

import server.io.Printable;

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
        Thread.sleep(1000);
        printable.printLine("/exit/");
    }

}
