package com.educarso.simulador.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class ConfigurarPolitica {

	@JsonInclude(Include.NON_NULL)
	private int contexto;
	@JsonInclude(Include.NON_NULL)
	private int fatiatempo;
	@JsonInclude(Include.NON_NULL)
	private int numeroNucleos;
	
	ConfigurarPolitica() {

	}

	public ConfigurarPolitica(int contexto, int fatiatempo, int numeroNucleos) {
		this.contexto = contexto;
		this.fatiatempo = fatiatempo;
		this.numeroNucleos = numeroNucleos;
	}



}

