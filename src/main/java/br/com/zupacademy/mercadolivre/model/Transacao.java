package br.com.zupacademy.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Transacao {

	@Id @NotNull
	private String id;
	@CreationTimestamp
	private LocalDateTime horario;
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusTransacao status;
	
	@ManyToOne
	@NotNull
	private Compra compra;
	
	@Deprecated
	public Transacao() { }
	
	public Transacao(@NotNull String id, @NotNull Compra compra, @NotNull StatusTransacao status) {
		this.id = id;
		this.compra = compra;
		this.status = status;
	}

	public Compra getCompra() {
		return compra;
	}

	public LocalDateTime getHorario() {
		return horario;
	}

	public StatusTransacao getStatus() {
		return status;
	}
	
	
}
