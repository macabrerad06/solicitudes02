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
        // 1️⃣ Buscar o crear usuario
        Usuario usuario = usuarioCtrl.buscarPorCorreo("juan.perez@example.com");
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setNombre("Juan");
            usuario.setApellido("Pérez");
            usuario.setCorreo("juan.perez@example.com");
            usuario.setCargo("Analista");
            usuarioCtrl.crear(usuario);
        }

        // 2️⃣ Buscar o crear rol
        Rol rol = rolCtrl.buscarPorNombre("Administrador");
        if (rol == null) {
            rol = new Rol();
            rol.setNombre("Administrador");
            rol.setDescripcion("Rol de prueba para asignaciones");
            rolCtrl.crear(rol);
        }

        // 3️⃣ Crear relación usuario-rol
        UsuarioRol ur = new UsuarioRol();
        ur.setUsuario(usuario);
        ur.setRol(rol);

        usuarioRolCtrl.crear(ur);
        assertNotNull("La relación usuario-rol debe tener un ID asignado", ur.getId());
        System.out.println("✅ UsuarioRol creado: " + usuario.getNombre() + " → " + rol.getNombre());
    }

    @Test
    public void testActualizarUsuarioRol() {
        List<UsuarioRol> lista = usuarioRolCtrl.listarTodos();
        assertTrue("Debe existir al menos una relación usuario-rol", !lista.isEmpty());

        UsuarioRol ur = lista.get(0);
        Rol rolNuevo = rolCtrl.buscarPorNombre("Administrador");

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

        // Si no hay relaciones, crear una por defecto
        if (lista.isEmpty()) {
            System.out.println("⚠️ No existen relaciones, creando una por defecto...");

            Usuario usuario = usuarioCtrl.buscarPorCorreo("juan.perez@example.com");
            Rol rol = rolCtrl.buscarPorNombre("Administrador");

            if (usuario != null && rol != null) {
                UsuarioRol ur = new UsuarioRol();
                ur.setUsuario(usuario);
                ur.setRol(rol);
                usuarioRolCtrl.crear(ur);
                lista = usuarioRolCtrl.listarTodos();
            }
        }

        assertTrue("Debe existir al menos una relación usuario-rol después de crear", !lista.isEmpty());
        System.out.println("📋 Total relaciones usuario-rol: " + lista.size());
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
