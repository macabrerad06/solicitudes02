package com.senadi.solicitud02.modelo.dao;

import java.util.List;
import com.senadi.solicitud02.modelo.entidades.UsuarioRol;

public interface UsuarioRolDao {

    void crear(UsuarioRol ur);
    UsuarioRol actualizar(UsuarioRol ur);
    void eliminar(Long id);
    UsuarioRol buscarPorId(Long id);
    List<UsuarioRol> listarTodos();
    List<UsuarioRol> buscarPorUsuario(Long idUsuario);
    List<UsuarioRol> buscarPorRol(Long idRol);
}
