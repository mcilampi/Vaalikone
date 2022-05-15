package services;

/** 
 * RESTful service to persist data and retrieve it from database.
 */
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import com.mysql.cj.x.protobuf.MysqlxCrud.Find;

import data.Kysymys;

@Path("/questionService")
public class QuestionService {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("questionapp");

	
	@POST
	@Path("/updateQuestion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	public void updateQuestion(@FormParam("id") int id, @FormParam("kysymys") String kysymys, @FormParam("tunniste") String tunniste, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		Kysymys itseKysymys = new Kysymys();
		itseKysymys.setId(id);
		itseKysymys.setKysymys(kysymys);
		itseKysymys.setTunniste(tunniste);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Kysymys k = em.find(Kysymys.class, itseKysymys.getId());
		if (itseKysymys != null) {
			em.merge(itseKysymys);
		}
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		List<Kysymys> kysymykset = em.createQuery("select a from Kysymys a").getResultList();
		em.getTransaction().commit();

		RequestDispatcher disp = request.getRequestDispatcher("/jsp/ReadQuestionsWithRest.jsp");
		request.setAttribute("kysymykset", kysymykset);
		try {
			disp.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@GET
	@Path("/readQuestions")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Kysymys> readQuestion() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Kysymys> list=em.createQuery("select a from Kysymys a").getResultList();
		em.getTransaction().commit();
		return list;
	}

	@GET
	@Path("/deleteQuestionRestful/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteQuestionByGet(@PathParam("id") int id, 
			@Context HttpServletRequest request,
			@Context HttpServletResponse response
			) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Kysymys f=em.find(Kysymys.class, id);
		if (f!=null) {
			em.remove(f);
		}
		em.getTransaction().commit();
				
		em.getTransaction().begin();
		List<Kysymys> kysymykset = em.createQuery("select a from Kysymys a").getResultList();
		em.getTransaction().commit();

		RequestDispatcher disp = request.getRequestDispatcher("/jsp/ReadQuestionsWithRest.jsp");
		request.setAttribute("kysymykset", kysymykset);
		try {
			disp.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@GET
	@Path("/readOneQuestionRestful/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateQuestion(@PathParam("id") int id, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Kysymys kysymys=em.find(Kysymys.class, id);
		em.getTransaction().commit();
		
		request.setAttribute("kysymys", kysymys);
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/EditSingleQuestionWithRest.jsp");
		try {
			disp.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@PUT
	@Path("/addQuestionRestful")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addQuestionRestful(Kysymys kysymys) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(kysymys);
		em.getTransaction().commit();
		kysymys.setKysymys(kysymys.getKysymys() + " modified");
		
		return "ok";
	}
	
	@GET
	@Path("/getQuestionsList/{tag}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void getQuestionList(@PathParam("tag") String tag, @Context HttpServletRequest request, @Context HttpServletResponse response){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Kysymys> kysymykset = em.createQuery("select a from Kysymys a").getResultList();
		em.getTransaction().commit();
		ArrayList<Kysymys> tunnisteelliset = new ArrayList<Kysymys>();
		ArrayList<String> tunnisteet = new ArrayList<String>();
		for (Kysymys kyssari: kysymykset) {
			boolean listassa = false;
			for (String tunniste: tunnisteet) {
				if (kyssari.getTunniste().equalsIgnoreCase(tunniste)) {
					listassa = true;
				}
			}
			if (!listassa) {
				tunnisteet.add(kyssari.getTunniste());
			}
		}
		if(tag.equalsIgnoreCase("all")) {
			for (Kysymys k: kysymykset) {
				tunnisteelliset.add(k);
			}
		} else {
			for (Kysymys k: kysymykset) {
				if (k.getTunniste().equalsIgnoreCase(tag)) {
					tunnisteelliset.add(k);
				}
			}
		}
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/ReadQuestionsWithRest.jsp");
		request.setAttribute("tunnisteet", tunnisteet);
		request.setAttribute("kysymykset", tunnisteelliset);
		try {
			disp.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}