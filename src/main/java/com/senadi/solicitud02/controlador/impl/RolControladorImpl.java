package com.senadi.solicitud02.controlador.impl;

import java.util.List;

import com.senadi.solicitud02.controlador.RolControlador;
import com.senadi.solicitud02.modelo.dao.RolDao;
import com.senadi.solicitud02.modelo.dao.impl.RolDaoImpl;
import com.senadi.solicitud02.modelo.entidades.Rol;

public class RolControladorImpl implements RolControlador {

    private final RolDao rolDao = new RolDaoImpl();

    @Override public void crear(Rol r) { rolDao.crear(r); }
    @Override public Rol actualizar(Rol r) { return rolDao.actualizar(r); }
    @Override public void eliminar(Long id) { rolDao.eliminar(id); }
    @Override public Rol buscarPorId(Long id) { return rolDao.buscarPorId(id); }
    @Override public Rol buscarPorNombre(String nombre) { return rolDao.buscarPorNombre(nombre); }
    @Override public List<Rol> listarTodos() { return rolDao.listarTodos(); }
}