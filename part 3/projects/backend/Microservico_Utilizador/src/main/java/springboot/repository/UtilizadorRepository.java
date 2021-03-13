package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.model.Utilizador;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long>{

}
