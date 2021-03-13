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

import springboot.dto.UtilizadorDTO;
import springboot.service.UtilizadorService;

@RestController
//@CrossOrigin
//@RequestMapping("utilizador")
public class UtilizadorController {

	@Autowired
	private UtilizadorService utilizadorService;

	@GetMapping("/utilizador")
	public ResponseEntity<List<UtilizadorDTO>> getAllUtilizador(){
		return ResponseEntity.ok().body(utilizadorService.getAllUtilizador());
	}

	@GetMapping("/utilizador/{id}")
	public ResponseEntity<UtilizadorDTO> getUtilizadorById(@PathVariable long id) {
		return ResponseEntity.ok().body(utilizadorService.getUtilizadorById(id));
	}

	@PostMapping("/utilizador")
	public ResponseEntity<HttpStatus> createUtilizador(@RequestBody UtilizadorDTO utilizadorDto) {
		return ResponseEntity.ok().body(this.utilizadorService.createUtilizador(utilizadorDto));
	}

	@PutMapping("/utilizador/{id}")
	public ResponseEntity<HttpStatus> updateUtilizador(@PathVariable long id, @RequestBody UtilizadorDTO utilizadorDto) {
		return ResponseEntity.ok().body(this.utilizadorService.updateUtilizador(id, utilizadorDto));
	}

	@DeleteMapping("/utilizador/{id}")
	public HttpStatus deleteUtilizador(@PathVariable long id) {
		this.utilizadorService.deleteUtilizador(id);
		return HttpStatus.OK;
	}

}
