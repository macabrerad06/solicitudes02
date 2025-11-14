package solicitud02;

import static org.junit.Assert.*;
import org.junit.Test;

import com.senadi.solicitud02.controlador.UsuarioControlador;
import com.senadi.solicitud02.controlador.impl.UsuarioControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Usuario;


public class TestUsuario {

    private final UsuarioControlador usuarioCtrl = new UsuarioControladorImpl();

    @Test
    public void testCrearUsuario() {
        Usuario nuevo = new Usuario();
        nuevo.setNombre("Juan");
        nuevo.setApellido("Pérez");
        nuevo.setCorreo("jperez@senadi.com");
        nuevo.setCargo("Analista");
        nuevo.setPassword("admin");

        usuarioCtrl.crear(nuevo);

        assertNotNull("El usuario debería haberse creado con ID", nuevo.getId());
        System.out.println("✅ Usuario creado con ID: " + nuevo.getId());
    }
    /*
    @Test
    public void testActualizarUsuario() {
        Usuario u = usuarioCtrl.buscarPorCorreo("juan.perez@example.com");
        assertNotNull("Debe existir el usuario para actualizar", u);

        u.setCargo("Supervisor");
        Usuario actualizado = usuarioCtrl.actualizar(u);

        assertEquals("El cargo debe haberse actualizado", "Supervisor", actualizado.getCargo());
        System.out.println("✅ Usuario actualizado: " + actualizado.getNombre() + " - Cargo: " + actualizado.getCargo());
    }

    @Test
    public void testListarUsuarios() {
        List<Usuario> lista = usuarioCtrl.listarTodos();
        assertTrue("Debe existir al menos un usuario", !lista.isEmpty());
        System.out.println("📋 Total usuarios: " + lista.size());
    }

    @Test
    public void testBuscarPorId() {
        List<Usuario> lista = usuarioCtrl.listarTodos();
        assertTrue(!lista.isEmpty());
        Long id = lista.get(0).getId();

        Usuario u = usuarioCtrl.buscarPorId(id);
        assertNotNull("Debe encontrarse el usuario por ID", u);
        System.out.println("🔍 Usuario encontrado: " + u.getNombre());
    }

    @Test
    public void testEliminarUsuario() {
        Usuario u = usuarioCtrl.buscarPorCorreo("juan.perez@example.com");
        if (u != null) {
            usuarioCtrl.eliminar(u.getId());
            Usuario eliminado = usuarioCtrl.buscarPorId(u.getId());
            assertNull("El usuario debería eliminarse", eliminado);
            System.out.println("🗑️ Usuario eliminado correctamente");
        }
    }

    // ----------------- NUEVO TEST BÚSQUEDA AVANZADA -----------------
    @Test
    public void testBusquedaAvanzadaUsuario() {
        // Preparar datos
        Usuario u1 = new Usuario();
        u1.setNombre("Carlos");
        u1.setApellido("Gómez");
        u1.setCorreo("carlos.gomez@example.com");
        u1.setCargo("Analista");
        usuarioCtrl.crear(u1);

        Usuario u2 = new Usuario();
        u2.setNombre("Carla");
        u2.setApellido("Lopez");
        u2.setCorreo("carla.lopez@example.com");
        u2.setCargo("Supervisor");
        usuarioCtrl.crear(u2);

        // Simular búsqueda avanzada usando listarTodos + filtro Java
        String textoBusqueda = "Car";
        List<Usuario> todos = usuarioCtrl.listarTodos();
        List<Usuario> resultados = todos.stream()
            .filter(u -> u.getNombre().contains(textoBusqueda) || u.getApellido().contains(textoBusqueda))
            .toList();

        assertTrue("Debe encontrar al menos 2 usuarios que contienen 'Car'", resultados.size() >= 2);
        System.out.println("🔎 Resultados búsqueda simulada por '" + textoBusqueda + "': " + resultados.size());

        // Limpiar datos de prueba
        usuarioCtrl.eliminar(u1.getId());
        usuarioCtrl.eliminar(u2.getId());
    }
*/
}
