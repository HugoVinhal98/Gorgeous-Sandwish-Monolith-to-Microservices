package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
	

}
