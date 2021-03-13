package springboot.model.ValueObject;

import utils.PropertiesObtain;

public class Nota {
 
private double nota;
	
	public Nota() {
		// TODO Auto-generated constructor stub
	}
	
	public Nota(double nota) {
		setNota(nota);
		// TODO Auto-generated constructor stub
	}
	
	public double getNota() {
		return nota;
	}
	
	public void setNota(double nota) {
		double escalaMin = Double.parseDouble(PropertiesObtain.getPropertiesValue("rate.min"));
		double escalaMax = Double.parseDouble(PropertiesObtain.getPropertiesValue("rate.max"));
		if(nota<escalaMin || nota>escalaMax ) {
			throw new IllegalArgumentException("Nota errada");
		}
		this.nota = nota;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
