<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Prodotto" %>
<%
  Prodotto p = (Prodotto) request.getAttribute("prodotto");
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
  <div class="container" style="padding: 40px 35px 38px 35px;">
    <% if (p != null) { %>
      <div class="product-detail-box">
        <div class="product-detail-imgbox">
          <img src="<%= request.getContextPath() %>/images/<%= p.getFoto() != null ? p.getFoto() : "placeholder.png" %>" 
               class="product-img-detail" alt="Immagine prodotto">
        </div>
        <div class="product-detail-info">
		 <h2 class="product-detail-title"><%= p.getNome() %></h2>
		<div>
		  <span class="product-detail-label">Categoria:</span>
		  <span class="product-detail-value"><%= p.getCategoria() %></span>
		</div>
		<div>
		  <span class="product-detail-label">Descrizione:</span>
		  <span class="product-detail-value"><%= p.getDescrizione() %></span>
		</div>
		<div class="product-detail-price">
		  € <%= String.format("%.2f", p.getPrezzo()) %>
		</div>
		 <h3 class="<%= (p.getDisponibilita() == 0 ? "disponbilità esaurito" : "disponbilità") %>">
			 <%= (p.getDisponibilita() == 0 ? "Prodotto esaurito" : "Disponibilità: " + p.getDisponibilita() + " pz") %>
			</h3>
		<form action="<%= request.getContextPath() %>/carrello" method="post" class="add-cart-form">
		  <input type="hidden" name="azione" value="aggiungi">
		  <input type="hidden" name="idProdotto" value="<%= p.getId() %>">
		  <button type="submit" class="btn-add-cart-d"
		    <%= (p.getDisponibilita() == 0 ? "disabled style='opacity:0.6;cursor:not-allowed;'" : "") %>>
		    Aggiungi al carrello
		</button>
		</form>
        </div>
      </div>
    <% } else { %>
      <div>Prodotto non trovato.</div>
    <% } %>
  </div>
</main>
<%@ include file="../footer.jsp" %>
<script>
  const contextPath = '<%=request.getContextPath()%>';
</script>
<script src="<%= request.getContextPath() %>/scripts/carrello.js"></script>
</body>
</html>
