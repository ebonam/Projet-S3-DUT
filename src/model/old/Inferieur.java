package model.old;

import java.math.BigDecimal;

public class Inferieur extends OperateurBoolean
{
    private static Inferieur INSTANCE;

    public static Inferieur getInstance()
    {
        if (Inferieur.INSTANCE == null)
        {
            Inferieur.INSTANCE = new Inferieur();
        }
        return Inferieur.INSTANCE;
    }

    public String toCode(){
		//ArrayList<String> sti  =new ArrayList<String>();

		return ("<");
	//	return sti;
	}
    @Override
    public boolean invoke(Variable arg1, Variable arg2)
    {
        // TODO: Gestion des erreurs de type numérique...
        // Vu qu'on met a disposition que les types de bases, ça ira pas plus loin que ça.

        if ((arg1.getValue() instanceof Number && arg2.getValue() instanceof Number) || (arg1.getValue() instanceof BigDecimal && arg2.getValue() instanceof BigDecimal))
        {
            BigDecimal number1 = new BigDecimal(arg1.getValue().toString());
            BigDecimal number2 = new BigDecimal(arg2.getValue().toString());
            return number1.compareTo(number2) < 0;
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    public String toString()
    {
        return "<";
    }
}
