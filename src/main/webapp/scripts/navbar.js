document.addEventListener('DOMContentLoaded', function () {
  const menuToggle = document.getElementById('menu-toggle');
  const navLinks = document.getElementById('navLinks');

  if (menuToggle && navLinks) {
    menuToggle.addEventListener('click', function () {
      navLinks.classList.toggle('open');
      // Cambia icona hamburger/X
      if(navLinks.classList.contains('open')){
        menuToggle.textContent = "close"; // Mostra la X
      } else {
        menuToggle.textContent = "menu"; // Mostra ≡
      }
    });

    // Chiudi il menu se clicchi su un link
    navLinks.querySelectorAll('a').forEach(link => {
      link.addEventListener('click', () => {
        navLinks.classList.remove('open');
        menuToggle.textContent = "menu"; // Torna all'hamburger
      });
    });

    // Chiudi se clicchi fuori dal menu (UX migliore)
    document.addEventListener('click', function(e) {
      if (navLinks.classList.contains('open') &&
         !navLinks.contains(e.target) &&
         e.target !== menuToggle) {
        navLinks.classList.remove('open');
        menuToggle.textContent = "menu";
      }
    });
  }
});
