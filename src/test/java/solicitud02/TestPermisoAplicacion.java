package solicitud02;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senadi.solicitud02.controlador.AplicacionControlador;
import com.senadi.solicitud02.controlador.PermisoAplicacionControlador;
import com.senadi.solicitud02.controlador.impl.AplicacionControladorImpl;
import com.senadi.solicitud02.controlador.impl.PermisoAplicacionControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Aplicacion;
import com.senadi.solicitud02.modelo.entidades.PermisoAplicacion;

import java.util.List;

public class TestPermisoAplicacion {

    private static final AplicacionControlador appCtrl = new AplicacionControladorImpl();
    private static final PermisoAplicacionControlador permisoCtrl = new PermisoAplicacionControladorImpl();

    private static Aplicacion appBase;

    @BeforeClass
    public static void inicializarDatos() {
        // Verifica si ya existe la aplicación base, si no la crea
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
    public void testCrearPermiso() {
        PermisoAplicacion nuevo = new PermisoAplicacion();
        nuevo.setNombre("CREAR_PRODUCTO");
        nuevo.setDescripcion("Permite registrar productos en el inventario");
        nuevo.setAplicacion(appBase);

        permisoCtrl.crear(nuevo);

        assertNotNull("El permiso debe tener un ID asignado", nuevo.getId());
        System.out.println("✅ Permiso creado con ID: " + nuevo.getId());
    }

    @Test
    public void testActualizarPermiso() {
        List<PermisoAplicacion> permisos = permisoCtrl.listarTodos();
        assertFalse("Debe existir al menos un permiso", permisos.isEmpty());

        PermisoAplicacion permiso = permisos.get(0);
        permiso.setDescripcion("Permite crear y editar productos en inventario");
        PermisoAplicacion actualizado = permisoCtrl.actualizar(permiso);

        assertEquals("La descripción debe actualizarse correctamente",
                "Permite crear y editar productos en inventario",
                actualizado.getDescripcion());
        System.out.println("✅ Permiso actualizado: " + actualizado.getNombre());
    }

    @Test
    public void testListarPermisos() {
        List<PermisoAplicacion> lista = permisoCtrl.listarTodos();
        assertTrue("Debe existir al menos un permiso", !lista.isEmpty());
        System.out.println("📋 Total de permisos: " + lista.size());
    }

    @Test
    public void testBuscarPorAplicacion() {
        assertNotNull("Debe existir la aplicación base", appBase);

        List<PermisoAplicacion> permisos = permisoCtrl.buscarPorAplicacion(appBase.getId());
        assertNotNull(permisos);
        System.out.println("🔍 Permisos encontrados para " + appBase.getNombre() + ": " + permisos.size());
    }

    @Test
    public void testEliminarPermiso() {
        List<PermisoAplicacion> lista = permisoCtrl.listarTodos();
        if (!lista.isEmpty()) {
            PermisoAplicacion permiso = lista.get(0);
            permisoCtrl.eliminar(permiso.getId());
            PermisoAplicacion eliminado = permisoCtrl.buscarPorId(permiso.getId());
            assertNull("El permiso debería eliminarse correctamente", eliminado);
            System.out.println("🗑️ Permiso eliminado: " + permiso.getNombre());
        } else {
            System.out.println("⚠️ No hay permisos para eliminar.");
        }
    }
}
