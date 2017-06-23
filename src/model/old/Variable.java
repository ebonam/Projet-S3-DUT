package model.old;

import java.math.BigDecimal;

public class Variable
{
    private Object value;
    private String nom;
    private String type;
    private boolean affichage;

    public Variable()
    {
        this.value = new BigDecimal(0);
        this.nom = new String("0");
        this.affichage = false;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;

    }

	public String toCode() {

	

		return("float "+nom+"= "+this.value+";\n");
		
		
	}

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String toString()
    {
        return this.nom;
    }

    public boolean isAffichage()
    {
        return affichage;
    }

    public void setAffichage(boolean affichage)
    {
        this.affichage = affichage;
    }

}
