
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Receives candidate id and relays it to Dao class's method to delete the candidate from database.
 */
@WebServlet("/delete")
public class DeleteCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = Dao.createDatabaseConnection(Dao.DBpath, Dao.username, Dao.password);
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		
		int ehdokasId = Integer.parseInt(request.getParameter("id"));
		int rowsAffected = Dao.deleteCandidate(con, ehdokasId);
		
		if(rowsAffected == 0) {
			pw.println("Ei voitu poistaa ehdokasta.");
		}else {
			pw.println("Ehdokkaan poistaminen onnistui!");
			pw.println("<p><a href='/editCandidates?sorter=2'>Palaa ehdokkaiden ylläpitosivulle.</a>");
		}
		pw.close();
		Dao.closeDatabaseConnection(con);
	}

}
