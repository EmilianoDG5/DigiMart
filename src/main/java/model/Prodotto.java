package model;

import java.io.Serializable;

public class Prodotto implements Serializable {
    private int id;
    private String nome;
    private String descrizione;
    private String categoria;
    private double prezzo;
    private String foto;
    private boolean inEvidenza;
    private int disponibilita;

    public Prodotto() {}

    public Prodotto(int id, String nome, String descrizione, String categoria, double prezzo, String foto, boolean inEvidenza, int disponibilita) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.prezzo = prezzo;
        this.foto = foto;
        this.inEvidenza = inEvidenza;
        this.disponibilita = disponibilita;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getPrezzo() { return prezzo; }
    public void setPrezzo(double prezzo) { this.prezzo = prezzo; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public boolean isInEvidenza() { return inEvidenza; }
    public void setInEvidenza(boolean inEvidenza) { this.inEvidenza = inEvidenza; }

    public int getDisponibilita() { return disponibilita; }
    public void setDisponibilita(int disponibilita) { this.disponibilita = disponibilita; }
}
