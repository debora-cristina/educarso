package com.educarso.simulador.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.educarso.simulador.domain.PoliticaDomain;
import com.educarso.simulador.enumerador.Politica;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Api(value = "politicas")
@RestController
@RequestMapping("/politicas")
public class PoliticaResource {

	private PoliticaDomain politica = new PoliticaDomain();

	@ApiOperation(value = "Listar Politicas" )
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Politica[]> listar() {
		Politica[] politicas = politica.getPoliticas().toArray(new Politica[politica.getPoliticas().size()]);

		return ResponseEntity.status(HttpStatus.OK).body(politicas);
	}
}
