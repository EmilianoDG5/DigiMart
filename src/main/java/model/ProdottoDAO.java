package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO prodotti DigiMart.
 */
public class ProdottoDAO {

    public Prodotto getProdottoById(int id) throws SQLException {
        String sql = "SELECT * FROM PRODOTTO WHERE ID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return estraiProdotto(rs);
            }
        }
        return null;
    }

    public List<Prodotto> listaProdotti() throws SQLException {
        String sql = "SELECT * FROM PRODOTTO";
        List<Prodotto> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(estraiProdotto(rs));
        }
        return list;
    }

    public List<Prodotto> prodottiPerCategoria(String categoria) throws SQLException {
        String sql = "SELECT * FROM PRODOTTO WHERE Categoria = ?";
        List<Prodotto> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, categoria);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(estraiProdotto(rs));
            }
        }
        return list;
    }

    public List<Prodotto> prodottiInEvidenza() throws SQLException {
        String sql = "SELECT * FROM PRODOTTO WHERE In_evidenza = true";
        List<Prodotto> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(estraiProdotto(rs));
        }
        return list;
    }

    public List<Prodotto> cercaPerNome(String nome) throws SQLException {
        String sql = "SELECT * FROM PRODOTTO WHERE LOWER(Nome) LIKE ?";
        List<Prodotto> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + nome.toLowerCase() + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(estraiProdotto(rs));
            }
        }
        return list;
    }

    public void inserisciProdotto(Prodotto p) throws SQLException {
        String sql = "INSERT INTO PRODOTTO (Nome, Descrizione, Categoria, Prezzo, Foto, In_evidenza, Disponibilita) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDescrizione());
            ps.setString(3, p.getCategoria());
            ps.setDouble(4, p.getPrezzo());
            ps.setString(5, p.getFoto());
            ps.setBoolean(6, p.isInEvidenza());
            ps.setInt(7, p.getDisponibilita());
            ps.executeUpdate();
        }
    }

    public void aggiornaProdotto(Prodotto p) throws SQLException {
        String sql = "UPDATE PRODOTTO SET Nome=?, Descrizione=?, Categoria=?, Prezzo=?, Foto=?, In_evidenza=?, Disponibilita=? WHERE ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNome());
            ps.setString(2, p.getDescrizione());
            ps.setString(3, p.getCategoria());
            ps.setDouble(4, p.getPrezzo());
            ps.setString(5, p.getFoto());
            ps.setBoolean(6, p.isInEvidenza());
            ps.setInt(7, p.getDisponibilita());
            ps.setInt(8, p.getId());
            ps.executeUpdate();
        }
    }

    public void cancellaProdotto(int id) throws SQLException {
        String sql = "DELETE FROM PRODOTTO WHERE ID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Prodotto estraiProdotto(ResultSet rs) throws SQLException {
        Prodotto p = new Prodotto();
        p.setId(rs.getInt("ID"));
        p.setNome(rs.getString("Nome"));
        p.setDescrizione(rs.getString("Descrizione"));
        p.setCategoria(rs.getString("Categoria"));
        p.setPrezzo(rs.getDouble("Prezzo"));
        p.setFoto(rs.getString("Foto"));
        p.setInEvidenza(rs.getBoolean("In_evidenza"));
        p.setDisponibilita(rs.getInt("Disponibilita"));
        return p;
    }
    

    public void doUpdateDisponibilita(int id, int nuovaDisp) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE prodotto SET Disponibilita = ? WHERE id = ?");
            ps.setInt(1, nuovaDisp);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
