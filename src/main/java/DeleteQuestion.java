
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
 * Servlet implementation class EditSingleCandidate
 */
@WebServlet("/deleteQ")
public class DeleteQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteQuestion() {
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
		
		int kysymysId = Integer.parseInt(request.getParameter("id"));
		int rowsAffected = Dao.deleteQuestion(con, kysymysId);
		
		if(rowsAffected == 0) {
			pw.println("Ei voitu poistaa kysymyst‰.");
		}else {
			pw.println("Kysymyksen poistaminen onnistui!");
			pw.println("<p><a href='index.html'>Palaa kysymysten yll√§pitosivulle.</a>");
		}
		pw.close();
		Dao.closeDatabaseConnection(con);
	}

}
