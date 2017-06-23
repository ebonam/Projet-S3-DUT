package model.old;

import java.util.Observable;

public class While extends Observable implements Element
{
    private Condition condition;
    private Sequence sequence;

    public While()
    {
        this.condition = new Condition();
        this.sequence = new Sequence();
    }

    @Override
    public Object invoke(Object... args)
    {
        this.setChanged();
        this.notifyObservers();
        while (condition.invoke())
        {
            sequence.invoke();
        }
        return null;
    }

    public Condition getCondition()
    {
        return condition;
    }

    public void setCondition(Condition condition)
    {
        this.condition = condition;
    }

    public Sequence getSequence()
    {
        return sequence;
    }

    public void setSequence(Sequence sequence)
    {
        this.sequence = sequence;
    }

	@Override
	public String toCode() {

		

	return ("while"+condition.toCode()+"\n{"+"\n"+sequence.toCode()+"}");
		//return sti;
		
	}

}
