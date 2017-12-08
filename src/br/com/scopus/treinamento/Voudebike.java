package br.com.scopus.treinamento;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;

@WebServlet("/Voudebike")
public class Voudebike extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	String retornoJson = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		Connection objConn = null;
		
		try
		{
			boolean autenticado = autenticarUsuario(objConn, email, senha);
			System.out.println(autenticado);
			retornoJson = new Gson().toJson(autenticado);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(retornoJson);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean autenticarUsuario(Connection objConn, String email, String senha)
	{
		int countUser = 0;

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("	count(*) as total ");
		sb.append("FROM ");
		sb.append(" usuario ");
		sb.append("WHERE ");
		sb.append(" email = ?");
		sb.append(" and senha = ?");

		PreparedStatement pst;
		ResultSet rs;
		
		try
		{
			objConn = (Connection) new Conexao().abreConn();
			pst = objConn.prepareStatement(sb.toString());
			pst.setString(1, email);
			pst.setString(2, senha);
			rs = pst.executeQuery();

			while (rs.next())
			{
				countUser = rs.getInt("total");
			}

			pst.close();
			objConn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		if (countUser == 1)
		{
			return (true);
		}
		else
		{
			return (false);
		}
	}

	public Map<Integer, String> getEstacionamentos(Connection objConn, String email, String senha)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("	idEstacionamento, ");
		sb.append("	nomeEstacionamento ");
		sb.append("FROM ");
		sb.append(" estacionamento");
//		sb.append("WHERE ");
//		sb.append(" ?");

		PreparedStatement pst;
		ResultSet rs;

		Map<Integer, String> lista = new HashMap<Integer, String>();

		try
		{
			objConn = (Connection) new Conexao().abreConn();
			pst = objConn.prepareStatement(sb.toString());
			rs = pst.executeQuery();

			while (rs.next())
			{
				lista.put(rs.getInt("idEstacionamento"), rs.getString("nomeEstacionamento"));
			}

			pst.close();
			objConn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return (lista);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}