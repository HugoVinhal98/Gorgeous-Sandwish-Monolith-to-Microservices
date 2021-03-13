package springboot.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import springboot.dto.EncomendaDTO;

public interface EncomendaService {
	
	HttpStatus createEncomenda(EncomendaDTO encomendaDTO);

	HttpStatus updateEncomenda(long id,EncomendaDTO encomendaDTO);

	List<EncomendaDTO> getAllEncomendas();

	EncomendaDTO getEncomendaById(long encomendaId);

	HttpStatus deleteEncomenda(long id);

}
