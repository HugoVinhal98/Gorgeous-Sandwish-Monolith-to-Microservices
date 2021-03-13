package springboot.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.Test;

public class EncomendaTest {

	@Test
	public void test() {
		Encomenda enc = new Encomenda();
		Date a =new Date();
		enc.setData(a);
		assertTrue(a.equals(enc.getDate()));
	}
	
	@Test
	public void testIsDonation() {
		Encomenda enc = new Encomenda();
		enc.setIsDonation(true);
		assertTrue(enc.getIsDonation());
	}
	
	@Test
	public void testUser() {
		Encomenda enc = new Encomenda();
		enc.setUserId(1);
		assertTrue(1==enc.getUserId());
	}
	
	@Test
	public void testQuantidade() {
		Encomenda enc = new Encomenda();
		Collection<Integer> quantidades = new ArrayList<Integer>();
		quantidades.add(1);
		enc.setQuantidade(quantidades);
		assertTrue(1==enc.getQuantidade().size());
	}
	
	@Test
	public void testSandes() {
		Encomenda enc = new Encomenda();
		Collection<Integer> Sandes = new ArrayList<Integer>();
		Sandes.add(1);
		Sandes.add(4);
		enc.setSandesId(Sandes);
		assertTrue(2==enc.getSandesId().size());
	}

}
