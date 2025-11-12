package com.senadi.solicitud02.modelo.dao;

import java.time.LocalDateTime;
import java.util.List;
import com.senadi.solicitud02.modelo.entidades.Firma;

public interface FirmaDao {

    void crear(Firma f);
    Firma actualizar(Firma f);
    void eliminar(Long id);
    Firma buscarPorId(Long id);
    List<Firma> buscarPorSolicitud(Long idSolicitud);
    List<Firma> listarTodos();
    List<Firma> buscarPorDescripcion(String descripcion);
    List<Firma> buscarPorFecha(LocalDateTime desde, LocalDateTime hasta);
    List<Firma> buscarPorSolicitudYFecha(Long idSolicitud, LocalDateTime desde, LocalDateTime hasta);
    List<Firma> buscarPorSolicitudYDescripcion(Long idSolicitud, String descripcion);
    List<Firma> buscarPorDescripcionYFecha(String descripcion, LocalDateTime desde, LocalDateTime hasta);

}
