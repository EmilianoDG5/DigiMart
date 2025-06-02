package control;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.ProdottoDAO;

@WebServlet("/dettaglioprodotto")
public class DettaglioProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendRedirect(request.getContextPath() + "/prodotti");
            return;
        }
        int id = Integer.parseInt(idStr);
        ProdottoDAO dao = new ProdottoDAO();
        try {
            Prodotto p = dao.getProdottoById(id);
            request.setAttribute("prodotto", p);
        } catch (Exception e) {
            request.setAttribute("errore", "Errore caricamento prodotto: " + e.getMessage());
        }
        request.getRequestDispatcher("/common/dettaglio-prodotto.jsp").forward(request, response);
    }
}
