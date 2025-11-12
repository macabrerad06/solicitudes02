package com.senadi.solicitud02.controlador.impl;

import java.util.List;
import com.senadi.solicitud02.controlador.AuditoriaControlador;
import com.senadi.solicitud02.modelo.dao.AuditoriaDao;
import com.senadi.solicitud02.modelo.dao.impl.AuditoriaDaoImpl;
import com.senadi.solicitud02.modelo.entidades.Auditoria;

public class AuditoriaControladorImpl implements AuditoriaControlador {

    private final AuditoriaDao auditoriaDao = new AuditoriaDaoImpl();

    @Override public void crear(Auditoria a) { auditoriaDao.crear(a); }
    @Override public Auditoria actualizar(Auditoria a) { return auditoriaDao.actualizar(a); }
    @Override public void eliminar(Long id) { auditoriaDao.eliminar(id); }
    @Override public Auditoria buscarPorId(Long id) { return auditoriaDao.buscarPorId(id); }
    @Override public List<Auditoria> listarTodos() { return auditoriaDao.listarTodos(); }
    @Override public List<Auditoria> buscarPorUsuario(Long idUsuario) { return auditoriaDao.buscarPorUsuario(idUsuario); }
    @Override public List<Auditoria> buscarPorAccion(String accion) { return auditoriaDao.buscarPorAccion(accion); }
}
