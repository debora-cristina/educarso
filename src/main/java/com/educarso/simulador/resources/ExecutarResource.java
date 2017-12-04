package com.educarso.simulador.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educarso.simulador.domain.EscalonadorDomain;
import com.educarso.simulador.domain.Processo;
import com.educarso.simulador.model.Escalonador;
import com.educarso.simulador.view.IPolitica;
import com.educarso.simulador.view.MapaProcessos;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import lombok.Getter;
import lombok.Setter;

@Api(value = "escalonar")
@RestController
@RequestMapping("/executar")
public class ExecutarResource {
	@Getter
	@Setter
	private List<MapaProcessos> mapa = new ArrayList<MapaProcessos>();
	private EscalonadorDomain escalonadorExec = new EscalonadorDomain();
	private List<Processo> listaProcessos = new ArrayList<Processo>();

    @ApiOperation(value = "Resultado do Escalonamento" )
    //Diz ao Swagger que essa operação REST deve ser documentado
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<MapaProcessos[]> listar() {

		Escalonador escalonador = new Escalonador();
		escalonador.getPolitica(escalonadorExec.getPolitica());

		IPolitica escalona = escalonador.getPolitica(escalonadorExec.getPolitica());
		mapa = escalona.executar(listaProcessos, escalonadorExec.getConfiguracao());

		MapaProcessos[] processos = mapa.toArray(new MapaProcessos[mapa.size()]);
		if(!listaProcessos.isEmpty()){
			listaProcessos.clear();
		}
		return ResponseEntity.status(HttpStatus.OK).body(processos);
	}

    @ApiOperation(value = "Adicionar Escalonador" )
    @ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody EscalonadorDomain dados) {
		escalonadorExec.setPolitica(dados.getPolitica());
		if (dados.getConfiguracao() != null) {
			escalonadorExec.setConfiguracao(dados.getConfiguracao());
		}
		escalonadorExec.setListaProcessos(listaProcessos);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dados.getPolitica())
				.toUri();
		return ResponseEntity.created(uri).build();

	}

    @ApiOperation(value = "Salvar Lista Processos" )
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/processos", method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Processo processo) {
		listaProcessos.add(processo);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(processo.getNomeProcesso()).toUri();
		return ResponseEntity.created(uri).build();
	}

    @ApiOperation(value = "Listar Processos" )
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin
	@RequestMapping(value = "/processos", method = RequestMethod.GET)
	public ResponseEntity<List<Processo>> listarProcessos() {

		return ResponseEntity.status(HttpStatus.OK).body(listaProcessos);
	}

}
