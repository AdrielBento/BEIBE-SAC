package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection() {
    	
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/beibe?verifyServerCertificate=false&useSSL=false";
            String login = "root";
            String senha = "rootadmin";
            Properties properties = new Properties();
            properties.setProperty("user", login);
            properties.setProperty("password", senha);
            properties.setProperty("useSSL", "false");
//            DriverManager.registerDriver(new com.mysql.jdbc.Driver());	
            return DriverManager.getConnection(url, properties);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}