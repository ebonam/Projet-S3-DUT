package model.old;

import java.math.BigDecimal;
import java.util.ArrayList;

public class InferieurOuEgal extends OperateurBoolean
{
    private static InferieurOuEgal INSTANCE;


    public static InferieurOuEgal getInstance()
    {
        if (InferieurOuEgal.INSTANCE == null)
        {
        	InferieurOuEgal.INSTANCE = new InferieurOuEgal();
        }
        return InferieurOuEgal.INSTANCE;
    }

    public String toCode(){
//		ArrayList<String> sti  =new ArrayList<String>();

		return ("<=");
		//return sti;
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
            return number1.compareTo(number2) < 0;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }
}
