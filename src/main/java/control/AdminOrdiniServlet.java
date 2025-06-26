package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.OrdineDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/admin/ordini")
public class AdminOrdiniServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dataDaStr = request.getParameter("dataDa");
        String dataAStr = request.getParameter("dataA");
        String nomeCliente = request.getParameter("nomeCliente"); 
        Date dataDa = null, dataA = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (dataDaStr != null && !dataDaStr.isBlank())
                dataDa = sdf.parse(dataDaStr);
            if (dataAStr != null && !dataAStr.isBlank())
                dataA = sdf.parse(dataAStr);
        } catch (Exception ignored) {}

        OrdineDAO dao = new OrdineDAO();
        try {
          
            request.setAttribute("ordini", dao.filtraOrdini(dataDa, dataA, nomeCliente));
        } catch (SQLException e) {
            request.setAttribute("errore", "Errore caricamento ordini: " + e.getMessage());
        }
        request.getRequestDispatcher("/admin/ordini-admin.jsp").forward(request, response);
    }
}