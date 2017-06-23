package model;

import java.util.ArrayList;

public class ObjetDeter {

	Letter l;
	ArrayList<Sommet> s;

	public ObjetDeter(Letter l) {
		super();
		this.l = l;
		s = new ArrayList<Sommet>();
	}

	boolean Final;

	void add(Sommet l) {
		if (l.etatfinal)
			Final = true;

		s.add(l);

	}

	boolean size() {

		return s.size() == 0 || s.size() == 1;

	}

	String SommetTransition() {
		String str = "";
		for (Sommet som : s)
			str += som.nom;
		return str;

	}

	ArrayList<Sommet> SommetTransi() {

		return s;

	}

}
