package com.educarso.simulador.view;

import java.util.List;

import com.educarso.simulador.domain.Processo;

public interface IEscalonar {
	List<Processo> ordenar(List<Processo> listaProcessos, int tipo);
	List<Processo> addProcessoPronto();
	void addProcessoExecutando(String id, boolean escalonar);
	void addProcessoFinalizado(String id, boolean escalonar);
}
