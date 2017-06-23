package model.old;

import java.math.BigDecimal;
import java.util.ArrayList;

public class For implements Element
{

    private Variable longueur;
    private Sequence sequence;
    private BigDecimal iterateur;

    @Override
    public Object invoke(Object... args)
    {
        if (longueur.getValue() instanceof BigDecimal)
        {
            for (iterateur = new BigDecimal(0); iterateur.compareTo((BigDecimal) longueur.getValue()) < 0; iterateur.add(new BigDecimal(1)))
            {
                sequence.invoke();
            }
        }
        return null;
    }

	@Override
	public String toCode() {
		ArrayList<String>r=new ArrayList<String>();
		
		//a changer
		
		return null;
	}

}
