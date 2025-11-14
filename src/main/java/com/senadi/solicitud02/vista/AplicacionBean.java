package com.senadi.solicitud02.vista;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.senadi.solicitud02.controlador.AplicacionControlador;
import com.senadi.solicitud02.controlador.impl.AplicacionControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Aplicacion;

@ManagedBean(name = "aplicacionBean")
@ViewScoped
public class AplicacionBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private AplicacionControlador appCtrl = new AplicacionControladorImpl();
    private List<Aplicacion> lista;
    private Aplicacion formulario;
    private Aplicacion seleccionado;

    public void init() {
        listar();
        formulario = new Aplicacion();
    }

    public void listar() { lista = appCtrl.listarTodos(); }

    public void prepararNuevo() { formulario = new Aplicacion(); }

    public void prepararEdicion(Aplicacion a) { this.formulario = a; }

    public String guardar() {
        try {
            if (formulario.getId() == null) {
                appCtrl.crear(formulario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aplicación creada"));
            } else {
                appCtrl.actualizar(formulario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aplicación actualizada"));
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
            appCtrl.eliminar(id);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Aplicación eliminada"));
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public String cancelar() {
        formulario = new Aplicacion();
        return "index?faces-redirect=true";
    }

    // getters / setters
    public List<Aplicacion> getLista() { return lista; }
    public Aplicacion getFormulario() { return formulario; }
    public void setFormulario(Aplicacion formulario) { this.formulario = formulario; }
    public Aplicacion getSeleccionado() { return seleccionado; }
    public void setSeleccionado(Aplicacion seleccionado) { this.seleccionado = seleccionado; }
}
