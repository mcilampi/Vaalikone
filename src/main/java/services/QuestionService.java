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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import data.Kysymys;

@Path("/questionService")
public class QuestionService {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("questionapp");
	
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

		List<Kysymys> list=readQuestion();		
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/ReadQuestionsWithRest.jsp");
		request.setAttribute("questionlist", list);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@PUT
	@Path("/updateQuestionRestful")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Kysymys> updateQuestion(Kysymys kysymys) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Kysymys f=em.find(Kysymys.class, kysymys.getId());
		if (f!=null) {
		em.merge(kysymys);
		}
		em.getTransaction().commit();
		List<Kysymys> list=readQuestion();	
		
		return list;
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
		if(tag.equalsIgnoreCase("all")) {
			tunnisteelliset.clear();
			for (Kysymys k: kysymykset) {
				tunnisteelliset.add(k);
			}
		} else {
			tunnisteelliset.clear();
			for (Kysymys k: kysymykset) {
				if (k.getTunniste().equalsIgnoreCase(tag)) {
					tunnisteelliset.add(k);
				}
			}
		}
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/ReadQuestionsWithRest.jsp");
		request.setAttribute("kysymykset", tunnisteelliset);
		try {
			disp.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}