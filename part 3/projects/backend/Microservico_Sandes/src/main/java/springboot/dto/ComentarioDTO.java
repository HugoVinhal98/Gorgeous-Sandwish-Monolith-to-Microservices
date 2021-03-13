package springboot.dto;

public class ComentarioDTO {

	private String designacao;

	private long sandwishId;

	private long userId;

	public ComentarioDTO(String designacao, long sandwishId, long userId) {
		this.designacao = designacao;
		this.sandwishId = sandwishId;
		this.userId = userId;
	}

	public ComentarioDTO() {
	}
	
	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
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
