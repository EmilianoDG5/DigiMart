package model;

import java.io.Serializable;

public class Utente implements Serializable {
    private int id;
    private String nome;
    private String cognome;
    private String mail;
    private String password;

    public Utente() {}

    public Utente(int id, String nome, String cognome, String mail, String password) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.password = password;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
