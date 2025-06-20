<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
</head>
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
</html>