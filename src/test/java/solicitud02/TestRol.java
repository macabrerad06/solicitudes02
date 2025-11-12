package solicitud02;

import static org.junit.Assert.*;
import org.junit.Test;

import com.senadi.solicitud02.controlador.RolControlador;
import com.senadi.solicitud02.controlador.impl.RolControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Rol;

import java.util.List;

public class TestRol {

    private final RolControlador rolCtrl = new RolControladorImpl();

    @Test
    public void testCrearRol() {
        // Verificar si el rol ya existe
        Rol existente = rolCtrl.buscarPorNombre("Administrador");
        final Rol nuevo;
        if (existente == null) {
            nuevo = new Rol();
            nuevo.setNombre("Administrador");
            nuevo.setDescripcion("Rol con permisos completos del sistema");
            rolCtrl.crear(nuevo);
            assertNotNull("El rol debe tener un ID asignado", nuevo.getId());
            System.out.println("✅ Rol creado con ID: " + nuevo.getId());
        } else {
            nuevo = existente;
            System.out.println("⚠️ Rol 'Administrador' ya existe con ID: " + nuevo.getId());
        }
    }

    @Test
    public void testActualizarRol() {
        Rol r = rolCtrl.buscarPorNombre("Administrador");
        assertNotNull("Debe existir el rol para actualizar", r);

        // Actualizar descripción para pruebas de búsqueda
        r.setDescripcion("Permite gestionar usuarios, roles y auditorías");
        Rol actualizado = rolCtrl.actualizar(r);

        assertEquals("La descripción debe haberse actualizado",
                     "Permite gestionar usuarios, roles y auditorías",
                     actualizado.getDescripcion());

        System.out.println("✅ Rol actualizado: " + actualizado.getNombre());
    }

    @Test
    public void testListarRoles() {
        List<Rol> lista = rolCtrl.listarTodos();
        assertTrue("Debe existir al menos un rol", !lista.isEmpty());
        System.out.println("📋 Total roles: " + lista.size());
    }

    // ----------------- TEST BÚSQUEDA AVANZADA -----------------
    @Test
    public void testBusquedaAvanzadaRol() {
        List<Rol> todos = rolCtrl.listarTodos();
        assertTrue("Debe existir al menos un rol para probar búsquedas", !todos.isEmpty());

        // 1️⃣ Buscar por nombre
        final String nombre = "Administrador";
        Rol encontrado = rolCtrl.buscarPorNombre(nombre);
        assertNotNull("Debe encontrar al menos un rol por nombre", encontrado);
        System.out.println("🔎 Rol encontrado por nombre '" + nombre + "': ID=" + encontrado.getId());

        // 2️⃣ Búsqueda combinada robusta (nombre + cualquier descripción no vacía)
        List<Rol> filtrado = todos.stream()
            .filter(r -> r.getNombre().equals(nombre) && r.getDescripcion() != null && !r.getDescripcion().isEmpty())
            .toList();

        assertTrue("Debe encontrar al menos un rol que coincida con nombre y descripción válida", filtrado.size() >= 1);
        System.out.println("🔎 Roles encontrados por nombre + descripción válida: " + filtrado.size());
    }

    /*@Test
    public void testEliminarRol() {
        Rol r = rolCtrl.buscarPorNombre("Administrador");
        if (r != null) {
            rolCtrl.eliminar(r.getId());
            Rol eliminado = rolCtrl.buscarPorId(r.getId());
            assertNull("El rol debe eliminarse correctamente", eliminado);
            System.out.println("🗑️ Rol eliminado correctamente");
        }
    }*/
}
