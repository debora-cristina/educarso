package com.educarso.simulador.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educarso.simulador.domain.EscalonadorDomain;

@RestController
@RequestMapping("/escalonador")
public class EscalonadorResource {
	
	private EscalonadorDomain escalona = new EscalonadorDomain();

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<EscalonadorDomain> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(escalona);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvarEscalonador(@Valid @RequestBody EscalonadorDomain esc) {

		escalona.setConfiguracao(esc.getConfiguracao());
		escalona.setListaProcessos(esc.getListaProcessos());
		escalona.setPolitica(esc.getPolitica());
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(esc.getPolitica()).toUri();
		return ResponseEntity.created(uri).build();

	}

}
