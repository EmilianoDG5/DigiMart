<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Ordine" %>
<%
  List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
  String errore = (String) request.getAttribute("errore");
%>

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

<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
