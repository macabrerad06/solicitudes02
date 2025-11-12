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
    @Override
    public List<PermisoAplicacion> buscarPorDescripcion(String descripcion) {
        return permisoDao.buscarPorDescripcion(descripcion);
    }

    @Override
    public List<PermisoAplicacion> buscarPorNombreODescripcion(String texto) {
        return permisoDao.buscarPorNombreODescripcion(texto);
    }

    @Override
    public List<PermisoAplicacion> buscarPorAplicacionYNombre(Long idAplicacion, String nombre) {
        return permisoDao.buscarPorAplicacionYNombre(idAplicacion, nombre);
    }

    @Override
    public List<PermisoAplicacion> buscarPorAplicacionYDescripcion(Long idAplicacion, String descripcion) {
        return permisoDao.buscarPorAplicacionYDescripcion(idAplicacion, descripcion);
    }

    @Override
    public List<PermisoAplicacion> buscarPorAplicacionNombreYDescripcion(Long idAplicacion, String nombre, String descripcion) {
        return permisoDao.buscarPorAplicacionNombreYDescripcion(idAplicacion, nombre, descripcion);
    }

}
