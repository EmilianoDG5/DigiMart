<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Utente" %>


<!DOCTYPE html>
<html>
<head>
<Title>PROFILO</Title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
</head>
<body>
<%@ include file="../header.jsp" %>
<%
  if (utente == null) {
    response.sendRedirect(request.getContextPath() + "/common/login.jsp");
    return;
  }
  String successo = (String)request.getAttribute("successo");
  String errore = (String)request.getAttribute("errore");
%>
<main>
  <div class="form-container">
    <h2>Profilo utente</h2>
    <% if (successo != null) { %>
      <div class="successo"><%= successo %></div>
    <% } %>
    <% if (errore != null) { %>
      <div class="errore"><%= errore %></div>
    <% } %>
    <form action="<%=request.getContextPath()%>/updateProfilo" method="post" id="profilo-form" class="form-styled">
      <label>Nome
        <input type="text" name="nome" value="<%=utente.getNome()%>" required>
      </label>
      <label>Cognome
        <input type="text" name="cognome" value="<%=utente.getCognome()%>" required>
      </label>
      <label>Email
        <input type="email" name="mail" value="<%=utente.getMail()%>" required>
      </label>
        <label>Password
	  <div class="password-wrapper">
	    <input type="password" name="password" id="password" placeholder="Lascia vuoto per non cambiare" >
	    <span id="togglePassword" class="material-symbols-outlined toggle-password"  title="Mostra/Nascondi password"  >
	      visibility
	    </span>
	  </div>
      <button type="submit" class="btn-acquista">Aggiorna dati</button>
    </form>
  </div>
</main>
<%@ include file="../footer.jsp" %>
<script>
  const contextPath = '<%= request.getContextPath() %>';
</script>
<script src="<%= request.getContextPath() %>/scripts/validazione.js"></script>
</body>
</html>
