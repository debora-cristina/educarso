package com.educarso.simulador.view;

import java.util.List;

import com.educarso.simulador.domain.ConfigurarPolitica;
import com.educarso.simulador.domain.Processo;

public interface IPolitica {
	void analisar();
	List<MapaProcessos> executar(List<Processo> lista, ConfigurarPolitica config);
}
