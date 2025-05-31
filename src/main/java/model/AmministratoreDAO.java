package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AmministratoreDAO {
    public Amministratore login(int id, String password) throws SQLException {
        String sql = "SELECT * FROM AMMINISTRATORE WHERE ID = ? AND Password = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Amministratore admin = new Amministratore();
                    admin.setId(rs.getInt("ID"));
                    admin.setPassword(rs.getString("Password"));
                    return admin;
                }
            }
        }
        return null;
    }
}
