package com.senadi.solicitud02.controlador;

import java.util.List;

import com.senadi.solicitud02.modelo.entidades.Usuario;

public interface UsuarioControlador {
	
	void crear(Usuario u);
    Usuario actualizar(Usuario u);
    void eliminar(Long id);
    Usuario buscarPorId(Long id);
    Usuario buscarPorCorreo(String correo);
    List<Usuario> listarTodos();

}
