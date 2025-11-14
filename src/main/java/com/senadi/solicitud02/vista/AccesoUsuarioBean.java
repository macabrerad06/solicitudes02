package com.senadi.solicitud02.vista;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.senadi.solicitud02.controlador.AccesoUsuarioControlador;
import com.senadi.solicitud02.controlador.impl.AccesoUsuarioControladorImpl;
import com.senadi.solicitud02.modelo.entidades.AccesoUsuario;

@ManagedBean(name = "accesoUsuarioBean")
@ViewScoped
public class AccesoUsuarioBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private AccesoUsuarioControlador accesoCtrl = new AccesoUsuarioControladorImpl();
    private List<AccesoUsuario> lista;
    private AccesoUsuario formulario;
    private AccesoUsuario seleccionado;

    public void init() {
        listar();
        formulario = new AccesoUsuario();
    }

    public void listar() { lista = accesoCtrl.listarTodos(); }

    public void prepararNuevo() { formulario = new AccesoUsuario(); }

    public void prepararEdicion(AccesoUsuario a) { this.formulario = a; }

    public String guardar() {
        try {
            if (formulario.getId() == null) {
                accesoCtrl.crear(formulario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Acceso creado"));
            } else {
                accesoCtrl.actualizar(formulario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Acceso actualizado"));
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
            accesoCtrl.eliminar(id);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Acceso eliminado"));
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public String cancelar() {
        formulario = new AccesoUsuario();
        return "index?faces-redirect=true";
    }

    // getters / setters
    public List<AccesoUsuario> getLista() { return lista; }
    public AccesoUsuario getFormulario() { return formulario; }
    public void setFormulario(AccesoUsuario formulario) { this.formulario = formulario; }
    public AccesoUsuario getSeleccionado() { return seleccionado; }
    public void setSeleccionado(AccesoUsuario seleccionado) { this.seleccionado = seleccionado; }
}
