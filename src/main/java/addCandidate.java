

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Ehdokas;

/**
 * Servlet implementation class addCandidate
 */
@WebServlet("/addCandidate")
public class addCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		Connection con = Dao.createDatabaseConnection(Dao.DBpath, Dao.username, Dao.password);
		Ehdokas ehdokas = new Ehdokas();
		PrintWriter pw = response.getWriter();
		ehdokas.setEtunimi(request.getParameter("etunimi"));
		ehdokas.setSukunimi(request.getParameter("sukunimi"));
		ehdokas.setPuolue(request.getParameter("puolue"));
		ehdokas.setEsittely(request.getParameter("esittely"));
		ehdokas.setEhdokasNumero(Integer.parseInt(request.getParameter("ehdokasnumero")));
		ehdokas.setKayttajanimi(request.getParameter("kayttajanimi"));
		ehdokas.setSalasana(request.getParameter("salasana"));
		int rowsAffected = Dao.createCandidate(con, ehdokas);
		if (rowsAffected == 0) {
			pw.print("Ehdokkaan lisääminen tietokantaan ei onnistunut.");
			pw.print("<a href='index.html'>Palaa hallinnointisivulle.</a>");
		} else {
			pw.print("Ehdokkaan lisääminen tietokantaan onnistui!");
			pw.print("<a href='index.html'>Palaa hallinnointisivulle.</a>");
		}
		
		pw.close();
		Dao.closeDatabaseConnection(con);
	}

}
