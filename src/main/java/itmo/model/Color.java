package itmo.model;

import itmo.exceptions.CollectionException;

import java.util.Locale;

/**
 * Enum с наполнением возможного цвета дракона
 */
public enum Color {
    BLUE,
    YELLOW,
    ORANGE,
    WHITE;

    /**
     * Получение элементов enum
     *
     * @return - строка со значениями
     */
    public static String getValues() {
        Color[] colorsArray = Color.values();
        StringBuilder stringBuilder = new StringBuilder();
        for (Color color : colorsArray) {
            stringBuilder.append(color).append(", ");
        }
        return stringBuilder.toString();

    }

    /**
     * Метод определяет, то ли значение добавляется в enum
     *
     * @param stringColor - цвет
     * @return - цвет
     */
    public static Color parse(String stringColor) throws Exception {
        try {
            return valueOf(stringColor.toUpperCase(Locale.ROOT).trim());
        } catch (Exception e) {
            throw new CollectionException("В " + Color.class.getSimpleName() + " нет константы " + stringColor + " :(");
        }
    }
}
