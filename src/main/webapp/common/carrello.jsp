<%@ page contentType="text/html; charset=UTF-8" %>
<body>
<%@ include file="../header.jsp" %>
<main>
  <div class="container carrello-box">
    <h2>Il tuo carrello</h2>
    <div id="carrello-dettagli">Caricamento...</div>
    <form id="svuota-carrello-form">
      <button type="submit">Svuota carrello</button>
    </form>
    <div id="checkout-div" style="text-align:right; margin-top:18px; display:none;">
      <form action="<%= request.getContextPath() %>/common/checkout.jsp" method="get">
        <button id="checkout-btn" type="submit" class="btn-checkout">Effettua ordine</button>
      </form>
    </div>
  </div>
</main>
<%@ include file="../footer.jsp" %>
</body>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
<script>
  const contextPath = '<%=request.getContextPath()%>';
</script>
<script src="<%= request.getContextPath() %>/scripts/carrello.js"></script>
