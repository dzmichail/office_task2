package app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://127.0.0.1:5432/office_task";
    private String user = "postgres";
    private String password = "postgres";
    private Connection conn = null;

    public ConnectionDB(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }catch (SQLException sex){
            sex.printStackTrace();
        }
    }

    public Connection getConn(){
        return conn;
    }

}
