document.getElementById('loginForm').addEventListener('submit', function (e) {
  e.preventDefault();

  const usuario = document.getElementById('usuario').value.trim();
  const clave = document.getElementById('clave').value.trim();
  const errorMsg = document.getElementById('mensaje-error');

  // Simulación de usuarios
  const usuarios = {
    "usuario": { clave: "1234", rol: "normal" },
    "jefe": { clave: "1234", rol: "jefe" },
    "admin": { clave: "1234", rol: "admin" }
  };

  if (usuarios[usuario] && usuarios[usuario].clave === clave) {
    // Guardar el rol en localStorage para usarlo en el dashboard
    localStorage.setItem("rolUsuario", usuarios[usuario].rol);
    window.location.href = "dashboard.html";
  } else {
    errorMsg.style.display = 'block';
  }
});
