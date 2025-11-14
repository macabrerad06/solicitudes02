package com.senadi.solicitud02.vista;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.senadi.solicitud02.controlador.RolControlador;
import com.senadi.solicitud02.controlador.impl.RolControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Rol;

@ManagedBean(name = "rolBean")
@ViewScoped
public class RolBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private RolControlador rolCtrl = new RolControladorImpl();

    private List<Rol> lista;
    private Rol formulario = new Rol();   // se usa en create y edit

    public RolBean() { }

    @PostConstruct
    public void init() {
        cargarLista();

        // Si estamos en edit.xhtml, viene un parámetro ?id=XX
        String idStr = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                Long id = Long.valueOf(idStr);
                Rol encontrado = rolCtrl.buscarPorId(id);
                if (encontrado != null) {
                    formulario = encontrado;
                }
            } catch (NumberFormatException e) {
                // opcional: loguear
            }
        }
    }

    private void cargarLista() {
        lista = rolCtrl.listarTodos();
    }

    // ---------- Navegación ----------

    public String irListado() {
        return "/Rol/index?faces-redirect=true";
    }

    public String irCrear() {
        formulario = new Rol(); // limpio
        return "/Rol/create?faces-redirect=true";
    }

    public String irEditar(Long id) {
        // Pasamos el id por parámetro y en init() se carga
        return "/Rol/edit?faces-redirect=true&id=" + id;
    }

    // ---------- Acciones CRUD ----------

    public String guardarNuevo() {
        rolCtrl.crear(formulario);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Rol creado correctamente", null));
        return irListado();
    }

    public String actualizar() {
        rolCtrl.actualizar(formulario);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Rol actualizado correctamente", null));
        return irListado();
    }

    public void eliminar(Long id) {
        rolCtrl.eliminar(id);
        cargarLista();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Rol eliminado", null));
    }

    // ---------- Getters / Setters ----------

    public List<Rol> getLista() {
        return lista;
    }

    public Rol getFormulario() {
        return formulario;
    }

    public void setFormulario(Rol formulario) {
        this.formulario = formulario;
    }
}
