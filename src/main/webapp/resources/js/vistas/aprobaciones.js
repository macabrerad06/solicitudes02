contenido.innerHTML = `
  <div class="card">
    <h3>Revisión de Formularios PDF</h3>
    <p>Aquí el jefe descarga, firma y vuelve a subir el documento.</p>
    <table>
      <tr><th>Usuario</th><th>Sistema</th><th>Archivo</th><th>Acción</th></tr>
      <tr>
        <td>Juan Pérez</td>
        <td>QUIPUX</td>
        <td><a href="pdfs/solicitud_quipux.pdf" download>Descargar</a></td>
        <td><button>Subir PDF Firmado</button></td>
      </tr>
    </table>
  </div>
`;
