package com.senadi.solicitud02.controlador;

import java.util.List;
import com.senadi.solicitud02.modelo.entidades.Auditoria;

public interface AuditoriaControlador {

    void crear(Auditoria a);
    Auditoria actualizar(Auditoria a);
    void eliminar(Long id);
    Auditoria buscarPorId(Long id);
    List<Auditoria> listarTodos();
    List<Auditoria> buscarPorUsuario(Long idUsuario);
    List<Auditoria> buscarPorAccion(String accion);
}
