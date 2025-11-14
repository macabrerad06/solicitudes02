package com.senadi.solicitud02.vista;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.senadi.solicitud02.controlador.AuditoriaControlador;
import com.senadi.solicitud02.controlador.impl.AuditoriaControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Auditoria;

@ManagedBean(name = "auditoriaBean")
@ViewScoped
public class AuditoriaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private AuditoriaControlador audCtrl = new AuditoriaControladorImpl();
    private List<Auditoria> lista;
    private Auditoria formulario;
    private Auditoria seleccionado;
   
    public void init() {
        listar();
        formulario = new Auditoria();
    }

    public void listar() { lista = audCtrl.listarTodos(); }

    public void prepararNuevo() { formulario = new Auditoria(); }

    public void prepararEdicion(Auditoria a) { this.formulario = a; }

    public String guardar() {
        try {
            if (formulario.getId() == null) {
                audCtrl.crear(formulario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Auditoría creada"));
            } else {
                audCtrl.actualizar(formulario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Auditoría actualizada"));
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
            audCtrl.eliminar(id);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Auditoría eliminada"));
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public String cancelar() {
        formulario = new Auditoria();
        return "index?faces-redirect=true";
    }

    // getters / setters
    public List<Auditoria> getLista() { return lista; }
    public Auditoria getFormulario() { return formulario; }
    public void setFormulario(Auditoria formulario) { this.formulario = formulario; }
    public Auditoria getSeleccionado() { return seleccionado; }
    public void setSeleccionado(Auditoria seleccionado) { this.seleccionado = seleccionado; }
}
