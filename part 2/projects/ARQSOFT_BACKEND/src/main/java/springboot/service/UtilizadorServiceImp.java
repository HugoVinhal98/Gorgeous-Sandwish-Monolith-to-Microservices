package springboot.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.dto.UtilizadorDTO;
import springboot.exception.ResourceNotFoundException;
import springboot.model.Utilizador;
import springboot.repository.UtilizadorRepository;
import utils.ObjectMapperUtils;

@Service
@Transactional
public class UtilizadorServiceImp implements UtilizadorService {

	@Autowired
	private UtilizadorRepository utilizadorRepository;

	@Override
	public HttpStatus createUtilizador(UtilizadorDTO utilizadorDTO) {
		
		Utilizador.Builder utilizadorBuilder = new Utilizador.Builder();
		Utilizador utilizador = utilizadorBuilder.setDescricao(utilizadorDTO.getDescricao()).setNome(utilizadorDTO.getNome()).setisVoluntrio(utilizadorDTO.getIsVoluntario()).build();
		
		utilizadorRepository.save(utilizador);
		return HttpStatus.OK;
	}

	@Override
	public HttpStatus updateUtilizador(long id, UtilizadorDTO utilizador) {
		Optional<Utilizador> utilizadorDB = this.utilizadorRepository.findById(id);

		if (utilizadorDB.isPresent()) {
			Utilizador utilizadorUpdate = utilizadorDB.get();
			if (utilizador.getDescricao() != null)
				utilizadorUpdate.setDescricao(utilizador.getDescricao());
			if (utilizador.getNome() != null)
				utilizadorUpdate.setNome(utilizador.getNome());
			if (!utilizador.getIsVoluntario() == utilizadorUpdate.getIsVoluntario())
				utilizadorUpdate.setIsVoluntario(utilizador.getIsVoluntario());
			utilizadorUpdate.setUpdatedAt(new Date());

			utilizadorRepository.save(utilizadorUpdate);
			
			return HttpStatus.OK;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + id);
		}
	}

	@Override
	public List<UtilizadorDTO> getAllUtilizador() {
		List<Utilizador> listaU= this.utilizadorRepository.findAll();
		return ObjectMapperUtils.mapAll(listaU, UtilizadorDTO.class);
	}

	@Override
	public UtilizadorDTO getUtilizadorById(long productId) {
		Optional<Utilizador> utilizadorDb = this.utilizadorRepository.findById(productId);
		if (utilizadorDb.isPresent()) {
			return ObjectMapperUtils.map(utilizadorDb.get(), UtilizadorDTO.class);
		} else {
			throw new ResourceNotFoundException("id nao existe");
		}

	}

	@Override
	public HttpStatus deleteUtilizador(long productId) {
		Optional<Utilizador> utilizadorDb = this.utilizadorRepository.findById(productId);
		if (utilizadorDb.isPresent()) {
			this.utilizadorRepository.delete(utilizadorDb.get());
			return HttpStatus.OK;
		} else {
			throw new ResourceNotFoundException("id nao existe");
		}
	}

}
