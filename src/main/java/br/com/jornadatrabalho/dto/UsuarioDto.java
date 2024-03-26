package br.com.jornadatrabalho.dto;

import br.com.jornadatrabalho.entidade.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {

	private Long id;
	private String nome;
	private String matricula;
	private String senha;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.matricula = usuario.getMatricula();
		this.senha = usuario.getSenha();
	}
}
