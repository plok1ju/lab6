package itmo.model.builders;

import itmo.exceptions.ServerException;
import itmo.io.Printable;
import itmo.io.Scannable;
import itmo.model.Coordinates;

/**
 * Этот класс создает объект класса Coordinates
 */
public class CoordinatesBuilder {

    /**
     * Поле координаты дракона
     * {@link Coordinates}
     */
    private Coordinates coordinates;

    /**
     * Поле консоль
     */
    private final boolean isConsole;

    /**
     * Конструктор класса CoordinatesBuilder
     *
     * @param isConsole - значение поля isConsole
     */
    public CoordinatesBuilder(boolean isConsole) {
        this.isConsole = isConsole;
    }

    /**
     * Вызывает необходимые методы для добавления полей в объект класса Coordinates
     *
     * @param scannable - значение поля scannable
     * @return coordinates   - значение объекта coordinates
     */
    public Coordinates build(Scannable scannable, Printable printable) throws Exception {
        coordinates = new Coordinates();
        this.buildX(scannable, printable);
        this.buildY(scannable, printable);
        return coordinates;

    }

    /**
     * Метод добавляет поле x объекту класса Coordinates
     *
     * @param scannable - значение поля scannable
     */
    private void buildX(Scannable scannable, Printable printable) throws Exception {
        if (isConsole) {
            try {
                printable.printLine("Введите координату х: ");
                Double x = Double.parseDouble(scannable.scanString());
                coordinates.setX(x);
            } catch (Exception e) {
                printable.printLine("/noresponse/Что-то не то с координатой х: " + e.getMessage());
                this.buildX(scannable, printable);
            }
        } else {
            Double x = Double.parseDouble(scannable.scanString());
            coordinates.setX(x);

        }
    }

    /**
     * Метод добавляет поле y объекту класса Coordinates
     *
     * @param scannable - значение поля scannable
     */
    private void buildY(Scannable scannable, Printable printable) throws Exception {
        if (isConsole) {
            try {
                printable.printLine("Введите координату y: ");
                int y = Integer.parseInt(scannable.scanString());
                coordinates.setY(y);
            } catch (ServerException serverException){
                throw serverException;
            }
            catch (Exception e) {
                printable.printLine("/noresponse/Что-то не то с координатой y: " + e.getMessage());
                this.buildY(scannable, printable);
            }
        } else {
            int y = Integer.parseInt(scannable.scanString());
            coordinates.setY(y);
        }

    }


}
