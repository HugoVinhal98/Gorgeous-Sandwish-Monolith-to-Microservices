package springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.model.Encomenda;
public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {
	List<Encomenda> findByUserIdAndSandesId(long userId, long sandesId);

}
