const solicitudes = JSON.parse(localStorage.getItem("solicitudes")) || [];
const total = solicitudes.length;
const aprobadas = solicitudes.filter(s => s.estado === "Aprobada").length;
const rechazadas = solicitudes.filter(s => s.estado === "Rechazada").length;

contenido.innerHTML = `
  <div class="card">
    <h3>Reportes Generales</h3>
    <p>Total solicitudes: ${total}</p>
    <p>Aprobadas: ${aprobadas}</p>
    <p>Rechazadas: ${rechazadas}</p>
  </div>
`;
