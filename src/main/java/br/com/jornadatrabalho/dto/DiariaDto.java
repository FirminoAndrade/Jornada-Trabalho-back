package br.com.jornadatrabalho.dto;

import br.com.jornadatrabalho.entidade.Diaria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiariaDto {
	private Long id;
	private String atividade;
	private String numero_veiculo;
	private int linha;
	private int km_inicial;
	private int km_final;
	private String hora_inicial;
	private String hora_final;
	private String data;
	private String valor_depositado;
	private int numero_passageiro;
	private String observacao;


	public DiariaDto(Diaria diaria) {
		this.id = diaria.getId();
		this.atividade = diaria.getAtividade();
		this.numero_veiculo = diaria.getNumero_veiculo();
		this.linha = diaria.getLinha();
		this.km_inicial = diaria.getKm_inicial();
		this.km_final = diaria.getKm_final();
		this.hora_inicial = diaria.getHora_inicial();
		this.hora_final = diaria.getHora_final();
		this.data = diaria.getData();
		this.valor_depositado = diaria.getValor_depositado();
		this.numero_passageiro = diaria.getNumero_passageiro();
		this.observacao = diaria.getObservacao();

	}
}
