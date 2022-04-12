
import data.Ehdokas;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
		int sorter = Integer.parseInt(request.getParameter("sorter"));
		Connection con = Dao.createDatabaseConnection(Dao.DBpath, Dao.username, Dao.password);
		ArrayList<Ehdokas> ehdokkaat = Dao.readFromDatabase(con, Dao.query);
		if (sorter == 1) {
			Collections.sort(ehdokkaat, new NameComparator());
		} else if (sorter == 2) {
			Collections.sort(ehdokkaat, new NumberComparator());
		} else if (sorter == 3) {
			Collections.sort(ehdokkaat, new PartyComparator());
		}

		request.setAttribute("ehdokasLista", ehdokkaat);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/EditCandidates.jsp");
		dispatcher.forward(request, response);
		
			
	}

	

}
