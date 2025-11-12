package com.senadi.solicitud02.controlador;

import java.util.List;
import com.senadi.solicitud02.modelo.entidades.Aplicacion;

public interface AplicacionControlador {

    void crear(Aplicacion a);
    Aplicacion actualizar(Aplicacion a);
    void eliminar(Long id);
    Aplicacion buscarPorId(Long id);
    Aplicacion buscarPorNombre(String nombre);
    List<Aplicacion> listarTodos();
}
