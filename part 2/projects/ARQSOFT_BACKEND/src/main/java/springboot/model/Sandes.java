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
@Table(name = "sandes")
public class Sandes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "designacao")
	private String designacao;

	@Column(name = "quantidade")
	private int quantidade;

	@Column(name = "imagem")
	private String imagem;

	@Column(name = "titulo")
	private String titulo;

	@CreationTimestamp
	private Date createdAt;

	@CreationTimestamp
	private Date updatedAt;

	public Sandes() {
	}

	public Sandes(String designacao, int quantidade, String imagem, String titulo) {
		this.designacao = designacao;
		this.quantidade = quantidade;
		this.imagem = imagem;
		this.titulo = titulo;
	}

	public long getId() {
		return id;
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
	
	public static class Builder{
		private String designacao;
		private int quantidade;
		private String imagem;
		private String titulo;
		
		public Builder setDesignacao(String designacao) {
			this.designacao = designacao;
			return this;
		}
		
		public Builder setQuantidade(int quantidade) {
			this.quantidade=quantidade;
			return this;
		}
		
		public Builder setImagem(String imagem) {
			this.imagem = imagem;
			return this;
		}
		
		public Builder setTitulo(String titulo) {
			this.titulo=titulo;
			return this;
		}
		
		public Sandes build() {
			return new Sandes(designacao,quantidade,imagem,titulo);
		}
	}
}
