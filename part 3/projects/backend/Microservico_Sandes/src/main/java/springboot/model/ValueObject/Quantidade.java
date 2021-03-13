package springboot.model.ValueObject;


/**
 * Quantidade
 */
public class Quantidade {

    private int quantidade;

    public Quantidade() {
		// TODO Auto-generated constructor stub
	}
    
    public Quantidade(int quantidade) {
    	setQuantidade(quantidade);
		// TODO Auto-generated constructor stub
	}
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if(quantidade<1){
            throw new IllegalArgumentException("Quantidade tem de ser maior que 0");
        }
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}