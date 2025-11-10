package com.senadi.solicitud02.modelo.entidades;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria")
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // usuario que ejecuta la acción auditada
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(length = 60, nullable = false)
    private String accion;  // p.ej.: "CREAR_SOLICITUD", "ASIGNAR_ROL", etc.

    @Column(length = 100)
    private String entidad; // nombre de entidad afectada (opcional)

    @Column(name = "entidad_id")
    private Long entidadId; // id del registro afectado (opcional)

    @Column(name = "fecha_evento", nullable = false)
    private LocalDateTime fechaEvento = LocalDateTime.now();

    @Column(length = 255)
    private String detalle; // info adicional

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public Long getEntidadId() {
		return entidadId;
	}

	public void setEntidadId(Long entidadId) {
		this.entidadId = entidadId;
	}

	public LocalDateTime getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(LocalDateTime fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return "Auditoria [id=" + id + ", usuario=" + usuario + ", accion=" + accion + ", entidad=" + entidad
				+ ", entidadId=" + entidadId + ", fechaEvento=" + fechaEvento + ", detalle=" + detalle + "]";
	}

    
}

