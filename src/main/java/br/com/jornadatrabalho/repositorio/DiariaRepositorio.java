package br.com.jornadatrabalho.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jornadatrabalho.entidade.Diaria;

@Repository
public interface DiariaRepositorio extends JpaRepository<Diaria, Long> {

	@Query("Select obj from Diaria obj where obj.usuario.id = :id_diaria ORDER BY id desc")
	List<Diaria> findByIdDiaria(@Param(value = "id_diaria") Long id_diaria);
	
	@Query(nativeQuery = true, value = "SELECT count(id) as soma FROM diaria WHERE id = id;")
	int findByQuantidadeDiarias();
}
