package springboot.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComentarioTest {

	@Test
	public void testCreation() {
		Comentario com = new Comentario("des",1,1);
		assertTrue("des".equals(com.getDescricao()));
		assertTrue(1==com.getUserId());
		assertTrue(1==com.getSandwishId());
	}
	
	@Test
	public void testSetDesc() {
		Comentario com = new Comentario("d",1,1);
		com.setDescricao("des");
		assertTrue("des".equals(com.getDescricao()));
	}
	@Test
	public void testSetUser() {
		Comentario com = new Comentario("des",1,1);
		com.setUserId(2);
		assertTrue(2==com.getUserId());
	}
	@Test
	public void testSetSandwich() {
		Comentario com = new Comentario("des",1,1);
		com.setSandwishId(3);
		assertTrue(3==com.getSandwishId());
	}
	

}
