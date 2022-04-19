
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
 * Servlet implementation class getFormDataToAddQuestion
 */
@WebServlet("/getFormDataToAddQuestion")
public class getFormDataToAddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getFormDataToAddQuestion() {
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
			System.out.println(tunnisteet.get(i));
			if ((tunnisteet.get(i) == null) || (tunnisteet.get(i).equals(""))) {
				tunnisteet.remove(i);
			}
		}
		Dao.closeDatabaseConnection(con);
		
		request.setAttribute("tunnisteet", tunnisteet);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/AddQuestion.jsp");
		dispatcher.forward(request, response);
	}

}
