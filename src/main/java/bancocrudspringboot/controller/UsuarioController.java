package bancocrudspringboot.controller;

import bancocrudspringboot.exception.ResourceNotFoundException;
import bancocrudspringboot.model.ConsultaPadrao;
import bancocrudspringboot.model.OperadoresConsulta;
import bancocrudspringboot.model.Usuario;
import bancocrudspringboot.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;


@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UsuarioRepository usuarioRepository;

	// LISTAR TODOS OS USUÁRIOS
	@GetMapping("/usuario_sistema")
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> getAllCadastros() {
		return this.usuarioRepository.findAll();
		}
	

		// // LISTAR UM VEÍCULO
		// @GetMapping("/usuario/{id}")
		// @ResponseStatus(HttpStatus.OK)
		// public ResponseEntity<Usuario> getCadastroById(@PathVariable(value = "id") Long cadastroId)
		// throws ResourceNotFoundException {
		// 	Usuario cadastro = usuarioRepository.findById(cadastroId)
		// 			.orElseThrow(() -> new ResourceNotFoundException("Cadastro de usuário não encontrado para o ID : " + cadastroId));
			
		// 	return ResponseEntity.ok().body(cadastro);
		// }


		// INSERIR USUÁRIO
		@PostMapping("/cadastrousuario")
		@ResponseStatus(HttpStatus.CREATED)
		public Usuario createCadastro(@RequestBody Usuario cadastro) {
			return this.usuarioRepository.save(cadastro);
			}


		// ALTERAR USUÁRIO  
		@PutMapping("/usuario/{id}")
		@ResponseStatus(HttpStatus.OK)
		public ResponseEntity<Usuario> updateCadastro(@PathVariable(value = "id") Long cadastroId,
													@Validated 
													@RequestBody Usuario cadastroCaracteristicas) throws ResourceNotFoundException {
			Usuario cadastro = usuarioRepository.findById(cadastroId)
					.orElseThrow(() -> new ResourceNotFoundException("Cadastro não encontrado para o ID : " + cadastroId));

			cadastro.setId(cadastroCaracteristicas.getId());
			cadastro.setTelefone(cadastroCaracteristicas.getTelefone());
			cadastro.setSenha(cadastroCaracteristicas.getSenha());
			cadastro.setCidade(cadastroCaracteristicas.getCidade());

			return ResponseEntity.ok(this.usuarioRepository.save(cadastro));
		}
	

	
			// DELETAR USUÁRIO
			@DeleteMapping("/usuario/{id}")
			@ResponseStatus(HttpStatus.OK)
			public Map<String, Boolean> deleteCadastro(@PathVariable(value = "id") Long cadastroId)
					throws ResourceNotFoundException {
				Usuario cadastro = usuarioRepository.findById(cadastroId)
						.orElseThrow(() -> new ResourceNotFoundException("Cadastro não encontrado para o ID : " + cadastroId));

				this.usuarioRepository.delete(cadastro);

				Map<String, Boolean> resposta = new HashMap<>();

				resposta.put("Usuário Deletado", Boolean.TRUE);

				return resposta;
			}


			// CONSULTA POR CAMPO E OPERADORES NO INSOMNIA EM POST CONSULTA
			@PostMapping("/consultausuario")
			@ResponseStatus(HttpStatus.OK)
			public List<Usuario> consultaCadastro(@Validated @RequestBody ConsultaPadrao cadastro) throws ResourceNotFoundException {

				String campoConsulta = cadastro.getCampo();
				List<Usuario> listaUsuario = new ArrayList<>();

				// conulta produto por valor1=id, onde pesquisa pelo id existente ou ""(vazio) onde retorna todos (findAll)
				if(cadastro.getValor1() == null){
					return this.usuarioRepository.findAll();
				} else if(cadastro.getValor1().equals("")){
					return this.usuarioRepository.findAll();
				}


				String operador = cadastro.getOperador();
				if(operador.equals(OperadoresConsulta.OPERADOR_TODOS)){
					return this.usuarioRepository.findAll();
				}

			
				if(operador.equals(OperadoresConsulta.OPERADOR_IGUAL)){
					switch (campoConsulta) {

						// CONSULTA POR ID
						case "usuarioConsulta":
							Usuario usuario = usuarioRepository.findUsuarioById(Long.parseLong(cadastro.getValor1()))
									.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado para o usuário de ID: " + cadastro.getValor1()));
							listaUsuario.add(usuario);
							break;

			// 			// tipo(de veiculo) ---  (1 = MOTO)   (2 = CARRO)  (3 = CAMINHÃO)
			// 			// no insomnia procurar por
			// 			case "tipoConsulta":
			// 				listaUsuario = this.usuarioRepository.findUsuarioByTipo(Integer.parseInt(cadastro.getValor1()));
			// 				break;

						// CONSULTA POR TELEFONE
						case "telefoneConsulta":
							listaUsuario = this.usuarioRepository.findUsuarioByTelefone(cadastro.getValor1());
							break;

			// 			// // ano, pesquisa por ano exatamente igual o valor digitado
			// 			// case "anoConsulta":
			// 			// 	listaVeiculo = this.veiculoRepository.findVeiculoByAno(cadastro.getValor1());
			// 			// 	break;


			// 			// case "modeloConsulta":
			// 			// 	listaVeiculo = this.veiculoRepository.findVeiculoByModelo(cadastro.getValor1());
			// 			// 	break;		
				
				
			// 			default:
			// 				throw new ResourceNotFoundException("Campo inexistente na tabela do banco de dados!" + cadastro.getCampo());				
			// 		}

			// 	}

				} 

		}
				return listaUsuario;
	}
}

	