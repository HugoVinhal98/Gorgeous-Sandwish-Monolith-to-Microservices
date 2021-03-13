package springboot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.SandesDTO;
import springboot.dto.SandesDetailedDTO;
import springboot.service.SandesService;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class SandesController {

	@Autowired
	private SandesService sandesService;
	
	@GetMapping("/sandes")
	public ResponseEntity<List<SandesDTO>> getAllSandes(){
		List<SandesDTO> sandesDTO = sandesService.getAllSandes();
		return ResponseEntity.ok().body(sandesDTO);
	}
	
	@GetMapping("/sandesDetails")
	public ResponseEntity<List<SandesDetailedDTO>> getAllSandesDetailed(){
		List<SandesDetailedDTO> sandesDTO = sandesService.getAllSandesDetailed();
		return ResponseEntity.ok().body(sandesDTO);
	}
	
	@GetMapping("/sandes/{id}")
	public ResponseEntity<SandesDTO> getSandesById(@PathVariable long id){
		SandesDTO sandesDTO = sandesService.getSandesById(id);
		return ResponseEntity.ok().body(sandesDTO);
	}
	
	@PostMapping("/sandes")
	public ResponseEntity<HttpStatus> createSandes(@RequestBody SandesDTO sandesDto){
		return ResponseEntity.ok().body(this.sandesService.createSandes(sandesDto));
	}
	
	@PutMapping("/sandes/{id}")
	public ResponseEntity<HttpStatus> updateSandes(@PathVariable long id,
			@RequestBody SandesDTO sandesDTO) {
		return ResponseEntity.ok().body(this.sandesService.updateSandes(id, sandesDTO));
	}

	@DeleteMapping("/sandes/{id}")
	public HttpStatus deleteSandes(@PathVariable long id) {
		return this.sandesService.deleteSandes(id);
	}
}
