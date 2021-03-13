package springboot.service;


import java.util.List;

import org.springframework.http.HttpStatus;

import springboot.dto.AvaliacaoDTO;

public interface AvaliacaoService {
	HttpStatus createAvaliacao(AvaliacaoDTO avaliacaoDto);

	HttpStatus updateAvaliacao(long id,AvaliacaoDTO avaliacaoDto);

	List<AvaliacaoDTO> getAllAvaliacao();

	AvaliacaoDTO getAvaliacaoById(long avaliacaoId);

	HttpStatus deleteAvaliacao(long id);
}
