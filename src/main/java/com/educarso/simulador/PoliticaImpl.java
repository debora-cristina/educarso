package com.educarso.simulador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.educarso.simulador.domain.Processo;
import com.educarso.simulador.enumerador.Estado;
import com.educarso.simulador.view.IEscalonar;
import com.educarso.simulador.view.MapaProcessos;

import lombok.Data;

@Data
public class PoliticaImpl extends Escalonador implements IEscalonar {

	 
	 
	private Map<Estado, List<Processo>> mapa = new LinkedHashMap<Estado, List<Processo>>();
	 
	 
	private Processo processo = new Processo();
	 
	 
	private List<MapaProcessos> mapaProcessos = new ArrayList<MapaProcessos>();
	 
	 
	private int tempo = 0;
	 
	 
	private int posicao = 0;

	@Override
	public List<Processo> ordenar(List<Processo> listaProcessos, int tipo) {
		Collections.sort(listaProcessos, new Comparator<Processo>() {
			@Override
			public int compare(Processo p1, Processo p2) {

				if (tipo == 1) {
					return Integer.compare(p1.getTempoChegada(), p2.getTempoChegada());
				} else {
					return Integer.compare(p1.getTempoCpu(), p2.getTempoCpu());
				}
			}

		});
		return listaProcessos;
	}

	@Override
	public List<Processo> addProcessoPronto() {
		List<Processo> processos = new ArrayList<Processo>();

		for (Processo p : getFila()) {
			if (p.getEstado().equals(Estado.CRIADO) || p.getEstado().equals(Estado.PRONTO)) {
				if (p.getTempoChegada() <= tempo) {
					p.setEstado(Estado.PRONTO);
					processos.add(new Processo(p));
				}
			}
		}

		mapa.put(Estado.PRONTO, processos);

		
		return getFila();

	}

	@Override
	public void addProcessoExecutando(String id, boolean escalonar) {
		addProcessoPronto();

		List<Processo> processos = new ArrayList<Processo>();
		for (Processo p : getFila()) {
			if (p.getNomeProcesso().equals(id)) {
				if (!p.getEstado().equals(Estado.ENCERRADO)) {
					p.setEstado(Estado.EXECUTANDO);
				}
			}
		}

		for (Processo p : getFila()) {
			if (p.getEstado().equals(Estado.EXECUTANDO)) {
				processos.add(new Processo(p));
			}
		}

		mapa.put(Estado.EXECUTANDO, processos);

	}

	@Override
	public void addProcessoFinalizado(String id, boolean escalonar) {
		List<Processo> processos = new ArrayList<Processo>();

		for (Processo p : getFila()) {
			if (p.getNomeProcesso().equals(id)) {
				p.setEstado(Estado.ENCERRADO);
			}
		}

		for (Processo p : getFila()) {
			if (p.getEstado().equals(Estado.ENCERRADO)) {
				processos.add(new Processo(p));
			}
		}

		mapa.put(Estado.ENCERRADO, processos);

	}

	public Map<Estado, List<Processo>> copia() {
		Map<Estado, List<Processo>> copiaMapa = new LinkedHashMap<Estado, List<Processo>>(getMapa());
		return copiaMapa;
	}

	public int analisa(Estado estado) {

		int i = 0;

		for (Processo p : getFila()) {
			if (p.getEstado().equals(Estado.ENCERRADO)) {
				i++;
			}

		}
		return i;
	}

}
