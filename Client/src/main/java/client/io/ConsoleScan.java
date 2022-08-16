package client.io;

import java.io.IOException;
import java.util.Scanner;

/**
 * Этот класс помогает читать данные с консоли
 */
public class ConsoleScan implements Scannable<String> {

    /**
     * Поле scanner
     */
    private final Scanner scanner;

    /**
     * Конструктор класса ConsoleScan
     */
    public ConsoleScan() {
        scanner = new Scanner(System.in);
    }

    /**
     * Переопределение метода scanString
     *
     * @return - считанная строка
     */
    @Override
    public String scan() throws IOException {
        return scanner.nextLine();
    }

    /**
     * Переопределение метода hasNextLine
     *
     * @return - true - если есть линия, false - если нет
     */

    @Override
    public void close() throws IOException {
        scanner.close();
    }
}
