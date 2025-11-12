package com.senadi.solicitud02.controlador.impl;

import java.time.LocalDateTime;
import java.util.List;
import com.senadi.solicitud02.controlador.AccesoUsuarioControlador;
import com.senadi.solicitud02.modelo.dao.AccesoUsuarioDao;
import com.senadi.solicitud02.modelo.dao.impl.AccesoUsuarioDaoImpl;
import com.senadi.solicitud02.modelo.entidades.AccesoUsuario;

public class AccesoUsuarioControladorImpl implements AccesoUsuarioControlador {

    private final AccesoUsuarioDao accesoDao = new AccesoUsuarioDaoImpl();

    @Override public void crear(AccesoUsuario a) { accesoDao.crear(a); }
    @Override public AccesoUsuario actualizar(AccesoUsuario a) { return accesoDao.actualizar(a); }
    @Override public void eliminar(Long id) { accesoDao.eliminar(id); }
    @Override public AccesoUsuario buscarPorId(Long id) { return accesoDao.buscarPorId(id); }
    @Override public List<AccesoUsuario> listarTodos() { return accesoDao.listarTodos(); }
    @Override public List<AccesoUsuario> buscarPorSolicitud(Long idSolicitud) { return accesoDao.buscarPorSolicitud(idSolicitud); }
    @Override public List<AccesoUsuario> buscarPorPermiso(Long idPermiso) { return accesoDao.buscarPorPermiso(idPermiso); }
    @Override
    public List<AccesoUsuario> buscarPorRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
        return accesoDao.buscarPorRangoFechas(desde, hasta);
    }

    @Override
    public List<AccesoUsuario> buscarPorSolicitudYPermiso(Long idSolicitud, Long idPermiso) {
        return accesoDao.buscarPorSolicitudYPermiso(idSolicitud, idPermiso);
    }

    @Override
    public List<AccesoUsuario> buscarPorSolicitudYFechas(Long idSolicitud, LocalDateTime desde, LocalDateTime hasta) {
        return accesoDao.buscarPorSolicitudYFechas(idSolicitud, desde, hasta);
    }

    @Override
    public List<AccesoUsuario> buscarPorPermisoYFechas(Long idPermiso, LocalDateTime desde, LocalDateTime hasta) {
        return accesoDao.buscarPorPermisoYFechas(idPermiso, desde, hasta);
    }

    @Override
    public List<AccesoUsuario> buscarPorSolicitudPermisoYFechas(Long idSolicitud, Long idPermiso, LocalDateTime desde, LocalDateTime hasta) {
        return accesoDao.buscarPorSolicitudPermisoYFechas(idSolicitud, idPermiso, desde, hasta);
    }

}
