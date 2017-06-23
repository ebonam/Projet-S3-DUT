package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Letter;
import model.old.Afficher;

public class ElementManager implements Observer {
	private static ElementManager INSTANCE;

	private Hashtable<String, Figure> elements;

	public Hashtable<String, FigureSommet> sommet;
	// TODO AL ??
	public Hashtable<String, Letter> letter;
	public Hashtable<String, FigureFleche> transition;
	public ArrayList<String> nameSommet;

	public String ToFile() {
		String str = "";
		Iterator<Entry<String, FigureSommet>> it2 = ElementManager.getInstance().sommet.entrySet().iterator();
		while (it2.hasNext()) {
			Entry<String, FigureSommet> entry = it2.next();
			str += entry.getValue().toSave();
		} // Foreahc
		Iterator<Entry<String, Letter>> it = ElementManager.getInstance().letter.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Letter> entry = it.next();
			str += entry.getValue().toSave();
		}
		Iterator<Entry<String, FigureFleche>> it3 = ElementManager.getInstance().transition.entrySet().iterator();
		while (it3.hasNext()) {
			Entry<String, FigureFleche> entry = it3.next();
			str += entry.getValue().toSave();
		}
		return str;
	}

	public void FromFile(String str) {
		String[] s = str.split("?");
		letter.clear();
		transition.clear();
		sommet.clear();
		FileSommet(s[0]);
		FileLetter(s[1]);
		FileTransition(s[2]);

	}

	private void FileSommet(String string) {
		String str[] = string.split("!");
		for (String s : str) {
			String[] ss = s.split(";");
			FigureSommet sequence = new FigureSommet(Boolean.parseBoolean(ss[2]), Boolean.parseBoolean(ss[3]), ss[1]);
			sequence.getCell().setId(ss[0]);
			sequence.getCell().getGeometry().setX(Double.parseDouble(""));
			sequence.getCell().getGeometry().setY(Double.parseDouble(""));
			// ElementManager.getInstance().getHeader().getHeader().getElements().add(sequence.getSequence());
			sequence.printToCanvas(null, Arbre.getInstance().getCanvas().getGraph());
			sommet.put(sequence.getCell().getId(), sequence);
			nameSommet.add(ss[1]);
		}

	}

	private void FileLetter(String string) {
		nbLetter = 0;
		String str[] = string.split("!");
		for (String s : str) {
			letter.put("" + nbLetter, new Letter(s));
			nbLetter++;

		}
	}

	int nbTrans;

	private void FileTransition(String string) {
		nbTrans = 0;
		String str[] = string.split("!");
		for (String s : str) {
			String[] ss = s.split(";");
			FigureFleche sequence = new FigureFleche(sommet.get(ss[1]), sommet.get(ss[2]), letter.get(ss[3]));
			sequence.printArrow(Arbre.getInstance().getCanvas().getGraph());
			this.transition.put("" + nbTrans, sequence);

		}

	}

	private ElementManager() {
		this.elements = new Hashtable<>();
		this.sommet = new Hashtable<>();
		this.letter = new Hashtable<>();
		this.transition = new Hashtable<>();
	}

	public static ElementManager getInstance() {
		if (ElementManager.INSTANCE == null) {
			ElementManager.INSTANCE = new ElementManager();
		}
		return ElementManager.INSTANCE;
	}

	// TODO delete
	public Figure getFigure(String id) {
		return this.elements.get(id);
	}

	public FigureSommet getSommet(String id) {
		return this.sommet.get(id);
	}

	public Letter getLetter(String id) {
		return this.letter.get(id);
	}

	// TODO DELETE
	public FigureHeader getHeader() {
		return Arbre.getInstance().getHeader();
	}

	// TODO DELETE
	public void ajouterHeader() {
		FigureHeader header = new FigureHeader();
		header.printToCanvas(null, Arbre.getInstance().getCanvas().getGraph());
		elements.put(header.getFirstCell().getId(), header);
		elements.put(header.getSecondCell().getId(), header);
		elements.put(header.getThirdCell().getId(), header);

		Arbre.getInstance().setHeader(header);

	}

	// TODO MODIF HERE + faire fleche
	public void ajoutSommet(Figure father, boolean a, boolean b, String n) {

		FigureSommet sequence = new FigureSommet(a, b, n);
		// ElementManager.getInstance().getHeader().getHeader().getElements().add(sequence.getSequence());
		sequence.printToCanvas(father, Arbre.getInstance().getCanvas().getGraph());
		sommet.put(sequence.getCell().getId(), sequence);

	}

	public void ajoutTransition(Figure father, String ks1, String kl1, String ks2) {

		FigureFleche sequence = new FigureFleche(sommet.get(ks1), sommet.get(ks2), letter.get(kl1));
		sequence.printArrow(Arbre.getInstance().getCanvas().getGraph());
		// ElementManager.getInstance().getHeader().getHeader().getElements().add(sequence.getSequence());
		// sequence.printToCanvas(father,
		// Arbre.getInstance().getCanvas().getGraph());
		// sommet.put(sequence.getCell().getId(), sequence);

	}

	public void ajouterSequence(Figure father) {
		if (father instanceof FigureHeader) {
			FigureSequence sequence = new FigureSequence();
			ElementManager.getInstance().getHeader().getHeader().getElements().add(sequence.getSequence());
			sequence.printToCanvas(father, Arbre.getInstance().getCanvas().getGraph());
			elements.put(sequence.getCell().getId(), sequence);
		} else if (father instanceof FigureSequence) {
			FigureSequence father_sequence = (FigureSequence) father;
			FigureSequence sequence = new FigureSequence();
			father_sequence.getSequence().ajouterFils(sequence.getSequence());
			sequence.printToCanvas(father, Arbre.getInstance().getCanvas().getGraph());
			elements.put(sequence.getCell().getId(), sequence);
		}
	}

	public void ajouterAfficher(Figure father) {
		if (father instanceof FigureSequence) {
			FigureAfficher afficher = new FigureAfficher();
			afficher.getAfficher().addObserver(this);
			FigureSequence father_sequence = (FigureSequence) father;
			father_sequence.getSequence().ajouterFils(afficher.getAfficher());
			afficher.printToCanvas(father_sequence, Arbre.getInstance().getCanvas().getGraph());
			elements.put(afficher.getCell().getId(), afficher);
		} else {
			throw new UnknownError();
		}
	}

	public void ajouterSaisir(Figure father) {
		if (father instanceof FigureSequence) {
			FigureSaisir saisir = new FigureSaisir();
			saisir.getSaisir().addObserver(this);
			FigureSequence father_sequence = (FigureSequence) father;
			father_sequence.getSequence().ajouterFils(saisir.getSaisir());
			saisir.printToCanvas(father_sequence, Arbre.getInstance().getCanvas().getGraph());
			elements.put(saisir.getCell().getId(), saisir);
		} else {
			throw new UnknownError();
		}
	}

	public FigureIf ajouterIf(Figure father) {
		FigureIf ifInstruct = null;
		if (father instanceof FigureSequence) {
			ifInstruct = new FigureIf();
			FigureSequence father_sequence = (FigureSequence) father;
			father_sequence.getSequence().ajouterFils(ifInstruct.getIfInstruct());
			ifInstruct.printToCanvas(father_sequence, Arbre.getInstance().getCanvas().getGraph());
			elements.put(ifInstruct.getIfCell().getId(), ifInstruct);
			elements.put(ifInstruct.getConditionCell().getId(), ifInstruct);
			elements.put(ifInstruct.getSi_vrai().getCell().getId(), ifInstruct.getSi_vrai());
			elements.put(ifInstruct.getSi_faux().getCell().getId(), ifInstruct.getSi_faux());
		} else {
			throw new UnknownError();
		}
		return ifInstruct;
	}

	public FigureWhile ajouterWhile(Figure father) {
		FigureWhile whileInstruct = null;
		if (father instanceof FigureSequence) {
			whileInstruct = new FigureWhile();
			FigureSequence father_sequence = (FigureSequence) father;
			father_sequence.getSequence().ajouterFils(whileInstruct.getWhileInstruct());
			whileInstruct.printToCanvas(father_sequence, Arbre.getInstance().getCanvas().getGraph());
			elements.put(whileInstruct.getWhileCell().getId(), whileInstruct);
			elements.put(whileInstruct.getConditionCell().getId(), whileInstruct);
			elements.put(whileInstruct.getSequence().getCell().getId(), whileInstruct.getSequence());
		}
		return whileInstruct;
	}

	public void supprimerFigure(Figure figure) {
		try {
			Figure father = figure.getFather();
			if (father instanceof FigureSequence) {

				figure.deleteFromCanvas(Arbre.getInstance().getCanvas().getGraph());
				Arbre.getInstance().getCanvas().getGraph().repaint();

				FigureSequence fatherSequence = (FigureSequence) father;
				fatherSequence.getSequence().getFils().remove(figure.getElement());
				fatherSequence.getFils().remove(figure);
			}
			for (Figure fils : figure.getFils()) {
				ElementManager.getInstance().supprimerFigure(fils);
			}
		} catch (ConcurrentModificationException e) {
			;
		}

	}

	public Hashtable<String, Figure> getElements() {
		return elements;
	}

	public void setElements(Hashtable<String, Figure> elements) {
		this.elements = elements;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Afficher) {
			Afficher afficher = (Afficher) arg1;
			JLabel jlabel = new JLabel(afficher.getAfficher().getValue().toString(), JLabel.CENTER);
			JOptionPane.showMessageDialog(new JFrame(), jlabel, "Afficher", JOptionPane.PLAIN_MESSAGE);

			// String lesTextes[] = { "Message", "Variable" };
			// int retourOptionDialog = JOptionPane.showOptionDialog(new
			// JFrame(), "Que voulez-vous afficher?", "Afficher", 0, 0, null,
			// lesTextes, lesTextes[0]);
			// if (retourOptionDialog == 0)
			// {
			// String retour = JOptionPane.showInputDialog(new JFrame(),
			// String.format("Saisir le texte à afficher."), "Saisir", 1);
			// Variable affichage = new Variable();
			// affichage.setType("String");
			// affichage.setValue(retour);
			// afficher.setAfficher(affichage);prim
			// }
			// else
			// {
			//
			// }

			// ArrayList<Variable> data = new ArrayList<>();
			// Iterator<Entry<String, Variable>> it =
			// Arbre.getInstance().getHeader().getHeader().getVariables_locals().entrySet().iterator();
			//
			// while (it.hasNext())
			// {
			// Entry<String, Variable> entry = it.next();
			// Variable v = entry.getValue();
			// data.add(v);
			// }
			//
			// JOptionPane.showInputDialog(new JFrame(), "Choisir une variable",
			// "Variable", JOptionPane.PLAIN_MESSAGE, null, data.toArray(),
			// (data.size() == 0) ? null : data.toArray()[0]);

			// Afficher afficher = (Afficher) arg1;
			// JOptionPane.showMessageDialog(new JFrame(),
			// afficher.getAfficher().getValue().toString(), "Afficher", 0);
		} else if (arg1 instanceof model.old.Saisir) {
			boolean flag = false;
			while (!flag) {
				flag = true;
				// les textes figurant sur les boutons
				model.old.Saisir saisir = (model.old.Saisir) arg1;
				String retour = JOptionPane.showInputDialog(new JFrame(),
						String.format("Saisir la valeur de la variable %s.", saisir.getVariable().getNom()), "Saisir",
						1);
				if (saisir.getVariable().getValue() instanceof BigDecimal
						|| saisir.getVariable().getValue() instanceof Number) {
					try {
						if (saisir.getVariable().getType().equals("Integer")) {
							saisir.getVariable().setValue(new Integer(retour));

						} else if (saisir.getVariable().getType().equals("Float")) {
							saisir.getVariable().setValue(new Float(retour));
						}
						// saisir.getVariable().setValue(new
						// BigDecimal(retour));
						// System.out.println(((Integer) ((BigDecimal)
						// saisir.getVariable().getValue()).intValue()).toString().equals(saisir.getVariable().getValue().toString()));
					} catch (NumberFormatException e) {
						flag = false;
						JOptionPane.showMessageDialog(new JFrame(), "Ce n'est pas le bon type !", "Erreur de type",
								JOptionPane.ERROR_MESSAGE);
					} catch (NullPointerException e) {
						flag = false;
						JOptionPane.showMessageDialog(new JFrame(), "Aucune saisie...", "Erreur de saisie",
								JOptionPane.ERROR_MESSAGE);

					}
				}
			}

		}
	}

	int nbLetter;

	public void ajoutLetter(String n) {
		// TODO Auto-generated method stub
		letter.put("" + nbLetter, new Letter(n));
		nbLetter++;
	}

}
