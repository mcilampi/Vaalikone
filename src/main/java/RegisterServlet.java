

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
 * Receives candidate data from JSP form and relays it to DAO, which then stores the data tto database.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");

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
			pw.println("Ehdokasta ei tallennettu tietokantaan.");
		}else {
			pw.println("Ehdokas lisätty tietokantaan.");
			pw.println("<p><a href='index.html'>Palaa ehdokkaiden ylläpitosivulle.</a>");
		}		
		
		//close the stream
		pw.close();
		Dao.closeDatabaseConnection(con);
	}

}