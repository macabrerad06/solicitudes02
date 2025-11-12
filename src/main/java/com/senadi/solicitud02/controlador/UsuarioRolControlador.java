package com.senadi.solicitud02.controlador;

import java.time.LocalDateTime;
import java.util.List;
import com.senadi.solicitud02.modelo.entidades.UsuarioRol;

public interface UsuarioRolControlador {

    void crear(UsuarioRol ur);
    UsuarioRol actualizar(UsuarioRol ur);
    void eliminar(Long id);
    UsuarioRol buscarPorId(Long id);
    List<UsuarioRol> listarTodos();
    List<UsuarioRol> buscarPorUsuario(Long idUsuario);
    List<UsuarioRol> buscarPorRol(Long idRol);
    List<UsuarioRol> buscarPorUsuarioYRol(Long idUsuario, Long idRol);
    List<UsuarioRol> buscarPorRangoFechas(LocalDateTime desde, LocalDateTime hasta);
    List<UsuarioRol> buscarPorUsuarioConFecha(Long idUsuario, LocalDateTime desde, LocalDateTime hasta);
    List<UsuarioRol> buscarPorRolConFecha(Long idRol, LocalDateTime desde, LocalDateTime hasta);

}
