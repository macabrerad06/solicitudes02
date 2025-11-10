package com.senadi.solicitud02.modelo.dao;

import java.util.List;

import com.senadi.solicitud02.modelo.entidades.Rol;

public interface RolDao {
	
	void crear(Rol r);
    Rol actualizar(Rol r);
    void eliminar(Long id);
    Rol buscarPorId(Long id);
    Rol buscarPorNombre(String nombre);
    List<Rol> listarTodos();

}
