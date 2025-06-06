<%@ page contentType="text/html; charset=UTF-8" %>
<body>
<%@ include file="../header.jsp" %>
<main>
  <div class="container">
    <h2>Area Amministratore</h2>
    <div class="admin-menu">
      <a class="btn-admin" href="<%= request.getContextPath() %>/admin/prodotto">Gestione Prodotti</a>
      <a class="btn-admin" href="<%= request.getContextPath() %>/admin/ordini"> Gestione Ordini</a>
      <a class="btn-admin" href="<%= request.getContextPath() %>/admin/logout">Logout</a>
    </div>
  </div>
</main>
<%@ include file="../footer.jsp" %>
</body>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
