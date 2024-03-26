package br.com.jornadatrabalho.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Campo NOME é requerido!")
	@Length(min = 3, max = 50, message = "O Campo NOME deve ter entre 3 e 50 caracteres.")
	private String nome;

	@NotEmpty(message = "Campo Matricula é requerido!")
	@Column(unique = true)
	private String matricula;

	@NotEmpty(message = "Campo SENHA é requerido!")
	private String senha;

	@OneToMany(mappedBy = "usuario")
	private List<Diaria> diarias = new ArrayList<>();

	public Usuario(Long id, String nome, String matricula, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
	}

}
