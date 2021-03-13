package springboot.dto;

public class AvaliacaoDTO {
	public AvaliacaoDTO() {
	}

	private double nota;
	private long utilizadorId;
	private long sandesId;
	private String comentario;

	public AvaliacaoDTO(long utilizadorId,long sandesId,double nota, String comentario) {
		this.utilizadorId=utilizadorId;
		this.sandesId=sandesId;
		this.nota=nota;
		this.comentario=comentario;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public long getUtilizadorId() {
		return utilizadorId;
	}

	public void setUtilizadorId(long utilizadorId) {
		this.utilizadorId = utilizadorId;
	}

	public long getSandesId() {
		return sandesId;
	}
	
	public String getComentario() {
		return comentario;
	}

	public void setSandesId(long sandesId) {
		this.sandesId = sandesId;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
}
