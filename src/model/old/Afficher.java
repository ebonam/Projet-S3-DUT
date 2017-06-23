package model.old;

import java.util.Observable;

public class Afficher extends Observable implements Element
{
    private Variable afficher;

    public Afficher()
    {
        this.afficher = new Variable();
    }

    @Override
    public Object invoke(Object... args)
    {
        // On est censé demander au singleton arbre d'afficher, c'est un controlleur, il s'occupera de demander à l'interface de l'afficher.
        // Pour les tests on fera ça avec println
        // System.out.print(afficher.getValue().getClass().cast(afficher.getValue()));
        this.setChanged();
        this.notifyObservers();
        this.setChanged();
        this.notifyObservers(this);
        return null;
    }

    public Variable getAfficher()
    {
        return afficher;
    }

    public void setAfficher(Variable afficher)
    {
        this.afficher = afficher;
    }




	@Override
	public String toCode() {
		
	if (afficher.getValue() instanceof String ){
		return( "printf(\""+ afficher.getValue()+"\");"+"\n");
		
		
		
}
		// TODO Auto-generated method stub
	return( "printf(\"%f\" ,"+ afficher.getNom() +");"+"\n");
		
		
		
	}
    

}

