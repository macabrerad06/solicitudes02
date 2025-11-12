package com.senadi.solicitud02.controlador;

import java.time.LocalDateTime;
import java.util.List;
import com.senadi.solicitud02.modelo.entidades.Aplicacion;

public interface AplicacionControlador {

    void crear(Aplicacion a);
    Aplicacion actualizar(Aplicacion a);
    void eliminar(Long id);
    Aplicacion buscarPorId(Long id);
    Aplicacion buscarPorNombre(String nombre);
    List<Aplicacion> listarTodos();
    List<Aplicacion> buscarPorDescripcion(String descripcion);
    List<Aplicacion> buscarPorNombreODescripcion(String texto);
    List<Aplicacion> buscarPorRangoFechas(LocalDateTime desde, LocalDateTime hasta);
    List<Aplicacion> buscarPorNombreYFechas(String nombre, LocalDateTime desde, LocalDateTime hasta);
    List<Aplicacion> buscarPorDescripcionYFechas(String descripcion, LocalDateTime desde, LocalDateTime hasta);
    List<Aplicacion> buscarPorNombreDescripcionYFechas(String nombre, String descripcion, LocalDateTime desde, LocalDateTime hasta);

}
