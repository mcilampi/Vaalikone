

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Ehdokas;

/**
 * Servlet implementation class getFormDataToAddCandidate
 */
@WebServlet("/getFormDataToAddCandidate")
public class getFormDataToAddCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getFormDataToAddCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = Dao.createDatabaseConnection(Dao.DBpath, Dao.username, Dao.password);
		ArrayList<Ehdokas> ehdokkaat = Dao.readFromDatabase(con, Dao.query);
		ArrayList<String> puolueLista = new ArrayList<String>();
		ArrayList<Integer> ehdokasNumerot = new ArrayList<Integer>();
		for (Ehdokas ehdokas: ehdokkaat) {
			if (!puolueLista.contains(ehdokas.getPuolue())) {
				if (!ehdokas.getPuolue().equalsIgnoreCase("")) {
					puolueLista.add(ehdokas.getPuolue());
				}
			}
		}
		for (Ehdokas ehdokas: ehdokkaat) {			
			ehdokasNumerot.add(ehdokas.getEhdokasNumero());
			
		}
		
		for (String puolue: puolueLista) {
			System.out.println(puolue);
			System.out.println("test");
		}
		request.setAttribute("puoluelista", puolueLista);
		request.setAttribute("ehdokasnumerot", ehdokasNumerot);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/AddCandidate.jsp");
		dispatcher.forward(request, response);
		Dao.closeDatabaseConnection(con);
	}

}
