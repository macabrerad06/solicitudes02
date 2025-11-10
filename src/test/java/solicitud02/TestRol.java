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
        Rol nuevo = new Rol();
        nuevo.setNombre("Administrador");
        nuevo.setDescripcion("Rol con permisos completos del sistema");

        rolCtrl.crear(nuevo);

        assertNotNull("El rol debe tener un ID asignado", nuevo.getId());
        System.out.println("✅ Rol creado con ID: " + nuevo.getId());
    }

    @Test
    public void testActualizarRol() {
        Rol r = rolCtrl.buscarPorNombre("Administrador");
        assertNotNull("Debe existir el rol para actualizar", r);

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

    /* @Test
    public void testBuscarPorId() {
        List<Rol> lista = rolCtrl.listarTodos();
        assertTrue(!lista.isEmpty());

        Rol primero = lista.get(0);
        Rol encontrado = rolCtrl.buscarPorId(primero.getId());

        assertNotNull("El rol debe encontrarse", encontrado);
        System.out.println("🔍 Rol encontrado: " + encontrado.getNombre());}
    }*/

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
