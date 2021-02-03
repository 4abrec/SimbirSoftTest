import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Map;

public class DataBase {
    final static Logger logger = Logger.getLogger(DataBase.class);
    private static Connection connection;
    private static Statement statement;

    private static void connect() throws ClassNotFoundException, SQLException {
        connection = null;
        String driverName = "org.sqlite.JDBC";
        String connectionString = "jdbc:sqlite:statistics.db";
        Class.forName(driverName);
        connection = DriverManager.getConnection(connectionString);
    }

    private static void createDB() throws SQLException {
        statement = connection.createStatement();
        statement.execute("CREATE TABLE if NOT EXISTS 'stats' ('word' text PRIMARY KEY, 'count' INT);");
}
    private static void writeDB(Map<String, Integer> stats) throws SQLException {
        statement.execute("delete from stats");
        for(Map.Entry<String, Integer> pair: stats.entrySet()){
            String word = pair.getKey();
            Integer count = pair.getValue();
            String query = "INSERT INTO 'stats' ('word', 'count') VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, word);
            preparedStatement.setInt(2, count);
            preparedStatement.execute();
        }
    }
    private static void closeDB() throws SQLException {
        connection.close();
    }

    public static void writeStatisticToDB(Map<String, Integer> stats){
        try {
            DataBase.connect();
        } catch (ClassNotFoundException e){
            logger.error("Can't get class. No driver found");
            return;
        } catch (SQLException e){
            logger.error("Can't get connection. Incorrect URL");
            return;
        }
        try{
            DataBase.createDB();
            DataBase.writeDB(stats);
        }catch (SQLException e){
            logger.error("incorrect request");
            return;
        }
        try{
            DataBase.closeDB();
        }catch (SQLException e){
            logger.error("Can't close connection");
        }
    }
}
