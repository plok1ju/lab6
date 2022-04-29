package itmo.io;

import java.io.IOException;
import java.util.Scanner;

/**
 * Этот класс помогает читать данные с консоли
 */
public class ConsoleScan implements Scannable {

    /**
     * Поле scanner
     */
    private final Scanner scanner;

    private int count;

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
    public String scanString() throws IOException {
        if (!hasNextLine()) {
            System.exit(0);
        }
        ++count;
        return scanner.nextLine();
    }

    /**
     * Переопределение метода hasNextLine
     *
     * @return - true - если есть линия, false - если нет
     */
    @Override
    public boolean hasNextLine() throws IOException {
        return scanner.hasNextLine();
    }

    /**
     * Возвращает число строк
     *
     * @return count - возвращает count
     */
    @Override
    public int linesCount() {
        return count;
    }

}
