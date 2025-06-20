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
   
 var mailLogin = formLogin.querySelector("[name='mail']");
	
 // submit
 formLogin.addEventListener("submit", function (e) {
   rimuoviErrore(formLogin);
   var email = mailLogin ? mailLogin.value.trim() : "";
   var valido = true;

   if (!regEmail.test(email)) {
     mostraErrore(formLogin, "Email non valida.");
     valido = false;
   }

   if (!valido) e.preventDefault();
 });
 
 //live

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
   
    var mailRegister = formRegister.querySelector("[name='mail']");
    var pwRegister = formRegister.querySelector("[name='password']");
    var nomeRegister = formRegister.querySelector("[name='nome']");
    var cognomeRegister = formRegister.querySelector("[name='cognome']");
 //submit
 
 formRegister.addEventListener("submit", function (e) {
   rimuoviErrore(formRegister);
   var valido = true;

   if (mailRegister && !regEmail.test(mailRegister.value.trim())) {
     mostraErrore(formRegister, "Email non valida.");
     valido = false;
   }
   if (pwRegister && pwRegister.value.length < 6) {
     mostraErrore(formRegister, "Password troppo corta. Almeno 6 caratteri.");
     valido = false;
   }
   if (nomeRegister && !regNome.test(nomeRegister.value.trim())) {
     mostraErrore(formRegister, "Il nome può contenere solo lettere.");
     valido = false;
   }
   if (cognomeRegister && !regNome.test(cognomeRegister.value.trim())) {
     mostraErrore(formRegister, "Il cognome può contenere solo lettere.");
     valido = false;
   }

   if (!valido) e.preventDefault();
 });

 
 //live 
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
          mostraErrore(formRegister, "Password troppo corta. Almeno 6 caratteri");
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

    var mailProfilo = regProfilo.querySelector("[name='mail']");
    var pwProfilo = regProfilo.querySelector("[name='password']");
    var nomeProfilo = regProfilo.querySelector("[name='nome']");
    var cognomeProfilo = regProfilo.querySelector("[name='cognome']");

	//submit
	
	regProfilo.addEventListener("submit", function (e) {
	  rimuoviErrore(regProfilo);
	  var valido = true;

	  if (mailProfilo && !regEmail.test(mailProfilo.value.trim())) {
	    mostraErrore(regProfilo, "Email non valida.");
	    valido = false;
	  }
	  if (pwProfilo && pwProfilo.value.length > 0 && pwProfilo.value.length < 6) {
	    mostraErrore(regProfilo, "Password troppo corta. Almeno 6 caratteri o lascia vuoto.");
	    valido = false;
	  }
	  if (nomeProfilo && !regNome.test(nomeProfilo.value.trim())) {
	    mostraErrore(regProfilo, "Il nome può contenere solo lettere.");
	    valido = false;
	  }
	  if (cognomeProfilo && !regNome.test(cognomeProfilo.value.trim())) {
	    mostraErrore(regProfilo, "Il cognome può contenere solo lettere.");
	    valido = false;
	  }

	  if (!valido) e.preventDefault();
	});

	
	//live
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
	    const val = this.value.trim();
	    if (val.length > 0 && val.length < 6) {
	      mostraErrore(regProfilo, "Password troppo corta. Almeno 6 caratteri o lascia vuoto.");
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
    
    var capOrdine = formOrdine.querySelector("[name='cap']");
    var numCartaOrdine = formOrdine.querySelector("[name='numeroCarta']");
    var cvvOrdine = formOrdine.querySelector("[name='cvv']");

	//submit
	
	
	formOrdine.addEventListener("submit", function (e) {
	  rimuoviErrore(formOrdine);
	  var valido = true;

	  if (capOrdine && !regCAP.test(capOrdine.value.trim())) {
	    mostraErrore(formOrdine, "CAP non valido (5 cifre numeriche).");
	    valido = false;
	  }
	  if (numCartaOrdine && !regCarta.test(numCartaOrdine.value.trim())) {
	    mostraErrore(formOrdine, "Numero carta non valido (16 cifre numeriche).");
	    valido = false;
	  }
	  if (cvvOrdine && !regCVV.test(cvvOrdine.value.trim())) {
	    mostraErrore(formOrdine, "CVV non valido (3 o 4 cifre).");
	    valido = false;
	  }

	  if (!valido) e.preventDefault();
	});
	//live
	
	
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

// Funzione per mostrare errore
function mostraErrore(form, msg) {
  var err = form.querySelector(".errore-form");
  if (!err) {
    err = document.createElement("div");
    err.className = "errore errore-form";
    form.prepend(err);
  }
  err.textContent = msg;
}
