package solicitud02;

import static org.junit.Assert.*;
import org.junit.Test;

import com.senadi.solicitud02.controlador.AplicacionControlador;
import com.senadi.solicitud02.controlador.impl.AplicacionControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Aplicacion;

import java.util.List;

public class TestAplicacion {

    private final AplicacionControlador appCtrl = new AplicacionControladorImpl();

    @Test
    public void testCrearAplicacion() {
        Aplicacion nueva = new Aplicacion();
        nueva.setNombre("SistemaInventario");
        nueva.setDescripcion("Aplicación para gestión de inventarios");

        appCtrl.crear(nueva);

        assertNotNull("La aplicación debe tener un ID asignado", nueva.getId());
        System.out.println("✅ Aplicación creada con ID: " + nueva.getId());
    }

    @Test
    public void testActualizarAplicacion() {
        Aplicacion a = appCtrl.buscarPorNombre("SistemaInventario");
        assertNotNull("Debe existir la aplicación para actualizar", a);

        a.setDescripcion("Sistema de inventario con control de usuarios");
        Aplicacion actualizada = appCtrl.actualizar(a);

        assertEquals("La descripción debe haberse actualizado",
                     "Sistema de inventario con control de usuarios",
                     actualizada.getDescripcion());

        System.out.println("✅ Aplicación actualizada: " + actualizada.getNombre());
    }

    @Test
    public void testListarAplicaciones() {
        List<Aplicacion> lista = appCtrl.listarTodos();
        assertTrue("Debe existir al menos una aplicación", !lista.isEmpty());
        System.out.println("📋 Total de aplicaciones: " + lista.size());
    }

    /* @Test
    public void testBuscarPorId() {
        List<Aplicacion> lista = appCtrl.listarTodos();
        assertTrue(!lista.isEmpty());

        Aplicacion primera = lista.get(0);
        Aplicacion encontrada = appCtrl.buscarPorId(primera.getId());

        assertNotNull("La aplicación debe encontrarse", encontrada);
        System.out.println("🔍 Aplicación encontrada: " + encontrada.getNombre());
    } */

    /* @Test
    public void testEliminarAplicacion() {
        Aplicacion a = appCtrl.buscarPorNombre("SistemaInventario");
        if (a != null) {
            appCtrl.eliminar(a.getId());
            Aplicacion eliminada = appCtrl.buscarPorId(a.getId());
            assertNull("La aplicación debe eliminarse correctamente", eliminada);
            System.out.println("🗑️ Aplicación eliminada correctamente");
        }
    } */
}
