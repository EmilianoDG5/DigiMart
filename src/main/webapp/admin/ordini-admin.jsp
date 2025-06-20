<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
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
</head>
<body> 
<main>
  <div class="container">
    <h2>Gestione Ordini</h2>
    <form method="get" class="admin-form" action="<%= request.getContextPath() %>/admin/ordini">
  <label>Da: <input type="date" name="dataDa"></label>
  <label>A: <input type="date" name="dataA"></label>
  <label>Nome cliente: <input type="text" name="nomeCliente"></label> <!-- Cambiato qui -->
  <button type="submit" class="btn-filtra" >Filtra</button>
</form>

    <% if (errore != null) { %>
      <div class="errore"><%= errore %></div>
    <% } %>
    <% if (ordini == null || ordini.isEmpty()) { %>
      <div>Nessun ordine trovato.</div>
    <% } else { %>
   <table class="table-ordini">
  <thead>
    <tr>
      <th>Numero</th>
      <th>Cliente</th>
      <th>Data</th>
      <th>Prodotti</th>
      <th>Totale</th>
      <th>Indirizzo</th>
    </tr>
  </thead>
  <tbody>
    <%
      model.OrdineDAO odao = new model.OrdineDAO();
      for (model.Ordine o : ordini) {
        // Recupera i dettagli prodotto dell'ordine
        java.util.List<model.DettaglioOrdine> dettagli = odao.dettagliPerOrdine(o.getId());
        String prodottiTxt = "";
        for (int i = 0; i < dettagli.size(); i++) {
          prodottiTxt += dettagli.get(i).getNomeProdotto() + " (x" + dettagli.get(i).getQuantita() + ")";
          if (i != dettagli.size() - 1) prodottiTxt += ", ";
        }
    %>
   <tr>
  <td data-label="Numero"><%= o.getNumeroOrdine() %></td>
  <td data-label="Cliente"><%= o.getNomeUtente() %></td>
  <td data-label="Data"><%= o.getData() %></td>
  <td data-label="Prodotti"><%= prodottiTxt %></td>
  <td data-label="Totale">€ <%= o.getTotale() %></td>
  <td data-label="Indirizzo"><%= o.getVia() %>, <%= o.getCap() %> <%= o.getCitta() %></td>
</tr>
    <% } %>
  </tbody>
</table>

    <% } %>
    <a class="btn-admin" href="dashboard.jsp">Torna alla dashboard</a>
  </div>
</main>
<%@ include file="../footer.jsp" %>
</body>
</html>
