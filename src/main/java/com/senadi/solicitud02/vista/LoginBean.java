package com.senadi.solicitud02.vista;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.senadi.solicitud02.controlador.UsuarioControlador;
import com.senadi.solicitud02.controlador.impl.UsuarioControladorImpl;
import com.senadi.solicitud02.modelo.entidades.Usuario;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String correo;
    private String password;
    private Usuario usuario;

    private final UsuarioControlador usuarioCtrl = new UsuarioControladorImpl();

    public String login() {
        usuario = usuarioCtrl.autenticar(correo, password);

        if (usuario != null) {
            // Ir al home
            return "home?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Credenciales inválidas", "Verifica correo y contraseña"));
            return null;
        }
    }

    // Se ejecuta antes de renderizar home.xhtml
    public void verificarSesion() throws IOException {
        if (usuario == null) {
            // si no hay usuario en sesión, redirigimos al login
            FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .redirect("index.xhtml");
        }
    }

    public String logout() {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            // invalidar sesión
            fc.getExternalContext().invalidateSession();

            // redirigir manualmente al login
            String ctxPath = fc.getExternalContext().getRequestContextPath();
            fc.getExternalContext().redirect(ctxPath + "/index.xhtml");

            // ya hicimos redirect, no hace falta navegación JSF
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    // getters y setters
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Usuario getUsuario() { return usuario; }
}

