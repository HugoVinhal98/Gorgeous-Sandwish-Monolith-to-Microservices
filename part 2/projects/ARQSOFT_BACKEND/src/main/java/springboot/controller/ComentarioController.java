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

import springboot.dto.ComentarioDTO;
import springboot.service.ComentarioService;

@RestController
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	@GetMapping("/comentario")
	public ResponseEntity<List<ComentarioDTO>> getAllComments() {
		List<ComentarioDTO> comentariosDTO = comentarioService.getAllComments();
		return ResponseEntity.ok().body(comentariosDTO);
	}

	@GetMapping("/comentario/{id}")
	public ResponseEntity<ComentarioDTO> getCommentById(@PathVariable long id) {
		ComentarioDTO comentarioDto = comentarioService.getCommentById(id);
		return ResponseEntity.ok().body(comentarioDto);
	}

	@PostMapping("/comentario")
	public ResponseEntity<HttpStatus> createComment(@RequestBody ComentarioDTO comentarioDto) {
		return ResponseEntity.ok().body(this.comentarioService.createComment(comentarioDto));
	}

	@PutMapping("/comentario/{id}")
	public ResponseEntity<HttpStatus> updateComment(@PathVariable long id,
			@RequestBody ComentarioDTO comentarioDto) {
		return ResponseEntity.ok().body(this.comentarioService.updateComment(id, comentarioDto));
	}

	@DeleteMapping("/comentario/{id}")
	public HttpStatus deleteComment(@PathVariable long id) {
		return this.comentarioService.deleteComment(id);
	}
}
