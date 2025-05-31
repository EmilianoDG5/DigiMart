package model;

import java.io.Serializable;
import java.util.Date;

public class Ordine implements Serializable {
    private int id;
    private String numeroOrdine;
    private int idUtente;
    private String nomeUtente;
    private String cognomeUtente;
    private String numeroCarta;
    private String cvv;
    private double totale;
    private Date data;
    private String via;
    private String cap;
    private String citta;

    public Ordine() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumeroOrdine() { return numeroOrdine; }
    public void setNumeroOrdine(String numeroOrdine) { this.numeroOrdine = numeroOrdine; }

    public int getIdUtente() { return idUtente; }
    public void setIdUtente(int idUtente) { this.idUtente = idUtente; }

    public String getNomeUtente() { return nomeUtente; }
    public void setNomeUtente(String nomeUtente) { this.nomeUtente = nomeUtente; }

    public String getCognomeUtente() { return cognomeUtente; }
    public void setCognomeUtente(String cognomeUtente) { this.cognomeUtente = cognomeUtente; }

    public String getNumeroCarta() { return numeroCarta; }
    public void setNumeroCarta(String numeroCarta) { this.numeroCarta = numeroCarta; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }

    public double getTotale() { return totale; }
    public void setTotale(double totale) { this.totale = totale; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getVia() { return via; }
    public void setVia(String via) { this.via = via; }

    public String getCap() { return cap; }
    public void setCap(String cap) { this.cap = cap; }

    public String getCitta() { return citta; }
    public void setCitta(String citta) { this.citta = citta; }
}
