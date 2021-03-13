package springboot.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SandesTest {

	@Test
	public void testCreation() {
		Sandes s = new Sandes( "des", 1, "String imagem", "String titulo");
		assertTrue("des".equals(s.getDesignacao()));
		assertTrue(1==s.getQuantidade());
		assertTrue("String imagem".equals(s.getImagem()));
		assertTrue("String titulo".equals(s.getTitulo()));
	}
	
	@Test
	public void testsetDes() {
		Sandes s = new Sandes( "des", 1, "String imagem", "String titulo");
		s.setDesignacao("dd");
		assertTrue("dd".equals(s.getDesignacao()));
	}

	@Test
	public void testsetQuanti() {
		Sandes s = new Sandes( "des", 1, "String imagem", "String titulo");
		s.setQuantidade(2);
		assertTrue(2==s.getQuantidade());
	}
	
	@Test
	public void testsetImagem() {
		Sandes s = new Sandes( "des", 1, "String imagem", "String titulo");
		s.setImagem("imagem");
		assertTrue("imagem".equals(s.getImagem()));
	}
	
	@Test
	public void testsetTitulo() {
		Sandes s = new Sandes( "des", 1, "String imagem", "String titulo");
		s.setTitulo("titulo");
		assertTrue("titulo".equals(s.getTitulo()));
	}
}
