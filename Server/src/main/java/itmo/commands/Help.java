package itmo.commands;

import itmo.io.Printable;

import java.io.IOException;

/**
 * Класс отвечает за выведение информации о доступных командах
 */
public class Help implements Command {

    private final Printable printable;

    public Help(Printable printable) {
        this.printable = printable;
    }

    /**
     * Переопределение метода execute
     * Вывод возможных команд
     */
    @Override
    public void execute() throws Exception {
        printable.printLine("/noresponse/help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "insert key {element}: добавить новый элемент с заданным ключом\n" +
                "update id  {element}: обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_key key : удалить элемент из коллекции по его ключу\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "replace_if_lower key {element} : заменить значение по ключу, если новое значение меньше старого\n" +
                "remove_greater_key key : удалить из коллекции все элементы, ключ которых превышает заданный\n" +
                "remove_all_by_color color : удалить из коллекции все элементы, значение поля color которого эквивалентно заданному\n" +
                "sum_of_age : вывести сумму значений поля age для всех элементов коллекции\n" +
                "print_descending : вывести элементы коллекции в порядке убывания");
    }

}
