package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Baglanti {

    private static final String URL = "jdbc:mysql://localhost/202503014.db";
    private static final String KULLANICI_ADI = "202503014.db"; 
    private static final String SIFRE = "Melo1234.";

    public static Connection baglan() {
        try {
            Connection connection = DriverManager.getConnection(URL, KULLANICI_ADI, SIFRE);
            return connection;
            
        } catch (SQLException e) {
            System.out.println( e.getMessage());
            return null;
        }
    }
}
