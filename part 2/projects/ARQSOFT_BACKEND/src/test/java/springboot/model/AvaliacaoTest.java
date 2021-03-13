package springboot.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class AvaliacaoTest {

	@Test
	public void testCreation() {
		Avaliacao av = new Avaliacao(1,2,2.2,"Desc");
		av.setEscalaMax(1);
		av.setEscalaMin(2);
		av.setNota(2.3);
		assertTrue(1==av.getUtilizadorId());
		assertTrue(2==av.getSandesId());
		assertTrue(2.3==av.getNota());
	}
	
	@Test
	public void testSetNota() {
		Avaliacao av = new Avaliacao(2,2,2.2,"Desc");
		av.setNota(3.0);
		assertTrue(3.0==av.getNota());
	}
	
	@Test
	public void testObtainProps() {
		Avaliacao av = new Avaliacao(2,2,2.2,"Desc");
		assertTrue(5==av.getEscalaMax());
	}
	
	@Test
	public void testSetComentario() {
		Avaliacao av = new Avaliacao();
		String co = "desc";
		av.setComentario(co);
		assertTrue(co.equals(av.getComentario()));
	}

}
