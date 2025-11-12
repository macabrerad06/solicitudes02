package solicitud02;

import static org.junit.Assert.*;
import org.junit.Test;

import com.senadi.solicitud02.controlador.UsuarioRolControlador;
import com.senadi.solicitud02.controlador.UsuarioControlador;
import com.senadi.solicitud02.controlador.RolControlador;
import com.senadi.solicitud02.controlador.impl.UsuarioRolControladorImpl;
import com.senadi.solicitud02.controlador.impl.UsuarioControladorImpl;
import com.senadi.solicitud02.controlador.impl.RolControladorImpl;
import com.senadi.solicitud02.modelo.entidades.UsuarioRol;
import com.senadi.solicitud02.modelo.entidades.Usuario;
import com.senadi.solicitud02.modelo.entidades.Rol;

import java.util.List;

public class TestUsuarioRol {

    private final UsuarioRolControlador usuarioRolCtrl = new UsuarioRolControladorImpl();
    private final UsuarioControlador usuarioCtrl = new UsuarioControladorImpl();
    private final RolControlador rolCtrl = new RolControladorImpl();

    @Test
    public void testCrearUsuarioRol() {
        // 1️⃣ Asegurar que usuario exista
        Usuario usuarioTmp = usuarioCtrl.buscarPorCorreo("juan.perez@example.com");
        final Usuario usuario;
        if (usuarioTmp == null) {
            usuario = new Usuario();
            usuario.setNombre("Juan");
            usuario.setApellido("Pérez");
            usuario.setCorreo("juan.perez@example.com");
            usuario.setCargo("Analista");
            usuarioCtrl.crear(usuario);
        } else {
            usuario = usuarioTmp;
        }

        // 2️⃣ Asegurar que rol exista
        Rol rolTmp = rolCtrl.buscarPorNombre("Administrador");
        final Rol rol;
        if (rolTmp == null) {
            rol = new Rol();
            rol.setNombre("Administrador");
            rol.setDescripcion("Rol de prueba para asignaciones");
            rolCtrl.crear(rol);
        } else {
            rol = rolTmp;
        }

        // 3️⃣ Verificar si la relación ya existe
        List<UsuarioRol> existentes = usuarioRolCtrl.buscarPorUsuario(usuario.getId());
        boolean relacionExiste = existentes.stream()
            .anyMatch(ur -> ur.getRol().getId().equals(rol.getId()));

        if (!relacionExiste) {
            UsuarioRol ur = new UsuarioRol();
            ur.setUsuario(usuario);
            ur.setRol(rol);
            usuarioRolCtrl.crear(ur);
            assertNotNull("La relación usuario-rol debe tener un ID asignado", ur.getId());
            System.out.println("✅ UsuarioRol creado: " + usuario.getNombre() + " → " + rol.getNombre());
        } else {
            System.out.println("⚠️ La relación usuario-rol ya existe, no se crea duplicado.");
        }
    }

    @Test
    public void testActualizarUsuarioRol() {
        List<UsuarioRol> lista = usuarioRolCtrl.listarTodos();
        assertTrue("Debe existir al menos una relación usuario-rol", !lista.isEmpty());

        UsuarioRol ur = lista.get(0);
        final Rol rolNuevo = rolCtrl.buscarPorNombre("Administrador");

        ur.setRol(rolNuevo);
        UsuarioRol actualizado = usuarioRolCtrl.actualizar(ur);

        assertEquals("El rol debe haberse actualizado correctamente",
                     rolNuevo.getNombre(),
                     actualizado.getRol().getNombre());

        System.out.println("✅ UsuarioRol actualizado: " + actualizado.getUsuario().getNombre() +
                           " → " + actualizado.getRol().getNombre());
    }

    @Test
    public void testListarUsuarioRol() {
        List<UsuarioRol> lista = usuarioRolCtrl.listarTodos();

        if (lista.isEmpty()) {
            System.out.println("⚠️ No existen relaciones, creando una por defecto...");

            Usuario usuarioTmp = usuarioCtrl.buscarPorCorreo("juan.perez@example.com");
            final Usuario usuario;
            if (usuarioTmp == null) {
                usuario = new Usuario();
                usuario.setNombre("Juan");
                usuario.setApellido("Pérez");
                usuario.setCorreo("juan.perez@example.com");
                usuario.setCargo("Analista");
                usuarioCtrl.crear(usuario);
            } else {
                usuario = usuarioTmp;
            }

            Rol rolTmp = rolCtrl.buscarPorNombre("Administrador");
            final Rol rol;
            if (rolTmp == null) {
                rol = new Rol();
                rol.setNombre("Administrador");
                rol.setDescripcion("Rol de prueba para asignaciones");
                rolCtrl.crear(rol);
            } else {
                rol = rolTmp;
            }

            List<UsuarioRol> existentes = usuarioRolCtrl.buscarPorUsuario(usuario.getId());
            boolean relacionExiste = existentes.stream()
                .anyMatch(ur -> ur.getRol().getId().equals(rol.getId()));

            if (!relacionExiste) {
                UsuarioRol ur = new UsuarioRol();
                ur.setUsuario(usuario);
                ur.setRol(rol);
                usuarioRolCtrl.crear(ur);
                assertNotNull("La relación usuario-rol debe tener un ID asignado", ur.getId());
            }

            lista = usuarioRolCtrl.listarTodos();
        }

        assertTrue("Debe existir al menos una relación usuario-rol después de crear", !lista.isEmpty());
        System.out.println("📋 Total relaciones usuario-rol: " + lista.size());
    }

    // ----------------- TEST BÚSQUEDA AVANZADA -----------------
    @Test
    public void testBusquedaAvanzadaUsuarioRol() {
        List<UsuarioRol> todos = usuarioRolCtrl.listarTodos();
        assertTrue("Debe existir al menos una relación usuario-rol para probar búsqueda", !todos.isEmpty());

        final Usuario u = todos.get(0).getUsuario();
        List<UsuarioRol> porUsuario = usuarioRolCtrl.buscarPorUsuario(u.getId());
        assertTrue("Debe encontrar al menos 1 relación para el usuario", porUsuario.size() >= 1);
        System.out.println("🔎 Relaciones encontradas por usuario " + u.getNombre() + ": " + porUsuario.size());

        final Rol r = todos.get(0).getRol();
        List<UsuarioRol> porRol = usuarioRolCtrl.buscarPorRol(r.getId());
        assertTrue("Debe encontrar al menos 1 relación para el rol", porRol.size() >= 1);
        System.out.println("🔎 Relaciones encontradas por rol " + r.getNombre() + ": " + porRol.size());

        List<UsuarioRol> combinada = todos.stream()
            .filter(ur -> ur.getUsuario().getId().equals(u.getId()) && ur.getRol().getId().equals(r.getId()))
            .toList();
        assertTrue("Debe encontrar al menos 1 relación que coincida con usuario y rol", combinada.size() >= 1);
        System.out.println("🔎 Relaciones encontradas combinadas usuario+rol: " + combinada.size());
    }

    /* @Test
    public void testEliminarUsuarioRol() {
        List<UsuarioRol> lista = usuarioRolCtrl.listarTodos();
        if (!lista.isEmpty()) {
            UsuarioRol ur = lista.get(0);
            usuarioRolCtrl.eliminar(ur.getId());
            UsuarioRol eliminado = usuarioRolCtrl.buscarPorId(ur.getId());
            assertNull("La relación usuario-rol debe eliminarse correctamente", eliminado);
            System.out.println("🗑️ UsuarioRol eliminado correctamente");
        }
    } */
}
