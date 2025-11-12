package com.senadi.solicitud02.modelo.dao;

import java.util.List;
import com.senadi.solicitud02.modelo.entidades.PermisoAplicacion;

public interface PermisoAplicacionDao {

    void crear(PermisoAplicacion p);
    PermisoAplicacion actualizar(PermisoAplicacion p);
    void eliminar(Long id);
    PermisoAplicacion buscarPorId(Long id);
    List<PermisoAplicacion> listarTodos();
    List<PermisoAplicacion> buscarPorAplicacion(Long idAplicacion);
    List<PermisoAplicacion> buscarPorNombre(String nombre);
}
