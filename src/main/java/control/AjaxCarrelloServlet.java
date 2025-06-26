package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.Prodotto;
import model.ProdottoDAO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

/**
 * Servlet AJAX */
@WebServlet("/ajax-carrello")
public class AjaxCarrelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Integer, Integer> carrello = (Map<Integer, Integer>) session.getAttribute("carrello");
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        JSONArray dettagli = new JSONArray();
        double totale = 0;

        if (carrello != null) {
            for (Map.Entry<Integer, Integer> entry : carrello.entrySet()) {
                try {
                    Prodotto p = prodottoDAO.getProdottoById(entry.getKey());
                    int qta = entry.getValue();
                    double subtot = p.getPrezzo() * qta;
                    totale += subtot;
                    JSONObject obj = new JSONObject();
                    obj.put("id", p.getId());
                    obj.put("nome", p.getNome());
                    obj.put("prezzo", p.getPrezzo());
                    obj.put("quantita", qta);
                    obj.put("subtotale", subtot);
                    obj.put("disponibilita", p.getDisponibilita());
dettagli.put(obj);
                } catch (SQLException ignored) {}
            }
        }
        JSONObject res = new JSONObject();
        res.put("dettagli", dettagli);
        res.put("totale", totale);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(res.toString());
        out.flush();
    }
}
