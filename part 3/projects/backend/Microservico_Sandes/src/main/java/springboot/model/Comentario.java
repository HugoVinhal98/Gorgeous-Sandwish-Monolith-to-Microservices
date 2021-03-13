package springboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import springboot.dto.ComentarioDTO;
import springboot.model.ValueObject.Designacao;

@Entity
@Table(name = "comentario")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	//@Column(name = "descricao")
	@Embedded
	private Designacao descricao;

	@Column(name = "sandwishId")
	private long sandwishId;

	@Column(name = "userId")
	private long userId;

	@CreationTimestamp
	private Date createdAt;

	@CreationTimestamp
	private Date updatedAt;

	public long getId() {
		return id;
	}
	
	public Comentario() {
	}

	public Comentario(Designacao descricao, long sandwishId, long userId) {
		this.descricao = descricao;
		this.sandwishId = sandwishId;
		this.userId = userId;
	}

	public Designacao getDesignacao() {
		return descricao;
	}

	public void setDesignacao(Designacao descricao) {
		this.descricao = descricao;
	}

	public long getSandwishId() {
		return sandwishId;
	}

	public void setSandwishId(long sandiwshId) {
		this.sandwishId = sandiwshId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public ComentarioDTO transformToDto() {
		return new ComentarioDTO(this.getDesignacao().getDesignacao(), this.getSandwishId(),
		this.getUserId());
	}

	public static class Builder {

		private Designacao designacao;
		private long sandwishId;
		private long userId;

		public Builder setDesignacao(Designacao designacao) {
			this.designacao = designacao;
			return this;
		}

		public Builder setSandwishId(long sandwishId) {
			this.sandwishId = sandwishId;
			return this;
		}

		public Builder setUserId(long userId) {
			this.userId = userId;
			return this;
		}

		public Comentario build() {
			return new Comentario(designacao, sandwishId, userId);
		}
	}

}
