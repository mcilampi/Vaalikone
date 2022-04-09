import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    Connection con = Dao.createDatabaseConnection(Dao.DBpath, Dao.username, Dao.password);
    System.out.println("servletitsä: " + con);
    ArrayList<Ehdokas> ehdokkaat = Dao.readFromDatabase(con, Dao.query);
    
    for (Ehdokas ehdokas: ehdokkaat) {
    	response.getWriter().print(ehdokas.toString() + "\n");
    }
    
    request.setAttribute("ehdokasLista", ehdokkaat);
    
    response.getWriter().print("Hello App Engine!\r\n");
    RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/EditCandidates.jsp");
    try {
		dispatcher.forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
  }
}