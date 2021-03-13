package springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.dto.EncomendaDTO;
import springboot.exception.ResourceNotFoundException;
import springboot.model.Encomenda;
import springboot.model.Sandes;
import springboot.model.Utilizador;
import springboot.repository.EncomendaRepository;
import springboot.repository.SandesRepository;
import springboot.repository.UtilizadorRepository;
import utils.DateValidationUtils;
import utils.PropertiesObtain;

@Service
@Transactional
public class EncomendaServiceImp implements EncomendaService {

	@Autowired
	private EncomendaRepository encomendaRepository;

	@Autowired
	private UtilizadorRepository utilizadorRepository;

	@Autowired
	private SandesRepository sandesRepository;

	@Override
	public HttpStatus createEncomenda(EncomendaDTO encomendaDTO) {

		DateValidationUtils.isHourBetweenInterval(encomendaDTO.getHora());

		Optional<Utilizador> userDB = this.utilizadorRepository.findById(encomendaDTO.getUserId());
		if (!userDB.isPresent()) {
			throw new ResourceNotFoundException("User not found with id : " + encomendaDTO.getUserId());
		}

		if (encomendaDTO.getIsDonation() && !userDB.get().getIsVoluntario())
			throw new ResourceNotFoundException("You cannot do a donation if you are not a volunteer");

		Collection<Integer> sandes = encomendaDTO.getSandes();
		Iterator<Integer> it= encomendaDTO.getQuantidades().iterator();
		if (sandes.size() > 0) {
			for (long sandeId : sandes) {
				Optional<Sandes> sandesDB = this.sandesRepository.findById(sandeId);
				if (!sandesDB.isPresent()) {
					throw new ResourceNotFoundException("Sandwich not found with id : " + sandeId);
				}
				Integer q = it.next();
				if(sandesDB.get().getQuantidade()-q<0) {
					throw new ResourceNotFoundException("Sandwich doesnt have storage");
				}
			}
		}

		if (!DateValidationUtils.isBetweenDates(encomendaDTO.getDate()))
			throw new ResourceNotFoundException("Invalid date inserted! You can only order sandwishes from "
					+ Integer.parseInt(PropertiesObtain.getPropertiesValue("encomenda.dateMin")) + " to "
					+ Integer.parseInt(PropertiesObtain.getPropertiesValue("encomenda.dateMax")) + " days from now!");
		Encomenda.Builder encomendaBuilder = new Encomenda.Builder();
		Encomenda encomenda = encomendaBuilder.setQuantidades(encomendaDTO.getQuantidades())
				.setSandes(encomendaDTO.getSandes()).setUser(encomendaDTO.getUserId()).setData(encomendaDTO.getDate())
				.setIsDonation(encomendaDTO.getIsDonation()).build();
		encomendaRepository.save(encomenda);
		return HttpStatus.OK;
	}

	@Override
	public HttpStatus updateEncomenda(long id, EncomendaDTO encomendaDTO) {
		Optional<Encomenda> encomendaDB = this.encomendaRepository.findById(id);
		Optional<Utilizador> userDB = this.utilizadorRepository.findById(encomendaDTO.getUserId());

		if (encomendaDTO.getUserId() != 0) {
			if (!userDB.isPresent()) {
				throw new ResourceNotFoundException("User not found with id : " + encomendaDTO.getUserId());
			}
		}

		if (encomendaDTO.getIsDonation() && !userDB.get().getIsVoluntario())
			throw new ResourceNotFoundException("You cannot do a donation if you are not a volunteer");

		if (encomendaDB.isPresent()) {
			Encomenda encomendaUpdate = encomendaDB.get();
			if (encomendaUpdate.getDate().before(DateValidationUtils.getDateFromDaysApart(5))) {
				throw new ResourceNotFoundException(
						"It's too late to change your order! Changes are only allowed up to "
								+ Integer.parseInt(PropertiesObtain.getPropertiesValue("encomenda.changeUntil"))
								+ " days before!");
			}
			Collection<Integer> sandes = encomendaDTO.getSandes();

			if (encomendaDTO.getUserId() != 0)
				encomendaUpdate.setUserId(encomendaDTO.getUserId());
			if (encomendaDTO.getQuantidades() != null)
				encomendaUpdate.setQuantidade(encomendaDTO.getQuantidades());
			if (encomendaDTO.getHora() != null)
				if (DateValidationUtils.isHourBetweenInterval(encomendaDTO.getHora()))
					encomendaUpdate.setHora(encomendaDTO.getHora());
			if (!encomendaDTO.getIsDonation() == encomendaUpdate.getIsDonation())
				encomendaUpdate.setIsDonation(encomendaDTO.getIsDonation());
			if (sandes != null) {
				if (sandes.size() > 0) {
					for (long sandeId : sandes) {
						Optional<Sandes> sandesDB = this.sandesRepository.findById(sandeId);
						if (!sandesDB.isPresent()) {
							throw new ResourceNotFoundException("Sandwich not found with id : " + sandeId);
						}
					}
				}
				encomendaUpdate.setSandesId(encomendaDTO.getSandes());
			}
			if (encomendaDTO.getDate() != null) {

				if (!DateValidationUtils.isBetweenDates(encomendaDTO.getDate()))
					throw new ResourceNotFoundException("Invalid date inserted! You can only order sandwishes from "
							+ Integer.parseInt(PropertiesObtain.getPropertiesValue("encomenda.dateMin")) + " to "
							+ Integer.parseInt(PropertiesObtain.getPropertiesValue("encomenda.dateMax"))
							+ " days from now!");
				encomendaUpdate.setData(encomendaDTO.getDate());
			}
			encomendaUpdate.setUpdatedAt(new Date());
			encomendaRepository.save(encomendaUpdate);
			return HttpStatus.OK;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + id);
		}
	}

	@Override
	public List<EncomendaDTO> getAllEncomendas() {
		List<Encomenda> encomendaDB = this.encomendaRepository.findAll();
		List<EncomendaDTO> encomendasDTO = new ArrayList<>();
		for (Encomenda encomenda : encomendaDB) {
			encomendasDTO.add(entityToDto(encomenda));
		}
		return encomendasDTO;
	}

	public EncomendaDTO entityToDto(Encomenda e) {
		return new EncomendaDTO(e.getQuantidade(), e.getSandesId(), e.getUserId(), e.getDate(), e.getIsDonation(),
				e.getHora());
	}

	@Override
	public EncomendaDTO getEncomendaById(long encomendaId) {
		Optional<Encomenda> encomendaDB = this.encomendaRepository.findById(encomendaId);
		if (encomendaDB.isPresent()) {
			return entityToDto(encomendaDB.get());
		} else {
			throw new ResourceNotFoundException("Encomenda com o id " + encomendaId + " nao encontrado");
		}
	}

	@Override
	public HttpStatus deleteEncomenda(long id) {
		Optional<Encomenda> encomendaDB = this.encomendaRepository.findById(id);
		if (encomendaDB.isPresent()) {
			this.encomendaRepository.delete(encomendaDB.get());
			return HttpStatus.OK;
		} else {
			throw new ResourceNotFoundException("Encomenda com o id " + id + " nao encontrado");
		}
	}

}
