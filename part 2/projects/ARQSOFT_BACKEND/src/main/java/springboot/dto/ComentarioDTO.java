package springboot.dto;

public class ComentarioDTO {

	public ComentarioDTO(String descricao, long sandwishId, long userId) {
		this.descricao = descricao;
		this.sandwishId = sandwishId;
		this.userId = userId;
	}

	public ComentarioDTO() {
	}

	private String descricao;

	private long sandwishId;

	private long userId;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getSandwishId() {
		return sandwishId;
	}

	public void setSandwishId(long sandwishId) {
		this.sandwishId = sandwishId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
