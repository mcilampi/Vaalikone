
import data.Ehdokas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditSingleCandidate
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
		response.setContentType("text/html");
		
		int ehdokasId = Integer.parseInt(request.getParameter("id"));
		int rowsAffected = Dao.deleteCandidate(con, ehdokasId);
		
		if(rowsAffected == 0) {
			pw.println("Candidate not deleted from database.");
		}else {
			pw.println("Candidate succesfully deleted!");
			pw.println("<p><a href='index.html'>Return to the admin page</a>");
		}
		
	}

}
