package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.OrdineDAO;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Dettaglio di un ordine (user).
 */
@WebServlet("/common/dettaglioordine")
public class DettaglioOrdineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendRedirect(request.getContextPath() + "/common/ordini.jsp");
            return;
        }
        int id = Integer.parseInt(idStr);
        OrdineDAO dao = new OrdineDAO();
        try {
            request.setAttribute("dettagli", dao.dettagliPerOrdine(id));
        } catch (SQLException e) {
            request.setAttribute("errore", "Errore caricamento dettagli: " + e.getMessage());
        }
        request.getRequestDispatcher("/common/dettaglio-ordine.jsp").forward(request, response);
    }
}
