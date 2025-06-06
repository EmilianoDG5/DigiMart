<%@ page contentType="text/html; charset=UTF-8" %>
<body>
<%@ include file="../header.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<main>
  <div class="container small-form admin-login-container">
    <h2 class="page-title" style="text-align:center;margin-bottom:30px;">Login Amministratore</h2>
    <% String errore = (String) request.getAttribute("errore"); %>
    <% if (errore != null) { %>
      <div class="errore" style="margin-bottom:20px;"><%= errore %></div>
    <% } %>
    <form action="<%= request.getContextPath() %>/admin/dologin" method="post" id="form-admin-login" class="admin-login-form">
      <label for="id-admin">ID
        <input type="number" id="id-admin" name="id" required placeholder="Inserisci ID amministratore">
      </label>
      <label for="pw-admin">Password
        <input type="password" id="pw-admin" name="password" required placeholder="Inserisci password">
      </label>
      <button type="submit" class="btn-admin-login">Accedi</button>
    </form>
  </div>
</main>
<%@ include file="../footer.jsp" %>
</body>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
<script src="<%= request.getContextPath() %>/scripts/validazione.js"></scrip>
