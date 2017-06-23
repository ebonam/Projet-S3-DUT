package model.old;

public class Retour implements Element
{

    private Variable toReturn;

    public Retour()
    {
        this.toReturn = new Variable();
    }

    public Variable getToReturn()
    {
        return toReturn;
    }

    public void setToReturn(Variable toReturn)
    {
        this.toReturn = toReturn;
    }

    @Override
	public Object invoke(Object... args) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toCode() {

		// TODO Auto-generated method stub
        return null;
    }

}
