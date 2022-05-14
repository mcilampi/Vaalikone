
import data.Kysymys;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Retrieves a single question and  all questions' tags from DAO
 * Relays them to JSP
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
		ArrayList<String> tunnisteet = Dao.readDistinctTags(con);
		for (int i = 0; i < tunnisteet.size(); i++) {
			if ((tunnisteet.get(i) == null) || (tunnisteet.get(i).equals(""))) {
				tunnisteet.remove(i);
			}
		}
		int kysymysId = Integer.parseInt(request.getParameter("id"));
		Kysymys kysymys = Dao.readOneKysymysFromDatabase(con, Dao.queryy, kysymysId);
		request.setAttribute("kysymys", kysymys);
		request.setAttribute("tunnisteet", tunnisteet);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/EditSingleQuestion.jsp");
		dispatcher.forward(request, response);
		Dao.closeDatabaseConnection(con);
	}

}
