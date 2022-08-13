package server.commands;

import server.Main;
import server.collection.HashTableCollection;
import server.io.Printable;
import server.model.Color;
import server.model.Colorable;
import server.utils.Response;

import java.util.List;

/**
 * Класс отвечает за удаление элементов по цвету
 */
public class RemoveAllByColor extends Command {
    @Override
    public void execute(List<Object> args, Response response) {
        List<?> keys = Main.collection.getKeysAsList();
        Color color = (Color) args.get(0);
        keys.stream().filter(key -> Main.collection.get(key).getColor().equals(color)).forEach(Main.collection::remove); // берет поток всех ключей, оставляет только нужные, и пробегается и удаляет
    }


}
