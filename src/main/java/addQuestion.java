
import data.Kysymys;
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
 * Servlet implementation class addQuestion
 */
@WebServlet("/addQuestion")
public class addQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		Connection con = Dao.createDatabaseConnection(Dao.DBpath, Dao.username, Dao.password);
		Kysymys kysymys = new Kysymys();
		kysymys.setKysymys(request.getParameter("kysymys"));
		kysymys.setTunniste(request.getParameter("tunniste"));
		int rowsAffected = Dao.createQuestion(con, kysymys);
		if (rowsAffected == 0) {
			pw.print("Kysymyksen lisääminen tietokantaan ei onnistunut.");
			pw.print("<a href='/editQuestions'>Palaa tarkastelemaan kysymyksiä.</a>");
		} else {
			pw.print("Kysymyksen lisääminen tietokantaan onnistui!");
			pw.print("<a href='/editQuestions?tag=all'>Palaa tarkastelemaan kysymyksiä.</a>");
		}
		
		pw.close();
		Dao.closeDatabaseConnection(con);

	}

}
