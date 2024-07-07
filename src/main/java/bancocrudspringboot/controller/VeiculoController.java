package bancocrudspringboot.controller;

import bancocrudspringboot.exception.ResourceNotFoundException;
import bancocrudspringboot.model.ConsultaPadrao;
import bancocrudspringboot.model.OperadoresConsulta;
import bancocrudspringboot.model.Veiculo;
import bancocrudspringboot.repository.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;

	// Listar todos os veiculos
	@GetMapping("/veiculo")
	@ResponseStatus(HttpStatus.OK)
	public List<Veiculo> getAllCadastros() {
		return this.veiculoRepository.findAll();
	}

	// Listar um veiculo
	@GetMapping("/veiculo/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Veiculo> getCadastroById(@PathVariable(value = "id") Long cadastroId)
	throws ResourceNotFoundException {
		Veiculo cadastro = veiculoRepository.findById(cadastroId)
				.orElseThrow(() -> new ResourceNotFoundException("Cadastro de veiculo não encontrado para o ID : " + cadastroId));
		
		return ResponseEntity.ok().body(cadastro);
	}
		
	// Inserir veiculo
	@PostMapping("/veiculo")
	@ResponseStatus(HttpStatus.CREATED)
	public Veiculo createCadastro(@RequestBody Veiculo cadastro) {
		return this.veiculoRepository.save(cadastro);
	}

	/// alterar veiculo    
	@PutMapping("/veiculo/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Veiculo> updateCadastro(@PathVariable(value = "id") Long cadastroId,
												  @Validated 
												  @RequestBody Veiculo cadastroCaracteristicas) throws ResourceNotFoundException {
		Veiculo cadastro = veiculoRepository.findById(cadastroId)
				.orElseThrow(() -> new ResourceNotFoundException("Cadastro não encontrado para o ID : " + cadastroId));

		cadastro.setTipo(cadastroCaracteristicas.getTipo());
		cadastro.setPlaca(cadastroCaracteristicas.getPlaca());
		cadastro.setAno(cadastroCaracteristicas.getAno());
		cadastro.setFabricante(cadastroCaracteristicas.getFabricante());
		cadastro.setModelo(cadastroCaracteristicas.getModelo());

		return ResponseEntity.ok(this.veiculoRepository.save(cadastro));
	}

	// deletar veiculo
	@DeleteMapping("/veiculo/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Boolean> deleteCadastro(@PathVariable(value = "id") Long cadastroId)
			throws ResourceNotFoundException {
		Veiculo cadastro = veiculoRepository.findById(cadastroId)
				.orElseThrow(() -> new ResourceNotFoundException("Cadastro não encontrado para o ID : " + cadastroId));

		this.veiculoRepository.delete(cadastro);

		Map<String, Boolean> resposta = new HashMap<>();

		resposta.put("cadastro deletado", Boolean.TRUE);

		return resposta;
	}


	// consulta por campo e operadores no insomnia em POST consulta
    @PostMapping("/consultaveiculo")
	@ResponseStatus(HttpStatus.OK)
	public List<Veiculo> consultaCadastro(@Validated @RequestBody ConsultaPadrao cadastro) throws ResourceNotFoundException {

		String campoConsulta = cadastro.getCampo();
		List<Veiculo> listaVeiculo = new ArrayList<>();

		// conulta produto por valor1=id, onde pesquisa pelo id existente ou ""(vazio) onde retorna todos (findAll)
		if(cadastro.getValor1() == null){
			return this.veiculoRepository.findAll();
		} else if(cadastro.getValor1().equals("")){
			return this.veiculoRepository.findAll();
		}


		// OPERADOR -> TODOS
		// ex insomnia:
		// {
		// 	"campo":"codigoConsulta",
		// 	"operador":"todos",
		// 	"valor1":"1"	aqui ignora valor e traz todos os registros
		// }

		String operador = cadastro.getOperador();
		if(operador.equals(OperadoresConsulta.OPERADOR_TODOS)){
			return this.veiculoRepository.findAll();
		}



		
		// Prof orientou deixar por ultimo	
		// "placa": "MFI-7815",
		// "ano": "2015",
		// "fabricante": "HONDA",
		// // "modelo": "NXR BROS"
		
		if(operador.equals(OperadoresConsulta.OPERADOR_IGUAL)){
			switch (campoConsulta) {

				// valor1 = id
				case "codigoConsulta":
					Veiculo veiculo = veiculoRepository.findVeiculoById(Long.parseLong(cadastro.getValor1()))
							.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado para o veiculo de ID: " + cadastro.getValor1()));
					listaVeiculo.add(veiculo);
					break;

				// tipo(de veiculo) ---  (1 = MOTO)   (2 = CARRO)  (3 = CAMINHÃO)
				// no insomnia procurar por
				case "tipoConsulta":
					listaVeiculo = this.veiculoRepository.findVeiculoByTipo(Integer.parseInt(cadastro.getValor1()));
					break;

				// placa
				case "placaConsulta":
					listaVeiculo = this.veiculoRepository.findVeiculoByPlaca(cadastro.getValor1());
					break;

				// ano, pesquisa por ano exatamente igual o valor digitado
				case "anoConsulta":
					listaVeiculo = this.veiculoRepository.findVeiculoByAno(cadastro.getValor1());
					break;


				case "modeloConsulta":
					listaVeiculo = this.veiculoRepository.findVeiculoByModelo(cadastro.getValor1());
					break;		
		
		
				default:
					throw new ResourceNotFoundException("Campo inexistente na tabela do banco de dados!" + cadastro.getCampo());				
			}

		}

        return listaVeiculo;
    } 

}