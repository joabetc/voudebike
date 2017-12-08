package br.com.scopus.treinamento;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Estacionamento
 */
@WebServlet("/EstacionamentoServlet")
public class EstacionamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EstacionamentoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection objConn = null;
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		
		try
		{
			ArrayList<Estacionamento> lista = getEstacionamentos(objConn);

			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			JSONObject json = new JSONObject();
			out.print("{\"listaEstacionamentos\": [");
			int contador = 0;
			for (Estacionamento estacionamento : lista) {
				if(Util.distanciaValida(Double.valueOf(latitude), estacionamento.getLatitude(), Double.valueOf(longitude), estacionamento.getLongitude())){
					if(contador >0){
						out.print(",");
					}
					json.put("id", estacionamento.getId());
					json.put("nome", estacionamento.getNome());
					json.put("endereco", estacionamento.getEndereco());
					json.put("telefone", estacionamento.getTelefone());
					json.put("estoque", estacionamento.getEstoque());
					json.put("latitude", estacionamento.getLatitude());
					json.put("longitude", estacionamento.getLongitude());
					out.print(json.toString());
					contador++;
					
				}
			}
			

			out.print("]}");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public ArrayList<Estacionamento> getEstacionamentos(Connection objConn)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("	id, nome, endereco, ");
		sb.append("	telefone, estoque, latitude, longitude ");
		sb.append("FROM ");
		sb.append(" treinamento.estacionamento");
		sb.append(" where estoque > 0");

		PreparedStatement pst;
		ResultSet rs;

		ArrayList<Estacionamento> lista = new ArrayList<Estacionamento>();

		try
		{
			objConn = (Connection) new Conexao().abreConn();
			pst = objConn.prepareStatement(sb.toString());
			rs = pst.executeQuery();

			while (rs.next())
			{
				Estacionamento estacionamento = new Estacionamento();
				estacionamento.setEndereco(rs.getString("endereco"));
				estacionamento.setId(rs.getInt("id"));
				estacionamento.setTelefone(rs.getString("telefone"));
				estacionamento.setNome(rs.getString("nome"));
				estacionamento.setEstoque(rs.getInt("estoque"));
				estacionamento.setLatitude(rs.getDouble("latitude"));
				estacionamento.setLongitude(rs.getDouble("longitude"));
				lista.add(estacionamento);
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

}
