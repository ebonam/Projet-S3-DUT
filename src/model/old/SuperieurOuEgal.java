
package model.old;


import java.math.BigDecimal;
import java.util.ArrayList;

public class SuperieurOuEgal extends OperateurBoolean
{
	private static SuperieurOuEgal INSTANCE;


	public String toCode(){
		return (">=");
	
	}

	public static SuperieurOuEgal getInstance()
	{
		if (SuperieurOuEgal.INSTANCE == null)
		{
			SuperieurOuEgal.INSTANCE = new SuperieurOuEgal();
		}
		return SuperieurOuEgal.INSTANCE;
	}

	
	
	@Override
	public boolean invoke(Variable arg1, Variable arg2)
	{
		// TODO: Gestion des erreurs de type numérique...
		// Vu qu'on met a disposition que les types de bases, ça ira pas plus loin que ça.

		if (arg1.getValue() instanceof BigDecimal && arg2.getValue() instanceof BigDecimal)
		{
			BigDecimal number1 = (BigDecimal) arg1.getValue();
			BigDecimal number2 = (BigDecimal) arg2.getValue();
			return number1.compareTo(number2) > 0;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}


}
