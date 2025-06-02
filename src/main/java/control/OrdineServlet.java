package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.*;
import java.io.IOException;
import java.util.*;

import java.text.SimpleDateFormat;
import java.sql.SQLException;

/**
 * Servlet per gestione creazione ordine (checkout).
 */
@WebServlet("/ordine")
public class OrdineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Util per validare campi sensibili lato server
    private boolean validaCampi(String via, String cap, String citta, String numCarta, String cvv) {
        if (via == null || cap == null || citta == null || numCarta == null || cvv == null)
            return false;
        if (via.trim().isEmpty() || cap.trim().isEmpty() || citta.trim().isEmpty()
            || numCarta.trim().isEmpty() || cvv.trim().isEmpty())
            return false;
        if (!cap.matches("\\d{5}"))
            return false;
        if (!numCarta.matches("\\d{16}"))
            return false;
        if (!cvv.matches("\\d{3,4}"))
            return false;
        return true;
    }

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/common/login.jsp");
            return;
        }

        Map<Integer, Integer> carrello = (Map<Integer, Integer>) session.getAttribute("carrello");
        if (carrello == null || carrello.isEmpty()) {
            request.setAttribute("errore", "Il carrello è vuoto.");
            request.getRequestDispatcher("/common/checkout.jsp").forward(request, response);
            return;
        }

        String via = request.getParameter("via");
        String cap = request.getParameter("cap");
        String citta = request.getParameter("citta");
        String numeroCarta = request.getParameter("numeroCarta");
        String cvv = request.getParameter("cvv");

        if (!validaCampi(via, cap, citta, numeroCarta, cvv)) {
            request.setAttribute("errore", "Campi non validi.");
            request.getRequestDispatcher("/common/checkout.jsp").forward(request, response);
            return;
        }

        ProdottoDAO pdao = new ProdottoDAO();
        OrdineDAO odao = new OrdineDAO();
        double totale = 0;
        List<DettaglioOrdine> dettagli = new ArrayList<>();
        try {
            // Ricalcola prezzi per sicurezza!
            for (Map.Entry<Integer, Integer> entry : carrello.entrySet()) {
                Prodotto p = pdao.getProdottoById(entry.getKey());
                if (p != null) {
                    DettaglioOrdine d = new DettaglioOrdine();
                    d.setIdProdotto(p.getId());
                    d.setNomeProdotto(p.getNome());
                    d.setQuantita(entry.getValue());
                    d.setPrezzoUnitarioAcquisto(p.getPrezzo());
                    dettagli.add(d);
                    totale += p.getPrezzo() * entry.getValue();
                }
            }
            // Crea ordine
            String numeroOrdine = "DM" + System.currentTimeMillis();
            Ordine ordine = new Ordine();
            ordine.setNumeroOrdine(numeroOrdine);
            ordine.setIdUtente(utente.getId());
            ordine.setNomeUtente(utente.getNome());
            ordine.setCognomeUtente(utente.getCognome());
            ordine.setNumeroCarta(numeroCarta);
            ordine.setCvv(cvv);
            ordine.setTotale(totale);
            ordine.setData(new Date());
            ordine.setVia(via);
            ordine.setCap(cap);
            ordine.setCitta(citta);

            int idOrdine = odao.creaOrdine(ordine);

            for (DettaglioOrdine d : dettagli) {
                d.setIdOrdine(idOrdine);
                odao.inserisciDettaglioOrdine(d);
            }
            // Svuota il carrello
            session.setAttribute("carrello", new HashMap<Integer, Integer>());
            request.setAttribute("successo", "ORDINE ANDATO A BUON FINE!");
            request.getRequestDispatcher("/common/ordine-success.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errore", "Errore nell'inserimento ordine: " + e.getMessage());
            request.getRequestDispatcher("/common/checkout.jsp").forward(request, response);
        }
    }
}
