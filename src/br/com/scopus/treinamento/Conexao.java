package br.com.scopus.treinamento;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Conexao
{
	public Connection abreConn()
	{
		Connection conn = null;

		try
		{
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);

			//Teste
			String url = "jdbc:mysql://172.16.213.24:3306/treinamento?useSSL=true";
			String username = "root";
			String password = "root";

//			//PROD
//			String url = "jdbc:mysql://172.16.42.44:3306/massagem?useSSL=true";
//			String username = "useradm";
//			String password = "Scopus2016";

			conn = (Connection) DriverManager.getConnection(url, username, password);
		}
		catch (ClassNotFoundException|SQLException e)
		{
			System.out.println(e.getMessage());
		}

		return (conn);
	}

	public void close(Conexao c)
	{
		close(c);
	}
}