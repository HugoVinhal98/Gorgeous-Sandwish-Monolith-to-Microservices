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

import springboot.dto.AvaliacaoDTO;
import springboot.service.AvaliacaoService;

@RestController
public class AvaliacaoController {

	@Autowired
	private AvaliacaoService avaliacaoService;


	@GetMapping("/avaliacao")
	public ResponseEntity<List<AvaliacaoDTO>> getAllAvaliacao() {
		return ResponseEntity.ok().body(avaliacaoService.getAllAvaliacao());
	}

	@GetMapping("/avaliacao/{id}")
	public ResponseEntity<AvaliacaoDTO> getAvaliacaoById(@PathVariable long id) {
		return ResponseEntity.ok().body(avaliacaoService.getAvaliacaoById(id));
	}

	@PostMapping("/avaliacao")
	public ResponseEntity<HttpStatus> createAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDto) {
		return ResponseEntity.ok().body(avaliacaoService.createAvaliacao(avaliacaoDto));
	}

	@PutMapping("/avaliacao/{id}")
	public ResponseEntity<HttpStatus> updateAvaliacao(@PathVariable long id, @RequestBody AvaliacaoDTO avaliacaoDto) {
		return ResponseEntity.ok().body(avaliacaoService.updateAvaliacao(id, avaliacaoDto));
	}

	@DeleteMapping("/avaliacao/{id}")
	public HttpStatus deleteProduct(@PathVariable long id) {
		avaliacaoService.deleteAvaliacao(id);
		return HttpStatus.OK;
	}
}
