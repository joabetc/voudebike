package br.com.scopus.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.mysql.jdbc.Connection;

import br.com.scopus.treinamento.Conexao;
import br.com.scopus.treinamento.Estacionamento;
import br.com.scopus.treinamento.EstacionamentoServlet;

public class EstacionamentoTest {

	@Test
	public void testListarEstacionamento() {
		Connection objConn = (Connection) new Conexao().abreConn();
		ArrayList<Estacionamento> estacionamento = new EstacionamentoServlet().getEstacionamentos(objConn);
		assertNotNull(estacionamento);
	}
	

}
