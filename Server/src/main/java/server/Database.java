package server;

import org.helper.HashTableCollection;
import org.helper.model.Color;
import org.helper.model.Dragon;
import org.helper.model.User;

import java.sql.*;

public class Database {

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db", "postgres", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static User getUser(String login, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * from public.\"Users\" WHERE login = ? AND password = ?");
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet set = statement.executeQuery();
        if(set.next()) {
            String loginRes = set.getString(1);
            String passwordRes = set.getString(2);
            int id = set.getInt(3);

            return new User(loginRes, passwordRes, id);
        }
        else{
            return null;
        }
    }

    public static boolean CreateUser(User user) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.\"Users\"(login, password) VALUES (?, ?)");
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        return preparedStatement.execute();
    }


    public static HashTableCollection<Integer, Dragon> getAllDragons() throws Exception {
        HashTableCollection<Integer, Dragon> collection = new HashTableCollection<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * from public.\"Dragons\"");
        ResultSet set = statement.executeQuery();
        while (set.next()){
            Dragon dragon = new Dragon();
            dragon.setId(set.getLong(set.findColumn("Id")));
            dragon.setAge(set.getInt(set.findColumn("age")));
            dragon.setColor(Color.parse(set.getString(set.findColumn("color"))));
            dragon.setDescription(set.getString(set.findColumn("description")));
            dragon.setCreationDate(set.getDate(set.findColumn("creationDate")));
            collection.put(set.getInt(set.findColumn("key")), dragon);
        }

        return collection;
    }
}
