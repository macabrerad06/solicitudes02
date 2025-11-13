package com.senadi.solicitud02.modelo.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80, nullable = false)
    private String nombre;

    @Column(length = 80, nullable = false)
    private String apellido;

    @Column(length = 120, nullable = false, unique = true)
    private String correo;
    
    @Column(length = 120, nullable = false, unique = true)
    private String password;

    @Column(length = 80)
    private String cargo;

    // Usuario -> Solicitudes (1:N)
    @OneToMany(mappedBy = "usuario")
    private List<Solicitud> solicitudes = new ArrayList<>();

    // Usuario -> UsuarioRol (1:N)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioRol> rolesAsignados = new ArrayList<>();

    // Usuario -> Auditoria (1:N)
    @OneToMany(mappedBy = "usuario")
    private List<Auditoria> auditorias = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public List<UsuarioRol> getRolesAsignados() {
		return rolesAsignados;
	}

	public void setRolesAsignados(List<UsuarioRol> rolesAsignados) {
		this.rolesAsignados = rolesAsignados;
	}

	public List<Auditoria> getAuditorias() {
		return auditorias;
	}

	public void setAuditorias(List<Auditoria> auditorias) {
		this.auditorias = auditorias;
	}
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo
				+ ", password=" + password + ", cargo=" + cargo + ", solicitudes=" + solicitudes + ", rolesAsignados="
				+ rolesAsignados + ", auditorias=" + auditorias + "]";
	}

	

    
}
