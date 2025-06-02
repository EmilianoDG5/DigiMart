package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Prodotto;
import model.ProdottoDAO;
import java.io.IOException;
import java.util.*;
import org.json.JSONObject;

@WebServlet("/carrello")
public class CarrelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    private Map<Integer, Integer> getCarrello(HttpSession session) {
        Map<Integer, Integer> carrello = (Map<Integer, Integer>) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new HashMap<>();
            session.setAttribute("carrello", carrello);
        }
        return carrello;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String azione = request.getParameter("azione");
        String idProdottoStr = request.getParameter("idProdotto");
        HttpSession session = request.getSession();
        Map<Integer, Integer> carrello = getCarrello(session);

        // Gestione robusta azione mancante
        if (azione == null || azione.isEmpty()) {
            String requestedWith = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(requestedWith)) {
                response.setContentType("application/json");
                JSONObject obj = new JSONObject();
                obj.put("errore", "Azione mancante!");
                response.getWriter().print(obj.toString());
            } else {
                response.sendRedirect(request.getContextPath() + "/common/carrello.jsp");
            }
            return;
        }

        int idProdotto = -1;
        if (idProdottoStr != null && !idProdottoStr.isEmpty()) {
            try {
                idProdotto = Integer.parseInt(idProdottoStr);
            } catch (NumberFormatException e) {
                idProdotto = -1;
            }
        }

        switch (azione) {
            case "aggiungi":
                if (idProdotto > 0)
                    carrello.put(idProdotto, carrello.getOrDefault(idProdotto, 0) + 1);
                break;
            case "rimuovi":
                if (idProdotto > 0)
                    carrello.remove(idProdotto);
                break;
            case "modifica":
                if (idProdotto > 0) {
                    int quantita = 0;
                    try {
                        quantita = Integer.parseInt(request.getParameter("quantita"));
                    } catch (Exception ex) {
                        quantita = 1;
                    }
                    if (quantita > 0)
                        carrello.put(idProdotto, quantita);
                    else
                        carrello.remove(idProdotto);
                }
                break;
            case "svuota":
                carrello.clear();
                break;
        }
        session.setAttribute("carrello", carrello);

        String requestedWith = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestedWith)) {
            response.setContentType("application/json");
            JSONObject obj = new JSONObject();
            obj.put("success", true);
            response.getWriter().print(obj.toString());
        } else {
            response.sendRedirect(request.getContextPath() + "/common/carrello.jsp");
        }
    }
}
