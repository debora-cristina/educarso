package com.educarso.simulador.domain;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
@Data
public class EscalonadorDomain {
	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message = "Preenchimento da politica obrigatório")
	private String politica;
	@JsonInclude(Include.NON_NULL)
	private ConfigurarPolitica configuracao;
	@JsonInclude(Include.NON_NULL)
	private List<Processo> listaProcessos;
}
