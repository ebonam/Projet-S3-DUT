package model;

import java.util.ArrayList;

import model.old.Element;
import model.old.Variable;


public class Affectation implements Element
{
    private Object from;
    private Variable to;

    public Affectation()
    {
        this.from = new Object();
        this.to = new Variable();
    }

    @Override
    public Object invoke(Object... args)
    {
        if (from instanceof Variable)
        {
            Variable fromVariable = (Variable) from;
            // On vérifie que les deux variables ont un type égal, quand même.
            if (fromVariable.getValue().getClass().equals(to.getValue().getClass()))
            {
                this.to.setValue(fromVariable.getValue());
            }
            else
            {
                throw new ClassCastException();
            }
        }
        return null;
    }

    public Object getFrom()
    {
        return from;
    }

    public void setFrom(Object from)
    {
        this.from = from;
    }

    public Variable getTo()
    {
        return to;
    }

    public void setTo(Variable to)
    {
        this.to = to;
    }

	@Override
	public String toCode() {
		
		//ArrayList<String>r=new ArrayList<String>();
		return (this.to+"="+this.from+";");
		
		//return r;
	}


}
