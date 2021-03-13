package springboot.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "encomenda")
public class Encomenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ElementCollection
	private Collection<Integer> quantidades;

	@ElementCollection
	private Collection<Integer> sandesId;

	@Column(name = "userId")
	private long userId;

	@Column(name = "data")
	private Date data;

	@Column(name = "isDonation")
	private boolean isDonation;
	
	@Column(name = "hora")
	private String hora;

	@CreationTimestamp
	private Date createdAt;

	@CreationTimestamp
	private Date updatedAt;

	public Encomenda() {
	}

	public Encomenda(Collection<Integer> quantidades, Collection<Integer> sandesId, long userId, Date data,
			boolean isDonation, String hora) {
		super();
		this.data = data;
		this.quantidades = quantidades;
		this.sandesId = sandesId;
		this.userId = userId;
		this.isDonation = isDonation;
		this.hora=hora;
	}

	public long getId() {
		return id;
	}

	public Collection<Integer> getQuantidade() {
		return quantidades;
	}

	public Collection<Integer> getSandesId() {
		return sandesId;
	}

	public void setQuantidade(Collection<Integer> quantidades) {
		this.quantidades = quantidades;
	}

	public void setSandesId(Collection<Integer> sandesId) {
		this.sandesId = sandesId;
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

	public Date getDate() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean getIsDonation() {
		return isDonation;
	}

	public void setIsDonation(boolean isDonation) {
		this.isDonation = isDonation;
	}
	
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public static class Builder {
		private Collection<Integer> quantidades;
		private Collection<Integer> sandesId;
		private long userId;
		private Date data;
		private boolean isDonation;
		private String hora;

		public Builder setQuantidades(Collection<Integer> quantidades) {
			this.quantidades = quantidades;
			return this;
		}

		public Builder setSandes(Collection<Integer> sandes) {
			this.sandesId = sandes;
			return this;
		}

		public Builder setUser(long user) {
			this.userId = user;
			return this;
		}

		public Builder setData(Date data) {
			this.data = data;
			return this;
		}

		public Builder setIsDonation(boolean isDonation) {
			this.isDonation = isDonation;
			return this;
		}
		
		public Builder setHora(String hora) {
			this.hora = hora;
			return this;
		}

		public Encomenda build() {
			return new Encomenda(quantidades, sandesId, userId, data, isDonation, hora);
		}
	}
}
