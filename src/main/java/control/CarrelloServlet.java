package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Prodotto;
import model.ProdottoDAO;
import java.io.IOException;
import java.sql.SQLException;
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
            if (idProdotto > 0) {
                ProdottoDAO prodottoDAO = new ProdottoDAO();
                try {
                    Prodotto prodotto = prodottoDAO.getProdottoById(idProdotto);

                    int disponibilita = (prodotto != null) ? prodotto.getDisponibilita() : 0;
                    int giaInCarrello = carrello.getOrDefault(idProdotto, 0);

                    if (prodotto == null) {
                        request.setAttribute("errore", "Prodotto non trovato.");
                    } else if (disponibilita == 0) {
                        request.setAttribute("errore", "Prodotto esaurito.");
                    } else if (giaInCarrello >= disponibilita) {
                        request.setAttribute("errore", "Quantità massima raggiunta per questo prodotto.");
                    } else {
                        carrello.put(idProdotto, giaInCarrello + 1);
                    }
                } catch (SQLException ex) {
                    request.setAttribute("errore", "Errore di database.");
                    ex.printStackTrace(); 
                }
            }
       
            break;
            case "svuota":
                carrello.clear();
                break;
            case "rimuovi":
                if (idProdotto > 0) {
                    carrello.remove(idProdotto);
                }
                break;
            case "modifica":
                if (idProdotto > 0) {
                    String quantitaStr = request.getParameter("quantita");
                    int quantita = 1;
                    try {
                        quantita = Integer.parseInt(quantitaStr);
                    } catch (Exception e) {
                        quantita = 1;
                    }
                    ProdottoDAO prodottoDAO = new ProdottoDAO();
                    try {
                        Prodotto prodotto = prodottoDAO.getProdottoById(idProdotto);
                        int disponibilita = (prodotto != null) ? prodotto.getDisponibilita() : 0;
                        if (prodotto == null) {
                            request.setAttribute("errore", "Prodotto non trovato.");
                        } else if (quantita <= 0) {
                            carrello.remove(idProdotto);
                        } else if (quantita > disponibilita) {
                            request.setAttribute("errore", "Quantità non disponibile.");
                        } else {
                            carrello.put(idProdotto, quantita);
                        }
                    } catch (Exception e) {
                        request.setAttribute("errore", "Errore di database.");
                    }
                }
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
