package com.senadi.solicitud02.controlador;

import java.util.List;
import com.senadi.solicitud02.modelo.entidades.PermisoAplicacion;

public interface PermisoAplicacionControlador {

    void crear(PermisoAplicacion p);
    PermisoAplicacion actualizar(PermisoAplicacion p);
    void eliminar(Long id);
    PermisoAplicacion buscarPorId(Long id);
    List<PermisoAplicacion> listarTodos();
    List<PermisoAplicacion> buscarPorAplicacion(Long idAplicacion);
    List<PermisoAplicacion> buscarPorNombre(String nombre);
    List<PermisoAplicacion> buscarPorDescripcion(String descripcion);
    List<PermisoAplicacion> buscarPorNombreODescripcion(String texto);
    List<PermisoAplicacion> buscarPorAplicacionYNombre(Long idAplicacion, String nombre);
    List<PermisoAplicacion> buscarPorAplicacionYDescripcion(Long idAplicacion, String descripcion);
    List<PermisoAplicacion> buscarPorAplicacionNombreYDescripcion(Long idAplicacion, String nombre, String descripcion);

}
