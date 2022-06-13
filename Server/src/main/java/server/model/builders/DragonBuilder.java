package server.model.builders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import server.exceptions.ServerException;
import server.io.Printable;
import server.io.Scannable;
import server.manager.IdGenerator;
import server.model.Color;
import server.model.Dragon;
import server.model.DragonCharacter;

import java.util.Date;
import java.util.Locale;

/**
 * Этот класс создает объект класса Dragon
 */
public class DragonBuilder {

    /**
     * Поле класса Dragon
     * {@link Dragon}
     */
    @JsonIgnore
    private Dragon dragon;

    /**
     * Поле определяющее ввод из консоли
     */
    private boolean isConsole;
    public boolean isConsole() {
        return isConsole;
    }

    public void setConsole(boolean console) {
        isConsole = console;
    }

    public DragonBuilder(){
        this.dragon = new Dragon();
    }
    /**
     * Конструктор класса DragonBuilder
     *
     * @param isConsole - значение поля isConsole
     */
    public DragonBuilder(boolean isConsole) {
        this.isConsole = isConsole;
        dragon = new Dragon();
    }

    /**
     * Вызывает необходимые методы для добавления полей в объект класса Dragon
     *
     * @return dragon   - значение объекта dragon
     */
    public Dragon build(Scannable scannable, Printable printable) throws Exception {
        this.buildId();
        this.buildName(scannable, printable);
        this.buildAge(scannable, printable);
        this.buildCoordinates(scannable, printable);
        this.setCreationDate();
        this.buildDescription(scannable, printable);
        this.buildColor(scannable, printable);
        this.buildCharacter(scannable, printable);
        this.buildPerson(scannable, printable);

        return this.dragon;

    }

    /**
     * Метод добавляет поле id объекту класса Dragon
     */
    private void buildId() throws Exception {
        dragon.setId(IdGenerator.getId());
    }

    /**
     * Метод добавляет поле name объекту класса Dragon
     *
     * @param scannable - значение поля scannable
     */
    private void buildName(Scannable scannable, Printable printable) throws Exception {
        if (isConsole) {
            try {
                printable.printLine("Введите имя дракона:");
                String nameDragon = scannable.scanString();
                dragon.setName(nameDragon);

            } catch (ServerException serverException){
                throw serverException;
            }
            catch (Exception e) {
                printable.printLine("Что-то пошло не так: " + e.getMessage());
                this.buildName(scannable, printable);
            }
        } else {
            String nameDragon = scannable.scanString();
            dragon.setName(nameDragon);
        }
    }

    /**
     * Метод добавляет поле age объекту класса Dragon
     *
     * @param scannable - значение поля scannable
     */
    private void buildAge(Scannable scannable, Printable printable) throws Exception {
        if (isConsole) {
            try {
                printable.printLine("Введите возраст дракона:");
                String ageString = scannable.scanString();
                Integer age;
                if (ageString.equals("")) {
                    age = null;
                } else {
                    age = Integer.parseInt(ageString);
                }
                dragon.setAge(age);
            } catch (ServerException serverException){
                throw serverException;
            }
            catch (Exception e) {
                printable.printLine("Что-то пошло не так: " + e.getMessage());
                this.buildAge(scannable, printable);

            }
        } else {
            String ageString = scannable.scanString();
            Integer age;
            if (ageString.equals("")) {
                age = null;
            } else {
                age = Integer.parseInt(ageString);
            }
            dragon.setAge(age);
        }
    }

    /**
     * Метод добавляет поле description объекту класса Dragon
     *
     * @param scannable - значение поля scannable
     */
    private void buildDescription(Scannable scannable, Printable printable) throws Exception {
        if (isConsole) {
            try {
                printable.printLine("Введите описание дракона:");
                String description = scannable.scanString();
                if (description.equals("")) {
                    description = null;
                }
                dragon.setDescription(description);

            } catch (ServerException serverException){
                throw serverException;
            }
            catch (Exception e) {
                printable.printLine("Что-то пошло не так: " + e.getMessage());
                this.buildDescription(scannable, printable);

            }
        } else {
            String description = scannable.scanString();
            if (description.equals("")) {
                description = null;
            }
            dragon.setDescription(description);
        }
    }

    /**
     * Метод добавляет поле color объекту класса Dragon
     *
     * @param scannable - значение поля scannable
     */
    private void buildColor(Scannable scannable, Printable printable) throws Exception {
        if (isConsole) {
            try {
                printable.printLine("Выберете одно из предложенных значений цвета для дракона " + Color.getValues());
                Color color = Color.parse(scannable.scanString());
                dragon.setColor(color);
            } catch (ServerException serverException){
                throw serverException;
            }
            catch (Exception e) {
                printable.printLine("Что-то пошло не так: " + e.getMessage());
                this.buildColor(scannable, printable);
            }
        } else {
            Color color = Color.parse(scannable.scanString());
            dragon.setColor(color);
        }
    }

    /**
     * Метод добавляет поле character объекту класса Dragon
     *
     * @param scannable - значение поля scannable
     */
    private void buildCharacter(Scannable scannable, Printable printable) throws Exception {
        if (isConsole) {
            try {
                printable.printLine("Выберете одно из предложенных значений характера дракона " + DragonCharacter.getValues());
                DragonCharacter character = DragonCharacter.parse(scannable.scanString());
                dragon.setCharacter(character);
            } catch (ServerException serverException){
                throw serverException;
            }
            catch (Exception e) {
                printable.printLine("Что-то пошло не так: " + e.getMessage());
                this.buildCharacter(scannable, printable);
            }
        } else {
            DragonCharacter character = DragonCharacter.parse(scannable.scanString());
            dragon.setCharacter(character);
        }
    }

    /**
     * Метод добавляет поле coordinates объекту класса Dragon
     *
     * @param scannable - значение поля scannable
     */
    private void buildCoordinates(Scannable scannable, Printable printable) throws Exception {
        CoordinatesBuilder coordinatesBuilder = new CoordinatesBuilder(isConsole);
        dragon.setCoordinates(coordinatesBuilder.build(scannable, printable));

    }

    /**
     * Метод добавляет поле creationDate объекту класса Dragon
     */
    private void setCreationDate() throws Exception {
        Date date = new Date();
        dragon.setCreationDate(date);

    }

    /**
     * Метод добавляет поле killer объекту класса Dragon
     *
     * @param scannable - значение поля scannable
     */
    private void buildPerson(Scannable scannable, Printable printable) throws Exception {
        if (isConsole) {
            printable.printLine("Задать значение убийцы дракона? Введите 'yes' или 'no'");
            String answer = scannable.scanString().toUpperCase(Locale.ROOT);
            if (answer.contains("YES")) {
                PersonBuilder personBuilder = new PersonBuilder(isConsole);
                dragon.setKiller(personBuilder.build(scannable, printable));
                return;
            }
            if (answer.contains("NO")) {
                dragon.setKiller(null);
                return;
            }
            this.buildPerson(scannable, printable);

        } else {
            try {
                PersonBuilder personBuilder = new PersonBuilder(isConsole);
                dragon.setKiller(personBuilder.build(scannable, printable));
            } catch (ServerException serverException){
                throw serverException;
            }
            catch (Exception e) {
                dragon.setKiller(null);
            }
        }
    }


}
