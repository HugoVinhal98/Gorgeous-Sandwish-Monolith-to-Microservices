package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

}
