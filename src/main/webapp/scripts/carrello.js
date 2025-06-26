document.addEventListener("DOMContentLoaded", aggiornaCarrello);

function aggiornaCarrello() {
  fetch(contextPath + '/ajax-carrello')
    .then(resp => resp.json())
    .then(data => {
      let html = '';
      let totale = 0;
      let carrelloVuoto = !data.dettagli || data.dettagli.length === 0;
      if (carrelloVuoto) {
        html = '<div>Il carrello è vuoto.</div>';
        document.getElementById("svuota-carrello-form").style.display = "none";
        document.getElementById("checkout-div").style.display = "none";
      } else {
		html = `<table class="table-ordini">
		  <thead>
		    <tr>
		      <th>Prodotto</th>
		      <th>Quantità</th>
		      <th>Prezzo</th>
		      <th></th>
		    </tr>
		  </thead>
		  <tbody>
		`;
		data.dettagli.forEach(p => {
		  totale += p.subtotale;
		  html += `<tr>
		    <td data-label="Prodotto">
		      ${p.foto ? `<img src="${contextPath}/images/${p.foto}" alt="${p.nome}" class="cart-img">` : ''}
		      ${p.nome}
		    </td>
		    <td data-label="Quantità">
		      <input type="number" min="1" max="${p.disponibilita}" value="${p.quantita}" class="qty-input"
		        onchange="modificaQuantita(${p.id}, this.value)">
		    </td>
		    <td data-label="Prezzo">€ ${(p.subtotale).toFixed(2)}</td>
		    <td>
		      <button type="button" class="btn-remove" onclick="rimuoviDalCarrello(${p.id})">Rimuovi</button>
		    </td>
		  </tr>`;
		});
		html += `<tr class="totale-row">
		  <td colspan="2"></td>
		  <td colspan="2">
		    Totale carrello: € ${totale.toFixed(2)}
		  </td>
		</tr>
		</tbody></table>`;

        document.getElementById("svuota-carrello-form").style.display = "block";
        document.getElementById("checkout-div").style.display = "block";
      }
      document.getElementById("carrello-dettagli").innerHTML = html;
    })
    .catch(e => {
      document.getElementById("carrello-dettagli").innerHTML = "Errore di caricamento carrello.";
    });
}

// Modifica quantità prodotto (AJAX)
function modificaQuantita(idProdotto, nuovaQuantita) {
  fetch(contextPath + '/carrello', {
    method: 'POST',
    headers: {'X-Requested-With':'XMLHttpRequest'},
    body: new URLSearchParams({
      azione: 'modifica',
      idProdotto: idProdotto,
      quantita: nuovaQuantita
    })
  }).then(() => aggiornaCarrello());
}

// Rimuovi prodotto (AJAX)
function rimuoviDalCarrello(idProdotto) {
  fetch(contextPath + '/carrello', {
    method: 'POST',
    headers: {'X-Requested-With':'XMLHttpRequest'},
    body: new URLSearchParams({
      azione: 'rimuovi',
      idProdotto: idProdotto
    })
  }).then(() => aggiornaCarrello());
}

// Svuota carrello (AJAX)
document.getElementById("svuota-carrello-form").addEventListener('submit', function(e){
  e.preventDefault();
  fetch(contextPath + '/carrello', {
    method: 'POST',
    headers: {'X-Requested-With':'XMLHttpRequest'},
    body: new URLSearchParams({ azione: 'svuota', idProdotto: 0 })
  }).then(() => aggiornaCarrello());
});
