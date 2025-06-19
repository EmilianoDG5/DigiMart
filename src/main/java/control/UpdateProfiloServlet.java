package control;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import java.io.IOException;

@WebServlet("/updateProfilo")
public class UpdateProfiloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String EMAIL_REGEX = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
    private static final String NAME_REGEX = "^[A-Za-zÀ-ÖØ-öø-ÿ'’\\s]+$";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente)session.getAttribute("utente") : null;
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/common/login.jsp");
            return;
        }

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String errore = null;
        // Validazioni base
       
        if (mail == null || !mail.matches(EMAIL_REGEX)) {
            errore = "Email non valida.";
        }else if (password != null && !password.trim().isEmpty() && password.length() < 6) {
            errore = "Password troppo corta.";
        } else if (nome == null || !nome.matches(NAME_REGEX)) {
            errore = "Il nome può contenere solo lettere.";
        } else if (cognome == null || !cognome.matches(NAME_REGEX)) {
            errore = "Il cognome può contenere solo lettere.";
        }

        if (errore != null) {
            request.setAttribute("errore", errore);
            request.getRequestDispatcher("/common/profilo.jsp").forward(request, response);
            return;
        }


        try {
            UtenteDAO dao = new UtenteDAO();
            utente.setNome(nome.trim());
            utente.setCognome(cognome.trim());
            utente.setMail(mail.trim());
            if (password != null && !password.trim().isEmpty()) {
                utente.setPassword(UtenteDAO.md5(password.trim()));
            }
            dao.aggiornaUtente(utente);
            session.setAttribute("utente", utente); // aggiorna in sessione
            request.setAttribute("successo", "Profilo aggiornato correttamente!");
        } catch (Exception e) {
            request.setAttribute("errore", "Errore nell'aggiornamento: " + e.getMessage());
        }
        request.getRequestDispatcher("/common/profilo.jsp").forward(request, response);
    }
}
