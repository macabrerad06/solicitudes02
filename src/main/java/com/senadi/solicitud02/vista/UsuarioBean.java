package com.senadi.solicitud02.vista;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.senadi.solicitud02.controlador.UsuarioControlador;
import com.senadi.solicitud02.controlador.impl.UsuarioControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Usuario;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private UsuarioControlador usuarioCtrl = new UsuarioControladorImpl();
    private List<Usuario> lista;
    private Usuario formulario;

    public void init() {
        listar();
        formulario = new Usuario();
    }

    public void listar() {
        lista = usuarioCtrl.listarTodos();
    }

    public void prepararNuevo() {
        formulario = new Usuario();
    }

    public void prepararEdicion(Usuario u) {
        formulario = u;
    }

    public String guardar() {
        try {
            if (formulario.getId() == null) {
                usuarioCtrl.crear(formulario);
                mensaje("Usuario creado correctamente");
            } else {
                usuarioCtrl.actualizar(formulario);
                mensaje("Usuario actualizado");
            }
            listar();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            error(e.getMessage());
            return null;
        }
    }

    public void eliminar(Long id) {
        try {
            usuarioCtrl.eliminar(id);
            mensaje("Usuario eliminado");
            listar();
        } catch (Exception e) {
            error(e.getMessage());
        }
    }

    public String cancelar() {
        formulario = new Usuario();
        return "index?faces-redirect=true";
    }

    private void mensaje(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
    }

    private void error(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg));
    }

    // getters & setters
    public List<Usuario> getLista() { return lista; }
    public Usuario getFormulario() { return formulario; }
    public void setFormulario(Usuario formulario) { this.formulario = formulario; }
}
