package com.senadi.solicitud02.controlador;

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
}
