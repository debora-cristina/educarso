package com.educarso.simulador.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.educarso.simulador.enumerador.Estado;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class Processo  {
	@NotEmpty(message = "Nome do processo obrigatório")
    @Size(min=2, max=30)
	@JsonInclude(Include.NON_NULL)
	private String nomeProcesso;
	@NotNull(message = "Tempo de Chegada obrigatório")
	@JsonInclude(Include.NON_NULL)
	private int tempoChegada;
	@NotNull(message = "Tempo de CPU obrigatório")
	@JsonInclude(Include.NON_NULL)
	private int tempoCpu;
	@JsonInclude(Include.NON_NULL)
	private Estado estado;
	@JsonInclude(Include.NON_NULL)
	private int prioridade;
	@JsonInclude(Include.NON_NULL)
	private int tempoExecucao;

	public Processo(){
		this.estado = Estado.CRIADO;
	}

	public Processo(String id, int tempoChegada, int tempoCpu, int prioridade, Estado estado) {
		this.nomeProcesso = id;
		this.tempoChegada = tempoChegada;
		this.tempoCpu = tempoCpu;
		this.prioridade = prioridade;
	    this.estado = estado;
	}
	
	public Processo(String id, int tempoChegada, int tempoCpu, int prioridade) {
		this.nomeProcesso = id;
		this.tempoChegada = tempoChegada;
		this.tempoCpu = tempoCpu;
		this.prioridade = prioridade;
	    //this.estado = estado;
	}

	public Processo(Processo processo) {
		this(processo.nomeProcesso, processo.tempoChegada, processo.tempoCpu,
				processo.prioridade, processo.estado);

	}

	

}

