package br.com.jornadatrabalho.controle;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jornadatrabalho.dto.UsuarioDto;
import br.com.jornadatrabalho.entidade.Usuario;
import br.com.jornadatrabalho.repositorio.UsuarioRepository;
import br.com.jornadatrabalho.servico.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://projeto-pesquisa-fronte.vercel.app", maxAge = 3600)
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/{id}")
	@Operation(summary = "Buscar Usuário")
	public ResponseEntity<Optional<Usuario>> buscarUsuario(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return ResponseEntity.ok().body(usuario);
	}

	@GetMapping("/matricula/{matricula}")
	@Operation(summary = "Buscar Usuário por Matricula")
	public ResponseEntity<Optional<Usuario>> buscarUsuarioPorEmail(@PathVariable String matricula) {
		Optional<Usuario> usuario = usuarioRepository.findByMatricula(matricula);
		return ResponseEntity.ok().body(usuario);
	}

	@PostMapping
	@Operation(summary = "Cadastrar Usuário")
	public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		usuario = usuarioService.criarUsuario(usuario);
		return ResponseEntity.ok().body(usuario);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar Usuário")
	public ResponseEntity<UsuarioDto> atualizarUsuario(@Valid @PathVariable Long id,
			@RequestBody UsuarioDto usuarioDto) {
		usuarioDto.setSenha(encoder.encode(usuarioDto.getSenha()));
		Usuario usuario = usuarioService.atualizarUsuario(id, usuarioDto);
		return ResponseEntity.ok(new UsuarioDto(usuario));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar Usuário")
	public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
		usuarioService.deleteUsuario(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/validarSenha")
	@Operation(summary = "Validar Senha")
	public ResponseEntity<Boolean> validarSenha(@RequestParam String matricula, @RequestParam String senha) {
		Optional<Usuario> optional = usuarioRepository.findByMatricula(matricula);
		if (optional.isEmpty()) {
			ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		Usuario usuario = optional.get();
		boolean valid = encoder.matches(senha, usuario.getSenha());
		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status).body(valid);
	}
}
