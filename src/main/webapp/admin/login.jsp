<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<Title>LOGIN ADMIN</Title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
</head>
<body>
<%@ include file="../header.jsp" %>
<main>
  <div class="admin-login-container">
  <h2 class="page-title">Login Amministratore</h2>
  <% String errore = (String) request.getAttribute("errore"); %>
  <% if (errore != null) { %>
    <div class="errore"><%= errore %></div>
  <% } %>
  <form action="<%= request.getContextPath() %>/admin/dologin" method="post" id="form-admin-login" class="admin-login-form">
    <label>ID
      <input type="number" id="id-admin" name="id" required placeholder="Inserisci ID amministratore">
    </label>
    <label>Password
      <input type="password" id="pw-admin" name="password" required placeholder="Inserisci password">
    </label>
    <button type="submit" class="btn-admin-login">Accedi</button>
  </form>
</div>
</main>
<%@ include file="../footer.jsp" %>
</body>
<script src="<%= request.getContextPath() %>/scripts/validazione.js"></script>
</html>
