
import data.Ehdokas;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editCandidates
 */
@WebServlet("/editCandidates")
public class editCandidates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editCandidates() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = Dao.createDatabaseConnection(Dao.DBpath, Dao.username, Dao.password);
		ArrayList<Ehdokas> ehdokkaat = Dao.readFromDatabase(con, Dao.query);
		request.setAttribute("ehdokasLista", ehdokkaat);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/EditCandidates.jsp");
		dispatcher.forward(request, response);
		
			
	}

	

}
