package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;
import java.util.List;

/**
 * Servlet per la visualizzazione dei prodotti (home e ricerca).
 */
@WebServlet("/prodotti")
public class ProdottiServlet extends HttpServlet {
    // ... 

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoria = request.getParameter("categoria");
        String search = request.getParameter("search");
        ProdottoDAO dao = new ProdottoDAO();
        try {
            List<Prodotto> prodotti;
            String jspPage;
            if (search != null && !search.trim().isEmpty()) {
                prodotti = dao.cercaPerNome(search.trim());
                request.setAttribute("search", search);
                jspPage = "/common/prodotti.jsp";
            } else if (categoria != null && !categoria.trim().isEmpty()) {
                prodotti = dao.prodottiPerCategoria(categoria.trim());
                request.setAttribute("categoria", categoria);
                jspPage = "/common/prodotti.jsp";
            } else {
                prodotti = dao.prodottiInEvidenza();
                jspPage = "/common/home.jsp";
            }
            request.setAttribute("prodotti", prodotti);
            request.getRequestDispatcher(jspPage).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errore", "Errore nel caricamento prodotti: " + e.getMessage());
            request.getRequestDispatcher("/common/home.jsp").forward(request, response);
        }
    }
}

