package springboot.dto;

public class UtilizadorDTO {

	private String nome;

	private String descricao;

	private boolean isVoluntario;

	protected UtilizadorDTO() {

	}

	public UtilizadorDTO(String nome, String descricao, boolean isVoluntario) {
		this.nome = nome;
		this.descricao = descricao;
		this.isVoluntario = isVoluntario;
	}

	public boolean getIsVoluntario() {
		return isVoluntario;
	}

	public void setIsVoluntario(boolean isVoluntario) {
		this.isVoluntario = isVoluntario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
