package itmo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import itmo.exceptions.CollectionException;

import java.time.LocalDateTime;

/**
 * Класс человек
 */
public class Person {

    /**
     * Поле имя человека
     */
    private String name; //Поле не может быть null, Строка не может быть пустой

    /**
     * Поле дата рождения человека
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime birthday; //Поле не может быть null

    /**
     * Поле рост человека
     */
    private Long height; //Поле может быть null, Значение поля должно быть больше 0

    /**
     * Поле паспорт человека
     */
    private String passportID; //Значение этого поля должно быть уникальным, Строка не может быть пустой, Поле не может быть null

    /**
     * Поле национальность человека
     */
    private Country nationality; //Поле не может быть null

    /**
     * Пустой конструктор класса Person
     */
    public Person() {
    }

    /**
     * Конструктор класса Person
     *
     * @param name        - значение поля name
     * @param birthday    - значение поля birthday
     * @param height      - значение поля height
     * @param passportID  - значение поля passportID
     * @param nationality - значение поля nationality
     */
    public Person(String name, LocalDateTime birthday, Long height, String passportID, Country nationality) throws Exception {
        setName(name);
        setBirthday(birthday);
        setHeight(height);
        setPassportID(passportID);
        setNationality(nationality);
    }

    /**
     * Получение passportID
     *
     * @return - значение поля passportID
     */
    public String getPassportID() {
        return passportID;
    }

    /**
     * Установка passportID
     *
     * @param passportID - значение поля passportID
     */
    public void setPassportID(String passportID) throws Exception {
        if (passportID == null) {
            throw new CollectionException("Поле passportID не может быть null!");
        } else if (passportID.equals("")) {
            throw new CollectionException("Поле passportID не может быть пустой строкой!");
        }
        this.passportID = passportID;
    }

    /**
     * Получение height
     *
     * @return - значение поля height
     */
    public Long getHeight() {
        return height;
    }

    /**
     * Установка height
     *
     * @param height - значение поля height
     */
    public void setHeight(Long height) throws Exception {
        if (height == null) {
            throw new CollectionException("Поле height не может быть null!");
        } else if (height <= 0) {
            throw new CollectionException("Поле height должно быть больше 0!");
        }
        this.height = height;
    }

    /**
     * Получение nationality
     *
     * @return - значение поля nationality
     */
    public Country getNationality() {
        return nationality;
    }

    /**
     * Установка nationality
     *
     * @param nationality - значение поля nationality
     */
    public void setNationality(Country nationality) throws Exception {
        if (nationality == null) {
            throw new CollectionException("Поле nationality не может быть null!");
        }
        this.nationality = nationality;
    }

    /**
     * Получение birthday
     *
     * @return - значение поля birthday
     */
    public LocalDateTime getBirthday() {
        return birthday;
    }

    /**
     * Установка birthday
     *
     * @param birthday - значение поля birthday
     */
    public void setBirthday(LocalDateTime birthday) throws Exception {
        if (birthday == null)
            throw new CollectionException("Поле birthday не может быть null!");
        this.birthday = birthday;
    }

    /**
     * Получение name
     *
     * @return - значение поля name
     */
    public String getName() {
        return name;
    }

    /**
     * Установка name
     *
     * @param name - значение поля name
     */
    public void setName(String name) throws Exception {
        if (name == null) {
            throw new CollectionException("Поле name не может быть null!");
        } else if (name.equals("")) {
            throw new CollectionException("Поле name не может быть пустой строкой!");
        }
        this.name = name;
    }

    /**
     * Переопределение метода toString
     *
     * @return - значения всех полей класса Person
     */
    @Override
    public String toString() {
        return "name: " + name + "\n" +
                "birthday: " + birthday + "\n" +
                "height: " + height + "\n" +
                "passportID: " + passportID + "\n" +
                "nationality: " + nationality;
    }

}