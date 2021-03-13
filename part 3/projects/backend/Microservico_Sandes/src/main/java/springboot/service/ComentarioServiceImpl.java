package springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import springboot.dto.ComentarioDTO;
import springboot.exception.ResourceNotFoundException;
import springboot.repository.ComentarioRepository;
import springboot.repository.SandesRepository;
import springboot.model.Comentario;
import springboot.model.Sandes;
import springboot.model.ValueObject.Designacao;

@Service
@Transactional
public class ComentarioServiceImpl implements ComentarioService {

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private SandesRepository sandesRepository;

	@Override
	public HttpStatus createComment(ComentarioDTO comentarioDTO) {
		try {
			final String uri = "http://localhost:8010/utilizador/" + comentarioDTO.getUserId();
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getForObject(uri, Object.class);
		} catch (Exception e) {
			throw new ResourceNotFoundException("User not found with id : " + comentarioDTO.getUserId());
		}
		Optional<Sandes> sandeDB = this.sandesRepository.findById(comentarioDTO.getSandwishId());
		if (!sandeDB.isPresent()) {
			throw new ResourceNotFoundException("Sandes not found with id : " + comentarioDTO.getUserId());
		}
		Comentario.Builder comentBuilder = new Comentario.Builder();
		Comentario comentario = comentBuilder.setDesignacao(new Designacao(comentarioDTO.getDesignacao()))
				.setSandwishId(comentarioDTO.getSandwishId()).setUserId(comentarioDTO.getUserId()).build();
		comentarioRepository.save(comentario);
		return HttpStatus.OK;
	}

	@Override
	public HttpStatus updateComment(long id, ComentarioDTO comentarioDTO) {
		Optional<Comentario> CommentDB = this.comentarioRepository.findById(id);

		if (comentarioDTO.getUserId() != 0) {
			/*
			 * Optional<Utilizador> userDB =
			 * this.utilizadorRepository.findById(comentarioDTO.getUserId()); if
			 * (!userDB.isPresent()) { throw new
			 * ResourceNotFoundException("User not found with id : " +
			 * comentarioDTO.getUserId()); }
			 */
		}

		if (comentarioDTO.getSandwishId() != 0) {
			Optional<Sandes> sandeDB = this.sandesRepository.findById(comentarioDTO.getSandwishId());
			if (!sandeDB.isPresent()) {
				throw new ResourceNotFoundException("Sandes not found with id : " + comentarioDTO.getSandwishId());
			}
		}

		if (CommentDB.isPresent()) {
			Comentario commentUpdate = CommentDB.get();
			if (comentarioDTO.getDesignacao() != null)
				commentUpdate.setDesignacao(new Designacao(comentarioDTO.getDesignacao()));
			if (comentarioDTO.getSandwishId() != 0)
				commentUpdate.setSandwishId(comentarioDTO.getSandwishId());
			commentUpdate.setUpdatedAt(new Date());
			if (comentarioDTO.getUserId() != 0)
				commentUpdate.setUserId(comentarioDTO.getUserId());
			comentarioRepository.save(commentUpdate);
			return HttpStatus.OK;
		} else {
			throw new ResourceNotFoundException("Commentary not found with id : " + id);
		}
	}

	@Override
	public List<ComentarioDTO> getAllComments() {
		List<Comentario> listaComentarios = this.comentarioRepository.findAll();
		List<ComentarioDTO> list=new ArrayList<>();
		for (Comentario comentario : listaComentarios) {
			list.add(comentario.transformToDto());
		}
		return list;
	}

	@Override
	public ComentarioDTO getCommentById(long commentId) {
		Optional<Comentario> commentDB = this.comentarioRepository.findById(commentId);
		if (commentDB.isPresent()) {
			return commentDB.get().transformToDto();
		} else {
			throw new ResourceNotFoundException("Commentary not found with id : " + commentId);
		}
	}

	@Override
	public HttpStatus deleteComment(long commentId) {
		Optional<Comentario> commentDB = this.comentarioRepository.findById(commentId);
		if (commentDB.isPresent()) {
			this.comentarioRepository.delete(commentDB.get());
			return HttpStatus.OK;
		} else {
			throw new ResourceNotFoundException("id nao existe");
		}
	}

}
