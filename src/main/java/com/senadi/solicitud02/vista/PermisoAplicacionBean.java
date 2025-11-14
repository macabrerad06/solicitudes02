package com.senadi.solicitud02.vista;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.senadi.solicitud02.controlador.PermisoAplicacionControlador;
import com.senadi.solicitud02.controlador.impl.PermisoAplicacionControladorImpl;
import com.senadi.solicitud02.modelo.entidades.PermisoAplicacion;

@ManagedBean(name = "permisoAplicacionBean")
@ViewScoped
public class PermisoAplicacionBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private PermisoAplicacionControlador permisoCtrl = new PermisoAplicacionControladorImpl();
    private List<PermisoAplicacion> lista;
    private PermisoAplicacion formulario;
    private PermisoAplicacion seleccionado;

    public void init() {
        listar();
        formulario = new PermisoAplicacion();
    }

    public void listar() { lista = permisoCtrl.listarTodos(); }

    public void prepararNuevo() { formulario = new PermisoAplicacion(); }

    public void prepararEdicion(PermisoAplicacion p) { this.formulario = p; }

    public String guardar() {
        try {
            if (formulario.getId() == null) {
                permisoCtrl.crear(formulario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Permiso creado"));
            } else {
                permisoCtrl.actualizar(formulario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Permiso actualizado"));
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
            permisoCtrl.eliminar(id);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Permiso eliminado"));
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public String cancelar() {
        formulario = new PermisoAplicacion();
        return "index?faces-redirect=true";
    }

    // getters / setters
    public List<PermisoAplicacion> getLista() { return lista; }
    public PermisoAplicacion getFormulario() { return formulario; }
    public void setFormulario(PermisoAplicacion formulario) { this.formulario = formulario; }
    public PermisoAplicacion getSeleccionado() { return seleccionado; }
    public void setSeleccionado(PermisoAplicacion seleccionado) { this.seleccionado = seleccionado; }
}
