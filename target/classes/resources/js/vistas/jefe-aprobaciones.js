const solicitudes = JSON.parse(localStorage.getItem("solicitudes")) || [];

let html = `
  <div class="card">
    <h3>Solicitudes Pendientes</h3>
    <p>Puede aprobar o rechazar las solicitudes de cualquier usuario.</p>
    <table>
      <tr><th>#</th><th>Sistema</th><th>Motivo</th><th>Solicitante</th><th>Estado</th><th>Acciones</th></tr>
`;

if (solicitudes.length === 0) {
  html += `<tr><td colspan="6">No hay solicitudes registradas</td></tr>`;
} else {
  solicitudes.forEach((s, i) => {
    html += `
      <tr>
        <td>${i + 1}</td>
        <td>${s.sistema}</td>
        <td>${s.motivo}</td>
        <td>${s.rolSolicitante}</td>
        <td>${s.estado}</td>
        <td>
          ${s.estado === "Pendiente" ? `
            <button onclick="aprobar(${s.id})">Aprobar</button>
            <button onclick="rechazar(${s.id})">Rechazar</button>` : "-"}
        </td>
      </tr>
    `;
  });
}
html += `</table></div>`;
contenido.innerHTML = html;

// Funciones globales para botones
function aprobar(id) {
  cambiarEstado(id, "Aprobada");
}
function rechazar(id) {
  cambiarEstado(id, "Rechazada");
}
function cambiarEstado(id, nuevoEstado) {
  const solicitudes = JSON.parse(localStorage.getItem("solicitudes")) || [];
  const index = solicitudes.findIndex(s => s.id === id);
  if (index !== -1) {
    solicitudes[index].estado = nuevoEstado;
    localStorage.setItem("solicitudes", JSON.stringify(solicitudes));
    alert(`Solicitud ${nuevoEstado}`);
    location.reload();
  }
}
