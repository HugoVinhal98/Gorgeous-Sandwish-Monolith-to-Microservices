package springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import springboot.dto.EncomendaDTO;
import springboot.exception.ResourceNotFoundException;
import springboot.model.Encomenda;
import springboot.repository.EncomendaRepository;
import utils.DateValidationUtils;
import utils.PropertiesObtain;

@Service
@Transactional
public class EncomendaServiceImp implements EncomendaService {

	@Autowired
	private EncomendaRepository encomendaRepository;

	@Override
	public HttpStatus createEncomenda(EncomendaDTO encomendaDTO) {

		DateValidationUtils.isHourBetweenInterval(encomendaDTO.getHora());

		Boolean isVoluntario;
		try {
			final String uri = "http://localhost:8010/utilizador/" + encomendaDTO.getUserId();
			RestTemplate restTemplate = new RestTemplate();
			Object result = restTemplate.getForObject(uri, Object.class);
			@SuppressWarnings("unchecked")
			Map<String, Object> user = (Map<String, Object>) result;
			isVoluntario = (Boolean) user.get("isVoluntario");
		} catch (Exception e) {
			throw new ResourceNotFoundException("User not found with id : " + encomendaDTO.getUserId());
		}

		if (encomendaDTO.getIsDonation() && !isVoluntario) {
			throw new ResourceNotFoundException("You cannot do a donation if you are not a volunteer");
		}

		Collection<Integer> sandes = encomendaDTO.getSandes();
		Iterator<Integer> it = encomendaDTO.getQuantidades().iterator();
		if (sandes.size() > 0) {
			for (long sandeId : sandes) {
				int quantidade;
				try {
					final String uri = "http://localhost:8030/sandes/" + sandeId;
					RestTemplate restTemplate = new RestTemplate();
					Object result = restTemplate.getForObject(uri, Object.class);

					@SuppressWarnings("unchecked")
					Map<String, Object> user = (Map<String, Object>) result;
					System.out.println(user);
					quantidade=(int) user.get("quantidade");		
				} catch (Exception e) {
					throw new ResourceNotFoundException("Sande not found with id : " + sandeId);
				}
				Integer q = it.next();
				if (quantidade - q < 0) {
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
