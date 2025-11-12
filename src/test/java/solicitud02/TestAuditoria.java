package solicitud02;

import static org.junit.Assert.*;
import org.junit.Test;

import com.senadi.solicitud02.controlador.AuditoriaControlador;
import com.senadi.solicitud02.controlador.UsuarioControlador;
import com.senadi.solicitud02.controlador.impl.AuditoriaControladorImpl;
import com.senadi.solicitud02.controlador.impl.UsuarioControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Auditoria;
import com.senadi.solicitud02.modelo.entidades.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class TestAuditoria {

    private final AuditoriaControlador auditoriaCtrl = new AuditoriaControladorImpl();
    private final UsuarioControlador usuarioCtrl = new UsuarioControladorImpl();

    // ✅ Helper para asegurarnos de tener un usuario de prueba
    private Usuario crearUsuarioPrueba() {
        Usuario usuario = usuarioCtrl.buscarPorCorreo("juan.perez@example.com");
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setNombre("Juan");
            usuario.setApellido("Pérez");
            usuario.setCorreo("juan.perez@example.com");
            usuario.setCargo("Analista");
            usuarioCtrl.crear(usuario);
        }
        return usuario;
    }

    @Test
    public void testCrearAuditoria() {
        Usuario usuario = crearUsuarioPrueba();

        Auditoria nueva = new Auditoria();
        nueva.setUsuario(usuario);
        nueva.setAccion("CREAR_SOLICITUD");
        nueva.setEntidad("Solicitud");
        nueva.setEntidadId(1L);
        nueva.setFechaEvento(LocalDateTime.now());
        nueva.setDetalle("Se creó una nueva solicitud de acceso a sistema");

        auditoriaCtrl.crear(nueva);

        assertNotNull("La auditoría debería haberse creado con ID", nueva.getId());
        System.out.println("✅ Auditoría creada con ID: " + nueva.getId());
    }

    @Test
    public void testActualizarAuditoria() {
        Usuario usuario = crearUsuarioPrueba();

        // Crear auditoría para actualizar
        Auditoria a = new Auditoria();
        a.setUsuario(usuario);
        a.setAccion("TEST_ACTUALIZAR");
        a.setEntidad("Solicitud");
        a.setEntidadId(999L);
        a.setDetalle("Auditoría temporal para test");
        auditoriaCtrl.crear(a);

        // Actualizar
        a.setDetalle("Detalle actualizado");
        Auditoria actualizado = auditoriaCtrl.actualizar(a);

        assertEquals("El detalle debe haberse actualizado", "Detalle actualizado", actualizado.getDetalle());
        System.out.println("✅ Auditoría actualizada: " + actualizado.getAccion());
    }

    @Test
    public void testListarAuditorias() {
        Usuario usuario = crearUsuarioPrueba();

        // Crear una auditoría para asegurar que la lista no esté vacía
        Auditoria a = new Auditoria();
        a.setUsuario(usuario);
        a.setAccion("TEST_LISTAR");
        a.setEntidad("Solicitud");
        a.setEntidadId(123L);
        a.setDetalle("Auditoría temporal para test listar");
        auditoriaCtrl.crear(a);

        List<Auditoria> lista = auditoriaCtrl.listarTodos();
        assertTrue("Debe existir al menos una auditoría", !lista.isEmpty());
        System.out.println("📋 Total auditorías: " + lista.size());
    }

    @Test
    public void testBuscarPorId() {
        Usuario usuario = crearUsuarioPrueba();

        // Crear auditoría para buscar
        Auditoria a = new Auditoria();
        a.setUsuario(usuario);
        a.setAccion("TEST_BUSCAR");
        a.setEntidad("Solicitud");
        a.setEntidadId(456L);
        a.setDetalle("Auditoría temporal para test buscar");
        auditoriaCtrl.crear(a);

        Auditoria encontrado = auditoriaCtrl.buscarPorId(a.getId());
        assertNotNull("Debe encontrarse la auditoría por ID", encontrado);
        System.out.println("🔍 Auditoría encontrada: " + encontrado.getAccion() + " - Usuario: " + encontrado.getUsuario().getNombre());
    }

    @Test
    public void testEliminarAuditoria() {
        Usuario usuario = crearUsuarioPrueba();

        // Crear auditoría para eliminar
        Auditoria a = new Auditoria();
        a.setUsuario(usuario);
        a.setAccion("TEST_ELIMINAR");
        a.setEntidad("Solicitud");
        a.setEntidadId(789L);
        a.setDetalle("Auditoría temporal para test eliminar");
        auditoriaCtrl.crear(a);

        auditoriaCtrl.eliminar(a.getId());
        Auditoria eliminado = auditoriaCtrl.buscarPorId(a.getId());
        assertNull("La auditoría debería eliminarse", eliminado);
        System.out.println("🗑️ Auditoría eliminada correctamente");
    }
}
