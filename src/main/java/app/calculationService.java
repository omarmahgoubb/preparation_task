package app;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ejbs.Calculation;
import java.util.List;
import javax.ejb.EJB;

@Stateless
@Path("docalc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class calculationService
{
	@PersistenceContext(unitName="hello")
	private EntityManager entityManager;
	
	@EJB
	Calculation calculation;
	
	@POST
	@Path("calc")
	public String doCalculation(Calculation calculation) 
	{
	    try 
	    {
	        int result;
	        switch (calculation.getOperation()) 
	        {
	            case "+":
	                result = calculation.add(calculation.getNumber1(), calculation.getNumber2());
	                break;
	            case "-":
	                result = calculation.subtract(calculation.getNumber1(), calculation.getNumber2());
	                break;
	            case "*":
	                result = calculation.multiply(calculation.getNumber1(), calculation.getNumber2());
	                break;
	            case "/":
	                result = calculation.divide(calculation.getNumber1(), calculation.getNumber2());
	                break;
	            default:
	                return "Invalid operation";
	        }
	        entityManager.persist(calculation);
	        return String.valueOf(result);
	    } 
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        return "Error occurred during calculation";
	    }
	}
	
	@GET
	@Path("calculations")
	public List<Calculation> getAllCalculations()
	{
		TypedQuery<Calculation> query =entityManager.createQuery("SELECT c from Calculation c", Calculation.class);
		List <Calculation> calculations= query.getResultList();
		
		return calculations;
	}
}


