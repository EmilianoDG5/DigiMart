<%@ page contentType="text/html; charset=UTF-8" %>

<body>
<%@ include file="../header.jsp" %>
<main>
<div class="form-container">
  <h2>Login Utente</h2>
  <% String errore = (String) request.getAttribute("errore"); %>
  <% if (errore != null) { %>
    <div class="errore"><%= errore %></div>
  <% } %>
  <form action="<%= request.getContextPath() %>/login" method="post" class="form-styled">
    <label>Email
      <input type="email" name="mail" required>
    </label>
    <label>Password
      <input type="password" name="password" required>
    </label>
    <button type="submit" class="btn-acquista">Accedi</button>
  </form>
  <p>Non hai un account? <a href="register.jsp">Registrati</a></p>
</div>
</main>
<%@ include file="../footer.jsp" %>

</body>

<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
<script src="<%= request.getContextPath() %>/scripts/validazione.js"></script>
