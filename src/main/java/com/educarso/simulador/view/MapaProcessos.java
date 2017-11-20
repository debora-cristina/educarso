package com.educarso.simulador.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.educarso.simulador.domain.Processo;
import com.educarso.simulador.enumerador.Estado;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
@Data
public class MapaProcessos {
	@JsonInclude(Include.NON_NULL)
	private int tempo;
	@JsonInclude(Include.NON_NULL)
	private Map<Estado,List<Processo>> processos = new HashMap<Estado,List<Processo>>();
	
	public MapaProcessos(int tempo, Map<Estado, List<Processo>> processos) {
		super();
		this.tempo = tempo;
		this.processos = processos;
	}
	
	

	

}
