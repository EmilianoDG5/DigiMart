package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.*;
import java.io.IOException;
import java.util.*;

import java.sql.SQLException;

@WebServlet("/ordine")
public class OrdineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String CAP_REGEX = "^\\d{5}$";
    private static final String CARTA_REGEX = "^\\d{16}$";
    private static final String CVV_REGEX = "^\\d{3,4}$";

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

        // 1. Via non vuota
        if (via == null || via.trim().isEmpty()) {
            request.setAttribute("errore", "La via non può essere vuota.");
            request.getRequestDispatcher("/common/checkout.jsp").forward(request, response);
            return;
        }
        // 2. Città non vuota
        if (citta == null || citta.trim().isEmpty()) {
            request.setAttribute("errore", "La città non può essere vuota.");
            request.getRequestDispatcher("/common/checkout.jsp").forward(request, response);
            return;
        }
        // 3. CAP valido
        if (cap == null || !cap.matches(CAP_REGEX)) {
            request.setAttribute("errore", "CAP non valido (5 cifre numeriche).");
            request.getRequestDispatcher("/common/checkout.jsp").forward(request, response);
            return;
        }
        // 4. Numero carta valido
        if (numeroCarta == null || !numeroCarta.matches(CARTA_REGEX)) {
            request.setAttribute("errore", "Numero carta non valido (16 cifre numeriche).");
            request.getRequestDispatcher("/common/checkout.jsp").forward(request, response);
            return;
        }
        // 5. CVV valido
        if (cvv == null || !cvv.matches(CVV_REGEX)) {
            request.setAttribute("errore", "CVV non valido (3 o 4 cifre).");
            request.getRequestDispatcher("/common/checkout.jsp").forward(request, response);
            return;
        }

        ProdottoDAO pdao = new ProdottoDAO();
        OrdineDAO odao = new OrdineDAO();
        double totale = 0;
        List<DettaglioOrdine> dettagli = new ArrayList<>();

        try {
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

            // ✅ Aggiorna disponibilità dei prodotti
            for (DettaglioOrdine d : dettagli) {
                Prodotto prodotto = pdao.getProdottoById(d.getIdProdotto());
                int nuovaDisp = prodotto.getDisponibilita() - d.getQuantita();
                if (nuovaDisp < 0) nuovaDisp = 0;
                pdao.doUpdateDisponibilita(d.getIdProdotto(), nuovaDisp);
            }

            session.setAttribute("carrello", new HashMap<Integer, Integer>());
            request.setAttribute("successo", "ORDINE ANDATO A BUON FINE!");
            request.getRequestDispatcher("/common/ordine-success.jsp").forward(request, response);

        } catch (SQLException e) {
            request.setAttribute("errore", "Errore nell'inserimento ordine: " + e.getMessage());
            request.getRequestDispatcher("/common/checkout.jsp").forward(request, response);
        }
    }
}
