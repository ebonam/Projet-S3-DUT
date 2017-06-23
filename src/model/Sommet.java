package model;

import java.util.ArrayList;
import java.util.Observable;

import model.old.Element;

public class Sommet extends Observable implements Element {

	 public String nom;
     public Boolean etatfinal = false;
     public Boolean etatInit = false;
     ArrayList<arc> entree=new ArrayList<arc>();
     ArrayList<arc> sortie=new ArrayList<arc>();
     ArrayList<Letter> recursion=new ArrayList<Letter>();
	
     public Sommet(String n,Boolean ef,Boolean eI) {
         nom = n;
         etatfinal = ef;
         etatInit = eI;

     }
     
     
     public String GetLetterREc()
     {
         if (this.recursion.size() == 0)
             return "";
         if (recursion.size() == 1)
             return recursion.get(1)+ "*";
         String str = "";
         //string str = "(";
         for (Letter rec : recursion) {
                 str += "(" + rec + ")*";    
         }
         return str;
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
