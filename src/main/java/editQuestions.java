

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import data.Kysymys;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Receives a list of all qustions retrieved from database by DAO
 * Based on request parameter filters the list by questions' tags
 * Relays the list to jsp.
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
		String tag = request.getParameter("tag").toString();
		System.out.println("Tag is " + tag);
		List<Kysymys> kysymykset = new ArrayList<Kysymys>();
		if (tag.equalsIgnoreCase("all")) {
			kysymykset = Dao.readAllQuestionsFromDatabase(con);
		} else {
			kysymykset = Dao.readAllQuestionsWithTagFromDatabase(con,tag);
		}
		ArrayList<String> tunnisteet = Dao.readDistinctTags(con);
		for (int i = 0; i < tunnisteet.size(); i++) {
			System.out.println(tunnisteet.get(i));
			if ((tunnisteet.get(i) == null) || (tunnisteet.get(i).equals(""))) {
				tunnisteet.remove(i);
			}
		}
		request.setAttribute("kysymykset", kysymykset);
		request.setAttribute("tunnisteet", tunnisteet);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/EditQuestions.jsp");
		dispatcher.forward(request, response);
		Dao.closeDatabaseConnection(con);
	}

}
