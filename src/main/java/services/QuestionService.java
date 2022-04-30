package services;


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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import data.Kysymys;

@Path("/questionService")
public class QuestionService {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("questionapp");
//	@POST
//	@Path("/addQuestionRestful")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Kysymys addQuestionRestful(Kysymys kysymys) {
//		System.out.println(kysymys.getKysymys());
//		kysymys.setKysymys(kysymys.getKysymys() + " modified");
//		System.out.println(kysymys.getKysymys());
//		return kysymys;
//	}
	
	@PUT
	@Path("/addQuestionRestful")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addQuestionRestful(Kysymys kysymys) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(kysymys);
		em.getTransaction().commit();
		System.out.println(kysymys.getKysymys());
		kysymys.setKysymys(kysymys.getKysymys() + " modified");
		System.out.println(kysymys.getKysymys());
		
		return "ok";
	}
	
	@GET
	@Path("/getQuestionsList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void getQuestionList(@Context HttpServletRequest request, @Context HttpServletResponse response){
		System.out.println("Here we go");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Kysymys> kysymykset = em.createQuery("select a from Kysymys a").getResultList();
		em.getTransaction().commit();
		RequestDispatcher disp = request.getRequestDispatcher("/jsp/ReadQuestionsWithRest.jsp");
		request.setAttribute("kysymykset", kysymykset);
		for (Kysymys k: kysymykset) {
			System.out.println(k.getKysymys());
		}
		try {
			disp.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
