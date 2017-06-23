package model.old;

import java.util.Observable;

public class If extends Observable implements Element
{

    private Condition condition;
    private Sequence si_vrai;
    private Sequence si_faux;

    public If()
    {
        this.condition = new Condition();
        this.si_vrai = new Sequence();
        this.si_faux = new Sequence();
    }

    @Override
    public Object invoke(Object... args)
    {
        this.setChanged();
        this.notifyObservers();
        if (condition.invoke())
        {
            return this.si_vrai.invoke();
        }
        else
        {
            return this.si_faux.invoke();
        }
    }

    public Condition getCondition()
    {
        return condition;
    }

    public void setCondition(Condition condition)
    {
        this.condition = condition;
    }

    public Sequence getSi_vrai()
    {
        return si_vrai;
    }

    public void setSi_vrai(Sequence si_vrai)
    {
        this.si_vrai = si_vrai;
    }

    public Sequence getSi_faux()
    {
        return si_faux;
    }

    public void setSi_faux(Sequence si_faux)
    {
        this.si_faux = si_faux;
    }

	@Override
	public String toCode() {
		String sti  =new String();

		sti+="if("+condition.toCode()+"){"+"\n";
		sti+=(si_vrai.toCode());
		sti+="}"+"" ;
		sti+=("else {"+"\n");
		sti+=si_faux.toCode();
		sti+=("}"+"\n");
		
		return sti;
	}


}
