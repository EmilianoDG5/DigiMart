<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<%@ page import="model.Prodotto" %>
<%
  Prodotto p = (Prodotto) request.getAttribute("prodotto");
%>
<main>
  <div class="container" style="padding: 40px 35px 38px 35px;">
    <% if (p != null) { %>
      <div class="product-detail-box" style="background: none; box-shadow: none; padding: 0; display: flex; gap: 40px; align-items: flex-start;">
        <div style="padding: 0; min-width: 310px; display: flex; align-items: center; justify-content: center; background: none; box-shadow: none;">
          <img src="<%= request.getContextPath() %>/images/<%= p.getFoto() != null ? p.getFoto() : "placeholder.png" %>" class="product-img-detail" alt="Immagine prodotto" style="max-width: 290px; max-height: 330px; border-radius: 13px; object-fit: contain; box-shadow: 0 1px 8px #bfe4f96b;">
        </div>
        <div class="product-detail-info" style="flex:1; min-width:300px; padding-top:10px;">
          <h2 style="color:#1fa1c8; font-weight:bold;"><%= p.getNome() %></h2>
          <div style="margin-bottom:12px;">
            <strong>Categoria:</strong><br>
            <span style="color:#1fa1c8; font-weight:600;"><%= p.getCategoria() %></span>
          </div>
          <div style="margin-bottom:20px;">
            <strong>Descrizione:</strong><br>
            <span><%= p.getDescrizione() %></span>
          </div>
          <div class="prezzo" style="font-size:1.6em; color:#1fa1c8; font-weight: bold; margin-bottom:28px;">
            € <%= String.format("%.2f", p.getPrezzo()) %>
          </div>
          <form action="<%= request.getContextPath() %>/carrello" method="post" class="add-cart-form" style="margin-top:0;">
            <input type="hidden" name="azione" value="aggiungi">
            <input type="hidden" name="idProdotto" value="<%= p.getId() %>">
            <button type="submit" class="btn-add-cart" style="padding: 16px 38px; font-size: 1.22em;">Aggiungi al carrello</button>
          </form>
        </div>
      </div>
    <% } else { %>
      <div>Prodotto non trovato.</div>
    <% } %>
  </div>
</main>
<%@ include file="../footer.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
<script src="<%= request.getContextPath() %>/scripts/carrello.js"></script>
