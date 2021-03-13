package springboot.model.ValueObject;

/**
 * Titulo
 */
public class Titulo {

    private String titulo;

    public Titulo() {
		// TODO Auto-generated constructor stub
	}
    
    public Titulo(String titulo){
        setTitulo(titulo);
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if(titulo.isEmpty()){
            throw new IllegalArgumentException("Titulo não pode ser vazio");
        }
        if(Character.isLowerCase(titulo.charAt(0))){
            Character.toUpperCase(titulo.charAt(0));
        }
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}