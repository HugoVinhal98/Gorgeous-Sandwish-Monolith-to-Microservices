package springboot.service;


import java.util.List;

import org.springframework.http.HttpStatus;

import springboot.dto.ComentarioDTO;

public interface ComentarioService {
	HttpStatus createComment(ComentarioDTO comentario);

	HttpStatus updateComment(long id,ComentarioDTO comentario);

	List<ComentarioDTO> getAllComments();

	ComentarioDTO getCommentById(long sandesId);

	HttpStatus deleteComment(long id);
}
