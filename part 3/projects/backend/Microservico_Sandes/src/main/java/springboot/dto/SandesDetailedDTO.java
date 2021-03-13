package springboot.dto;

import java.util.List;

public class SandesDetailedDTO {
	private String designacao;

	private String imagem;

	private String titulo;

	private List<String> comentarios;

	private List<AvaliacaoSandesDTO> avaliacoes;

	public SandesDetailedDTO(String designacao, String imagem, String titulo, List<String> comentarios,
			List<AvaliacaoSandesDTO> avaliacoes) {
		this.designacao = designacao;
		this.imagem = imagem;
		this.titulo = titulo;
		this.comentarios = comentarios;
		this.avaliacoes = avaliacoes;
	}

	public SandesDetailedDTO() {
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<String> comentarios) {
		this.comentarios = comentarios;
	}

	public List<AvaliacaoSandesDTO> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<AvaliacaoSandesDTO> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

}
