<%@ page contentType="text/html; charset=UTF-8" %>
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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
<Title>HOME JSP</Title>
</head>
<body> 
<%@ include file="../header.jsp" %>
<main>
  <div class="container">
  <div class="slogan-banner">
  <span class="slogan-title">DIGIMART</span>
  <span class="slogan-desc">Il tuo e-commerce di prodotti digitali</span>
</div>
  
    <h2 class="page-title">Prodotti in evidenza</h2>
    <% if (errore != null) { %>
      <div class="errore"><%= errore %></div>
    <% } %>
    <div class="product-grid">
      <% if (prodotti != null && !prodotti.isEmpty()) {
        for (Prodotto p : prodotti) { %>
        <!-- Card interamente cliccabile (escluso bottone) -->
        <a href="<%= request.getContextPath() %>/dettaglioprodotto?id=<%= p.getId() %>" class="product-link" >
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
    <div id="msg-carrello" style="display:none; margin-top:20px;" class="success"></div>
  </div>
</main>

<%@ include file="../footer.jsp" %>
<script>
  const contextPath = '<%= request.getContextPath() %>';
</script>
<script src="<%= request.getContextPath() %>/scripts/carrello.js"></script>
</body>

