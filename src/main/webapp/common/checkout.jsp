<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta charset="UTF-8">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
</head>
<body>
<%@ include file="../header.jsp" %>
<%
  if (utente == null) {
      response.sendRedirect(request.getContextPath() + "/common/login.jsp");
      return;
  }
%>
<main>
  <div class="form-container">
    <h2>Conferma ordine</h2>
    <% String errore = (String) request.getAttribute("errore"); %>
    <% if (errore != null) { %>
      <div class="errore"><%= errore %></div>
    <% } %>
    <form action="<%= request.getContextPath() %>/ordine" method="post" id="form-ordine" class="form-styled">
      <h4>Dati utente</h4>
      <p><strong>Nome:</strong> <%= utente.getNome() %></p>
      <p><strong>Cognome:</strong> <%= utente.getCognome() %></p>
      <p><strong>Mail:</strong> <%= utente.getMail() %></p>

      <h4>Dati spedizione</h4>
      <label>Via:
        <input type="text" name="via" required>
      </label>
      <label>CAP:
        <input type="text" name="cap" required maxlength="5">
      </label>
      <label>Città:
        <input type="text" name="citta" required>
      </label>

      <h4>Dati pagamento:</h4>
      <label>Numero carta:
        <input type="text" name="numeroCarta" required maxlength="16">
      </label>
      <label>CVV:
        <input type="text" name="cvv" required maxlength="4">
      </label>

      <button type="submit">Effettua ordine</button>
    </form>
  </div>
</main>
<%@ include file="../footer.jsp" %>
<script>
  const contextPath = '<%=request.getContextPath()%>';
</script>
<script src="<%= request.getContextPath() %>/scripts/validazione.js"></script>
</body>
</html>
