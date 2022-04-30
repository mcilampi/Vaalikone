package services;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Kysymys;

@Path("/questionService")
public class QuestionService {
	
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("questionadd");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(kysymys);
		em.getTransaction().commit();
		System.out.println(kysymys.getKysymys());
		kysymys.setKysymys(kysymys.getKysymys() + " modified");
		System.out.println(kysymys.getKysymys());
		
		return "ok";
	}
}
