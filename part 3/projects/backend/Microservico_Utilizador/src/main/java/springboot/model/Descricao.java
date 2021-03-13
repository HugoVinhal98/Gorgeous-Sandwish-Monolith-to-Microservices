package springboot.model;

public class Descricao {

	private String descricao;
	
	public Descricao() {
		// TODO Auto-generated constructor stub
	}
	
	public Descricao(String descricao) {
		setDescricao(descricao);
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		if(descricao.isEmpty()) {
			throw new IllegalArgumentException("descrição nao pode ser nula");
		}
		this.descricao = descricao;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
