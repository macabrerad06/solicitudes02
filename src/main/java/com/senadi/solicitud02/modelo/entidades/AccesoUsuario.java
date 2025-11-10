package com.senadi.solicitud02.modelo.entidades;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "acceso_usuario")
public class AccesoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación CORREGIDA: Acceso referencia a la Solicitud (no al usuario directo)
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private Solicitud solicitud;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_permiso", nullable = false)
    private PermisoAplicacion permiso;

    @Column(name = "fecha_carga", nullable = false)
    private LocalDateTime fechaCarga = LocalDateTime.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public PermisoAplicacion getPermiso() {
		return permiso;
	}

	public void setPermiso(PermisoAplicacion permiso) {
		this.permiso = permiso;
	}

	public LocalDateTime getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(LocalDateTime fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	@Override
	public String toString() {
		return "AccesoUsuario [id=" + id + ", solicitud=" + solicitud + ", permiso=" + permiso + ", fechaCarga="
				+ fechaCarga + "]";
	}

    
}

