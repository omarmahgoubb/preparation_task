package ejbs;


import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Stateless
public class Calculation 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id;
    private int Number1;
    private int Number2;
    private String Operation;

    // Constructors
    public Calculation() {}

    public Calculation(int Number1, int Number2, String Operation) 
    {
        this.Number1 = Number1;
        this.Number2 = Number2;
        this.Operation = Operation;
    }
    
    public int getNumber1() {return Number1;}

    public void setNumber1(int number1) {this.Number1 = number1;}

    public int getNumber2() {return Number2;}

    public void setNumber2(int number2) {this.Number2 = number2;}

    public String getOperation() {return Operation;}

    public void setOperation(String operation) 
    {this.Operation = operation;}
    
    public int add(int n1, int n2)
    {return n1+n2;}
    
    public int subtract(int n1, int n2)
    {return n1-n2;}
    
    public int multiply(int n1, int n2)
    {return n1*n2;}
    
    public int divide(int n1, int n2)
    {
    	if (n2 == 0) 
    	{
            throw new ArithmeticException("Division by zero");
        }
        return n1 / n2;
    }
    
}

