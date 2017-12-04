package com.educarso.simulador.model;
//package teste;

import java.util.ArrayList;
import java.util.List;

import com.educarso.simulador.domain.ConfigurarPolitica;
import com.educarso.simulador.domain.Processo;
import com.educarso.simulador.view.IPolitica;

import lombok.Getter;
import lombok.Setter;

public class Escalonador{

	@Getter @Setter
	private List<Processo> fila = new ArrayList<Processo>();
	@Getter @Setter
	private ConfigurarPolitica configuracoes;
	
	
	   public IPolitica getPolitica(String tipo){
	      if(tipo == null){
	         return null;
	      }		
	      if(tipo.equalsIgnoreCase("FIFO")){
	    	  return new FIFO();
	         
	      } else if(tipo.equalsIgnoreCase("SJF")){
	         return new SJF();
	         
	      } else if(tipo.equalsIgnoreCase("PRIORIDADE")){
		         return new Prioridade();
		         
		  } else if(tipo.equalsIgnoreCase("CIRCULAR")){
		         //return new Circular();
		         
		  }
	      
	      return null;
	   }

	void inserirFila(List<Processo> fila) {
		this.fila = fila;
		
	}

	
	

}
