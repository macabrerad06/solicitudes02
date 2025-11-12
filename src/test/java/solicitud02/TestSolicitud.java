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
        usuarioBase = usuarioCtrl.buscarPorCorreo("solicitud.user@example.com");
        if (usuarioBase == null) {
            usuarioBase = new Usuario();
            usuarioBase.setNombre("Carlos");
            usuarioBase.setApellido("Suárez");
            usuarioBase.setCorreo("solicitud.user@example.com");
            usuarioBase.setCargo("Analista");
            usuarioCtrl.crear(usuarioBase);
            System.out.println("🆕 Usuario base creado para solicitudes");
        } else {
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
            // Si no hay, crear una antes de continuar
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
}
