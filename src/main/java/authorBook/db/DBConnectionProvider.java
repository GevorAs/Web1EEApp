package authorBook.db;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionProvider {
    private static DBConnectionProvider provider ;
//    private String dbDriver="com.mysql.jdbc.Driver";
//    private String dbUrl="jdbc:mysql://localhost:3306/library_servlet";
//    private String dbUsername="root";
//    private String dbPassword="073580";
    private String dbDriver;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private Connection connection;

    private DBConnectionProvider() {
        try {
            loadProperties();
            Class.forName(dbDriver);
        } catch (Exception e) {
            throw new RuntimeException("Failed to " +
                    "initialize DB Connection Provider ", e);
        }
    }

    public static DBConnectionProvider getInstance() {
        if (provider == null) {
            synchronized (DBConnectionProvider.class) {
                if (provider==null) {
                    provider = new DBConnectionProvider();
                }
            }
        }
        return provider;
    }
    private void loadProperties() {
      try {


           Properties properties = new Properties();
          properties.load(new FileInputStream("D:\\NKAR_ERG_ARPINE_GOHAR\\Projects\\WebApps\\Web1EEApp\\src\\main\\resources\\dbConfig.properties"));
          dbDriver = properties.getProperty("db.driver");
          dbUrl = properties.getProperty("db.url");
          dbUsername = properties.getProperty("db.username");
          dbPassword = properties.getProperty("db.password");
      }catch (IOException e){
          throw new RuntimeException(" 1111 ",e);
      }
    }

    public synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl,
                        dbUsername, dbPassword);
            }
            return connection;
        } catch (SQLException e) {

            throw new RuntimeException(" 222  ",e);
        }
    }
}
