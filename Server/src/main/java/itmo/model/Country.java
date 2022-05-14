package itmo.model;

import itmo.exceptions.CollectionException;

import java.util.Locale;

/**
 * Enum с наполнением возможной национальности
 */
public enum Country {
    GERMANY,
    CHINA,
    ITALY;

    /**
     * Получение элементов enum
     *
     * @return - строка со значениями
     */
    public static String getValues() {
        Country[] countriesArray = Country.values();
        StringBuilder stringBuilder = new StringBuilder();
        for (Country country : countriesArray) {
            stringBuilder.append(country).append(", ");
        }
        return stringBuilder.toString();

    }

    /**
     * Метод определяет, то ли значение добавляется в enum
     *
     * @param countryString
     * @return - страна рождения
     */
    public static Country parse(String countryString) throws Exception {
        try {
            return valueOf(countryString.toUpperCase(Locale.ROOT).trim());
        } catch (Exception e) {
            throw new CollectionException("В " + Country.class.getSimpleName() + " нет константы " + countryString + " :(");
        }
    }
}
