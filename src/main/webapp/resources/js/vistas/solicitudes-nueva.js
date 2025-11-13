contenido.innerHTML = `
  <div class="card">
    <h3>Nueva Solicitud</h3>
    <form id="form-solicitud">
      <label>Nombre del Sistema:</label>
      <input type="text" id="sistema" placeholder="Ej. QUIPUX" required>
      <label>Motivo:</label>
      <textarea id="motivo" placeholder="Explique brevemente..."></textarea>
      <button type="submit" class="btn-login">Enviar Solicitud</button>
    </form>
  </div>
`;

document.getElementById("form-solicitud").addEventListener("submit", e => {
  e.preventDefault();

  const sistema = document.getElementById("sistema").value.trim();
  const motivo = document.getElementById("motivo").value.trim();
  const rol = localStorage.getItem("rolUsuario");

  if (!sistema || !motivo) return alert("Complete todos los campos");

  const solicitudes = JSON.parse(localStorage.getItem("solicitudes")) || [];
  const nueva = {
    id: Date.now(),
    sistema,
    motivo,
    rolSolicitante: rol,
    estado: "Pendiente"
  };
  solicitudes.push(nueva);
  localStorage.setItem("solicitudes", JSON.stringify(solicitudes));

  alert("Solicitud enviada correctamente ✅");
  document.getElementById("form-solicitud").reset();
});
