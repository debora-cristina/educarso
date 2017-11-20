var xPos = 120;
var yPos = 0;
$(function() {

	$(".js-load-books").on('click', function() {

		$.ajax({

			url : "http://localhost:8080/executar",

			type : "get",

			headers : {

				"Authorization" : "Basic YWxnYXdvcmtzOnMzbmg0"

			},

			success : function(response) {

				desenhaTabela(response);

			}

		});

	})
	
});

$(function() {

	$(".teste").on('click', function() {

		$.ajax({

			url : "http://localhost:8080/executar/processos",

			type : "get",

			headers : {

				"Authorization" : "Basic YWxnYXdvcmtzOnMzbmg0"

			},

			success : function(response) {

				console.log(response);
				processos(response);

			}

		});

	})

});

var count = 0;

function isEmpty(obj) {
	for ( var prop in obj) {
		if (obj.hasOwnProperty(prop))
			return false;
	}

	return true;
}

function compare(a, b) {
	if (a.posicao < b.posicao)
		return -1;
	if (a.posicao > b.posicao)
		return 1;
	return 0;
}

function processos(dados){
	str = JSON.stringify(dados);
	str = JSON.stringify(dados, null, 4); 
	console.log(str);
	
}

function desenhaTabela(dados) {

	var colors = [ 'aqua', 'black', 'blue', 'fuchsia', 'gray', 'green', 'lime',
			'maroon', 'navy', 'olive', 'orange', 'purple', 'red', 'silver',
			'teal', 'white', 'yellow' ];

	$(".js-books-table-body tr").remove();
	str = JSON.stringify(dados);
	str = JSON.stringify(dados, null, 4);
	// output.
	console.log(str); 
	var response = JSON.parse(str);

	var datajson = JSON.parse(str);
	var processos;
	var keyArr = Object.keys(datajson);

	var c = document.getElementById("canvas");
	var ctx = c.getContext("2d");

	// Create gradient

	var nomesProcessos = [];
	var processosExecutando = [];

	var posicao = 0;
	var tempo =0;
	for (var i = 0; i < keyArr.length; i++) {
		var val = datajson[keyArr[i]];
		
		if (val.processos.PRONTO != null ) {

			processosExecutando.push(val.processos.PRONTO);

			for ( var key in val.processos.PRONTO) {
				var obj = val.processos.PRONTO[key];
				console.log(obj.nomeProcesso);

				var nome = "" + obj.nomeProcesso + "";
				if (!isin(nome, nomesProcessos)) {
					nomesProcessos.push({
						"nomeProcesso" : obj.nomeProcesso,
						"posicao" : posicao
					});

					posicao = posicao + 1;
				}
			}

			processosExecutando = [];
		}
		
		if (val.processos.EXECUTANDO != null) {
			
			processosExecutando.push(val.processos.EXECUTANDO);

			for ( var key in val.processos.EXECUTANDO) {
				var obj = val.processos.EXECUTANDO[key];
				exec = val.processos.EXECUTANDO;
			}
			
			var nome = "" + obj.nomeProcesso + "";
			if (!isin(nome, nomesProcessos)) {
				nomesProcessos.push({
					"nomeProcesso" : obj.nomeProcesso,
					"posicao" : posicao
				});

				posicao = posicao + 1;
			}

			processosExecutando = [];
		}		
	}
	
	nomesProcessos.sort(function(a,b) {
	    return a.nomeProcesso < b.nomeProcesso ? -1 : a.nomeProcesso > b.nomeProcesso ? 1 : 0;
	});
	
	var grd = ctx.createLinearGradient(0, 0, processosExecutando.length * 100,
			0);
	grd.addColorStop(0, "red");
	grd.addColorStop(1, "white");

	var exec = [];
	var fin = [];
	var posNome = 70;
	for (var i = 0; i < nomesProcessos.length; i++) {
		ctx.font = "16px Arial";
		ctx.fillStyle = "black";
		ctx.textAlign = "left";
		ctx.fillText(nomesProcessos[i].nomeProcesso, 5, posNome);
		posNome = posNome + 40;
	}

	var posYProcesso = 0;
	var tempoAnterior =0;
	for (var i = 0; i < keyArr.length; i++) {
		var val = datajson[keyArr[i]];

		ctx.font = "12px Arial";
		ctx.fillStyle = "black";
		ctx.textAlign = "center";

		ctx.fillText(tempoAnterior, xPos + 20, 20);
		tempoAnterior = tempoAnterior+1;
		for (var j = 0; j < nomesProcessos.length; j++) {
			ctx.rect(xPos, (nomesProcessos[j].posicao * 40) + 40, 40, 40);
			ctx.stroke();
		}

		xPos = xPos + 40;
				if (val.processos.PRONTO != null) {

			for ( var key in val.processos.PRONTO) {
				var obj = val.processos.PRONTO[key];
				exec = val.processos.PRONTO;
				ctx.fillStyle = "blue";
				for (var k = 0; k < nomesProcessos.length; k++) {
					if (obj.nomeProcesso == nomesProcessos[k].nomeProcesso) {
						ctx.fillRect(120 + ((val.tempo) * 40),
								40 + (nomesProcessos[k].posicao * 40), 40, 40);
					}
				}

			}
		}
				
				if (val.processos.EXECUTANDO != null) {

					for ( var key in val.processos.EXECUTANDO) {
						var obj = val.processos.EXECUTANDO[key];
						exec = val.processos.EXECUTANDO;
						ctx.fillStyle = "yellow";
						for (var k = 0; k < nomesProcessos.length; k++) {
							if (obj.nomeProcesso == nomesProcessos[k].nomeProcesso) {
								ctx.fillRect(120 + ((val.tempo) * 40),
										40 + (nomesProcessos[k].posicao * 40), 40, 40);
							}

						}

					}
				}



		if (val.processos.ENCERRADO != null) {

			for ( var key in val.processos.ENCERRADO) {
				var obj = val.processos.ENCERRADO[key];
				exec = val.processos.ENCERRADO;

				// console.log("exec" + obj.nomeProcesso + " tempo" +
				// val.tempo);
				ctx.fillStyle = "red";
				for (var k = 0; k < nomesProcessos.length; k++) {
					if (obj.nomeProcesso == nomesProcessos[k].nomeProcesso) {
						ctx.fillRect(120 + ((val.tempo) * 40),
								40 + (nomesProcessos[k].posicao * 40), 40, 40);
					}
				}
			}
		}

	}

	var posicaoLegenda = 120;
	var posicaoY = nomesProcessos.length * 40;

	ctx.font = "12px Arial";
	ctx.fillStyle = "black";
	ctx.textAlign = "center";

	ctx.fillStyle = "blue";
	ctx.fillRect(posicaoLegenda , 165, 20, 20);
	ctx.fillStyle = "black";
	ctx.fillText("PRONTO", posicaoLegenda + 55, 180);
	
	

	posicaoLegenda = posicaoLegenda + 120;

	ctx.fillStyle = "yellow";
	ctx.fillRect(posicaoLegenda + 5, 165, 20, 20);
	ctx.fillStyle = "black";
	ctx.fillText(" EXECUTANDO", posicaoLegenda + 75, 180);
	
	

	posicaoLegenda = posicaoLegenda + 120;

	ctx.fillStyle = "red";
	ctx.fillRect(posicaoLegenda+5, 165, 20, 20);
	ctx.fillStyle = "black";
	ctx.fillText("ENCERRADO", posicaoLegenda + 85, 180);
	
	

	/*
	 * dados.forEach(function(json) { for ( var key in json) { var log =
	 * "Tempo:"; //log = log.replace(":", key); // key str =
	 * JSON.stringify(json[key]); //log = log.replace("Processos:", str); //
	 * value console.log(log); } });
	 */

}

function isin(n, a) {
	for (var i = 0; i < a.length; i++) {

		if (a[i].nomeProcesso == n) {
			console.log(a[i].nomeProcesso + "nome vetor" + n);
			return true;

		}
	}

	return false;
}

function retornaCor(nome, vetor) {
	for (var i = 0; i < vetor.length; i++) {

		if (vetor[i].nomeProcesso == nome) {
			return vetor[i].cor;

		}
	}

	return false;
}

function desenhaLinha(linha) {

	var linhaTabela = $("<tr/>");

	$(".js-books-table-body").append(linhaTabela);

	linhaTabela.append("<td>" + linha.nomeProcesso + "</td>");

	linhaTabela.append("<td>" + linha.tempoChegada + "</td>");

	linhaTabela.append("<td>" + linha.tempoCpu + "</td>");

}