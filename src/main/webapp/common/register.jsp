<%@ page contentType="text/html; charset=UTF-8" %>

<body>
<%@ include file="../header.jsp" %>
<%
  String successo = (String) request.getAttribute("successo");
  String errore = (String) request.getAttribute("errore");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">


<body>
<main>
<div class="form-container">
  <h2>Registrati</h2>

  <% if (successo != null) { %>
    <div class="successo"><%= successo %></div>
  <% } %>
  <% if (errore != null) { %>
    <div class="errore"><%= errore %></div>
  <% } %>

  <form id="form-register" action="<%= request.getContextPath() %>/register" method="post" class="form-styled">
    <label>Nome
      <input type="text" name="nome" required>
    </label>  
    <label>Cognome
      <input type="text" name="cognome" required>
    </label>
    <label>Email
      <input type="text" name="mail" required>
    </label>
    <label>Password
      <input type="password" name="password" required>
    </label>
    <button type="submit" class="btn-acquista">Registrati</button>
  </form>
  <p>Hai già un account? <a href="login.jsp">Login</a></p>
</div>
</main>
<%@ include file="../footer.jsp" %>
<script>
  const contextPath = '<%= request.getContextPath() %>';
</script>
<script src="<%= request.getContextPath() %>/scripts/validazione.js"></script>
</body>
</html>

