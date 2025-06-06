<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="model.Prodotto" %>
<%
  List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("prodotti");
  String errore = (String) request.getAttribute("errore");
%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<main>
<body>
<%@ include file="../header.jsp" %>
  <div class="container">
    <h2>Gestione Prodotti</h2>
    <% if (errore != null) { %>
      <div class="errore"><%= errore %></div>
    <% } %>
    <!-- Form inserimento nuovo prodotto -->
    <form action="<%= request.getContextPath() %>/admin/prodotto" method="post" enctype="multipart/form-data" class="admin-form">
      <input type="hidden" name="azione" value="inserisci">
      <label>Nome: <input type="text" name="nome" required></label>
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
   <table class="table-ordini">
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
      <form action="<%= request.getContextPath() %>/admin/prodotto" method="post" enctype="multipart/form-data" style="display:contents;">
        <input type="hidden" name="azione" value="modifica">
        <input type="hidden" name="id" value="<%= p.getId() %>">
        <td><input type="text" name="nome" value="<%= p.getNome() %>" required></td>
        <td>
          <textarea name="descrizione" class="desc-area" rows="2" required><%= p.getDescrizione() %></textarea>
        </td>
        <td>
          <select name="categoria">
            <option value="TELEFONI" <%= "TELEFONI".equals(p.getCategoria()) ? "selected" : "" %>>Telefoni</option>
            <option value="COMPUTER" <%= "COMPUTER".equals(p.getCategoria()) ? "selected" : "" %>>Computer</option>
            <option value="COMPONENTI" <%= "COMPONENTI".equals(p.getCategoria()) ? "selected" : "" %>>Componenti</option>
          </select>
        </td>
        <td><input type="number" name="prezzo" value="<%= p.getPrezzo() %>" min="0" step="0.01" required></td>
        <td><input type="number" name="disponibilita" value="<%= p.getDisponibilita() %>" min="0" required></td>
        <td style="text-align:center;">
          <input type="checkbox" name="inEvidenza" <%= p.isInEvidenza() ? "checked" : "" %>>
        </td>
        <td>
          <input type="file" name="foto" accept="image/*">
          <input type="hidden" name="fotoVecchia" value="<%= p.getFoto() %>">
          <% if (p.getFoto() != null) { %>
            <img src="<%= request.getContextPath() %>/images/<%= p.getFoto() %>" style="max-height:28px;">
          <% } %>
        </td>
        <td style="min-width:110px; text-align:center;">
          <button type="submit" class="btn-admin" style="margin-bottom:6px;">Salva</button>
      </form>
      <form action="<%= request.getContextPath() %>/admin/prodotto" method="post" style="display:inline;">
        <input type="hidden" name="azione" value="cancella">
        <input type="hidden" name="id" value="<%= p.getId() %>">
        <button type="submit" class="btn-admin" style="background:#e74c3c;" >Elimina</button>
      </form>
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
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/style.css">
