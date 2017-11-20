package com.educarso.simulador.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import com.algaworks.socialbooks.domain.Livro;
import com.educarso.simulador.domain.Processo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import lombok.Getter;
import lombok.Setter;

@Api(value = "processos")
@RestController("controller_V1")
@RequestMapping("/processos")
public class ProcessoResource {

	@JsonProperty("listaProcessos")
	@Getter
	@Setter
	private List<Processo> listaProcessos = new ArrayList<Processo>();
	
	 public static final Logger logger = LoggerFactory.getLogger(ProcessoResource.class);

	@ApiOperation(value = "Listar Processos" )
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Processo>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(listaProcessos);
	}

	@ApiOperation(value = "Adicionar Processo" )
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Processo processo) {

		listaProcessos.add(processo);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(processo.getNomeProcesso()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Atualizar Processo" )
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Processo> atualizar(@Valid String id, @RequestBody Processo processo) {
		if (listaProcessos.isEmpty()) {
			// processo.setId(0);
		} else {
			 for(Processo p: listaProcessos){
				 if(p.getNomeProcesso().equals(id)){
					 p.setPrioridade(processo.getPrioridade());
					 p.setTempoChegada(processo.getTempoChegada());
					 p.setTempoCpu(processo.getTempoCpu());
				 }
			 }
		}

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(processo.getNomeProcesso()).toUri();
		return new ResponseEntity<Processo>(processo, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Deletar Processo" )
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.DELETE, value="{id}")
	public ResponseEntity<Processo> deletar(@Valid @PathVariable String id) {
		Processo p;
		p = null;
		if (listaProcessos.isEmpty()) {
			// processo.setId(0);
		} else {
			
			
			 for(int i =0; i< listaProcessos.size(); i++){
				 if(listaProcessos.get(i).getNomeProcesso().equals(id)){
					 p = listaProcessos.get(i);
					 listaProcessos.remove(i);
				 }
			 }
		}
		
		if (p == null) {
            logger.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity<Processo>(HttpStatus.NOT_FOUND);
        }

		return new ResponseEntity<Processo>(HttpStatus.NO_CONTENT);
	}
}
