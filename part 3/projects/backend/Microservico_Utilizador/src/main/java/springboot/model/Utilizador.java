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

import springboot.dto.UtilizadorDTO;

@Entity
@Table(name = "utilizador")
public class Utilizador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "tipo")
	private boolean isVoluntario;

	//@Column(name = "nome")
	@Embedded
	private Nome nome;

	//@Column(name = "descricao")
	@Embedded
	private Descricao descricao;

	@CreationTimestamp
	private Date createdAt;

	@CreationTimestamp
	private Date updatedAt;
	
	public Utilizador() {
	}

	public Utilizador(boolean isVoluntario, Nome nome, Descricao descricao) {
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

	public Nome getNome() {
		return nome;
	}

	public void setNome(Nome nome) {
		this.nome = nome;
	}

	public Descricao getDescricao() {
		return descricao;
	}

	public void setDescricao(Descricao descricao) {
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
	public UtilizadorDTO transformToDto() {
		return new UtilizadorDTO(this.getNome().getNome(), this.getDescricao().getDescricao(),
		this.getIsVoluntario());
	}
	
	public static class Builder {
		
		private boolean isVoluntario;
		private Nome nome;
		private Descricao descricao;
		
		public Builder setisVoluntrio(boolean isVoluntario) {
			this.isVoluntario=isVoluntario;
			return this;
		}
		
		public Builder setNome(Nome nome) {
			this.nome=nome;
			return this;
		}
		
		public Builder setDescricao(Descricao descricao) {
			this.descricao=descricao;
			return this;
		}
		
		public Utilizador build() {
			return new Utilizador(isVoluntario,nome,descricao);
		}
	}
}
