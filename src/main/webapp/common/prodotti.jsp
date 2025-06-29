<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Prodotto" %>
<%
  List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("prodotti");
  String errore = (String) request.getAttribute("errore");
  String categoria = (String) request.getAttribute("categoria");
  String search = (String) request.getAttribute("search");
%>
<!DOCTYPE html>
<html>
<head>
<Title>PRODOTTI</Title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body> 
<%@ include file="../header.jsp" %>
<main>
  <div class="container">
    <h2 class="page-title">
      <% if (categoria != null) { %>
        Prodotti: <%= categoria %>
      <% } else if (search != null) { %>
        Risultati per: <%= search %>
      <% } else { %>
        Tutti i Prodotti
      <% } %>
    </h2>
    <% if (errore != null) { %>
      <div class="errore"><%= errore %></div>
    <% } %>
<div class="product-grid">
      <% if (prodotti != null && !prodotti.isEmpty()) {
        for (Prodotto p : prodotti) { %>
        <a href="<%= request.getContextPath() %>/dettaglioprodotto?id=<%= p.getId() %>" class="product-link">
          <div class="product-card" tabindex="0">
            <img src="<%= request.getContextPath() %>/images/<%= p.getFoto() != null ? p.getFoto() : "placeholder.png" %>" alt="img" class="product-img">
            <div class="product-info">
              <h3><%= p.getNome() %></h3>
              <div class="categoria"><%= p.getCategoria() %></div>
              <div class="prezzo"><b>€ <%= p.getPrezzo() %></b></div>
              <form class="add-cart-form" action="<%= request.getContextPath() %>/carrello" method="post" onClick="event.stopPropagation();">
                <input type="hidden" name="azione" value="aggiungi">
                <input type="hidden" name="idProdotto" value="<%= p.getId() %>">
                <button type="submit" class="btn-add-cart"
				    <%= (p.getDisponibilita() == 0 ? "disabled style='opacity:0.6;cursor:not-allowed;'" : "") %>>
				    Aggiungi al carrello
				</button>
              </form>
             <p class="<%= (p.getDisponibilita() == 0 ? "disponbilità esaurito" : "disponbilità") %>">
			 <%= (p.getDisponibilita() == 0 ? "Prodotto esaurito" : "Disponibilità: " + p.getDisponibilita() + " pz") %>
			</p>
            </div>
          </div>
        </a>
      <% } } else { %>
        <div>Nessun prodotto trovato.</div>
      <% } %>
  </div>
  
</main>

<%@ include file="../footer.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
<script>
  const contextPath = '<%= request.getContextPath() %>';
</script>
<script src="<%= request.getContextPath() %>/scripts/carrello.js"></script>
</body>
</html>
