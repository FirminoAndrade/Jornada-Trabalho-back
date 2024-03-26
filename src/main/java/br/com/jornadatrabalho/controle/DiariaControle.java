package br.com.jornadatrabalho.controle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornadatrabalho.dto.DiariaDto;
import br.com.jornadatrabalho.entidade.Diaria;
import br.com.jornadatrabalho.repositorio.DiariaRepositorio;
import br.com.jornadatrabalho.servico.DiariaServico;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://jornada-trabalho.vercel.app",  maxAge = 3600)
@RequestMapping("/api/diaria")
public class DiariaControle {

	@Autowired
	private DiariaRepositorio diariaRepository;

	@Autowired
	private DiariaServico diariaService;

	@GetMapping("/{id}")
	@Operation(summary = "Buscar Diaria")
	public ResponseEntity<Optional<Diaria>> buscarDiaria(@PathVariable Long id) {
		Optional<Diaria> diaria = diariaRepository.findById(id);
		return ResponseEntity.ok().body(diaria);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar Diaria")
	public ResponseEntity<Diaria> atualizarDiaria(@PathVariable Long id, @Valid @RequestBody Diaria dados) {
		Diaria novoDados = diariaService.atualizarDiaria(id, dados);
		return ResponseEntity.ok().body(novoDados);
	}

	@PatchMapping("/{id}")
	@Operation(summary = "Atualizar Diaria Patch")
	public ResponseEntity<Diaria> atualizarDiariaPatch(@PathVariable Long id, @Valid @RequestBody Diaria dados) {
		Diaria novoDados = diariaService.atualizarDiaria(id, dados);
		return ResponseEntity.ok().body(novoDados);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar Diaria")
	public ResponseEntity<?> deletarDiaria(@PathVariable Long id) {
		diariaService.deleteDiaria(id);
		return ResponseEntity.ok().build();

	}

	@GetMapping
	// localhost:8081/api/diaria?usuario=1
	@Operation(summary = "Listar Diaria Por usuario passando ID")
	public ResponseEntity<List<DiariaDto>> listarDiariaPorUsuario(
			@RequestParam(value = "usuario", defaultValue = "0") Long id_usuario) {
		List<Diaria> listadiarias = diariaService.listarDiariasPorUsuario(id_usuario);
		List<DiariaDto> listaDiariasDto = listadiarias.stream().map(DiariaDto::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDiariasDto);
	}

	@PostMapping
	// localhost:8081/diaria?usuario=1
	@Operation(summary = "Cadastrar Diaria")
	public ResponseEntity<Diaria> cadastrarDiaria(@RequestParam(value = "usuario", defaultValue = "0") Long id_usuario,
			@Valid @RequestBody Diaria diaria) {
		Diaria novaDiaria = diariaService.criarDiaria(id_usuario, diaria);
		return ResponseEntity.ok().body(novaDiaria);
	}
	
	@GetMapping("/quantidade")
	@Operation(summary = "Quantidade de Diárias")
	public int valorTotalAgendas() {
		int valorTotal = diariaRepository.findByQuantidadeDiarias();
		return valorTotal;
	}
}
