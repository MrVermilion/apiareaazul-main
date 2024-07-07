package bancocrudspringboot.controller;

// import bancocrudspringboot.exception.ResourceNotFoundException;
// import bancocrudspringboot.model.ConsultaPadrao;
// import bancocrudspringboot.model.OperadoresConsulta;
// import bancocrudspringboot.model.Cartao;
// import bancocrudspringboot.repository.CartaoRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

// import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CartaoController {


}

// 	@Autowired
// 	private CartaoRepository cartaoRepository;

// 	@GetMapping("/veiculo")
// 	@ResponseStatus(HttpStatus.OK)
// 	public List<Cartao> getAllCadastros() {
// 		return this.cartaoRepository.findAll();
// 	}

//     	// Listar um produto
// 	@GetMapping("/veiculo/{id}")
// 	@ResponseStatus(HttpStatus.OK)
// 	public ResponseEntity<Cartao> getCadastroById(@PathVariable(value = "id") Long cadastroId)
// 	throws ResourceNotFoundException {
// 		Cartao cadastro = cartaoRepository.findById(cadastroId)
// 				.orElseThrow(() -> new ResourceNotFoundException("Cadastro não encontrado para o ID :: " + cadastroId));
		
// 		return ResponseEntity.ok().body(cadastro);
// 	}
		
// 	// Inserir produto
// 	@PostMapping("/veiculo")
// 	@ResponseStatus(HttpStatus.CREATED)
// 	public Cartao createCadastro(@RequestBody Cartao cadastro) {
// 		return this.cartaoRepository.save(cadastro);
// 	}

// 	/// alterar produto    
// 	@PutMapping("/veiculo/{id}")
// 	@ResponseStatus(HttpStatus.OK)
// 	public ResponseEntity<Cartao> updateCadastro(@PathVariable(value = "id") Long cadastroId,
// 												  @Validated 
// 												  @RequestBody Cartao cadastroCaracteristicas) throws ResourceNotFoundException {
// 		Cartao cadastro = cartaoRepository.findById(cadastroId)
// 				.orElseThrow(() -> new ResourceNotFoundException("Cadastro não encontrado para o ID :: " + cadastroId));

// 		//  cadastro.setTipo(cadastroCaracteristicas.getTipo());
// 		// cadastro.setPlaca(cadastroCaracteristicas.getPlaca());
// 		// cadastro.setAno(cadastroCaracteristicas.getAno());
// 		// cadastro.setFabricante(cadastroCaracteristicas.getFabricante());
// 		// cadastro.setModelo(cadastroCaracteristicas.getModelo());

// 		return ResponseEntity.ok(this.cartaoRepository.save(cadastro));
// 	}

// 	// deletar veiculo
// 	@DeleteMapping("/veiculo/{id}")
// 	@ResponseStatus(HttpStatus.OK)
// 	public Map<String, Boolean> deleteCadastro(@PathVariable(value = "id") Long cadastroId)
// 			throws ResourceNotFoundException {
// 		Cartao cadastro = cartaoRepository.findById(cadastroId)
// 				.orElseThrow(() -> new ResourceNotFoundException("Cadastro não encontrado para o ID :: " + cadastroId));

// 		this.cartaoRepository.delete(cadastro);

// 		Map<String, Boolean> resposta = new HashMap<>();

// 		resposta.put("cadastro deletado", Boolean.TRUE);

// 		return resposta;
// 	}

//     @PostMapping("/consultaveiculo")
// 	@ResponseStatus(HttpStatus.OK)
// 	public List<Cartao> consultaCadastro(@Validated @RequestBody ConsultaPadrao cadastro) throws ResourceNotFoundException {

// 		String campoConsulta = cadastro.getCampo();
// 		List<Cartao> listaProduto = new ArrayList<>();

// 		if(cadastro.getValor1() == null){
// 			return this.cartaoRepository.findAll();
// 		} else if(cadastro.getValor1().equals("")){
// 			return this.cartaoRepository.findAll();
// 		}

// 		// OPERADOR -> TODOS
// 		String operador = cadastro.getOperador();
// 		if(operador.equals(OperadoresConsulta.OPERADOR_TODOS)){
// 			return this.cartaoRepository.findAll();
// 		}

// 		// // OPERADOR -> IGUAL alteração aula 11/06
// 		// String operador = cadastro.getOperador();

// 		if(operador.equals(OperadoresConsulta.OPERADOR_IGUAL)){
// 			switch (campoConsulta) {
// 				case "codigoConsulta":
// 					Cartao veiculo = cartaoRepository.findById(Long.parseLong(cadastro.getValor1()))
// 							.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado para o ID: " + cadastro.getValor1()));
// 					listaProduto.add(veiculo);
// 					break;
// 				// case "descricaoConsulta":
// 				// 	listaProduto = this.veiculoRepository.findProdutoByDescricao(cadastro.getValor1());
// 				// 	break;
// 				// case "estoqueConsulta":
// 				// 	listaProduto = this.veiculoRepository.findProdutoByEstoque(Integer.parseInt(cadastro.getValor1()));
// 				// 	break;
// 				// case "precoConsulta":
// 				// 	listaProduto = this.veiculoRepository.findProdutoByPreco(Double.parseDouble(cadastro.getValor1()));
// 				// 	break;					
// 				default:
// 					throw new ResourceNotFoundException("Campo inexistente na tabela do banco de dados!" + cadastro.getCampo());				
// 			}
// 		}

//         return listaProduto;
//     } 
// }