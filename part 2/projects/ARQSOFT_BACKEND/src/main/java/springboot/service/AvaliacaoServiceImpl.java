package springboot.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.dto.AvaliacaoDTO;
import springboot.exception.ResourceNotFoundException;
import springboot.model.Avaliacao;
import springboot.model.Encomenda;
import springboot.model.Sandes;
import springboot.model.Utilizador;
import springboot.repository.AvaliacaoRepository;
import springboot.repository.EncomendaRepository;
import springboot.repository.SandesRepository;
import springboot.repository.UtilizadorRepository;
import utils.ObjectMapperUtils;
import utils.PropertiesObtain;

@Service
@Transactional
public class AvaliacaoServiceImpl implements AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	@Autowired
	private SandesRepository sandesRepository;
	@Autowired
	private UtilizadorRepository utilizadorRepository;
	@Autowired
	private EncomendaRepository encomendaRepository;

	@Override
	public HttpStatus createAvaliacao(AvaliacaoDTO avaliacaoDto) {
		Optional<Utilizador> userDB = this.utilizadorRepository.findById(avaliacaoDto.getUtilizadorId());
		if (!userDB.isPresent()) {
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

		List<Encomenda> encomendas = this.encomendaRepository.findAll();
		for (Encomenda encomenda : encomendas) {
			if (encomenda.getUserId() == avaliacaoDto.getUtilizadorId()) {
				long idToFind = avaliacaoDto.getSandesId();
				Collection<Integer> listaEncomendas = encomenda.getSandesId();
				for (Integer encomendaId : listaEncomendas) {
					if (encomendaId == idToFind) {
						Avaliacao.Builder avaliacaoBuilder = new Avaliacao.Builder();
						Avaliacao avaliacao = avaliacaoBuilder.setUtilizadorId(avaliacaoDto.getUtilizadorId())
								.setNota(avaliacaoDto.getNota()).setSandesId(avaliacaoDto.getSandesId())
								.setComentario(avaliacaoDto.getComentario()).build();

						avaliacaoRepository.save(avaliacao);
						return HttpStatus.OK;
					}
				}
			}
		}
		throw new ResourceNotFoundException("You cannot review a sandwish that you never ordered!");

	}

	@Override
	public HttpStatus updateAvaliacao(long id, AvaliacaoDTO avaliacaoDto) {
		Optional<Avaliacao> AvaliacaoDB = this.avaliacaoRepository.findById(id);
		if (avaliacaoDto.getUtilizadorId() != 0) {
			Optional<Utilizador> userDB = this.utilizadorRepository.findById(avaliacaoDto.getUtilizadorId());
			if (!userDB.isPresent()) {
				throw new ResourceNotFoundException("User not found with id : " + avaliacaoDto.getUtilizadorId());
			}
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
			avaliacaoUpdate.setNota(avaliacaoDto.getNota());
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
		return ObjectMapperUtils.mapAll(listaAvaliacao, AvaliacaoDTO.class);
	}

	@Override
	public AvaliacaoDTO getAvaliacaoById(long avaliacaoId) {
		Optional<Avaliacao> avaliacaoDb = this.avaliacaoRepository.findById(avaliacaoId);
		if (avaliacaoDb.isPresent()) {
			return ObjectMapperUtils.map(avaliacaoDb.get(), AvaliacaoDTO.class);
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
