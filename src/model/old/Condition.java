package model.old;

import java.util.Observable;

public class Condition extends Observable
{
    private Variable arg1;
    private Variable arg2;
    private OperateurBoolean operateur;

    public Condition()
    {
        this.arg1 = new Variable();
        this.arg2 = new Variable();
        this.operateur = Inferieur.getInstance();
    }

    public boolean invoke(Object... args)
    {
        this.setChanged();
        this.notifyObservers(this);
        return operateur.invoke(this.arg1, this.arg2);
    }

    public Variable getArg1()
    {
        return arg1;
    }

    public void setArg1(Variable arg1)
    {
        this.arg1 = arg1;
    }

    public Variable getArg2()
    {
        return arg2;
    }

    public void setArg2(Variable arg2)
    {
        this.arg2 = arg2;
    }

    public OperateurBoolean getOperateur()
    {
        return operateur;
    }

    public void setOperateur(OperateurBoolean operateur)
    {
        this.operateur = operateur;
    }

	public String toCode() {

		

		return ("("+arg1+" "+operateur.toCode()+" "+arg2+")");
		
		
	}

}
