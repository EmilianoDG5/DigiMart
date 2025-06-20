<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
</head>
<body>

<%@ include file="../header.jsp" %>
<main>
<div class="form-container">
  <h2>Login Utente</h2>
  <% String errore = (String) request.getAttribute("errore"); %>
  <% if (errore != null) { %>
    <div class="errore"><%= errore %></div>
  <% } %>
 <form id="form-login" action="<%= request.getContextPath() %>/login" method="post" class="form-styled">
  <label>Email
  <input type="text" name="mail" id="email" required>
  </label>
  <label for="password">Password
   <div class="password-wrapper">
    <input type="password" name="password" id="password" required>
    <span id="togglePassword" class="material-symbols-outlined toggle-password" title="Mostra/Nascondi password">
      visibility
    </span>
  </div>
  </label>
  <button type="submit" class="btn-form">Accedi</button>
</form>
  <p class="p-register">Non hai un account? <a href="register.jsp">Registrati</a></p>
</div>
</main>
<%@ include file="../footer.jsp" %>
<script>
  const contextPath = '<%= request.getContextPath() %>';
</script>
<script src="<%= request.getContextPath() %>/scripts/validazione.js"></script>
</body>
</html>
