package solicitud02;

import static org.junit.Assert.*;
import org.junit.Test;

import com.senadi.solicitud02.controlador.AccesoUsuarioControlador;
import com.senadi.solicitud02.controlador.AplicacionControlador;
import com.senadi.solicitud02.controlador.SolicitudControlador;
import com.senadi.solicitud02.controlador.PermisoAplicacionControlador;
import com.senadi.solicitud02.controlador.UsuarioControlador;
import com.senadi.solicitud02.controlador.impl.AccesoUsuarioControladorImpl;
import com.senadi.solicitud02.controlador.impl.AplicacionControladorImpl;
import com.senadi.solicitud02.controlador.impl.SolicitudControladorImpl;
import com.senadi.solicitud02.controlador.impl.PermisoAplicacionControladorImpl;
import com.senadi.solicitud02.controlador.impl.UsuarioControladorImpl;
import com.senadi.solicitud02.modelo.entidades.AccesoUsuario;
import com.senadi.solicitud02.modelo.entidades.Aplicacion;
import com.senadi.solicitud02.modelo.entidades.Solicitud;
import com.senadi.solicitud02.modelo.entidades.PermisoAplicacion;
import com.senadi.solicitud02.modelo.entidades.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class TestAccesoUsuario {

    private final AccesoUsuarioControlador accesoCtrl = new AccesoUsuarioControladorImpl();
    private final SolicitudControlador solicitudCtrl = new SolicitudControladorImpl();
    private final PermisoAplicacionControlador permisoCtrl = new PermisoAplicacionControladorImpl();
    private final AplicacionControlador aplicacionCtrl = new AplicacionControladorImpl();
    private final UsuarioControlador usuarioCtrl = new UsuarioControladorImpl();

    // Helper: Usuario de prueba
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

    // Helper: Aplicación de prueba
    private Aplicacion crearAplicacionPrueba() {
        Aplicacion app = aplicacionCtrl.buscarPorNombre("Aplicación Prueba");
        if (app == null) {
            app = new Aplicacion();
            app.setNombre("Aplicación Prueba");
            app.setDescripcion("Aplicación temporal para tests");
            aplicacionCtrl.crear(app);
        }
        return app;
    }

    // Helper: PermisoAplicacion de prueba
    private PermisoAplicacion crearPermisoPrueba() {
        Aplicacion app = crearAplicacionPrueba();
        PermisoAplicacion p = new PermisoAplicacion();
        p.setNombre("Permiso Prueba");
        p.setDescripcion("Permiso temporal para tests");
        p.setAplicacion(app); // obligatorio
        permisoCtrl.crear(p);
        return p;
    }

    // Helper: Solicitud de prueba
    private Solicitud crearSolicitudPrueba() {
        Usuario usuario = crearUsuarioPrueba();
        Solicitud s = new Solicitud();
        s.setUsuario(usuario);
        s.setEstado("CREADA");
        solicitudCtrl.crear(s);
        return s;
    }

    @Test
    public void testCrearAccesoUsuario() {
        Solicitud s = crearSolicitudPrueba();
        PermisoAplicacion p = crearPermisoPrueba();

        AccesoUsuario nuevo = new AccesoUsuario();
        nuevo.setSolicitud(s);
        nuevo.setPermiso(p);
        nuevo.setFechaCarga(LocalDateTime.now());

        accesoCtrl.crear(nuevo);

        assertNotNull("El accesoUsuario debería haberse creado con ID", nuevo.getId());
        System.out.println("estoy aqui AccesoUsuario creado con ID: " + nuevo.getId());
    }

    @Test
    public void testActualizarAccesoUsuario() {
        Solicitud s = crearSolicitudPrueba();
        PermisoAplicacion p = crearPermisoPrueba();

        AccesoUsuario a = new AccesoUsuario();
        a.setSolicitud(s);
        a.setPermiso(p);
        a.setFechaCarga(LocalDateTime.now());
        accesoCtrl.crear(a);

        // Actualizar fecha de carga
        LocalDateTime nuevaFecha = LocalDateTime.now().plusDays(1);
        a.setFechaCarga(nuevaFecha);
        AccesoUsuario actualizado = accesoCtrl.actualizar(a);

        assertEquals("La fecha de carga debe haberse actualizado", nuevaFecha, actualizado.getFechaCarga());
        System.out.println("✅ AccesoUsuario actualizado ID: " + actualizado.getId());
    }

    @Test
    public void testListarAccesosUsuario() {
        Solicitud s = crearSolicitudPrueba();
        PermisoAplicacion p = crearPermisoPrueba();

        AccesoUsuario a = new AccesoUsuario();
        a.setSolicitud(s);
        a.setPermiso(p);
        accesoCtrl.crear(a);

        List<AccesoUsuario> lista = accesoCtrl.listarTodos();
        assertTrue("Debe existir al menos un accesoUsuario", !lista.isEmpty());
        System.out.println("📋 Total accesosUsuario: " + lista.size());
    }

    @Test
    public void testBuscarPorId() {
        Solicitud s = crearSolicitudPrueba();
        PermisoAplicacion p = crearPermisoPrueba();

        AccesoUsuario a = new AccesoUsuario();
        a.setSolicitud(s);
        a.setPermiso(p);
        accesoCtrl.crear(a);

        AccesoUsuario encontrado = accesoCtrl.buscarPorId(a.getId());
        assertNotNull("Debe encontrarse el accesoUsuario por ID", encontrado);
        System.out.println("🔍 AccesoUsuario encontrado ID: " + encontrado.getId());
    }

    @Test
    public void testEliminarAccesoUsuario() {
        Solicitud s = crearSolicitudPrueba();
        PermisoAplicacion p = crearPermisoPrueba();

        AccesoUsuario a = new AccesoUsuario();
        a.setSolicitud(s);
        a.setPermiso(p);
        accesoCtrl.crear(a);

        accesoCtrl.eliminar(a.getId());
        AccesoUsuario eliminado = accesoCtrl.buscarPorId(a.getId());
        assertNull("El accesoUsuario debería eliminarse", eliminado);
        System.out.println("🗑️ AccesoUsuario eliminado correctamente");
    }
}
