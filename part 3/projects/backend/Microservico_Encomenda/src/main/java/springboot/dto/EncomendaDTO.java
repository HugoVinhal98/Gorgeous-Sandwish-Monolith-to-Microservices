package springboot.dto;

import java.util.Collection;
import java.util.Date;

public class EncomendaDTO {

	private Collection<Integer> quantidades;
	private Collection<Integer> sandesId;
	private long userId;
	private Date data;
	private boolean isDonation;
	private String hora;

	public EncomendaDTO() {

	}

	public EncomendaDTO(Collection<Integer> quantidades, Collection<Integer> sandes, long user, Date data,
			boolean isDonation, String hora) {
		this.data = data;
		this.quantidades = quantidades;
		this.sandesId = sandes;
		this.userId = user;
		this.isDonation = isDonation;
		this.hora=hora;
	}

	public Collection<Integer> getQuantidades() {
		return quantidades;
	}

	public void setQuantidades(Collection<Integer> quantidades) {
		this.quantidades = quantidades;
	}

	public Collection<Integer> getSandes() {
		return sandesId;
	}

	public void setSandes(Collection<Integer> sandes) {
		this.sandesId = sandes;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long users) {
		this.userId = users;
	}

	public Date getDate() {
		return data;
	}

	public void setDate(Date data) {
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

}
