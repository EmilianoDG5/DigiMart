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
 * Login utente (registrato) per DigiMart.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final String EMAIL_REGEX = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";

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
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");
            String errore = null;

            if (mail == null || !mail.matches(EMAIL_REGEX)) {
                errore = "Email non valida.";
            } else if (password == null || password.length() < 6) {
                errore = "Password troppo corta.";
            }

            if (errore != null) {
                request.setAttribute("errore", errore);
                request.getRequestDispatcher("/common/login.jsp").forward(request, response);
                return;
            }

        if (mail == null || password == null || mail.isBlank() || password.isBlank()) {
            request.setAttribute("errore", "Inserisci email e password.");
            request.getRequestDispatcher("/common/login.jsp").forward(request, response);
            return;
        }

        UtenteDAO dao = new UtenteDAO();
        try {
            Utente u = dao.login(mail, hashPassword(password));
            if (u != null) {
                HttpSession session = request.getSession();
                session.setAttribute("utente", u);
                session.setAttribute("admin", null);
                response.sendRedirect(request.getContextPath() + "/prodotti");
            } else {
                request.setAttribute("errore", "Credenziali non valide.");
                request.getRequestDispatcher("/common/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("errore", "Errore database: " + e.getMessage());
            request.getRequestDispatcher("/common/login.jsp").forward(request, response);
        }
    }
}

