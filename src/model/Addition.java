
package model;



import java.math.BigDecimal;
import java.util.ArrayList;

import model.old.OperateurArithmetique;
import model.old.Variable;


public class Addition extends OperateurArithmetique
{


	@Override
	public Object invoke(Variable arg1, Variable arg2) {

		
		  if (arg1.getValue() instanceof BigDecimal && arg2.getValue() instanceof BigDecimal)
	        {
	            BigDecimal number1 = (BigDecimal) arg1.getValue();
	            BigDecimal number2 = (BigDecimal) arg2.getValue();
	            return number1.add(number2);
	        }
	        else
	        {
	            throw new IllegalArgumentException();
	        } //traiter exception
		
	        

	
	}

	public String toCode(){
		
		return "+";

	}


}
