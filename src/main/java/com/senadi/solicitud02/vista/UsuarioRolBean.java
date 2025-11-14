package com.senadi.solicitud02.vista;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.senadi.solicitud02.controlador.UsuarioRolControlador;
import com.senadi.solicitud02.controlador.impl.UsuarioRolControladorImpl;
import com.senadi.solicitud02.modelo.entidades.UsuarioRol;

@ManagedBean(name = "usuarioRolBean")
@ViewScoped
public class UsuarioRolBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private UsuarioRolControlador urCtrl = new UsuarioRolControladorImpl();
    private List<UsuarioRol> lista;
    private UsuarioRol formulario;
    private UsuarioRol seleccionado;

    public void init() {
        listar();
        formulario = new UsuarioRol();
    }

    public void listar() { lista = urCtrl.listarTodos(); }

    public void prepararNuevo() { formulario = new UsuarioRol(); }

    public void prepararEdicion(UsuarioRol ur) { this.formulario = ur; }

    public String guardar() {
        try {
            if (formulario.getId() == null) {
                urCtrl.crear(formulario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("UsuarioRol creado"));
            } else {
                urCtrl.actualizar(formulario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("UsuarioRol actualizado"));
            }
            listar();
            return "index?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }

    public void eliminar(Long id) {
        try {
            urCtrl.eliminar(id);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("UsuarioRol eliminado"));
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public String cancelar() {
        formulario = new UsuarioRol();
        return "index?faces-redirect=true";
    }

    // getters / setters
    public List<UsuarioRol> getLista() { return lista; }
    public UsuarioRol getFormulario() { return formulario; }
    public void setFormulario(UsuarioRol formulario) { this.formulario = formulario; }
    public UsuarioRol getSeleccionado() { return seleccionado; }
    public void setSeleccionado(UsuarioRol seleccionado) { this.seleccionado = seleccionado; }
}
