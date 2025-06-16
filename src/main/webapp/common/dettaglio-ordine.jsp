<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="model.DettaglioOrdine" %>
<%
  List<DettaglioOrdine> dettagli = (List<DettaglioOrdine>) request.getAttribute("dettagli");
  String errore = (String) request.getAttribute("errore");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
</head>
<body>
<%@ include file="../header.jsp" %>
<main>
  <div class="container">
    <h2>Dettaglio ordine</h2>
    <% if (errore != null) { %>
      <div class="errore"><%= errore %></div>
    <% } %>
    <% if (dettagli == null || dettagli.isEmpty()) { %>
      <div>Nessun dettaglio trovato.</div>
    <% } else { %>
      <table class="table-ordini">
  <thead>
    <tr>
      <th>Prodotto</th>
      <th>Quantità</th>
      <th>Prezzo unitario</th>
      <th>Totale</th>
    </tr>
  </thead>
  <tbody>
    <% for (DettaglioOrdine d : dettagli) { %>
    <tr>
      <td data-label="Prodotto"><%= d.getNomeProdotto() %></td>
      <td data-label="Quantità"> <%= d.getQuantita() %></td> 
      <td data-label="Prezzo unitario">€ <%= d.getPrezzoUnitarioAcquisto() %></td>
      <td data-label="Totale">€<%= d.getPrezzoUnitarioAcquisto() * d.getQuantita() %></td>
    </tr>
    <% } %>
  </tbody>
</table>

    <% } %>
    <a href="<%= request.getContextPath() %>/common/mieiordini" class="btn-back">Torna ai tuoi ordini</a>
  </div>
</main>
<%@ include file="../footer.jsp" %>
</body>
</html>
