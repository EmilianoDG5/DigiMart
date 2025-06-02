package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;

import java.io.IOException;
import java.util.List;

/**
 * Servlet che mostra gli ordini effettuati dall’utente loggato.
 */
@WebServlet("/common/mieiordini")
public class OrdiniUtenteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/common/login.jsp");
            return;
        }

        OrdineDAO odao = new OrdineDAO();
        try {
            List<Ordine> ordini = odao.ordiniPerUtente(utente.getId());
            request.setAttribute("ordini", ordini);
            request.getRequestDispatcher("/common/ordini.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errore", "Errore nel caricamento ordini: " + e.getMessage());
            request.getRequestDispatcher("/common/ordini.jsp").forward(request, response);
        }
    }
}
