const rol = localStorage.getItem("rolUsuario");
const solicitudes = JSON.parse(localStorage.getItem("solicitudes")) || [];

const mias = solicitudes.filter(s => s.rolSolicitante === rol);

let html = `
  <div class="card">
    <h3>Mis Solicitudes</h3>
    <table>
      <tr><th>#</th><th>Sistema</th><th>Motivo</th><th>Estado</th></tr>
`;

if (mias.length === 0) {
  html += `<tr><td colspan="4">No tiene solicitudes registradas</td></tr>`;
} else {
  mias.forEach((s, i) => {
    html += `<tr><td>${i + 1}</td><td>${s.sistema}</td><td>${s.motivo}</td><td>${s.estado}</td></tr>`;
  });
}

html += `</table></div>`;
contenido.innerHTML = html;
