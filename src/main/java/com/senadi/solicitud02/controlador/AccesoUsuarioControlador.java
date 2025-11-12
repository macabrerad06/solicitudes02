package com.senadi.solicitud02.controlador;

import java.time.LocalDateTime;
import java.util.List;
import com.senadi.solicitud02.modelo.entidades.AccesoUsuario;

public interface AccesoUsuarioControlador {

    void crear(AccesoUsuario a);
    AccesoUsuario actualizar(AccesoUsuario a);
    void eliminar(Long id);
    AccesoUsuario buscarPorId(Long id);
    List<AccesoUsuario> listarTodos();
    List<AccesoUsuario> buscarPorSolicitud(Long idSolicitud);
    List<AccesoUsuario> buscarPorPermiso(Long idPermiso);
    List<AccesoUsuario> buscarPorRangoFechas(LocalDateTime desde, LocalDateTime hasta);
    List<AccesoUsuario> buscarPorSolicitudYPermiso(Long idSolicitud, Long idPermiso);
    List<AccesoUsuario> buscarPorSolicitudYFechas(Long idSolicitud, LocalDateTime desde, LocalDateTime hasta);
    List<AccesoUsuario> buscarPorPermisoYFechas(Long idPermiso, LocalDateTime desde, LocalDateTime hasta);
    List<AccesoUsuario> buscarPorSolicitudPermisoYFechas(Long idSolicitud, Long idPermiso, LocalDateTime desde, LocalDateTime hasta);

}
