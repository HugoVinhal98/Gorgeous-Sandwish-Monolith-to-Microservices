package springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.EncomendaDTO;
import springboot.service.EncomendaService;

@RestController
public class EncomendaController {

	@Autowired
	private EncomendaService encomendaService;

	@GetMapping("/encomenda")
	public ResponseEntity<List<EncomendaDTO>> getAllEncomenda() {
		return ResponseEntity.ok().body(encomendaService.getAllEncomendas());
	}

	@GetMapping("/encomenda/{id}")
	public ResponseEntity<EncomendaDTO> getEncomendaById(@PathVariable long id) {
		return ResponseEntity.ok().body(encomendaService.getEncomendaById(id));
	}

	@PostMapping("/encomenda")
	public ResponseEntity<HttpStatus> createEncomenda(@RequestBody EncomendaDTO encomendaDto) {
		return ResponseEntity.ok().body(this.encomendaService.createEncomenda(encomendaDto));
	}

	@PutMapping("/encomenda/{id}")
	public ResponseEntity<HttpStatus> updateEncomenda(@PathVariable long id, @RequestBody EncomendaDTO encomendaDto) {
		return ResponseEntity.ok().body(this.encomendaService.updateEncomenda(id, encomendaDto));
	}

	@DeleteMapping("/encomenda/{id}")
	public HttpStatus deleteEncomenda(@PathVariable long id) {
		this.encomendaService.deleteEncomenda(id);
		return HttpStatus.OK;
	}

}
