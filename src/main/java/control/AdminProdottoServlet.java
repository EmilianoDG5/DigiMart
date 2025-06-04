package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/admin/prodotto")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 20    // 20 MB
)
public class AdminProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String getImagesPath(HttpServletRequest request) {
        return request.getServletContext().getRealPath("/images");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProdottoDAO dao = new ProdottoDAO();
        try {
            List<Prodotto> prodotti = dao.listaProdotti();
            request.setAttribute("prodotti", prodotti);
            request.getRequestDispatcher("/admin/prodotti-admin.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("errore", "Errore nel caricamento prodotti: " + e.getMessage());
            request.getRequestDispatcher("/admin/prodotti-admin.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String azione = request.getParameter("azione");
        ProdottoDAO dao = new ProdottoDAO();

        try {
            if ("inserisci".equals(azione)) {
                Prodotto p = new Prodotto();
                p.setNome(request.getParameter("nome"));
                p.setDescrizione(request.getParameter("descrizione"));
                p.setCategoria(request.getParameter("categoria"));
                p.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
                p.setInEvidenza(request.getParameter("inEvidenza") != null);
                p.setDisponibilita(Integer.parseInt(request.getParameter("disponibilita")));

                // Upload file
                Part filePart = request.getPart("foto");
                String nomeFile = null;
                if (filePart != null && filePart.getSize() > 0) {
                    nomeFile = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String imagesDir = getImagesPath(request);
                    File uploads = new File(imagesDir);
                    if (!uploads.exists()) uploads.mkdirs();
                    filePart.write(imagesDir + File.separator + nomeFile);
                }
                p.setFoto(nomeFile);

                dao.inserisciProdotto(p);

            } else if ("modifica".equals(azione)) {
                Prodotto p = new Prodotto();
                p.setId(Integer.parseInt(request.getParameter("id")));
                p.setNome(request.getParameter("nome"));
                p.setDescrizione(request.getParameter("descrizione"));
                p.setCategoria(request.getParameter("categoria"));
                p.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
                p.setInEvidenza(request.getParameter("inEvidenza") != null);
                p.setDisponibilita(Integer.parseInt(request.getParameter("disponibilita")));

                // Upload file MODIFICA: solo se nuovo file caricato
                Part filePart = request.getPart("foto");
                String nomeFile = null;
                if (filePart != null && filePart.getSize() > 0) {
                    nomeFile = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String imagesDir = getImagesPath(request);
                    File uploads = new File(imagesDir);
                    if (!uploads.exists()) uploads.mkdirs();
                    filePart.write(imagesDir + File.separator + nomeFile);
                    p.setFoto(nomeFile);
                } else {
                    p.setFoto(request.getParameter("fotoVecchia"));
                }
                dao.aggiornaProdotto(p);

            } else if ("cancella".equals(azione)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.cancellaProdotto(id);
            }
            response.sendRedirect(request.getContextPath() + "/admin/prodotto");
        } catch (Exception e) {
            request.setAttribute("errore", "Errore gestione prodotti: " + e.getMessage());
            doGet(request, response);
        }
    }
}
