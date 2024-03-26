package br.com.jornadatrabalho.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.jornadatrabalho.entidade.Diaria;
import br.com.jornadatrabalho.entidade.Usuario;
import br.com.jornadatrabalho.repositorio.DiariaRepositorio;
import br.com.jornadatrabalho.repositorio.UsuarioRepository;

@Service
public class ObjetosTeste {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private DiariaRepositorio diariaRepositorio;

	@Autowired
	private PasswordEncoder encoder;

	@Bean
	public void instaciaBaseDeDados() {

		String senha = "q";
		Usuario usuario = new Usuario(null, "Firmino", "11424", senha);
		usuario.setSenha(encoder.encode(senha));
		usuarioRepository.save(usuario);

//		Usuario usuario1 = new Usuario(null, "Andrade", "w", senha);
//		usuario1.setSenha(encoder.encode(senha));
//		usuarioRepository.save(usuario1);

//		Diaria diaria1 = new Diaria(null, "SIM", 0001, 4004, 100, 200, "13:00", "22:00", "08/02/2024", "120,50", 400,
//				"nenhuma observação", usuario);
//		Diaria diaria2 = new Diaria(null, "NÃO", 0002, 4005, 00000000, 00000100, "13:00", "22:00", "09/02/2024",
//				"100.25", 00500, "nenhuma observação", usuario);
//		diariaRepositorio.save(diaria1);
//		diariaRepositorio.save(diaria2);

	}
}
