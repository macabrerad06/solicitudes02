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

import java.util.List;

public class TestFirma {

    private static final FirmaControlador firmaCtrl = new FirmaControladorImpl();
    private static final SolicitudControlador solicitudCtrl = new SolicitudControladorImpl();
    private static final UsuarioControlador usuarioCtrl = new UsuarioControladorImpl();

    private static Usuario usuarioBase;
    private static Solicitud solicitudBase;

    @BeforeClass
    public static void inicializarDatos() {
        // Crear usuario base si no existe
        usuarioBase = usuarioCtrl.buscarPorCorreo("firma.user@example.com");
        if (usuarioBase == null) {
            usuarioBase = new Usuario();
            usuarioBase.setNombre("Luis");
            usuarioBase.setApellido("Mendoza");
            usuarioBase.setCorreo("firma.user@example.com");
            usuarioBase.setCargo("Supervisor");
            usuarioCtrl.crear(usuarioBase);
            System.out.println("🆕 Usuario base creado para firmas");
        } else {
            System.out.println("ℹ️ Usuario base encontrado: " + usuarioBase.getCorreo());
        }

        // Crear o asegurar solicitud base: siempre crear si no hay ninguna
        List<Solicitud> solicitudes = solicitudCtrl.listarTodos();
        if (solicitudes.isEmpty()) {
            solicitudBase = new Solicitud();
            solicitudBase.setEstado("CREADA");
            solicitudBase.setUsuario(usuarioBase);
            solicitudCtrl.crear(solicitudBase);
            System.out.println("🆕 Solicitud base creada para firma (ID: " + solicitudBase.getId() + ")");
        } else {
            // usar la primera solicitud existente
            solicitudBase = solicitudes.get(0);
            System.out.println("ℹ️ Solicitud base reutilizada ID: " + solicitudBase.getId());
        }

        // recargar solicitudBase para obtenerla gestionada y con datos actualizados
        solicitudBase = solicitudCtrl.buscarPorId(solicitudBase.getId());
    }

    @Test
    public void testCrearFirma() {
        try {
            // recargar solicitud para asegurarnos que esté gestionada
            solicitudBase = solicitudCtrl.buscarPorId(solicitudBase.getId());

            // verificar si ya existe una firma para esta solicitud y eliminarla si existe
            List<Firma> todas = firmaCtrl.listarTodos();
            for (Firma f : todas) {
                if (f.getSolicitud() != null && f.getSolicitud().getId().equals(solicitudBase.getId())) {
                    System.out.println("⚠️ Ya existe una firma para la solicitud " + solicitudBase.getId() + ", la eliminaré antes de crear otra.");
                    firmaCtrl.eliminar(f.getId());
                }
            }

            // crear nueva firma
            Firma f = new Firma();
            f.setDescripcion("Firma de autorización inicial (testCrearFirma)");
            f.setSolicitud(solicitudBase);

            firmaCtrl.crear(f);

            assertNotNull("La firma debe tener un ID asignado", f.getId());
            System.out.println("✅ Firma creada con ID: " + f.getId());
        } catch (Throwable t) {
            t.printStackTrace();
            fail("Se produjo una excepción durante testCrearFirma: " + t.getMessage());
        }
    }

    @Test
    public void testListarFirmas() {
        try {
            List<Firma> lista = firmaCtrl.listarTodos();
            if (lista.isEmpty()) {
                // si no hay firmas, crear una mínima
                testCrearFirma();
                lista = firmaCtrl.listarTodos();
            }
            assertTrue("Debe existir al menos una firma", !lista.isEmpty());
            System.out.println("📋 Total firmas: " + lista.size());
        } catch (Throwable t) {
            t.printStackTrace();
            fail("Excepción en testListarFirmas: " + t.getMessage());
        }
    }

    @Test
    public void testActualizarFirma() {
        try {
            List<Firma> firmas = firmaCtrl.listarTodos();
            if (firmas.isEmpty()) {
                testCrearFirma();
                firmas = firmaCtrl.listarTodos();
            }

            Firma f = firmas.get(0);
            f.setDescripcion("Firma actualizada desde test");
            Firma actualizada = firmaCtrl.actualizar(f);

            assertEquals("La descripción debe haberse actualizado",
                         "Firma actualizada desde test", actualizada.getDescripcion());
            System.out.println("✅ Firma actualizada correctamente");
        } catch (Throwable t) {
            t.printStackTrace();
            fail("Excepción en testActualizarFirma: " + t.getMessage());
        }
    }

    @Test
    public void testEliminarFirma() {
        try {
            List<Firma> lista = firmaCtrl.listarTodos();
            if (lista.isEmpty()) {
                testCrearFirma();
                lista = firmaCtrl.listarTodos();
            }

            Firma f = lista.get(0);
            Long id = f.getId();
            firmaCtrl.eliminar(id);
            Firma eliminado = firmaCtrl.buscarPorId(id);

            assertNull("La firma debería eliminarse correctamente", eliminado);
            System.out.println("🗑️ Firma eliminada correctamente");
        } catch (Throwable t) {
            t.printStackTrace();
            fail("Excepción en testEliminarFirma: " + t.getMessage());
        }
    }
}
