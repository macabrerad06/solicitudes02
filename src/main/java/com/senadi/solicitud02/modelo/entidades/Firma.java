package com.senadi.solicitud02.modelo.entidades;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "firma")
public class Firma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String descripcion;

    @Column(name = "fecha_firma", nullable = false)
    private LocalDateTime fechaFirma = LocalDateTime.now();

    // (N:1) con Solicitud
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private Solicitud solicitud;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaFirma() {
		return fechaFirma;
	}

	public void setFechaFirma(LocalDateTime fechaFirma) {
		this.fechaFirma = fechaFirma;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	@Override
	public String toString() {
		return "Firma [id=" + id + ", descripcion=" + descripcion + ", fechaFirma=" + fechaFirma + ", solicitud="
				+ solicitud + "]";
	}

    
}

