package springboot.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import springboot.dto.UtilizadorDTO;

public interface UtilizadorService {
	
	HttpStatus createUtilizador(UtilizadorDTO utilizadorDTO);

	HttpStatus updateUtilizador(long id,UtilizadorDTO utilizadorDTO);

	List<UtilizadorDTO> getAllUtilizador();

	UtilizadorDTO getUtilizadorById(long utilizadorId);

	HttpStatus deleteUtilizador(long id);

}
