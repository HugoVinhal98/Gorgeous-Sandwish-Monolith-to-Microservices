package springboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "comentario")
public class Comentario {

	public Comentario() {
	}

	public Comentario(String descricao, long sandwishId, long userId) {
		this.descricao = descricao;
		this.sandwishId = sandwishId;
		this.userId = userId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "descricao")
	private String descricao;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
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

	public static class Builder {

		private String descricao;
		private long sandwishId;
		private long userId;

		public Builder setDescricao(String descricao) {
			this.descricao = descricao;
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
			return new Comentario(descricao, sandwishId, userId);
		}
	}

}
