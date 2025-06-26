<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Prodotto" %>
<%
  List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("prodotti");
  String errore = (String) request.getAttribute("errore");
%>
<!DOCTYPE html>
<html>
<head>
<Title>PRODOTTI ADMIN</Title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
</head>
<body class="admin-prodotti">
<main>
<%@ include file="../header.jsp" %>
  <div class="container">
    <h2>Gestione Prodotti</h2>
    <% if (errore != null) { %>
      <div class="errore"><%= errore %></div>
    <% } %>
    <!-- Form inserimento nuovo prodotto -->
    <form action="<%= request.getContextPath() %>/admin/prodotto" method="post" enctype="multipart/form-data" class="admin-form">
      <input type="hidden" name="azione" value="inserisci">
      <label>Nome:
       <textarea name="nome" class="nome-area" rows="3" required></textarea>
      </label>
      <label>Descrizione:
        <textarea name="descrizione" class="desc-area" rows="3" required></textarea>
      </label>
      <label>Categoria:
        <select name="categoria">
          <option value="TELEFONI">Telefoni</option>
          <option value="COMPUTER">Computer</option>
          <option value="COMPONENTI">Componenti</option>
        </select>
      </label>
      <label>Prezzo: <input type="number" name="prezzo" min="0" step="0.01" required></label>
      <label>Foto (carica file): <input type="file" name="foto" accept="image/*"></label>
      <label>In evidenza: <input type="checkbox" name="inEvidenza"></label>
      <label>Disponibilità: <input type="number" name="disponibilita" min="0" required></label>
      <button type="submit">Aggiungi prodotto</button>
    </form>
    <hr>
    <h3>Elenco Prodotti</h3>
    <div class="table-responsive">
   <table class="table-prodotti-admin">
  <thead>
    <tr>
      <th>Nome</th>
      <th>Descrizione</th>
      <th>Categoria</th>
      <th>Prezzo</th>
      <th>Disponibilità</th>
      <th>In evidenza</th>
      <th>Foto</th>
      <th>Azioni</th>
    </tr>
  </thead>
  <tbody>
  <% if (prodotti != null) {
    for (Prodotto p : prodotti) { %>
    <tr>
      <td data-label="Nome">
        <textarea name="nome" class="nome-area" rows="4" required form="form-modifica-<%= p.getId() %>"><%= p.getNome() %></textarea>
      </td>
      <td data-label="Descrizione">
        <textarea name="descrizione" class="desc-area" rows="4" required form="form-modifica-<%= p.getId() %>"><%= p.getDescrizione() %></textarea>
      </td>
      <td data-label="Categoria">
        <select name="categoria" form="form-modifica-<%= p.getId() %>">
          <option value="TELEFONI" <%= "TELEFONI".equals(p.getCategoria()) ? "selected" : "" %>>Telefoni</option>
          <option value="COMPUTER" <%= "COMPUTER".equals(p.getCategoria()) ? "selected" : "" %>>Computer</option>
          <option value="COMPONENTI" <%= "COMPONENTI".equals(p.getCategoria()) ? "selected" : "" %>>Componenti</option>
        </select>
      </td>
      <td data-label="Prezzo">
        <input type="number" name="prezzo" value="<%= p.getPrezzo() %>" min="0" step="0.01" required form="form-modifica-<%= p.getId() %>">
      </td>
      <td data-label="Disponibilità">
        <input type="number" name="disponibilita" value="<%= p.getDisponibilita() %>" min="0" required form="form-modifica-<%= p.getId() %>">
      </td>
      <td data-label="In evidenza">
        <input type="checkbox" name="inEvidenza" <%= p.isInEvidenza() ? "checked" : "" %> form="form-modifica-<%= p.getId() %>">
      </td>
      <td data-label="Foto">
        <input type="file" name="foto" accept="image/*" form="form-modifica-<%= p.getId() %>">
        <input type="hidden" name="fotoVecchia" value="<%= p.getFoto() %>" form="form-modifica-<%= p.getId() %>">
        <% if (p.getFoto() != null) { %>
          <img src="<%= request.getContextPath() %>/images/<%= p.getFoto() %>">
        <% } %>
      </td>
      <td data-label="Azioni" class="azioni-td">
        <div class="admin-actions">
          <!-- FORM MODIFICA -->
          <form id="form-modifica-<%= p.getId() %>" action="<%= request.getContextPath() %>/admin/prodotto" method="post" enctype="multipart/form-data">
            <input type="hidden" name="azione" value="modifica">
            <input type="hidden" name="id" value="<%= p.getId() %>">
            <button type="submit" class="btn-admin">Salva</button>
          </form>
          <!-- FORM ELIMINA -->
          <form action="<%= request.getContextPath() %>/admin/prodotto" method="post" onsubmit="return confirm('Sei sicuro di voler eliminare questo prodotto?');">
            <input type="hidden" name="azione" value="cancella">
            <input type="hidden" name="id" value="<%= p.getId() %>">
            <button type="submit" class="btn-admin-e">Elimina</button>
          </form>
        </div>
      </td>
    </tr>
  <% }} %>
  </tbody>
</table>
</div>
  </div>
</main>
<%@ include file="../footer.jsp" %>
</body>
</html>
