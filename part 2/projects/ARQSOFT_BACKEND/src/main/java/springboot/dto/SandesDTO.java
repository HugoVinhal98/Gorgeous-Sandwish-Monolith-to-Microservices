package springboot.dto;

public class SandesDTO {
	private String designacao;

	private int quantidade;

	private String imagem;

	private String titulo;

	public SandesDTO(String designacao, int quantidade, String imagem, String titulo) {
		this.designacao = designacao;
		this.quantidade = quantidade;
		this.imagem = imagem;
		this.titulo = titulo;
	}

	public SandesDTO() {
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
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
}
