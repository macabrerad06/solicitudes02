package com.senadi.solicitud02.modelo.dao;

import java.util.List;
import com.senadi.solicitud02.modelo.entidades.Firma;

public interface FirmaDao {

    void crear(Firma f);
    Firma actualizar(Firma f);
    void eliminar(Long id);
    Firma buscarPorId(Long id);
    Firma buscarPorSolicitud(Long idSolicitud);
    List<Firma> listarTodos();
}
