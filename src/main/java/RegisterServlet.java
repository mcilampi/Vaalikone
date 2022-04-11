

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	//create the query
	private static final String INSERT_QUERY ="INSERT INTO ehdokkaat(etunimi,sukunimi,puolue,esittely,ehdokasnumero,kayttajanimi,salasana) VALUES(?,?,?,?,?,?,?)";
		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw = response.getWriter();
		//set content type
		response.setContentType("text/html");
		//Read the form values
		String etunimi = request.getParameter("etunimi");
		String sukunimi = request.getParameter("sukunimi");
		String puolue = request.getParameter("puolue");
		String esittely = request.getParameter("esittely");
		String ehdokasnumero = request.getParameter("ehdokasnumero");
		String kayttajanimi = request.getParameter("kayttajanimi");
		String salasana = request.getParameter("salasana");
		
		//create the connection
		// try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaalikone","hannu","kukkuu123");
		Connection con = Dao.createDatabaseConnection(Dao.DBpath, Dao.username, Dao.password);
		try {
			PreparedStatement prepared = con.prepareStatement(INSERT_QUERY);{
			//SET THE VALUES
			prepared.setString(1, etunimi);
			prepared.setString(2, sukunimi);
			prepared.setString(3, puolue);
			prepared.setString(4, esittely);
			prepared.setString(5, ehdokasnumero);
			prepared.setString(6, kayttajanimi);
			prepared.setString(7, salasana);
			
			//execute the query
			int count= prepared.executeUpdate();
			
			if(count==0) {
				pw.println("Candidate not stored into database");
			}else {
				pw.println("Candidate succesfully stored into database");
				pw.println("<p><a href='index.html'>Return to the admin page</a>");
			}
			} 
			}catch(Exception e) {
				pw.println(e.getMessage());
				e.printStackTrace();
			}
		
		
		
		//close the stream
		pw.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}