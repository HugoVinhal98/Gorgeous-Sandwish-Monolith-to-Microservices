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

import springboot.dto.SandesDTO;
import springboot.model.ValueObject.Designacao;
import springboot.model.ValueObject.Quantidade;
import springboot.model.ValueObject.Titulo;

@Entity
@Table(name = "sandes")
public class Sandes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// @Column(name = "designacao")
	@Embedded
	private Designacao designacao;

	// @Column(name = "quantidade")
	@Embedded
	private Quantidade quantidade;

	@Column(name = "imagem")
	private String imagem;

	@Column(name = "titulo")
	@Embedded
	private Titulo titulo;

	@CreationTimestamp
	private Date createdAt;

	@CreationTimestamp
	private Date updatedAt;

	public Sandes() {
	}

	public Sandes(Designacao designacao, Quantidade quantidade, String imagem, Titulo titulo) {
		this.designacao = designacao;
		this.quantidade = quantidade;
		this.imagem = imagem;
		this.titulo = titulo;
	}

	public long getId() {
		return id;
	}

	public Designacao getDesignacao() {
		return designacao;
	}

	public void setDesignacao(Designacao designacao) {
		this.designacao = designacao;
	}

	public Quantidade getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Quantidade quantidade) {
		this.quantidade = quantidade;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
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

	public SandesDTO transformToDto() {
		return new SandesDTO(this.getDesignacao().getDesignacao(), this.getQuantidade().getQuantidade(),
				this.getImagem(), this.getTitulo().getTitulo());
	}

	public static class Builder {
		private Designacao designacao;
		private Quantidade quantidade;
		private String imagem;
		private Titulo titulo;

		public Builder setDesignacao(Designacao designacao) {
			this.designacao = designacao;
			return this;
		}

		public Builder setQuantidade(Quantidade quantidade) {
			this.quantidade = quantidade;
			return this;
		}

		public Builder setImagem(String imagem) {
			this.imagem = imagem;
			return this;
		}

		public Builder setTitulo(Titulo titulo) {
			this.titulo = titulo;
			return this;
		}

		public Sandes build() {
			return new Sandes(designacao, quantidade, imagem, titulo);
		}
	}
}
