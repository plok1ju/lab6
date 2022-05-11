package itmo.model;

import itmo.exceptions.CollectionException;

import java.util.Locale;

/**
 * Enum с наполнением возможных характеров драконов
 */
public enum DragonCharacter {
    WISE,
    GOOD,
    FICKLE;

    /**
     * Получение элементов enum
     *
     * @return - строка со значениями
     */
    public static String getValues() {
        DragonCharacter[] charactersArray = DragonCharacter.values();
        StringBuilder stringBuilder = new StringBuilder();
        for (DragonCharacter character : charactersArray) {
            stringBuilder.append(character).append(", ");
        }
        return stringBuilder.toString();

    }

    /**
     * Метод определяет, то ли значение добавляется в enum
     *
     * @param characterString
     * @return - характер дракона
     */
    public static DragonCharacter parse(String characterString) throws Exception {
        try {
            return valueOf(characterString.toUpperCase(Locale.ROOT).trim());
        } catch (Exception e) {
            throw new CollectionException("В " + DragonCharacter.class.getSimpleName() + " нет константы " + characterString + " :(");
        }

    }
}
