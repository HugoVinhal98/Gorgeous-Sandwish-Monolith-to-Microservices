package springboot.model.ValueObject;

/**
 * Designacao
 */
public class Designacao {

    private String designacao;

    public Designacao() {
		// TODO Auto-generated constructor stub
	}
    
    public Designacao(String designacao){
        setDesignacao(designacao);
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        if(designacao.isEmpty()){
            throw new IllegalArgumentException("Designação não pode ser vazia");
        }
        this.designacao = designacao;
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