package solicitud02;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import com.senadi.solicitud02.controlador.FirmaControlador;
import com.senadi.solicitud02.controlador.SolicitudControlador;
import com.senadi.solicitud02.controlador.UsuarioControlador;
import com.senadi.solicitud02.controlador.impl.FirmaControladorImpl;
import com.senadi.solicitud02.controlador.impl.SolicitudControladorImpl;
import com.senadi.solicitud02.controlador.impl.UsuarioControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Firma;
import com.senadi.solicitud02.modelo.entidades.Solicitud;
import com.senadi.solicitud02.modelo.entidades.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class TestFirma {

    private static final FirmaControlador firmaCtrl = new FirmaControladorImpl();
    private static final SolicitudControlador solicitudCtrl = new SolicitudControladorImpl();
    private static final UsuarioControlador usuarioCtrl = new UsuarioControladorImpl();

    private static Usuario usuarioBase;
    private static Solicitud solicitudBase;

    @BeforeClass
    public static void inicializarDatos() {
        // Crear usuario base
        usuarioBase = usuarioCtrl.buscarPorCorreo("firma.user@example.com");
        if (usuarioBase == null) {
            usuarioBase = new Usuario();
            usuarioBase.setNombre("Luis");
            usuarioBase.setApellido("Mendoza");
            usuarioBase.setCorreo("firma.user@example.com");
            usuarioBase.setCargo("Supervisor");
            usuarioCtrl.crear(usuarioBase);
        }

        // Crear solicitud base
        List<Solicitud> solicitudes = solicitudCtrl.listarTodos();
        if (solicitudes.isEmpty()) {
            solicitudBase = new Solicitud();
            solicitudBase.setEstado("CREADA");
            solicitudBase.setUsuario(usuarioBase);
            solicitudCtrl.crear(solicitudBase);
        } else {
            solicitudBase = solicitudes.get(0);
        }
    }

    @Test
    public void testCrearFirma() {
        Firma f = new Firma();
        f.setDescripcion("Firma prueba " + LocalDateTime.now());
        f.setSolicitud(solicitudBase);

        firmaCtrl.crear(f);
        assertNotNull("La firma debe tener un ID asignado", f.getId());
        System.out.println("✅ Firma creada con ID: " + f.getId());
    }

    @Test
    public void testListarFirmas() {
        List<Firma> lista = firmaCtrl.listarTodos();
        if (lista.isEmpty()) {
            testCrearFirma();
            lista = firmaCtrl.listarTodos();
        }
        assertFalse("Debe existir al menos una firma", lista.isEmpty());
        System.out.println("📋 Total firmas: " + lista.size());
    }

    @Test
    public void testActualizarFirma() {
        List<Firma> lista = firmaCtrl.listarTodos();
        if (lista.isEmpty()) {
            testCrearFirma();
            lista = firmaCtrl.listarTodos();
        }

        Firma f = lista.get(0);
        f.setDescripcion("Firma actualizada " + LocalDateTime.now());
        Firma actualizada = firmaCtrl.actualizar(f);

        assertEquals(f.getDescripcion(), actualizada.getDescripcion());
        System.out.println("✅ Firma actualizada: " + actualizada.getDescripcion());
    }

    @Test
    public void testEliminarFirma() {
        List<Firma> lista = firmaCtrl.listarTodos();
        if (!lista.isEmpty()) {
            Firma f = lista.get(0);
            firmaCtrl.eliminar(f.getId());
            Firma eliminado = firmaCtrl.buscarPorId(f.getId());
            assertNull("La firma debería eliminarse", eliminado);
            System.out.println("🗑️ Firma eliminada correctamente");
        }
    }

    @Test
    public void testBuscarPorDescripcion() {
        Firma f = new Firma();
        f.setDescripcion("Busqueda Desc");
        f.setSolicitud(solicitudBase);
        firmaCtrl.crear(f);

        List<Firma> resultado = firmaCtrl.buscarPorDescripcion("Busqueda Desc");
        assertFalse("Debe encontrar al menos una firma por descripción", resultado.isEmpty());
        System.out.println("🔍 Firmas encontradas por descripción: " + resultado.size());
    }

    @Test
    public void testBuscarPorFecha() {
        LocalDateTime ahora = LocalDateTime.now();
        Firma f = new Firma();
        f.setDescripcion("Busqueda Fecha");
        f.setSolicitud(solicitudBase);
        f.setFechaFirma(ahora);
        firmaCtrl.crear(f);

        List<Firma> resultado = firmaCtrl.buscarPorFecha(ahora.minusMinutes(1), ahora.plusMinutes(1));
        assertFalse("Debe encontrar al menos una firma por fecha", resultado.isEmpty());
        System.out.println("🔍 Firmas encontradas por fecha: " + resultado.size());
    }

    @Test
    public void testBuscarPorSolicitudYFecha() {
        LocalDateTime ahora = LocalDateTime.now();
        Firma f = new Firma();
        f.setDescripcion("Busqueda SolFecha");
        f.setSolicitud(solicitudBase);
        f.setFechaFirma(ahora);
        firmaCtrl.crear(f);

        List<Firma> resultado = firmaCtrl.buscarPorSolicitudYFecha(
            solicitudBase.getId(), ahora.minusMinutes(1), ahora.plusMinutes(1)
        );
        assertFalse("Debe encontrar al menos una firma por solicitud y fecha", resultado.isEmpty());
        System.out.println("🔍 Firmas encontradas por solicitud y fecha: " + resultado.size());
    }
}
