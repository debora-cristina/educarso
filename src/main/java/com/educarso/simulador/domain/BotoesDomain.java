package com.educarso.simulador.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
@Data
public class BotoesDomain {
	
	@JsonInclude(Include.NON_NULL)
	private boolean executar;


}
