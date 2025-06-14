// Validazione lato client per form registrazione e ordine (email, password, CAP, carta, CVV, ecc)
document.addEventListener("DOMContentLoaded", function () {
  var regEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  var regCAP = /^[0-9]{5}$/;
  var regCVV = /^[0-9]{3,4}$/;
  var regCarta = /^[0-9]{16}$/;
  var regNome = /^[A-Za-zÀ-ÖØ-öø-ÿ'’\s]+$/;

  
  var formLogin = document.getElementById("form-login");
  if (formLogin) {
    formLogin.addEventListener("submit", function (e) {
      var email = formLogin.mail.value.trim();
      if (!regEmail.test(email)) {
        mostraErrore(formLogin, "Email non valida.");
        e.preventDefault();
      }
    });
  }
  //validazione registrazione
  var formRegister = document.getElementById("form-register");
  if (formRegister) {
    formRegister.addEventListener("submit", function (e) {
      var email = formRegister.mail.value.trim();
      var pw = formRegister.password.value;
      var nome = formRegister.nome.value.trim();
      var cognome = formRegister.cognome.value.trim();

      if (!regEmail.test(email)) {
        mostraErrore(formRegister, "Email non valida.");
        e.preventDefault();
      }
      if (pw.length < 6) {
        mostraErrore(formRegister, "Password troppo corta.");
        e.preventDefault();
      }
      if (!regNome.test(nome)) {
        mostraErrore(formRegister, "Il nome può contenere solo lettere.");
        e.preventDefault();
      }
      if (!regNome.test(cognome)) {
        mostraErrore(formRegister, "Il cognome può contenere solo lettere.");
        e.preventDefault();
      }
    });
  }
  // Validazione profilo
  var regProfilo = document.getElementById("profilo-form");
  if (regProfilo) {
    regProfilo.addEventListener("submit", function (e) {
      var email = regProfilo.mail.value.trim();
      var pw = regProfilo.password.value;
      var nome = regProfilo.nome.value.trim();
      var cognome = regProfilo.cognome.value.trim();

      if (!regEmail.test(email)) {
        mostraErrore(regProfilo, "Email non valida.");
        e.preventDefault();
      }
      if (pw.length < 6) {
        mostraErrore(regProfilo, "Password troppo corta.");
        e.preventDefault();
      }
      if (!regNome.test(nome)) {
        mostraErrore(regProfilo, "Il nome può contenere solo lettere.");
        e.preventDefault();
      }
      if (!regNome.test(cognome)) {
        mostraErrore(regProfilo, "Il cognome può contenere solo lettere.");
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
  
  //visione password
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

function mostraErrore(form, msg) {
  var err = form.querySelector(".errore-form");
  if (!err) {
    err = document.createElement("div");
    err.className = "errore errore-form";
    form.prepend(err);
  }
  err.textContent = msg;
}
