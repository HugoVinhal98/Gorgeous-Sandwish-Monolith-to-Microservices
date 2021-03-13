package springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import springboot.dto.AvaliacaoDTO;
import springboot.exception.ResourceNotFoundException;
import springboot.model.Avaliacao;
import springboot.model.Sandes;
import springboot.model.ValueObject.Nota;
import springboot.repository.AvaliacaoRepository;
import springboot.repository.SandesRepository;
import utils.PropertiesObtain;

@Service
@Transactional
public class AvaliacaoServiceImpl implements AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	@Autowired
	private SandesRepository sandesRepository;

	@Override
	public HttpStatus createAvaliacao(AvaliacaoDTO avaliacaoDto) {
		try {
			final String uri = "http://localhost:8010/utilizador/" + avaliacaoDto.getUtilizadorId();

			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getForObject(uri, Object.class);
		} catch (Exception e) {
			throw new ResourceNotFoundException("User not found with id : " + avaliacaoDto.getUtilizadorId());
		}

		Optional<Sandes> sandesDB = sandesRepository.findById(avaliacaoDto.getSandesId());
		if (!sandesDB.isPresent()) {
			throw new ResourceNotFoundException("Sandes not found with id : " + avaliacaoDto.getSandesId());
		}
		int min = Integer.parseInt(PropertiesObtain.getPropertiesValue("rate.min"));
		int max = Integer.parseInt(PropertiesObtain.getPropertiesValue("rate.max"));
		int nota = (int) avaliacaoDto.getNota();
		if (nota < min || nota > max) {
			throw new ResourceNotFoundException(
					"Erro a inserir a avaliacao! Lembra-te que deve ser entre " + min + " e " + max);
		}
		Avaliacao.Builder avaliacaoBuilder = new Avaliacao.Builder();
		Avaliacao avaliacao = avaliacaoBuilder.setUtilizadorId(avaliacaoDto.getUtilizadorId())
				.setSandesId(avaliacaoDto.getSandesId()).setNota(new Nota(avaliacaoDto.getNota()))
				.setComentario(avaliacaoDto.getComentario()).build();
		avaliacaoRepository.save(avaliacao);
		return HttpStatus.OK;
	}

	@Override
	public HttpStatus updateAvaliacao(long id, AvaliacaoDTO avaliacaoDto) {
		Optional<Avaliacao> AvaliacaoDB = this.avaliacaoRepository.findById(id);
		if (avaliacaoDto.getUtilizadorId() != 0) {
			/*
			 * Optional<Utilizador> userDB =
			 * this.utilizadorRepository.findById(avaliacaoDto.getUtilizadorId()); if
			 * (!userDB.isPresent()) { throw new
			 * ResourceNotFoundException("User not found with id : " +
			 * avaliacaoDto.getUtilizadorId()); }
			 */
		}
		if (avaliacaoDto.getSandesId() != 0) {
			Optional<Sandes> sandesDB = sandesRepository.findById(avaliacaoDto.getSandesId());
			if (!sandesDB.isPresent()) {
				throw new ResourceNotFoundException("Sandes not found with id : " + avaliacaoDto.getSandesId());
			}
		}

		int min = Integer.parseInt(PropertiesObtain.getPropertiesValue("rate.min"));
		int max = Integer.parseInt(PropertiesObtain.getPropertiesValue("rate.max"));
		int nota = (int) avaliacaoDto.getNota();
		if (nota < min || nota > max) {
			throw new ResourceNotFoundException(
					"Erro a inserir a avaliacao! Lembra-te que deve ser entre " + min + " e " + max);
		}

		if (AvaliacaoDB.isPresent()) {
			Avaliacao avaliacaoUpdate = AvaliacaoDB.get();
			avaliacaoUpdate.setNota((new Nota(avaliacaoDto.getNota())));
			if (avaliacaoDto.getComentario() != null)
				avaliacaoUpdate.setComentario(avaliacaoDto.getComentario());
			avaliacaoUpdate.setUpdatedAt(new Date());
			avaliacaoRepository.save(avaliacaoUpdate);
			return HttpStatus.OK;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + id);
		}
	}

	@Override
	public List<AvaliacaoDTO> getAllAvaliacao() {
		List<Avaliacao> listaAvaliacao = this.avaliacaoRepository.findAll();
		List<AvaliacaoDTO> list=new ArrayList<>();
		for (Avaliacao avaliacao : listaAvaliacao) {
			list.add(avaliacao.transformToDto());
		}
		return list;
	}

	@Override
	public AvaliacaoDTO getAvaliacaoById(long avaliacaoId) {
		Optional<Avaliacao> avaliacaoDb = this.avaliacaoRepository.findById(avaliacaoId);
		if (avaliacaoDb.isPresent()) {
			return avaliacaoDb.get().transformToDto();
		} else {
			throw new ResourceNotFoundException("id nao existe");
		}
	}

	@Override
	public HttpStatus deleteAvaliacao(long id) {
		Optional<Avaliacao> avaliacaoDb = this.avaliacaoRepository.findById(id);
		this.avaliacaoRepository.delete(avaliacaoDb.get());
		return HttpStatus.OK;

	}

}
