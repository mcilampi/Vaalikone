

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import data.Ehdokas;
/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");

		// create the connection
		Connection con = Dao.createDatabaseConnection(Dao.DBpath, Dao.username, Dao.password);
		Ehdokas ehdokas = new Ehdokas();
		//Read the form values and set them to ehdokas object values
		ehdokas.setEtunimi(request.getParameter("etunimi"));
		ehdokas.setSukunimi(request.getParameter("sukunimi"));
		ehdokas.setPuolue(request.getParameter("puolue"));
		ehdokas.setEsittely(request.getParameter("esittely"));
		ehdokas.setEhdokasNumero(Integer.parseInt(request.getParameter("ehdokasnumero")));
		ehdokas.setKayttajanimi(request.getParameter("kayttajanimi"));
		ehdokas.setSalasana(request.getParameter("salasana"));

		int rowsAffected = Dao.createCandidate(con, ehdokas);
			
		if(rowsAffected == 0) {
			pw.println("Candidate not stored into database");
		}else {
			pw.println("Candidate succesfully stored into database");
			pw.println("<p><a href='index.html'>Return to the admin page</a>");
		}		
		
		//close the stream
		pw.close();
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Not able to close database connection.");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}