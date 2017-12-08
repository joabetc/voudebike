package br.com.scopus.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.scopus.treinamento.Util;

public class UtilTest {

	@Test
	public void testDistanciaValida() {
		
		double lon1 = -49.2461181;
		double lat1= -25.4526806;
		double lon2 = -49.2680434;
		double lat2= -25.471051;
		boolean result = Util.distanciaValida(lat1, lat2, lon1, lon2);
		
		assertTrue(result);
	}
	
	@Test
	public void testDistanciaInvalida() {
		
		double lat2 = -25.4825005;
		double lat1 = -49.2204183;
		double lon2 = -25.451064;
		double lon1 = -49.2431273;
		boolean result = Util.distanciaValida(lat1, lat2, lon1, lon2);
		
		assertFalse(result);
		
	}

}
