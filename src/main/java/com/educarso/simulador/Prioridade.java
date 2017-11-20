package com.educarso.simulador;

import java.util.ArrayList;
import java.util.List;

import com.educarso.simulador.domain.ConfigurarPolitica;
import com.educarso.simulador.domain.Processo;
import com.educarso.simulador.enumerador.Estado;
import com.educarso.simulador.view.IPolitica;
import com.educarso.simulador.view.MapaProcessos;

public class Prioridade extends PoliticaImpl implements IPolitica {

	private int tempo = 0;
	private int posicao = 0;
	private boolean troca = false;
	private int indice = 0;
	private boolean escalonar = true;

	@Override
	public void analisar() {

		indice = 0;
		setPosicao(0);
		
		if(getFila().size() == 1){
			setPosicao(0);
			
		} else{
			for (int i = 0; i < getFila().size(); i++) {
	
				if (getFila().get(i).getEstado() != Estado.ENCERRADO) {
					
					if (indice == getFila().size() - 1) {
						indice = i;
					} else {
						indice++;
					}
						if (getFila().get(i).getTempoChegada() <= getTempo()
								&& getFila().get(i).getPrioridade() <= getFila().get(indice).getPrioridade()) {
							System.out.println(getTempo() + "EXEC" + getPosicao());
							setPosicao(i);
						} else if (getFila().get(indice).getTempoChegada() <= getTempo()
								&& getFila().get(indice).getPrioridade() < getFila().get(i).getPrioridade()) {
							getFila().get(getPosicao()).setEstado(Estado.PRONTO);
							System.out.println(getTempo() + "EXEC- TROCA" + getPosicao());
							setPosicao(indice);
							troca = true;
							
						}
	
				}
			}
			addProcessoPronto();
		}		

		getFila().get(getPosicao()).setEstado(Estado.EXECUTANDO);
	}

	@Override
	public List<MapaProcessos> executar(List<Processo> fila, ConfigurarPolitica config) {
		inserirFila(fila);

		List<Processo> filaProcessos = new ArrayList<Processo>();
		filaProcessos = getFila();
		setPosicao(0);
		setProcesso(filaProcessos.get(0));
		setConfiguracoes(config);
		setFila(ordenar(getFila(), 1));
		addProcessoPronto();
		getMapaProcessos().add(new MapaProcessos(getTempo(), copia()));
		int tempoEx;
		while (escalonar) {

			analisar();
			
			if (getFila().get(getPosicao()).getTempoCpu() == getFila().get(getPosicao()).getTempoExecucao()) {
				getFila().get(getPosicao()).setEstado(Estado.ENCERRADO);
				getFila().remove(getPosicao());
				addProcessoFinalizado(getFila().get(getPosicao()).getNomeProcesso(), escalonar);
				if (analisa(Estado.ENCERRADO) == getFila().size()) {

					escalonar = false;
					addProcessoFinalizado(getFila().get(getPosicao()).getNomeProcesso(), escalonar);
				}
				analisar();

			}
			
			addProcessoExecutando(getFila().get(getPosicao()).getNomeProcesso(), escalonar);
			
			getMapaProcessos().add(new MapaProcessos(getTempo(), copia()));
			
			tempoEx = getFila().get(getPosicao()).getTempoExecucao() + 1;
			getFila().get(getPosicao()).setTempoExecucao(tempoEx);
			setTempo(getTempo() + 1);
			
			
		}

		return getMapaProcessos();
	}

}
