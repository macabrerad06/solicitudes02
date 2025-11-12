package com.senadi.solicitud02.modelo.dao;

import java.util.List;
import com.senadi.solicitud02.modelo.entidades.Solicitud;

public interface SolicitudDao {

    void crear(Solicitud s);
    Solicitud actualizar(Solicitud s);
    void eliminar(Long id);
    Solicitud buscarPorId(Long id);
    List<Solicitud> listarTodos();
    List<Solicitud> buscarPorUsuario(Long idUsuario);
    List<Solicitud> buscarPorEstado(String estado);
}
