

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
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		Connection con = Dao.createDatabaseConnection(Dao.DBpath, Dao.username, Dao.password);
		Ehdokas ehdokas = new Ehdokas();
		ehdokas = Dao.readOneEhdokasFromDatabase(con, Dao.query, Integer.parseInt(request.getParameter("id")));
		ehdokas.setEtunimi(request.getParameter("etunimi"));
		ehdokas.setSukunimi(request.getParameter("sukunimi"));
		ehdokas.setPuolue(request.getParameter("puolue"));
		ehdokas.setEsittely(request.getParameter("esittely"));
		ehdokas.setEhdokasNumero(Integer.parseInt(request.getParameter("ehdokasnumero")));

		
		int rowsAffected = Dao.updateEntry(con, ehdokas);
		
		if(rowsAffected == 0) {
			pw.println("Ehdokkaan tietojen päivitys ei onnistunut.");
		}else {
			pw.println("Ehdokkaan tietojen päivitys onnistui!");
			pw.println("<p><a href='index.html'>Palaa ehdokkaiden ylläpitosivulle.</a>");
		}
		pw.close();
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Tietokantayhteyden sulkeminen ei onnistunut.");
			e.printStackTrace();
		}
	}

}
