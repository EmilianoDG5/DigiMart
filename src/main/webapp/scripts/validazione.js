// Funzione per rimuovere errore live
function rimuoviErrore(form) {
  var err = form.querySelector(".errore-form");
  if (err) err.remove();
}

document.addEventListener("DOMContentLoaded", function () {
  var regEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  var regCAP = /^[0-9]{5}$/;
  var regCVV = /^[0-9]{3,4}$/;
  var regCarta = /^[0-9]{16}$/;
  var regNome = /^[A-Za-zÀ-ÖØ-öø-ÿ'’\s]+$/;

  // --- Login ---
  var formLogin = document.getElementById("form-login");
  if (formLogin) {
    // ...submit uguale...
    var mailLogin = formLogin.querySelector("[name='mail']");
    if (mailLogin) {
      mailLogin.addEventListener("blur", function () {
        if (!regEmail.test(this.value.trim())) {
          mostraErrore(formLogin, "Email non valida.");
        } else {
          rimuoviErrore(formLogin);
        }
      });
    }
  }

  // --- Registrazione ---
  var formRegister = document.getElementById("form-register");
  if (formRegister) {
    // ...submit uguale...
    var mailRegister = formRegister.querySelector("[name='mail']");
    var pwRegister = formRegister.querySelector("[name='password']");
    var nomeRegister = formRegister.querySelector("[name='nome']");
    var cognomeRegister = formRegister.querySelector("[name='cognome']");

    if (mailRegister) {
      mailRegister.addEventListener("blur", function () {
        if (!regEmail.test(this.value.trim())) {
          mostraErrore(formRegister, "Email non valida.");
        } else {
          rimuoviErrore(formRegister);
        }
      });
    }
    if (pwRegister) {
      pwRegister.addEventListener("blur", function () {
        if (this.value.length < 6) {
          mostraErrore(formRegister, "Password troppo corta.");
        } else {
          rimuoviErrore(formRegister);
        }
      });
    }
    if (nomeRegister) {
      nomeRegister.addEventListener("blur", function () {
        if (!regNome.test(this.value.trim())) {
          mostraErrore(formRegister, "Il nome può contenere solo lettere.");
        } else {
          rimuoviErrore(formRegister);
        }
      });
    }
    if (cognomeRegister) {
      cognomeRegister.addEventListener("blur", function () {
        if (!regNome.test(this.value.trim())) {
          mostraErrore(formRegister, "Il cognome può contenere solo lettere.");
        } else {
          rimuoviErrore(formRegister);
        }
      });
    }
  }

  // --- Profilo ---
  var regProfilo = document.getElementById("profilo-form");
  if (regProfilo) {
    // ...submit uguale...
    var mailProfilo = regProfilo.querySelector("[name='mail']");
    var pwProfilo = regProfilo.querySelector("[name='password']");
    var nomeProfilo = regProfilo.querySelector("[name='nome']");
    var cognomeProfilo = regProfilo.querySelector("[name='cognome']");

    if (mailProfilo) {
      mailProfilo.addEventListener("blur", function () {
        if (!regEmail.test(this.value.trim())) {
          mostraErrore(regProfilo, "Email non valida.");
        } else {
          rimuoviErrore(regProfilo);
        }
      });
    }
    if (pwProfilo) {
      pwProfilo.addEventListener("blur", function () {
        if (this.value.length < 6) {
          mostraErrore(regProfilo, "Password troppo corta. Almeno 6 caratteri");
        } else {
          rimuoviErrore(regProfilo);
        }
      });
    }
    if (nomeProfilo) {
      nomeProfilo.addEventListener("blur", function () {
        if (!regNome.test(this.value.trim())) {
          mostraErrore(regProfilo, "Il nome può contenere solo lettere.");
        } else {
          rimuoviErrore(regProfilo);
        }
      });
    }
    if (cognomeProfilo) {
      cognomeProfilo.addEventListener("blur", function () {
        if (!regNome.test(this.value.trim())) {
          mostraErrore(regProfilo, "Il cognome può contenere solo lettere.");
        } else {
          rimuoviErrore(regProfilo);
        }
      });
    }
  }

  // --- Ordine/checkout ---
  var formOrdine = document.getElementById("form-ordine");
  if (formOrdine) {
    // ...submit uguale...
    var capOrdine = formOrdine.querySelector("[name='cap']");
    var numCartaOrdine = formOrdine.querySelector("[name='numeroCarta']");
    var cvvOrdine = formOrdine.querySelector("[name='cvv']");

    if (capOrdine) {
      capOrdine.addEventListener("blur", function () {
        if (!regCAP.test(this.value.trim())) {
          mostraErrore(formOrdine, "CAP non valido (5 cifre numeriche).");
        } else {
          rimuoviErrore(formOrdine);
        }
      });
    }
    if (numCartaOrdine) {
      numCartaOrdine.addEventListener("blur", function () {
        if (!regCarta.test(this.value.trim())) {
          mostraErrore(formOrdine, "Numero carta non valido (16 cifre numeriche).");
        } else {
          rimuoviErrore(formOrdine);
        }
      });
    }
    if (cvvOrdine) {
      cvvOrdine.addEventListener("blur", function () {
        if (!regCVV.test(this.value.trim())) {
          mostraErrore(formOrdine, "CVV non valido (3 o 4 cifre).");
        } else {
          rimuoviErrore(formOrdine);
        }
      });
    }
  }

  // visione password
  const toggle = document.getElementById("togglePassword");
  const passwordField = document.getElementById("password");
  if (toggle && passwordField) {
    toggle.addEventListener("click", function () {
      const isHidden = passwordField.type === "password";
      passwordField.type = isHidden ? "text" : "password";
      toggle.textContent = isHidden ? "visibility_off" : "visibility";
    });
  }
});

// Funzione per mostrare errore (NON toccare)
function mostraErrore(form, msg) {
  var err = form.querySelector(".errore-form");
  if (!err) {
    err = document.createElement("div");
    err.className = "errore errore-form";
    form.prepend(err);
  }
  err.textContent = msg;
}
