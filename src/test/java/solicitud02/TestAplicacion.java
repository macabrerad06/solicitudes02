package solicitud02;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senadi.solicitud02.controlador.AplicacionControlador;
import com.senadi.solicitud02.controlador.impl.AplicacionControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Aplicacion;

import java.util.List;

public class TestAplicacion {

    private static final AplicacionControlador appCtrl = new AplicacionControladorImpl();
    private static Aplicacion appBase;

    @BeforeClass
    public static void inicializarDatos() {
        appBase = appCtrl.buscarPorNombre("SistemaInventario");
        if (appBase == null) {
            appBase = new Aplicacion();
            appBase.setNombre("SistemaInventario");
            appBase.setDescripcion("Aplicación para gestión de inventarios");
            appCtrl.crear(appBase);
            System.out.println("🆕 Aplicación base creada: " + appBase.getNombre());
        } else {
            System.out.println("ℹ️ Aplicación base encontrada: " + appBase.getNombre());
        }
    }

    @Test
    public void testCrearAplicacion() {
        Aplicacion nueva = new Aplicacion();
        nueva.setNombre("SistemaRRHH");
        nueva.setDescripcion("Aplicación para gestión de recursos humanos");

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

    @Test
    public void testBuscarPorNombre() {
        // Buscar la aplicación base
        Aplicacion encontrada = appCtrl.buscarPorNombre("SistemaInventario");
        assertNotNull("Debe encontrarse la aplicación por nombre", encontrada);
        System.out.println("🔍 Aplicación encontrada por nombre: " + encontrada.getNombre());
    }

    @Test
    public void testEliminarAplicacion() {
        // Crear temporal para eliminar
        Aplicacion temp = new Aplicacion();
        temp.setNombre("TempApp");
        temp.setDescripcion("Aplicación temporal para eliminar");
        appCtrl.crear(temp);

        appCtrl.eliminar(temp.getId());
        Aplicacion eliminado = appCtrl.buscarPorNombre("TempApp");
        assertNull("La aplicación temporal debe eliminarse correctamente", eliminado);
        System.out.println("🗑️ Aplicación eliminada correctamente");
    }
}
