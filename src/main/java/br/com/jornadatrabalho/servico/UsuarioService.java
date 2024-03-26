package br.com.jornadatrabalho.servico;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jornadatrabalho.dto.UsuarioDto;
import br.com.jornadatrabalho.entidade.Usuario;
import br.com.jornadatrabalho.repositorio.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// buscar por id
	public Usuario findById(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.get();
	}

	// listar
	public static List<UsuarioDto> listarUsuarios(List<Usuario> usuario) {
		return usuario.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}

	// criar
	public Usuario criarUsuario(Usuario usuario) {
		usuario.setId(null);
		return usuarioRepository.save(usuario);
	}

	// atualizar
	public Usuario atualizarUsuario(Long id, UsuarioDto usuarioDto) {
		Usuario usuario = findById(id);
		usuario.setNome(usuarioDto.getNome());
		usuario.setMatricula(usuarioDto.getMatricula());
		usuario.setSenha(usuarioDto.getSenha());

		return usuarioRepository.save(usuario);
	}

	// deletar
	public void deleteUsuario(Long id) {
		findById(id);
		usuarioRepository.deleteById(id);
	}
}
