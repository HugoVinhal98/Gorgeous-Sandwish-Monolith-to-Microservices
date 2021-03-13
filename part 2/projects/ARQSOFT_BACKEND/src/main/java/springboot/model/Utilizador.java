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
@Table(name = "utilizador")
public class Utilizador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "tipo")
	private boolean isVoluntario;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@CreationTimestamp
	private Date createdAt;

	@CreationTimestamp
	private Date updatedAt;
	
	public Utilizador() {
	}

	public Utilizador(boolean isVoluntario, String nome, String descricao) {
		this.isVoluntario = isVoluntario;
		this.nome = nome;
		this.descricao = descricao;
	}

	public long getId() {
		return id;
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
		
		private boolean isVoluntario;
		private String nome;
		private String descricao;
		
		public Builder setisVoluntrio(boolean isVoluntario) {
			this.isVoluntario=isVoluntario;
			return this;
		}
		
		public Builder setNome(String nome) {
			this.nome=nome;
			return this;
		}
		
		public Builder setDescricao(String descricao) {
			this.descricao=descricao;
			return this;
		}
		
		public Utilizador build() {
			return new Utilizador(isVoluntario,nome,descricao);
		}
	}
}
