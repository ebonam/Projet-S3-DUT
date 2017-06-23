package model.old;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Soustraction extends OperateurArithmetique
{
    private static Soustraction INSTANCE;

    public static Soustraction getInstance()
    {
        if (Soustraction.INSTANCE == null)
        {
            Soustraction.INSTANCE = new Soustraction();
        }
        return Soustraction.INSTANCE;
    }

    @Override
    public Object invoke(Variable arg1, Variable arg2)
    {
        // TODO: Gestion des erreurs de type numérique...
        // Vu qu'on met a disposition que les types de bases, ça ira pas plus loin que ça.

        if (arg1.getValue() instanceof BigDecimal && arg2.getValue() instanceof BigDecimal)
        {
            BigDecimal number1 = (BigDecimal) arg1.getValue();
            BigDecimal number2 = (BigDecimal) arg2.getValue();
            return number1.subtract(number2);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

	public String toCode(){
	//	ArrayList<String> sti  =new ArrayList<String>();

		return ("-");
		//return sti;
	}

}
