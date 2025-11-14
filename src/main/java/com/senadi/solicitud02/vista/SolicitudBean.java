package com.senadi.solicitud02.vista;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.senadi.solicitud02.controlador.SolicitudControlador;
import com.senadi.solicitud02.controlador.impl.SolicitudControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Solicitud;

@ManagedBean(name = "solicitudBean")
@ViewScoped
public class SolicitudBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private SolicitudControlador solCtrl = new SolicitudControladorImpl();
    private List<Solicitud> lista;
    private Solicitud formulario;
    private Solicitud seleccionado;

    public void init() {
        listar();
        formulario = new Solicitud();
    }

    public void listar() { lista = solCtrl.listarTodos(); }

    public void prepararNuevo() { formulario = new Solicitud(); }

    public void prepararEdicion(Solicitud s) { this.formulario = s; }

    public String guardar() {
        try {
            if (formulario.getId() == null) {
                solCtrl.crear(formulario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Solicitud creada"));
            } else {
                solCtrl.actualizar(formulario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Solicitud actualizada"));
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
            solCtrl.eliminar(id);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Solicitud eliminada"));
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public String cancelar() {
        formulario = new Solicitud();
        return "index?faces-redirect=true";
    }

    // getters / setters
    public List<Solicitud> getLista() { return lista; }
    public Solicitud getFormulario() { return formulario; }
    public void setFormulario(Solicitud formulario) { this.formulario = formulario; }
    public Solicitud getSeleccionado() { return seleccionado; }
    public void setSeleccionado(Solicitud seleccionado) { this.seleccionado = seleccionado; }
}
