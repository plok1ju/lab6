package itmo.model;

import itmo.exceptions.CollectionException;

/**
 * Класс координат дракона
 */
public class Coordinates {

    /**
     * Поле значения координаты x
     */
    private Double x; //Поле не может быть null

    /**
     * Поле значения координаты y
     */
    private int y; //Значение поля должно быть больше -16

    /**
     * Пустой конструктор класса Coordinates
     */
    public Coordinates() {
    }

    /**
     * Конструктор класса Coordinates
     *
     * @param x - значение поля x
     * @param y - значение поля y
     */
    public Coordinates(Double x, int y) throws Exception {
        setX(x);
        setY(y);
    }

    /**
     * Получение x
     *
     * @return - значение поля x
     */
    public Double getX() {
        return x;
    }

    /**
     * Добавление координаты x
     *
     * @param x - значение поля x
     */
    public void setX(Double x) throws Exception {
        if (x == null) {
            throw new CollectionException("Координата х не может быть null");
        }
        this.x = x;
    }

    /**
     * Получение y
     *
     * @return - значение поля y
     */
    public int getY() {
        return y;
    }

    /**
     * Добавление координаты y
     *
     * @param y - значение поля y
     */
    public void setY(int y) throws Exception {
        if (y <= -16) {
            throw new CollectionException("Координата y должна быть больше -16!");
        }
        this.y = y;
    }

    /**
     * Переопределение метода toString
     *
     * @return - значения всех полей класса Coordinates
     */
    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }
}
