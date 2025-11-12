package com.senadi.solicitud02.controlador.impl;

import java.util.List;
import com.senadi.solicitud02.controlador.FirmaControlador;
import com.senadi.solicitud02.modelo.dao.FirmaDao;
import com.senadi.solicitud02.modelo.dao.impl.FirmaDaoImpl;
import com.senadi.solicitud02.modelo.entidades.Firma;

public class FirmaControladorImpl implements FirmaControlador {

    private final FirmaDao firmaDao = new FirmaDaoImpl();

    @Override public void crear(Firma f) { firmaDao.crear(f); }
    @Override public Firma actualizar(Firma f) { return firmaDao.actualizar(f); }
    @Override public void eliminar(Long id) { firmaDao.eliminar(id); }
    @Override public Firma buscarPorId(Long id) { return firmaDao.buscarPorId(id); }
    @Override public Firma buscarPorSolicitud(Long idSolicitud) { return firmaDao.buscarPorSolicitud(idSolicitud); }
    @Override public List<Firma> listarTodos() { return firmaDao.listarTodos(); }
}
