<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Ordine" %>
<%
  List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
  String errore = (String) request.getAttribute("errore");
%>
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
  <div class="container">
    <h2>I tuoi ordini</h2>
    <% if (errore != null) { %>
      <div class="errore"><%= errore %></div>
    <% } %>
    <% if (ordini == null || ordini.isEmpty()) { %>
      <div>Non hai ordini effettuati.</div>
    <% } else { %>
      <table class="table-ordini">
  <thead>
    <tr>
      <th>Numero ordine</th>
      <th>Data</th>
      <th>Totale</th>
      <th>Dettagli</th>
    </tr>
  </thead>
  <tbody>
    <% for (Ordine ordine : ordini) { %>
      <tr>
        <td data-label="Numero ordine"><%= ordine.getNumeroOrdine() %></td>
        <td data-label="Data"><%= ordine.getData() %></td>
        <td data-label="Totale">€ <%= ordine.getTotale() %></td>
        <td data-label="Dettagli">
          <a href="dettaglioordine?id=<%= ordine.getId() %>" class="btn-orders">Visualizza</a>
        </td>
      </tr>
    <% } %>
  </tbody>
</table>

    </div>
  <% } %>
</div>

</main>
<%@ include file="../footer.jsp" %>
</body>
</html>

