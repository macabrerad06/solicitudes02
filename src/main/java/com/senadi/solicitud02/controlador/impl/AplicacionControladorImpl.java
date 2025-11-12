package com.senadi.solicitud02.controlador.impl;

import java.time.LocalDateTime;
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
    @Override
    public List<Aplicacion> buscarPorDescripcion(String descripcion) {
        return aplicacionDao.buscarPorDescripcion(descripcion);
    }

    @Override
    public List<Aplicacion> buscarPorNombreODescripcion(String texto) {
        return aplicacionDao.buscarPorNombreODescripcion(texto);
    }

    @Override
    public List<Aplicacion> buscarPorRangoFechas(LocalDateTime desde, LocalDateTime hasta) {
        return aplicacionDao.buscarPorRangoFechas(desde, hasta);
    }

    @Override
    public List<Aplicacion> buscarPorNombreYFechas(String nombre, LocalDateTime desde, LocalDateTime hasta) {
        return aplicacionDao.buscarPorNombreYFechas(nombre, desde, hasta);
    }

    @Override
    public List<Aplicacion> buscarPorDescripcionYFechas(String descripcion, LocalDateTime desde, LocalDateTime hasta) {
        return aplicacionDao.buscarPorDescripcionYFechas(descripcion, desde, hasta);
    }

    @Override
    public List<Aplicacion> buscarPorNombreDescripcionYFechas(String nombre, String descripcion, LocalDateTime desde, LocalDateTime hasta) {
        return aplicacionDao.buscarPorNombreDescripcionYFechas(nombre, descripcion, desde, hasta);
    }

}
