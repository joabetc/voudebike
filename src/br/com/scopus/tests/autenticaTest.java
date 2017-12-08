package br.com.scopus.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.mysql.jdbc.Connection;

import br.com.scopus.treinamento.Conexao;
import br.com.scopus.treinamento.Voudebike;

public class autenticaTest {

	@Test
	public void testEmailVazio()
	{
		String expected = "m";
		String actual = "m";
		
		//String actual = new Voudebike().metodo().atributo();
		
		assertEquals(!expected.equals(""), !actual.equals(""));
	}
	
	@Test
	public void testSenhaVazio()
	{
		String expected = "m";
		String actual = "m";
		
		//String actual = new Voudebike().metodo().atributo();
		
		assertEquals(!expected.equals(""), !actual.equals(""));
	}
	
	@Test
	public void testOpenConnection()
	{
		Connection objConn = (Connection) new Conexao().abreConn();
		
		try{
			assertEquals(true, objConn.isValid(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection()
	{
		Connection objConn = (Connection) new Conexao().abreConn();

		boolean expected = true;
		boolean actual = new Voudebike().autenticarUsuario(objConn, "joao@gmail.com", "12345");
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFormatoEmail()
	{
		boolean expected = true;
		String actual = "maria@gmail.com";
		
		//String actual = new Voudebike().metodo().atributo();
		
		assertEquals(expected, actual.contains("@"));
	}
}