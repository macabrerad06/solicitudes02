package com.senadi.solicitud02.controlador.impl;

import java.util.List;
import com.senadi.solicitud02.controlador.UsuarioRolControlador;
import com.senadi.solicitud02.modelo.dao.UsuarioRolDao;
import com.senadi.solicitud02.modelo.dao.impl.UsuarioRolDaoImpl;
import com.senadi.solicitud02.modelo.entidades.UsuarioRol;

public class UsuarioRolControladorImpl implements UsuarioRolControlador {

    private final UsuarioRolDao usuarioRolDao = new UsuarioRolDaoImpl();

    @Override public void crear(UsuarioRol ur) { usuarioRolDao.crear(ur); }
    @Override public UsuarioRol actualizar(UsuarioRol ur) { return usuarioRolDao.actualizar(ur); }
    @Override public void eliminar(Long id) { usuarioRolDao.eliminar(id); }
    @Override public UsuarioRol buscarPorId(Long id) { return usuarioRolDao.buscarPorId(id); }
    @Override public List<UsuarioRol> listarTodos() { return usuarioRolDao.listarTodos(); }
    @Override public List<UsuarioRol> buscarPorUsuario(Long idUsuario) { return usuarioRolDao.buscarPorUsuario(idUsuario); }
    @Override public List<UsuarioRol> buscarPorRol(Long idRol) { return usuarioRolDao.buscarPorRol(idRol); }
}
