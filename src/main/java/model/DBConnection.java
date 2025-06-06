package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility di connessione DB per DigiMart.
 */
public class DBConnection {
    // Connessione locale. Puoi cambiare username e password se servono.
    private static final String URL = "jdbc:mysql://localhost:3306/digimart?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "2002";

    /**
     * Ritorna una connessione valida al database DigiMart.
     * Forza il caricamento del driver JDBC MySQL.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Obbliga il caricamento del driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC MySQL non trovato!", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
