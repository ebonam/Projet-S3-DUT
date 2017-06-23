package model.old;

public class Calcul implements Element
{
    private Variable arg1;
    private Variable arg2;
    private OperateurArithmetique operateur;

    public Calcul()
    {
        this.arg1 = new Variable();
        this.arg2 = new Variable();
        this.operateur = Soustraction.getInstance();
    }

    @Override
    public Object invoke(Object... args)
    {
        return operateur.invoke(arg1, arg2);
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

    public OperateurArithmetique getOperateur()
    {
        return operateur;
    }

    public void setOperateur(OperateurArithmetique operateur)
    {
        this.operateur = operateur;
    }

	@Override
	public String toCode() {
		//ArrayList<String> sti  =new ArrayList<String>();

		return(arg1+""+operateur.toCode()+""+arg2+";\n");
		//return sti;
		
	}

}
