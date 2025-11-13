// Obtener rol del usuario
const rol = localStorage.getItem("rolUsuario");
const menu = document.getElementById("menu");
const titulo = document.getElementById("titulo-bienvenida");
const descripcion = document.getElementById("descripcion-rol");
const contenido = document.getElementById("contenido");
const logoutBtn = document.getElementById("logoutBtn");

if (!rol) window.location.href = "index.html";

// Menú base para todos (solicitudes)
let opciones = [
  { id: "nueva", nombre: "Nueva Solicitud", vista: "solicitudes-nueva.js" },
  { id: "mis", nombre: "Mis Solicitudes", vista: "solicitudes-mis.js" }
];

// Extender según el rol
switch (rol) {
  case "jefe":
    titulo.textContent = "Bienvenido Jefe Autorizador";
    descripcion.textContent = "Puede crear, consultar y aprobar solicitudes.";
    opciones.push({ id: "aprobaciones", nombre: "Aprobaciones", vista: "jefe-aprobaciones.js" });
    break;

  case "admin":
    titulo.textContent = "Bienvenido Administrador";
    descripcion.textContent = "Puede crear, aprobar solicitudes y administrar usuarios.";
    opciones.push(
      { id: "aprobaciones", nombre: "Aprobaciones", vista: "jefe-aprobaciones.js" },
      { id: "usuarios", nombre: "Gestión de Usuarios", vista: "admin-usuarios.js" },
      { id: "reportes", nombre: "Reportes", vista: "admin-reportes.js" }
    );
    break;

  default:
    titulo.textContent = "Bienvenido Usuario";
    descripcion.textContent = "Puede crear y consultar sus solicitudes.";
    break;
}

// Generar menú
menu.innerHTML = "";
opciones.forEach(opt => {
  const li = document.createElement("li");
  li.innerHTML = `<a href="#" data-vista="${opt.vista}">${opt.nombre}</a>`;
  menu.appendChild(li);
});

// Vista inicial
contenido.innerHTML = `<div class="card"><h3>Inicio</h3><p>Seleccione una opción del menú lateral.</p></div>`;

// Eventos
menu.addEventListener("click", e => {
  e.preventDefault();
  const vista = e.target.getAttribute("data-vista");
  if (vista) cargarVista(vista);
});

// Cerrar sesión
logoutBtn.addEventListener("click", () => {
  localStorage.removeItem("rolUsuario");
  window.location.href = "index.html";
});

// Cargar vistas dinámicas
function cargarVista(nombreArchivo) {
  fetch(`js/vistas/${nombreArchivo}`)
    .then(resp => resp.text())
    .then(code => {
      contenido.innerHTML = "";
      const script = document.createElement("script");
      script.textContent = code;
      contenido.appendChild(script);
    })
    .catch(() => {
      contenido.innerHTML = `<div class="card"><p>Error al cargar la vista.</p></div>`;
    });
}
