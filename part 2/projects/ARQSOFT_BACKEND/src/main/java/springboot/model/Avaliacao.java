package springboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import utils.PropertiesObtain;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "utilizadorId")
	private long utilizadorId;

	@Column(name = "sandesId")
	private long sandesId;

	@Column(name = "nota")
	private double nota;

	@Column(name = "escalaMin")
	private double escalaMin;

	@Column(name = "escalaMax")
	private double escalaMax;

	@Column(name = "comentario")
	private String comentario;

	@CreationTimestamp
	private Date createdAt;

	@CreationTimestamp
	private Date updatedAt;

	public Avaliacao() {
	}

	public Avaliacao(long utilizadorId, long sandesId, double nota, String comentario) {
		this.utilizadorId = utilizadorId;
		this.sandesId = sandesId;
		this.nota = nota;
		this.comentario = comentario;
		this.escalaMin = Double.parseDouble(PropertiesObtain.getPropertiesValue("rate.min"));
		this.escalaMax = Double.parseDouble(PropertiesObtain.getPropertiesValue("rate.max"));
	}

	public long getId() {
		return id;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public double getEscalaMin() {
		return escalaMin;
	}

	public double getEscalaMax() {
		return escalaMax;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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

	public void setSandesId(long sandesId) {
		this.sandesId = sandesId;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void setEscalaMin(double escalaMin) {
		this.escalaMin = escalaMin;
	}

	public void setEscalaMax(double escalaMax) {
		this.escalaMax = escalaMax;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public static class Builder {

		private long utilizadorId;
		private long sandesId;
		private double nota;
		private String comentario;

		public Builder setUtilizadorId(long utilizadorId) {
			this.utilizadorId = utilizadorId;
			return this;
		}

		public Builder setSandesId(long sandesId) {
			this.sandesId = sandesId;
			return this;
		}

		public Builder setNota(double nota) {
			this.nota = nota;
			return this;
		}

		public Builder setComentario(String comentario) {
			this.comentario = comentario;
			return this;
		}

		public Avaliacao build() {
			return new Avaliacao(utilizadorId, sandesId, nota, comentario);
		}

	}

}
