package com.senadi.solicitud02.modelo.entidades;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aplicacion")
public class Aplicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    // Aplicacion -> PermisoAplicacion (1:N)
    @OneToMany(mappedBy = "aplicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PermisoAplicacion> permisos = new ArrayList<>();

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<PermisoAplicacion> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<PermisoAplicacion> permisos) {
		this.permisos = permisos;
	}

	@Override
	public String toString() {
		return "Aplicacion [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fechaCreacion="
				+ fechaCreacion + ", permisos=" + permisos + "]";
	}

    
}

