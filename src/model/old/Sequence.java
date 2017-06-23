package model.old;

import java.util.ArrayList;
import java.util.Observable;

public class Sequence extends Observable implements Element
{
    private ArrayList<Element> fils;

    public Sequence()
    {
        this.fils = new ArrayList<>();
    }

    @Override
    public Object invoke(Object... args)
    {
        boolean flagReturn = false;
        Object toReturn = null;
        int i = 0;
        this.setChanged();
        this.notifyObservers();
        while (i < fils.size() && !flagReturn)
        {
            Element element = fils.get(i);
            toReturn = element.invoke();
            if (element instanceof Retour)
            {
                flagReturn = true;
            }
            else
            {
                i++;
            }
        }
        return toReturn;
    }

    public void ajouterFils(Element fils)
    {
        this.fils.add(fils);
    }

    public ArrayList<Element> getFils()
    {
        return fils;
    }

    public void setFils(ArrayList<Element> fils)
    {
        this.fils = fils;
    }

	@Override
	public String toCode() {
String r=new String();
		for(Element e:fils)
			r+=(e.toCode());
		return r;
	}


}
