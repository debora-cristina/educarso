package com.educarso.simulador.domain;

import java.util.Arrays;
import java.util.List;

import com.educarso.simulador.enumerador.Politica;

import lombok.Data;
@Data
public class PoliticaDomain {
	List<Politica> politicas  = Arrays.asList(Politica.values());
	
	
}
