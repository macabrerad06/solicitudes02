package com.senadi.solicitud02.controlador.impl;

import java.util.List;

import com.senadi.solicitud02.controlador.UsuarioControlador;
import com.senadi.solicitud02.modelo.dao.UsuarioDao;
import com.senadi.solicitud02.modelo.dao.impl.UsuarioDaoImpl;
import com.senadi.solicitud02.modelo.entidades.Usuario;

public class UsuarioControladorImpl implements UsuarioControlador {

    private final UsuarioDao usuarioDao = new UsuarioDaoImpl();

    @Override public void crear(Usuario u) { usuarioDao.crear(u); }
    @Override public Usuario actualizar(Usuario u) { return usuarioDao.actualizar(u); }
    @Override public void eliminar(Long id) { usuarioDao.eliminar(id); }
    @Override public Usuario buscarPorId(Long id) { return usuarioDao.buscarPorId(id); }
    @Override public Usuario buscarPorCorreo(String correo) { return usuarioDao.buscarPorCorreo(correo); }
    @Override public List<Usuario> listarTodos() { return usuarioDao.listarTodos(); }
    @Override
    public List<Usuario> buscarPorNombre(String nombre) {
        return usuarioDao.buscarPorNombre(nombre);
    }

    @Override
    public List<Usuario> buscarPorApellido(String apellido) {
        return usuarioDao.buscarPorApellido(apellido);
    }

    @Override
    public List<Usuario> buscarPorCargo(String cargo) {
        return usuarioDao.buscarPorCargo(cargo);
    }

    @Override
    public List<Usuario> buscarPorNombreYApellido(String nombre, String apellido) {
        return usuarioDao.buscarPorNombreYApellido(nombre, apellido);
    }

    @Override
    public List<Usuario> buscarPorNombreOCorreo(String texto) {
        return usuarioDao.buscarPorNombreOCorreo(texto);
    }

}
