<%@ page contentType="text/html; charset=UTF-8" %>
<%
  String successo = (String) request.getAttribute("successo");
  String errore = (String) request.getAttribute("errore");
%>
<!DOCTYPE html>
<html>
<head>
<Title>REGISTRAZIONE</Title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
<link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />

</head>

<body>
<%@ include file="../header.jsp" %>
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
      <input type="text" name="nome" placeholder="Nome" required>
    </label>  
    <label>Cognome
      <input type="text" name="cognome" placeholder="Cognome" required>
    </label>
    <label>Email
      <input type="email" name="mail" placeholder="Email" required>
    </label>
  <label>Password
  <div class="password-wrapper">
    <input type="password" name="password" id="password"  placeholder="Password" required>
    <span id="togglePassword" class="material-symbols-outlined toggle-password" title="Mostra/Nascondi password">
      visibility
    </span>
  </div>
</label>
    <button type="submit" class="btn-form">Registrati</button>
  </form>
  <p class="p-register">Hai già un account? <a href="<%= request.getContextPath() %>/common/login.jsp">Login</a></p>
</div>
</main>
<%@ include file="../footer.jsp" %>
<script>
  const contextPath = '<%= request.getContextPath() %>';
</script>
<script src="<%= request.getContextPath() %>/scripts/validazione.js"></script>
</body>
</html>

