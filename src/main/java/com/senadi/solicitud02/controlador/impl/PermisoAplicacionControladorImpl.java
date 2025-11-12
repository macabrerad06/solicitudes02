package com.senadi.solicitud02.controlador.impl;

import java.util.List;
import com.senadi.solicitud02.controlador.PermisoAplicacionControlador;
import com.senadi.solicitud02.modelo.dao.PermisoAplicacionDao;
import com.senadi.solicitud02.modelo.dao.impl.PermisoAplicacionDaoImpl;
import com.senadi.solicitud02.modelo.entidades.PermisoAplicacion;

public class PermisoAplicacionControladorImpl implements PermisoAplicacionControlador {

    private final PermisoAplicacionDao permisoDao = new PermisoAplicacionDaoImpl();

    @Override public void crear(PermisoAplicacion p) { permisoDao.crear(p); }
    @Override public PermisoAplicacion actualizar(PermisoAplicacion p) { return permisoDao.actualizar(p); }
    @Override public void eliminar(Long id) { permisoDao.eliminar(id); }
    @Override public PermisoAplicacion buscarPorId(Long id) { return permisoDao.buscarPorId(id); }
    @Override public List<PermisoAplicacion> listarTodos() { return permisoDao.listarTodos(); }
    @Override public List<PermisoAplicacion> buscarPorAplicacion(Long idAplicacion) { return permisoDao.buscarPorAplicacion(idAplicacion); }
    @Override public List<PermisoAplicacion> buscarPorNombre(String nombre) { return permisoDao.buscarPorNombre(nombre); }
}
