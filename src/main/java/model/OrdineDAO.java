package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DAO gestione ordini DigiMart.
 */
public class OrdineDAO {

    // Crea ordine (ritorna id generato)
    public int creaOrdine(Ordine o) throws SQLException {
        String sql = "INSERT INTO ORDINE (Numero_ordine, ID_UTENTE, Nome_utente, Cognome_utente, Numero_carta, CVV, Totale, Data, Via, CAP, città) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, o.getNumeroOrdine());
            ps.setInt(2, o.getIdUtente());
            ps.setString(3, o.getNomeUtente());
            ps.setString(4, o.getCognomeUtente());
            ps.setString(5, o.getNumeroCarta());
            ps.setString(6, o.getCvv());
            ps.setDouble(7, o.getTotale());
            ps.setDate(8, new java.sql.Date(o.getData().getTime()));
            ps.setString(9, o.getVia());
            ps.setString(10, o.getCap());
            ps.setString(11, o.getCitta());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    // Inserisce dettaglio ordine
    public void inserisciDettaglioOrdine(DettaglioOrdine d) throws SQLException {
        String sql = "INSERT INTO DETTAGLIO_ORDINE (ID_ORDINE, ID_PRODOTTO, Nome_prodotto, Quantita, Prezzo_unitario_acquisto) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, d.getIdOrdine());
            ps.setInt(2, d.getIdProdotto());
            ps.setString(3, d.getNomeProdotto());
            ps.setInt(4, d.getQuantita());
            ps.setDouble(5, d.getPrezzoUnitarioAcquisto());
            ps.executeUpdate();
        }
    }

    // Ordini per utente
    public List<Ordine> ordiniPerUtente(int idUtente) throws SQLException {
        String sql = "SELECT * FROM ORDINE WHERE ID_UTENTE = ? ORDER BY Data DESC";
        List<Ordine> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idUtente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(estraiOrdine(rs));
            }
        }
        return list;
    }

    // Dettagli di un ordine
    public List<DettaglioOrdine> dettagliPerOrdine(int idOrdine) throws SQLException {
        String sql = "SELECT * FROM DETTAGLIO_ORDINE WHERE ID_ORDINE = ?";
        List<DettaglioOrdine> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idOrdine);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DettaglioOrdine d = new DettaglioOrdine();
                    d.setIdOrdine(rs.getInt("ID_ORDINE"));
                    d.setIdProdotto(rs.getInt("ID_PRODOTTO"));
                    d.setNomeProdotto(rs.getString("Nome_prodotto"));
                    d.setQuantita(rs.getInt("Quantita"));
                    d.setPrezzoUnitarioAcquisto(rs.getDouble("Prezzo_unitario_acquisto"));
                    list.add(d);
                }
            }
        }
        return list;
    }

    // Filtro per ordini (admin)
    public List<Ordine> filtraOrdini(Date dataDa, Date dataA, String nomeCliente) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM ORDINE WHERE 1=1");
        List<Object> param = new ArrayList<>();
        if (dataDa != null) { sql.append(" AND Data >= ?"); param.add(new java.sql.Date(dataDa.getTime())); }
        if (dataA != null)  { sql.append(" AND Data <= ?"); param.add(new java.sql.Date(dataA.getTime())); }
        if (nomeCliente != null && !nomeCliente.isEmpty()) {
            sql.append(" AND Nome_utente = ?");
            param.add(nomeCliente);
        }
        sql.append(" ORDER BY Data DESC");
        List<Ordine> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < param.size(); i++) ps.setObject(i + 1, param.get(i));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(estraiOrdine(rs));
            }
        }
        return list;
    }


    private Ordine estraiOrdine(ResultSet rs) throws SQLException {
        Ordine o = new Ordine();
        o.setId(rs.getInt("ID"));
        o.setNumeroOrdine(rs.getString("Numero_ordine"));
        o.setIdUtente(rs.getInt("ID_UTENTE"));
        o.setNomeUtente(rs.getString("Nome_utente"));
        o.setCognomeUtente(rs.getString("Cognome_utente"));
        o.setNumeroCarta(rs.getString("Numero_carta"));
        o.setCvv(rs.getString("CVV"));
        o.setTotale(rs.getDouble("Totale"));
        o.setData(rs.getDate("Data"));
        o.setVia(rs.getString("Via"));
        o.setCap(rs.getString("CAP"));
        o.setCitta(rs.getString("città"));
        return o;
    }
}
