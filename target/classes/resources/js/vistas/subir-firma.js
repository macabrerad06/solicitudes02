contenido.innerHTML = `
  <div class="card">
    <h3>Subir Documento Firmado</h3>
    <form id="form-subir">
      <label>Seleccione el archivo PDF firmado:</label>
      <input type="file" id="archivo" accept=".pdf" required>
      <button type="submit" class="btn-login">Subir</button>
    </form>
  </div>
`;

document.getElementById("form-subir").addEventListener("submit", e => {
  e.preventDefault();
  const file = document.getElementById("archivo").files[0];
  if (!file) return alert("Seleccione un archivo PDF");
  alert("📎 Archivo cargado correctamente (simulado)");
});
