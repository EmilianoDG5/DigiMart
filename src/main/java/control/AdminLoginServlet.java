package control;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Amministratore;
import model.AmministratoreDAO;

@WebServlet("/admin/dologin")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("---- RICEVO LA POST DI LOGIN ADMIN ----");
    	System.out.println("Parametro id: '" + request.getParameter("id") + "'");
    	System.out.println("Parametro password: '" + request.getParameter("password") + "'");

    	String idStr = request.getParameter("id");
        String password = request.getParameter("password");

        try {
            int id = Integer.parseInt(idStr);
            AmministratoreDAO dao = new AmministratoreDAO();
            Amministratore admin = dao.login(id, password);

            // DEBUG: stampa in console
            System.out.println("Login admin id=" + id + ", password=" + password);
            if (admin != null) {
                System.out.println("LOGIN ADMIN OK");
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin);
                session.setAttribute("token", System.currentTimeMillis() + "-admin-" + admin.getId());
                response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
            } else {
                System.out.println("LOGIN ADMIN FALLITO");
                request.setAttribute("errore", "ID o password errati.");
                request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errore", "Errore tecnico: " + e.getMessage());
            request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
        }
    }
}
