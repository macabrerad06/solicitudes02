contenido.innerHTML = `
  <div class="card">
    <h3>Formulario de Acceso a Aplicaciones y Sistemas</h3>
    <form id="form-acceso">
      <label>Cédula del solicitante:</label>
      <input type="text" id="cedula" placeholder="Ingrese su cédula" required>

      <div id="datos-solicitante" style="display:none;">
        <h4>Datos del Servidor Solicitante</h4>
        <p><b>Nombres:</b> <span id="nombreSolic"></span></p>
        <p><b>Unidad:</b> <span id="unidadSolic"></span></p>
        <p><b>Cargo:</b> <span id="cargoSolic"></span></p>
        <p><b>Correo:</b> <span id="correoSolic"></span></p>

        <h4>Datos del Jefe Autorizador</h4>
        <p><b>Nombres:</b> <span id="nombreJefe"></span></p>
        <p><b>Cargo:</b> <span id="cargoJefe"></span></p>
        <p><b>Correo:</b> <span id="correoJefe"></span></p>

        <h4>Seleccione los permisos requeridos</h4>
        <label><input type="checkbox" value="GDA"> GDA - Gestión de Registro</label><br>
        <label><input type="checkbox" value="QUIPUX"> QUIPUX - Documentos</label><br>
        <label><input type="checkbox" value="DSM"> DSM - Document Solution Management</label><br>
        <label><input type="checkbox" value="CASILLEROS"> Casilleros Virtuales</label><br>

        <button type="button" id="btnGenerarPDF" class="btn-login">Generar PDF</button>
      </div>
    </form>
  </div>
`;

// Cargar datos base
const script = document.createElement("script");
script.src = "js/vistas/base-datos.js";
document.head.appendChild(script);

// Buscar empleado por cédula
document.getElementById("cedula").addEventListener("change", () => {
  const cedula = document.getElementById("cedula").value.trim();
  const empleado = empleados.find(e => e.cedula === cedula);

  if (empleado) {
    document.getElementById("datos-solicitante").style.display = "block";
    document.getElementById("nombreSolic").textContent = `${empleado.nombres} ${empleado.apellidos}`;
    document.getElementById("unidadSolic").textContent = empleado.unidad;
    document.getElementById("cargoSolic").textContent = empleado.cargo;
    document.getElementById("correoSolic").textContent = empleado.correo;
    document.getElementById("nombreJefe").textContent = `${empleado.jefe.nombres} ${empleado.jefe.apellidos}`;
    document.getElementById("cargoJefe").textContent = empleado.jefe.cargo;
    document.getElementById("correoJefe").textContent = empleado.jefe.correo;
  } else {
    alert("No se encontraron datos para esa cédula");
  }
});

// Generar PDF (simulado)
document.addEventListener("click", e => {
  if (e.target.id === "btnGenerarPDF") {
    const seleccionados = Array.from(document.querySelectorAll('input[type="checkbox"]:checked')).map(cb => cb.value);
    if (seleccionados.length === 0) return alert("Seleccione al menos un permiso");

    alert("✅ PDF generado correctamente (simulado)");
    // En versión con backend: aquí se llamará a una API PHP para generar el PDF real.
  }
});
