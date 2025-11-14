package com.senadi.solicitud02.vista;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.senadi.solicitud02.controlador.FirmaControlador;
import com.senadi.solicitud02.controlador.impl.FirmaControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Firma;

@ManagedBean(name = "firmaBean")
@ViewScoped
public class FirmaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private FirmaControlador firmaCtrl = new FirmaControladorImpl();
    private List<Firma> lista;
    private Firma formulario;
    private Firma seleccionado;

    public void init() {
        listar();
        formulario = new Firma();
    }

    public void listar() { lista = firmaCtrl.listarTodos(); }

    public void prepararNuevo() { formulario = new Firma(); }

    public void prepararEdicion(Firma f) { this.formulario = f; }

    public String guardar() {
        try {
            if (formulario.getId() == null) {
                firmaCtrl.crear(formulario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Firma creada"));
            } else {
                firmaCtrl.actualizar(formulario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Firma actualizada"));
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
            firmaCtrl.eliminar(id);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Firma eliminada"));
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public String cancelar() {
        formulario = new Firma();
        return "index?faces-redirect=true";
    }

    // getters / setters
    public List<Firma> getLista() { return lista; }
    public Firma getFormulario() { return formulario; }
    public void setFormulario(Firma formulario) { this.formulario = formulario; }
    public Firma getSeleccionado() { return seleccionado; }
    public void setSeleccionado(Firma seleccionado) { this.seleccionado = seleccionado; }
}
