package com.senadi.solicitud02.modelo.entidades;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "solicitud")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(length = 30, nullable = false)
    private String estado = "CREADA"; // ejemplo

    // (N) -> Usuario (1)
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Solicitud -> Firma (1:1)
    @OneToOne(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    private Firma firma;

    // Solicitud -> AccesoUsuario (1:N)
    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccesoUsuario> accesos = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}

	public List<AccesoUsuario> getAccesos() {
		return accesos;
	}

	public void setAccesos(List<AccesoUsuario> accesos) {
		this.accesos = accesos;
	}

	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", fechaCreacion=" + fechaCreacion + ", estado=" + estado + ", usuario="
				+ usuario + ", firma=" + firma + ", accesos=" + accesos + "]";
	}

    
}

