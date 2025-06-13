package control;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class FiltroAutenticazione implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String path = request.getRequestURI();
        HttpSession session = request.getSession(false);

        boolean loggedUser = (session != null && session.getAttribute("utente") != null);
        boolean loggedAdmin = (session != null && session.getAttribute("admin") != null);

        // Pagine admin
        if (path.contains("/admin/")
            && !path.endsWith("/login.jsp")
            && !path.endsWith("/dologin")
            && !loggedAdmin) {
            response.sendRedirect(request.getContextPath() + "/prodotti");
            return;
        }
        // Pagine utente comuni che richiedono login
        if (path.contains("/common/") &&
                (path.contains("checkout") || path.contains("ordini") || path.contains("dettaglioordine")|| path.contains("dettaglio-ordine")|| path.contains("profilo"))) {
            if (!loggedUser) {
                response.sendRedirect(request.getContextPath() + "/common/login.jsp");
                return;
            }
        }
        chain.doFilter(req, res);
    }
}
