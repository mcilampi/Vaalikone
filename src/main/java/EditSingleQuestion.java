
import data.Kysymys;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditSingleCandidate
 */
@WebServlet("/editQuestion")
public class EditSingleQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSingleQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = Dao.createDatabaseConnection(Dao.DBpath, Dao.username, Dao.password);
			
		int kysymysId = Integer.parseInt(request.getParameter("id"));
		Kysymys kysymys = Dao.readOneKysymysFromDatabase(con, Dao.queryy, kysymysId);
		request.setAttribute("kysymys", kysymys);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/EditSingleQuestion.jsp");
		dispatcher.forward(request, response);
		Dao.closeDatabaseConnection(con);
	}

}
