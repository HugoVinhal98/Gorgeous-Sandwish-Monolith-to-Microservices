package springboot.service;


import java.util.List;

import org.springframework.http.HttpStatus;

import springboot.dto.SandesDTO;
import springboot.dto.SandesDetailedDTO;

public interface SandesService {
	HttpStatus createSandes(SandesDTO sandes);

	HttpStatus updateSandes(long id,SandesDTO sandes);

	List<SandesDTO> getAllSandes();
	
	List<SandesDetailedDTO> getAllSandesDetailed();

	SandesDTO getSandesById(long sandesId);

	HttpStatus deleteSandes(long id);
}
