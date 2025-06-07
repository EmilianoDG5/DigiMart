// Validazione lato client per form registrazione e ordine (email, password, CAP, carta, CVV, ecc)
document.addEventListener("DOMContentLoaded", function () {
  var regEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  var regCAP = /^[0-9]{5}$/;
  var regCVV = /^[0-9]{3,4}$/;
  var regCarta = /^[0-9]{16}$/;

  // Validazione registrazione
  var reg = document.getElementById("form-register");
  if (reg) {
    reg.addEventListener("submit", function (e) {
      var email = reg.mail.value.trim();
      var pw = reg.password.value;
      if (!regEmail.test(email)) {
        mostraErrore(reg, "Email non valida.");
        e.preventDefault();
      }
      if (pw.length < 6) {
        mostraErrore(reg, "Password troppo corta.");
        e.preventDefault();
      }
    });
  }

  // Validazione ordine
  var formOrdine = document.getElementById("form-ordine");
  if (formOrdine) {
    formOrdine.addEventListener("submit", function (e) {
      var cap = formOrdine.cap.value.trim();
      var cvv = formOrdine.cvv.value.trim();
      var carta = formOrdine.numeroCarta.value.trim();

      if (!regCAP.test(cap)) {
        mostraErrore(formOrdine, "CAP non valido (5 cifre numeriche).");
        e.preventDefault();
      }

      if (!regCarta.test(carta)) {
        mostraErrore(formOrdine, "Numero carta non valido (16 cifre numeriche).");
        e.preventDefault();
      }

      if (!regCVV.test(cvv)) {
        mostraErrore(formOrdine, "CVV non valido (3 o 4 cifre).");
        e.preventDefault();
      }
    });
  }
});

function mostraErrore(form, msg) {
  var err = form.querySelector(".errore-form");
  if (!err) {
    err = document.createElement("div");
    err.className = "errore errore-form";
    form.prepend(err);
  }
  err.textContent = msg;
}
