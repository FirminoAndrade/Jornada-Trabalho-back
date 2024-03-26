package br.com.jornadatrabalho.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class InstaciaDeDados {

	@Autowired
	private ObjetosTeste dbServico;

	//@Bean
	public void instaciaBaseDeDados() {
		this.dbServico.instaciaBaseDeDados();
	}
}
