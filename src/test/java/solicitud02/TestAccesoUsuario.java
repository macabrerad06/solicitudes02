package solicitud02;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senadi.solicitud02.controlador.AplicacionControlador;
import com.senadi.solicitud02.controlador.PermisoAplicacionControlador;
import com.senadi.solicitud02.controlador.AccesoUsuarioControlador;
import com.senadi.solicitud02.controlador.SolicitudControlador;
import com.senadi.solicitud02.controlador.UsuarioControlador;
import com.senadi.solicitud02.controlador.impl.AplicacionControladorImpl;
import com.senadi.solicitud02.controlador.impl.PermisoAplicacionControladorImpl;
import com.senadi.solicitud02.controlador.impl.AccesoUsuarioControladorImpl;
import com.senadi.solicitud02.controlador.impl.SolicitudControladorImpl;
import com.senadi.solicitud02.controlador.impl.UsuarioControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Aplicacion;
import com.senadi.solicitud02.modelo.entidades.PermisoAplicacion;
import com.senadi.solicitud02.modelo.entidades.AccesoUsuario;
import com.senadi.solicitud02.modelo.entidades.Solicitud;
import com.senadi.solicitud02.modelo.entidades.Usuario;

import java.util.List;

public class TestAccesoUsuario {

    private static final UsuarioControlador usuarioCtrl = new UsuarioControladorImpl();
    private static final SolicitudControlador solicitudCtrl = new SolicitudControladorImpl();
    private static final AplicacionControlador appCtrl = new AplicacionControladorImpl();
    private static final PermisoAplicacionControlador permisoCtrl = new PermisoAplicacionControladorImpl();
    private static final AccesoUsuarioControlador accesoCtrl = new AccesoUsuarioControladorImpl();

    private static Usuario usuarioBase;
    private static Solicitud solicitudBase;
    private static Aplicacion appBase;
    private static PermisoAplicacion permisoBase;

    @BeforeClass
    public static void inicializarDatos() {
        // 1️⃣ Crear usuario base
        usuarioBase = usuarioCtrl.buscarPorCorreo("acceso.user@example.com");
        if (usuarioBase == null) {
            usuarioBase = new Usuario();
            usuarioBase.setNombre("Ana");
            usuarioBase.setApellido("Gómez");
            usuarioBase.setCorreo("acceso.user@example.com");
            usuarioBase.setCargo("Analista");
            usuarioCtrl.crear(usuarioBase);
            System.out.println("🆕 Usuario base creado");
        }

        // 2️⃣ Crear solicitud base
        List<Solicitud> solicitudes = solicitudCtrl.listarTodos();
        if (solicitudes.isEmpty()) {
            solicitudBase = new Solicitud();
            solicitudBase.setEstado("CREADA");
            solicitudBase.setUsuario(usuarioBase);
            solicitudCtrl.crear(solicitudBase);
        } else {
            solicitudBase = solicitudes.get(0);
        }

        // 3️⃣ Crear aplicación base
        appBase = appCtrl.buscarPorNombre("SistemaInventario");
        if (appBase == null) {
            appBase = new Aplicacion();
            appBase.setNombre("SistemaInventario");
            appBase.setDescripcion("Aplicación para gestión de inventarios");
            appCtrl.crear(appBase);
        }

        // 4️⃣ Crear permiso base
        List<PermisoAplicacion> permisos = permisoCtrl.buscarPorNombre("PRUEBA ACCESO");
        permisoBase = permisos.isEmpty() ? null : permisos.get(0);

        if (permisoBase == null) {
            permisoBase = new PermisoAplicacion();
            permisoBase.setNombre("PRUEBA ACCESO");
            permisoBase.setDescripcion("Permiso para test de acceso");
            permisoBase.setAplicacion(appBase);
            permisoCtrl.crear(permisoBase);
        }

        // 5️⃣ Crear un acceso usuario si no existe
        List<AccesoUsuario> accesos = accesoCtrl.buscarPorSolicitud(solicitudBase.getId());
        if (accesos.isEmpty()) {
            AccesoUsuario a = new AccesoUsuario();
            a.setSolicitud(solicitudBase);
            a.setPermiso(permisoBase);
            accesoCtrl.crear(a);
        }
    }

    @Test
    public void testCrearAccesoUsuario() {
        AccesoUsuario a = new AccesoUsuario();
        a.setSolicitud(solicitudBase);
        a.setPermiso(permisoBase);

        accesoCtrl.crear(a);
        assertNotNull("El acceso usuario debe tener ID", a.getId());
        System.out.println("✅ AccesoUsuario creado con ID: " + a.getId());
    }

    @Test
    public void testListarAccesos() {
        List<AccesoUsuario> lista = accesoCtrl.listarTodos();
        assertFalse("Debe existir al menos un acceso", lista.isEmpty());
        System.out.println("📋 Total accesos: " + lista.size());
    }

    @Test
    public void testBuscarPorSolicitud() {
        List<AccesoUsuario> lista = accesoCtrl.buscarPorSolicitud(solicitudBase.getId());
        assertFalse("Debe existir al menos un acceso para la solicitud base", lista.isEmpty());
        System.out.println("🔍 Accesos encontrados para solicitud: " + lista.size());
    }

    @Test
    public void testBuscarPorPermiso() {
        List<AccesoUsuario> lista = accesoCtrl.buscarPorPermiso(permisoBase.getId());
        assertFalse("Debe existir al menos un acceso para el permiso base", lista.isEmpty());
        System.out.println("🔍 Accesos encontrados para permiso: " + lista.size());
    }

    @Test
    public void testEliminarAcceso() {
        List<AccesoUsuario> lista = accesoCtrl.listarTodos();
        if (!lista.isEmpty()) {
            AccesoUsuario a = lista.get(0);
            accesoCtrl.eliminar(a.getId());
            AccesoUsuario eliminado = accesoCtrl.buscarPorId(a.getId());
            assertNull("El acceso usuario debe eliminarse", eliminado);
            System.out.println("🗑️ AccesoUsuario eliminado correctamente");
        }
    }
}
