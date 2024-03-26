package br.com.jornadatrabalho.servico;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jornadatrabalho.dto.DiariaDto;
import br.com.jornadatrabalho.entidade.Diaria;
import br.com.jornadatrabalho.entidade.Usuario;
import br.com.jornadatrabalho.repositorio.DiariaRepositorio;

@Service
public class DiariaServico {

	@Autowired
	private DiariaRepositorio diariaRepository;

	@Autowired
	private UsuarioService usuarioService;

	// buscar por id
	public Diaria findById(Long id) {
		Optional<Diaria> diaria = diariaRepository.findById(id);
		return diaria.get();
	}

	public List<Diaria> listarDiariasPorUsuario(Long id_usuario) {
		usuarioService.findById(id_usuario);
		return diariaRepository.findByIdDiaria(id_usuario);
	}

	// listar
	public static List<DiariaDto> listarTodasDiarias(List<Diaria> diaria) {
		return diaria.stream().map(DiariaDto::new).collect(Collectors.toList());
	}

	// criar
	public Diaria criarDiaria(Long id_usuario, Diaria diaria) {
		diaria.setId(null);
		Usuario usuario = usuarioService.findById(id_usuario);
		diaria.setUsuario(usuario);
		return diariaRepository.save(diaria);
	}

	// atualizar
	public Diaria atualizarDiaria(Long id, Diaria dados) {
		Diaria novoDados = findById(id);
		atualizarDadosDaDiaria(novoDados, dados);
		return diariaRepository.save(novoDados);
	}

	// atualizar dados do Diaria
	private void atualizarDadosDaDiaria(Diaria novoDados, Diaria diaria) {
		novoDados.setAtividade(diaria.getAtividade());
		novoDados.setNumero_veiculo(diaria.getNumero_veiculo());
		novoDados.setLinha(diaria.getLinha());
		novoDados.setKm_inicial(diaria.getKm_inicial());
		novoDados.setKm_final(diaria.getKm_final());
		novoDados.setHora_inicial(diaria.getHora_inicial());
		novoDados.setHora_final(diaria.getHora_final());
		novoDados.setData(diaria.getData());
		novoDados.setValor_depositado(diaria.getValor_depositado());
		novoDados.setNumero_passageiro(diaria.getNumero_passageiro());
		novoDados.setObservacao(diaria.getObservacao());

	}
	// deletar
	public void deleteDiaria(Long id) {
		findById(id);
		diariaRepository.deleteById(id);
	}
}
