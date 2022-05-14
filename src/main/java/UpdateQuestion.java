

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Kysymys;
/**
 * Receives question data from JSP form and relays it to DAO, which then stores the data tto database.
 */
@WebServlet("/UpdateQuestion")
public class UpdateQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

		Connection con = Dao.createDatabaseConnection(Dao.DBpath, Dao.username, Dao.password);
		Kysymys kysymys = new Kysymys();
		kysymys = Dao.readOneKysymysFromDatabase(con, Dao.queryy, Integer.parseInt(request.getParameter("id")));
		kysymys.setTunniste(request.getParameter("tunniste"));
		kysymys.setKysymys(request.getParameter("kysymys"));
	

		
		int rowsAffected = Dao.updateEntryQuestion(con, kysymys);
		
		String onnistuiko = null;
		if (rowsAffected > 0) {
			onnistuiko = "Ehdokkaan tiedot tallennettu onnistuneesti!";
		}
		response.setHeader("onnistuiko", onnistuiko);
		response.sendRedirect(request.getContextPath()+"/editQuestions?tag=all");

		Dao.closeDatabaseConnection(con);
	}

}
