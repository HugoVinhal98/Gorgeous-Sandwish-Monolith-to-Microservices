package springboot.model;

public class Nome {

	private String nome;
	
	public Nome() {
		// TODO Auto-generated constructor stub
	}
	
	public Nome(String nome) {
		setNome(nome);
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		if(nome.isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser vazio");
		}
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
}
