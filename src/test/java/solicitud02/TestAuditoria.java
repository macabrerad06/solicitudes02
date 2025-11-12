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

    // Helper para asegurarnos de tener un usuario de prueba
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
    }

    @Test
    public void testActualizarAuditoria() {
        Usuario usuario = crearUsuarioPrueba();

        Auditoria a = new Auditoria();
        a.setUsuario(usuario);
        a.setAccion("TEST_ACTUALIZAR");
        a.setEntidad("Solicitud");
        a.setEntidadId(999L);
        a.setDetalle("Auditoría temporal para test");
        auditoriaCtrl.crear(a);

        a.setDetalle("Detalle actualizado");
        Auditoria actualizado = auditoriaCtrl.actualizar(a);

        assertEquals("El detalle debe haberse actualizado", "Detalle actualizado", actualizado.getDetalle());
    }

    @Test
    public void testListarAuditorias() {
        Usuario usuario = crearUsuarioPrueba();

        Auditoria a = new Auditoria();
        a.setUsuario(usuario);
        a.setAccion("TEST_LISTAR");
        a.setEntidad("Solicitud");
        a.setEntidadId(123L);
        a.setDetalle("Auditoría temporal para test listar");
        auditoriaCtrl.crear(a);

        List<Auditoria> lista = auditoriaCtrl.listarTodos();
        assertTrue("Debe existir al menos una auditoría", !lista.isEmpty());
    }

    @Test
    public void testBuscarPorId() {
        Usuario usuario = crearUsuarioPrueba();

        Auditoria a = new Auditoria();
        a.setUsuario(usuario);
        a.setAccion("TEST_BUSCAR");
        a.setEntidad("Solicitud");
        a.setEntidadId(456L);
        a.setDetalle("Auditoría temporal para test buscar");
        auditoriaCtrl.crear(a);

        Auditoria encontrado = auditoriaCtrl.buscarPorId(a.getId());
        assertNotNull("Debe encontrarse la auditoría por ID", encontrado);
    }

    @Test
    public void testEliminarAuditoria() {
        Usuario usuario = crearUsuarioPrueba();

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
    }

    // ===================== Búsquedas avanzadas =====================

    @Test
    public void testBuscarPorUsuario() {
        Usuario usuario = crearUsuarioPrueba();

        Auditoria a = new Auditoria();
        a.setUsuario(usuario);
        a.setAccion("BUSQUEDA_USUARIO");
        a.setEntidad("Solicitud");
        a.setEntidadId(1000L);
        a.setDetalle("Auditoría para prueba buscar por usuario");
        auditoriaCtrl.crear(a);

        List<Auditoria> resultado = auditoriaCtrl.buscarPorUsuario(usuario.getId());
        assertFalse("Debe encontrar al menos una auditoría para el usuario", resultado.isEmpty());
    }

    @Test
    public void testBuscarPorAccion() {
        Usuario usuario = crearUsuarioPrueba();

        Auditoria a = new Auditoria();
        a.setUsuario(usuario);
        a.setAccion("BUSQUEDA_ACCION");
        a.setEntidad("Solicitud");
        a.setEntidadId(1001L);
        a.setDetalle("Auditoría para prueba buscar por acción");
        auditoriaCtrl.crear(a);

        List<Auditoria> resultado = auditoriaCtrl.buscarPorAccion("BUSQUEDA_ACCION");
        assertFalse("Debe encontrar al menos una auditoría con la acción específica", resultado.isEmpty());
    }
}
