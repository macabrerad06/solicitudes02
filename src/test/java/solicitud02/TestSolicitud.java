package solicitud02;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senadi.solicitud02.controlador.SolicitudControlador;
import com.senadi.solicitud02.controlador.UsuarioControlador;
import com.senadi.solicitud02.controlador.impl.SolicitudControladorImpl;
import com.senadi.solicitud02.controlador.impl.UsuarioControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Solicitud;
import com.senadi.solicitud02.modelo.entidades.Usuario;

import java.util.List;

public class TestSolicitud {

    private static final SolicitudControlador solCtrl = new SolicitudControladorImpl();
    private static final UsuarioControlador usuarioCtrl = new UsuarioControladorImpl();

    private static Usuario usuarioBase;

    @BeforeClass
    public static void inicializarDatos() {
        // Crear usuario base si no existe
        Usuario usuarioTmp = usuarioCtrl.buscarPorCorreo("solicitud.user@example.com");
        if (usuarioTmp == null) {
            usuarioBase = new Usuario();
            usuarioBase.setNombre("Carlos");
            usuarioBase.setApellido("Suárez");
            usuarioBase.setCorreo("solicitud.user@example.com");
            usuarioBase.setCargo("Analista");
            usuarioCtrl.crear(usuarioBase);
            System.out.println("🆕 Usuario base creado para solicitudes");
        } else {
            usuarioBase = usuarioTmp;
            System.out.println("ℹ️ Usuario base encontrado: " + usuarioBase.getNombre());
        }

        // Crear al menos una solicitud si la tabla está vacía
        List<Solicitud> existentes = solCtrl.listarTodos();
        if (existentes.isEmpty()) {
            Solicitud s = new Solicitud();
            s.setEstado("CREADA");
            s.setUsuario(usuarioBase);
            solCtrl.crear(s);
            System.out.println("🆕 Solicitud base creada automáticamente");
        } else {
            System.out.println("ℹ️ Solicitudes existentes detectadas: " + existentes.size());
        }
    }

    @Test
    public void testCrearSolicitud() {
        Solicitud s = new Solicitud();
        s.setEstado("PENDIENTE");
        s.setUsuario(usuarioBase);

        solCtrl.crear(s);

        assertNotNull("La solicitud debe tener un ID asignado", s.getId());
        System.out.println("✅ Solicitud creada con ID: " + s.getId());
    }

    @Test
    public void testActualizarSolicitud() {
        List<Solicitud> solicitudes = solCtrl.listarTodos();
        if (solicitudes.isEmpty()) {
            Solicitud nueva = new Solicitud();
            nueva.setEstado("CREADA");
            nueva.setUsuario(usuarioBase);
            solCtrl.crear(nueva);
            solicitudes = solCtrl.listarTodos();
        }

        Solicitud s = solicitudes.get(0);
        s.setEstado("APROBADA");
        Solicitud actualizada = solCtrl.actualizar(s);

        assertEquals("El estado debe haberse actualizado correctamente",
                     "APROBADA", actualizada.getEstado());
        System.out.println("✅ Solicitud actualizada correctamente");
    }

    @Test
    public void testListarSolicitudes() {
        List<Solicitud> lista = solCtrl.listarTodos();
        if (lista.isEmpty()) {
            Solicitud s = new Solicitud();
            s.setEstado("CREADA");
            s.setUsuario(usuarioBase);
            solCtrl.crear(s);
            lista = solCtrl.listarTodos();
        }

        assertTrue("Debe existir al menos una solicitud", !lista.isEmpty());
        System.out.println("📋 Total de solicitudes: " + lista.size());
    }

    @Test
    public void testBuscarPorId() {
        List<Solicitud> lista = solCtrl.listarTodos();
        if (lista.isEmpty()) {
            Solicitud s = new Solicitud();
            s.setEstado("CREADA");
            s.setUsuario(usuarioBase);
            solCtrl.crear(s);
            lista = solCtrl.listarTodos();
        }

        Long id = lista.get(0).getId();
        Solicitud encontrada = solCtrl.buscarPorId(id);

        assertNotNull("La solicitud debe encontrarse por ID", encontrada);
        System.out.println("🔍 Solicitud encontrada con ID: " + id);
    }

    @Test
    public void testEliminarSolicitud() {
        List<Solicitud> lista = solCtrl.listarTodos();
        if (lista.isEmpty()) {
            Solicitud s = new Solicitud();
            s.setEstado("CREADA");
            s.setUsuario(usuarioBase);
            solCtrl.crear(s);
            lista = solCtrl.listarTodos();
        }

        Solicitud s = lista.get(0);
        solCtrl.eliminar(s.getId());
        Solicitud eliminada = solCtrl.buscarPorId(s.getId());

        assertNull("La solicitud debería eliminarse correctamente", eliminada);
        System.out.println("🗑️ Solicitud eliminada correctamente");
    }

    // ----------------- TEST BÚSQUEDA AVANZADA -----------------
    @Test
    public void testBusquedaAvanzadaSolicitud() {
        List<Solicitud> todas = solCtrl.listarTodos();
        assertTrue("Debe existir al menos una solicitud para probar búsquedas", !todas.isEmpty());

        // 1️⃣ Buscar por usuario
        final Usuario u = todas.get(0).getUsuario();
        List<Solicitud> porUsuario = solCtrl.buscarPorUsuario(u.getId());
        assertTrue("Debe encontrar al menos 1 solicitud para el usuario", porUsuario.size() >= 1);
        System.out.println("🔎 Solicitudes encontradas por usuario " + u.getNombre() + ": " + porUsuario.size());

        // 2️⃣ Buscar por estado
        final String estado = todas.get(0).getEstado();
        List<Solicitud> porEstado = solCtrl.buscarPorEstado(estado);
        assertTrue("Debe encontrar al menos 1 solicitud con estado " + estado, porEstado.size() >= 1);
        System.out.println("🔎 Solicitudes encontradas por estado '" + estado + "': " + porEstado.size());

        // 3️⃣ Búsqueda combinada (usuario + estado)
        List<Solicitud> combinada = todas.stream()
            .filter(s -> s.getUsuario().getId().equals(u.getId()) && s.getEstado().equals(estado))
            .toList();
        assertTrue("Debe encontrar al menos 1 solicitud que coincida con usuario y estado", combinada.size() >= 1);
        System.out.println("🔎 Solicitudes encontradas combinadas usuario+estado: " + combinada.size());
    }
}
