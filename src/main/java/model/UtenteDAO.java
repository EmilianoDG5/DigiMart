package model;

import java.sql.*;

/**
 * DAO per la gestione degli utenti.
 */
public class UtenteDAO {

    // Controlla se l'email esiste già
    public boolean emailEsiste(String email) throws SQLException {
        String sql = "SELECT ID FROM UTENTE WHERE Mail = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Registra un nuovo utente
    public void registraUtente(Utente u) throws SQLException {
        String sql = "INSERT INTO UTENTE (Nome, Cognome, Mail, Password) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getMail());
            ps.setString(4, u.getPassword());
            ps.executeUpdate();
        }
    }

    // Login utente
    public Utente login(String mail, String passwordHash) throws SQLException {
        String sql = "SELECT * FROM UTENTE WHERE Mail = ? AND Password = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, mail);
            ps.setString(2, passwordHash);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Utente u = new Utente();
                    u.setId(rs.getInt("ID"));
                    u.setNome(rs.getString("Nome"));
                    u.setCognome(rs.getString("Cognome"));
                    u.setMail(rs.getString("Mail"));
                    u.setPassword(rs.getString("Password"));
                    return u;
                }
            }
        }
        return null;
    }

    // Trova utente per id
    public Utente getUtenteById(int id) throws SQLException {
        String sql = "SELECT * FROM UTENTE WHERE ID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Utente u = new Utente();
                    u.setId(rs.getInt("ID"));
                    u.setNome(rs.getString("Nome"));
                    u.setCognome(rs.getString("Cognome"));
                    u.setMail(rs.getString("Mail"));
                    u.setPassword(rs.getString("Password"));
                    return u;
                }
            }
        }
        return null;
    }
 // Aggiorna i dati di un utente
    public void aggiornaUtente(Utente u) throws SQLException {
        String sql = "UPDATE UTENTE SET Nome=?, Cognome=?, Mail=?, Password=? WHERE ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getMail());
            ps.setString(4, u.getPassword());
            ps.setInt(5, u.getId());
            ps.executeUpdate();
        }
    }
    public static String md5(String s) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(s.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash)
                sb.append(String.format("%02x", b & 0xff));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
