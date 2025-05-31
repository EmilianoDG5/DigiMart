package model;

import java.io.Serializable;

public class DettaglioOrdine implements Serializable {
    private int idOrdine;
    private int idProdotto;
    private String nomeProdotto;
    private int quantita;
    private double prezzoUnitarioAcquisto;

    public DettaglioOrdine() {}

    public int getIdOrdine() { return idOrdine; }
    public void setIdOrdine(int idOrdine) { this.idOrdine = idOrdine; }

    public int getIdProdotto() { return idProdotto; }
    public void setIdProdotto(int idProdotto) { this.idProdotto = idProdotto; }

    public String getNomeProdotto() { return nomeProdotto; }
    public void setNomeProdotto(String nomeProdotto) { this.nomeProdotto = nomeProdotto; }

    public int getQuantita() { return quantita; }
    public void setQuantita(int quantita) { this.quantita = quantita; }

    public double getPrezzoUnitarioAcquisto() { return prezzoUnitarioAcquisto; }
    public void setPrezzoUnitarioAcquisto(double prezzoUnitarioAcquisto) { this.prezzoUnitarioAcquisto = prezzoUnitarioAcquisto; }
}
