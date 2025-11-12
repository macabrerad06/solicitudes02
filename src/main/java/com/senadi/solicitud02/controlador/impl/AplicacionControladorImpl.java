package com.senadi.solicitud02.controlador.impl;

import java.util.List;
import com.senadi.solicitud02.controlador.AplicacionControlador;
import com.senadi.solicitud02.modelo.dao.AplicacionDao;
import com.senadi.solicitud02.modelo.dao.impl.AplicacionDaoImpl;
import com.senadi.solicitud02.modelo.entidades.Aplicacion;

public class AplicacionControladorImpl implements AplicacionControlador {

    private final AplicacionDao aplicacionDao = new AplicacionDaoImpl();

    @Override public void crear(Aplicacion a) { aplicacionDao.crear(a); }
    @Override public Aplicacion actualizar(Aplicacion a) { return aplicacionDao.actualizar(a); }
    @Override public void eliminar(Long id) { aplicacionDao.eliminar(id); }
    @Override public Aplicacion buscarPorId(Long id) { return aplicacionDao.buscarPorId(id); }
    @Override public Aplicacion buscarPorNombre(String nombre) { return aplicacionDao.buscarPorNombre(nombre); }
    @Override public List<Aplicacion> listarTodos() { return aplicacionDao.listarTodos(); }
}
