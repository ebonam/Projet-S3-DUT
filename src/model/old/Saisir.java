package model.old;

import java.util.Observable;
import java.util.Scanner;

public class Saisir extends Observable implements Element
{
    private Variable variable;

    public Saisir()
    {
        this.variable = new Variable();
    }

    @Override
    public Object invoke(Object... args)
    {
        @SuppressWarnings({ "resource", "unused" })
        Scanner sc = new Scanner(System.in);
        // TODO: Gérer la saisie des différents type.
        this.setChanged();
        this.notifyObservers();
        if (this.variable.getValue() instanceof Number)
        {
            // this.variable.setValue(new BigDecimal(sc.nextLine()));
            this.setChanged();
            this.notifyObservers(this);

        }
        else
        {
            throw new UnknownError();
        }
        return null;
    }

    public Variable getVariable()
    {
        return variable;
    }

    public void setVariable(Variable variable)
    {
        this.variable = variable;
    }

	@Override
	public String toCode() {
		///A/rrayList<String>r=new ArrayList();
		
	return("scanf(\"%f\",&"+this.variable+");"+"\n");
		
		
	}

}
