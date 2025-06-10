<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header.jsp" %>
<main>
  <div class="container">
    <h2>Ordine effettuato!</h2>
    <div class="successo">ORDINE ANDATO A BUON FINE!</div>
    <a href="home.jsp" class="btn-home">Torna alla home</a>
    <a href="<%= request.getContextPath() %>/common/mieiordini" class="btn-orders">Vedi i tuoi ordini</a>
  </div>
</main>
<%@ include file="../footer.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
