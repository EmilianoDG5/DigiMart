package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Servlet per registrazione utente su DigiMart.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) sb.append(String.format("%02x", b & 0xff));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        if (nome == null || cognome == null || mail == null || password == null ||
            nome.isBlank() || cognome.isBlank() || mail.isBlank() || password.isBlank()) {
            request.setAttribute("errore", "Compila tutti i campi.");
            request.getRequestDispatcher("/common/register.jsp").forward(request, response);
            return;
        }
        if (password.length() < 6) {
            request.setAttribute("errore", "Password troppo corta.");
            request.getRequestDispatcher("/common/register.jsp").forward(request, response);
            return;
        }

        UtenteDAO dao = new UtenteDAO();
        try {
            if (dao.emailEsiste(mail)) {
                request.setAttribute("errore", "Email già registrata.");
                request.getRequestDispatcher("/common/register.jsp").forward(request, response);
                return;
            }
            Utente u = new Utente();
            u.setNome(nome);
            u.setCognome(cognome);
            u.setMail(mail);
            u.setPassword(hashPassword(password));
            dao.registraUtente(u);
            request.setAttribute("successo", "Registrazione completata! Ora puoi accedere.");
            request.getRequestDispatcher("/common/register.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errore", "Errore database: " + e.getMessage());
            request.getRequestDispatcher("/common/register.jsp").forward(request, response);
        }
    }
}
