
import data.Ehdokas;
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
@WebServlet("/edit")
public class EditSingleCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSingleCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = Dao.createDatabaseConnection(Dao.DBpath, Dao.username, Dao.password);
			
		int ehdokasId = Integer.parseInt(request.getParameter("id"));
		Ehdokas ehdokas = Dao.readOneEhdokasFromDatabase(con, Dao.query, ehdokasId);
		ehdokas.printEhdokas();
		request.setAttribute("ehdokas", ehdokas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/EditSingleCandidate.jsp");
		dispatcher.forward(request, response);
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Tietokantayhteyden sulkeminen ei onnistunut.");
			e.printStackTrace();
		}
	}

}
