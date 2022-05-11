package itmo.model.builders;

import itmo.exceptions.CollectionException;
import itmo.io.Scannable;
import itmo.model.Country;
import itmo.model.Person;

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

    /**
     * Поле определяющее ввод из консоли
     */
    private final boolean isConsole;

    /**
     * Конструктор класса PersonBuilder
     *
     * @param isConsole - значение поля isConsole
     */
    public PersonBuilder(boolean isConsole) {
        person = new Person();
        this.isConsole = isConsole;
    }

    /**
     * Вызывает необходимые методы для добавления полей в объект класса Person
     *
     * @param scannable - значение поля scannable
     * @return person   - значение поля person
     */
    public Person build(Scannable scannable) throws Exception {
        this.buildName(scannable);
        this.buildBirthday(scannable);
        this.buildHeight(scannable);
        this.buildPassportID(scannable);
        this.buildNationality(scannable);

        return this.person;
    }

    /**
     * Метод добавляет поле name объекту класса Person
     *
     * @param scannable - значение объекта scannable
     */
    private void buildName(Scannable scannable) throws Exception {
        if (isConsole) {
            try {
                System.out.println("Введите имя человека: ");
                String nameKiller = scannable.scanString();
                person.setName(nameKiller);
            } catch (Exception e) {
                System.out.println("Что-то пошло не так: " + e.getMessage());
                this.buildName(scannable);
            }

        } else {
            String name = scannable.scanString();
            if (name.equals("")) {
                throw new CollectionException();
            }
            person.setName(name);

        }
    }

    /**
     * Метод добавляет поле birthday объекту класса Person
     *
     * @param scannable - значение поля scannable
     */
    private void buildBirthday(Scannable scannable) throws Exception {
        if (isConsole) {
            try {
                System.out.println("Введите дату рождения в формате yyyy-MM-dd HH:mm: ");
                LocalDateTime birthday = LocalDateTime.parse(scannable.scanString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                person.setBirthday(birthday);
            } catch (Exception e) {
                System.out.println("Что-то пошло не так: " + e.getMessage());
                this.buildBirthday(scannable);
            }
        } else {
            LocalDateTime birthday = LocalDateTime.parse(scannable.scanString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            person.setBirthday(birthday);
        }
    }

    /**
     * Метод добавляет поле height объекту класса Person
     *
     * @param scannable - значение поля scannable
     */
    private void buildHeight(Scannable scannable) throws Exception {
        if (isConsole) {
            try {
                System.out.println("Введите рост человека:");
                String heightString = scannable.scanString();
                Long height;
                if (!heightString.equals("")) {
                    height = Long.parseLong(heightString);
                } else {
                    height = null;
                }
                person.setHeight(height);

            } catch (Exception e) {
                System.out.println("Что-то пошло не так: " + e.getMessage());
                this.buildHeight(scannable);
            }
        } else {
            String heightString = scannable.scanString();
            Long height;
            if (!heightString.equals("")) {
                height = Long.parseLong(heightString);
            } else {
                height = null;
            }
            person.setHeight(height);
        }
    }

    /**
     * Метод добавляет поле passportId объекту класса Person
     *
     * @param scannable - значение поля scannable
     */
    private void buildPassportID(Scannable scannable) throws Exception {
        if (isConsole) {
            try {
                System.out.println("Введите ID паспорта: ");
                String passportId = scannable.scanString();

                person.setPassportID(passportId);
            } catch (Exception e) {
                System.out.println("Что-то пошло не так: " + e.getMessage());
                this.buildPassportID(scannable);
            }

        } else {
            String passportId = scannable.scanString();
            person.setPassportID(passportId);

        }
    }

    /**
     * Метод добавляет поле nationality объекту класса Person
     *
     * @param scannable - значение поля scannable
     */
    private void buildNationality(Scannable scannable) throws Exception {
        if (isConsole) {
            try {
                System.out.println("Выберете одну из предложенных национальностей");
                System.out.println(Country.getValues());
                Country nationality = Country.parse(scannable.scanString());
                person.setNationality(nationality);
            } catch (Exception e) {
                System.out.println("Что-то пошло не так: " + e.getMessage());
                this.buildNationality(scannable);
            }
        } else {
            Country nationality = Country.parse(scannable.scanString());
            person.setNationality(nationality);
        }
    }

}
