<%@ page import="model.Utente" %>
<%
  Utente utente = (session != null) ? (Utente) session.getAttribute("utente") : null;
  Object admin = (session != null) ? session.getAttribute("admin") : null;
%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Material Symbols -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" />

<nav class="navbar">
  <div class="nav-container">
    <!-- Logo -->
    <div class="nav-logo">
      <a href="<%= request.getContextPath() %>/prodotti">
        <img src="<%= request.getContextPath() %>/images/logo.png" alt="DigiMart" class="logo-img">
      </a>
    </div>

    <!-- Barra di ricerca solo desktop -->
    <form class="search-bar desktop-search" action="<%= request.getContextPath() %>/prodotti" method="get">
      <input type="text" name="search" placeholder="Cerca prodotti..." aria-label="Cerca prodotti">
      <button type="submit" class="material-symbols-outlined search-btn">search</button>
    </form>

    <!-- Hamburger solo mobile -->
    <span class="material-symbols-outlined hamburger" id="menu-toggle">menu</span>

    <!-- Link menu -->
    <ul class="nav-links" id="navLinks">
      <!-- Barra ricerca visibile solo in mobile -->
      <li class="search-li">
        <form class="search-bar" action="<%= request.getContextPath() %>/prodotti" method="get">
          <input type="text" name="search" placeholder="Cerca prodotti..." aria-label="Cerca prodotti">
          <button type="submit" class="material-symbols-outlined search-btn">search</button>
        </form>
      </li>
      <li><a href="<%= request.getContextPath() %>/prodotti?categoria=Computer">Computer</a></li>
      <li><a href="<%= request.getContextPath() %>/prodotti?categoria=Telefoni">Telefoni</a></li>
      <li><a href="<%= request.getContextPath() %>/prodotti?categoria=Componenti">Componenti</a></li>
      <% if (admin != null) { %>
        <li class="user-dropdown">
          <a href="javascript:void(0);" class="user-link">
            <span class="material-symbols-outlined">admin_panel_settings</span> Amministratore
            <span class="material-symbols-outlined">expand_more</span>
          </a>
          <ul class="dropdown-menu">
            <li>
              <a href="<%= request.getContextPath() %>/admin/logout">
                <span class="material-symbols-outlined">logout</span> Logout
              </a>
            </li>
          </ul>
        </li>
      <% } else if (utente == null) { %>
        <li>
          <a href="<%= request.getContextPath() %>/common/login.jsp">
            <span class="material-symbols-outlined">lock</span> Login
          </a>
        </li>
        <li>
          <a href="<%= request.getContextPath() %>/common/register.jsp">
            <span class="material-symbols-outlined">person</span> Registrati
          </a>
        </li>
      <% } else { %>
        <li class="user-dropdown">
          <a href="javascript:void(0);" class="user-link">
            <span class="material-symbols-outlined">person</span> <%= utente.getNome() %>
            <span class="material-symbols-outlined">expand_more</span>
          </a>
          <ul class="dropdown-menu">
            <li>
              <a href="<%= request.getContextPath() %>/common/profilo.jsp">
                <span class="material-symbols-outlined">edit</span> Cambia informazioni
              </a>
            </li>
            <li>
              <a href="<%= request.getContextPath() %>/common/mieiordini">
                <span class="material-symbols-outlined">list_alt</span> Ordini
              </a>
            </li>
            <li>
              <a href="<%= request.getContextPath() %>/logout">
                <span class="material-symbols-outlined">logout</span> Logout
              </a>
            </li>
          </ul>
        </li>
      <% } %>
      <li>
        <a href="<%= request.getContextPath() %>/common/carrello.jsp">
          <span class="material-symbols-outlined">shopping_cart</span>
        </a>
      </li>
    </ul>
  </div>
</nav>
<script src="<%= request.getContextPath() %>/scripts/navbar.js"></script>