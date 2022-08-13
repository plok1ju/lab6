package server.model.builders;

import server.exceptions.CollectionException;
import server.exceptions.ServerException;
import server.io.Printable;
import server.io.Scannable;
import server.model.Country;
import server.model.Person;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Этот класс создает объект класса Person
 */
public class PersonBuilder {

    /**
     * Поле класса Person
     * {@link Person}
     */
    private final Person person;


    public PersonBuilder() {
        person = new Person();
    }

    /**
     * Вызывает необходимые методы для добавления полей в объект класса Person
     *
     * @param scannable - значение поля scannable
     * @return person   - значение поля person
     */
    public Person build(Scannable scannable, Printable printable) throws Exception {
        this.buildName(scannable, printable);
        this.buildBirthday(scannable, printable);
        this.buildHeight(scannable, printable);
        this.buildPassportID(scannable, printable);
        this.buildNationality(scannable, printable);

        return this.person;
    }

    /**
     * Метод добавляет поле name объекту класса Person
     *
     * @param scannable - значение объекта scannable
     */
    private void buildName(Scannable scannable, Printable printable) throws Exception {
        try {
            printable.printLine("Введите имя человека: ");
            String nameKiller = scannable.scanString();
            person.setName(nameKiller);
        } catch (ServerException serverException) {
            throw serverException;
        } catch (Exception e) {
            printable.printLine("Что-то пошло не так: " + e.getMessage());
            this.buildName(scannable, printable);
        }
    }

    /**
     * Метод добавляет поле birthday объекту класса Person
     *
     * @param scannable - значение поля scannable
     */
    private void buildBirthday(Scannable scannable, Printable printable) throws Exception {
        try {
            printable.printLine("Введите дату рождения в формате yyyy-MM-dd HH:mm: ");
            LocalDateTime birthday = LocalDateTime.parse(scannable.scanString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            person.setBirthday(birthday);
        } catch (ServerException serverException) {
            throw serverException;
        } catch (Exception e) {
            printable.printLine("Что-то пошло не так: " + e.getMessage());
            this.buildBirthday(scannable, printable);
        }
    }

    /**
     * Метод добавляет поле height объекту класса Person
     *
     * @param scannable - значение поля scannable
     */
    private void buildHeight(Scannable scannable, Printable printable) throws Exception {
        try {
            printable.printLine("Введите рост человека:");
            String heightString = scannable.scanString();
            Long height;
            if (!heightString.equals("")) {
                height = Long.parseLong(heightString);
            } else {
                height = null;
            }
            person.setHeight(height);

        } catch (ServerException serverException) {
            throw serverException;
        } catch (Exception e) {
            printable.printLine("Что-то пошло не так: " + e.getMessage());
            this.buildHeight(scannable, printable);
        }
    }

    /**
     * Метод добавляет поле passportId объекту класса Person
     *
     * @param scannable - значение поля scannable
     */
    private void buildPassportID(Scannable scannable, Printable printable) throws Exception {
        try {
            printable.printLine("Введите ID паспорта: ");
            String passportId = scannable.scanString();

            person.setPassportID(passportId);
        } catch (ServerException serverException) {
            throw serverException;
        } catch (Exception e) {
            printable.printLine("Что-то пошло не так: " + e.getMessage());
            this.buildPassportID(scannable, printable);
        }

    }

    /**
     * Метод добавляет поле nationality объекту класса Person
     *
     * @param scannable - значение поля scannable
     */
    private void buildNationality(Scannable scannable, Printable printable) throws Exception {
        try {
            printable.printLine("Выберете одну из предложенных национальностей: " + Country.getValues());
            Country nationality = Country.parse(scannable.scanString());
            person.setNationality(nationality);
        } catch (ServerException serverException) {
            throw serverException;
        } catch (Exception e) {
            printable.printLine("Что-то пошло не так: " + e.getMessage());
            this.buildNationality(scannable, printable);
        }
    }

}
