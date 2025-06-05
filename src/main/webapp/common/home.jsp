<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Prodotto" %>
<%
  List<Prodotto> prodotti = null;
  try {
      prodotti = (List<Prodotto>) request.getAttribute("prodotti");
  } catch (Exception e) {
      prodotti = null;
  }
  String errore = (String) request.getAttribute("errore");
%>
<body> 
<main>
  <div class="container">
    <h2 class="page-title">Prodotti in evidenza</h2>
    <% if (errore != null) { %>
      <div class="errore"><%= errore %></div>
    <% } %>
    <div class="product-grid">
      <% if (prodotti != null && !prodotti.isEmpty()) {
        for (Prodotto p : prodotti) { %>
        <!-- Card interamente cliccabile (escluso bottone) -->
        <a href="<%= request.getContextPath() %>/dettaglioprodotto?id=<%= p.getId() %>" class="product-link" style="text-decoration:none; color:inherit;">
          <div class="product-card" tabindex="0" style="cursor:pointer; position:relative;">
            <img src="<%= request.getContextPath() %>/images/<%= p.getFoto() != null ? p.getFoto() : "placeholder.png" %>" alt="img" class="product-img">
            <div class="product-info">
              <h3><%= p.getNome() %></h3>
              <div class="categoria"><%= p.getCategoria() %></div>
              <div class="prezzo"><b>€ <%= p.getPrezzo() %></b></div>
              <!-- Il bottone ferma la propagazione -->
              <form class="add-cart-form" action="<%= request.getContextPath() %>/carrello" method="post" onClick="event.stopPropagation();">
                <input type="hidden" name="azione" value="aggiungi">
                <input type="hidden" name="idProdotto" value="<%= p.getId() %>">
                <button type="submit" class="btn-add-cart">Aggiungi al carrello</button>
              </form>
            </div>
          </div>
        </a>
      <% } } else { %>
        <div>Nessun prodotto trovato.</div>
      <% } %>
    </div>
    <div id="msg-carrello" style="display:none; margin-top:20px;" class="success"></div>
  </div>
</main>

<%@ include file="../footer.jsp" %>
</body>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
<script>
  const contextPath = '<%= request.getContextPath() %>';
</script>
<script src="<%= request.getContextPath() %>/scripts/carrello.js"></script>
