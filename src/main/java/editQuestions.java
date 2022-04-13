

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import data.Kysymys;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editQuestions
 */
@WebServlet("/editQuestions")
public class editQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editQuestions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = Dao.createDatabaseConnection(Dao.DBpath, Dao.username, Dao.password);
		ArrayList<Kysymys> kysymykset = Dao.readAllQuestionsFromDatabase(con);
		
		request.setAttribute("kysymykset", kysymykset);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/EditQuestions.jsp");
		dispatcher.forward(request, response);
	}

}
