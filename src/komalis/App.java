package komalis;

import controller.ElementManager;

public class App
{

    // Ceci est une classe de test, il faudra la supprimer à la fin!
    public static void main(String[] args)
    {

        ElementManager.getInstance().ajouterHeader();
        ElementManager.getInstance().ajouterSequence(ElementManager.getInstance().getHeader());
        // ElementManager.getInstance().getHeader().getHeader().launchArbre();
        // element_manager.ajouterSequence(element_manager.getFigure("6"));

        // Header header = ElementManager.getInstance().getHeader().getHeader();
        //
        // header.newVariable(new Variable());
        // header.setNameVariable("temporary", "devine");
        // header.newVariable(new Variable());
        // header.setNameVariable("temporary", "donner");
        // header.newVariable(new Variable());
        //
        // Variable offsetAfficher = new Variable();
        // offsetAfficher.setValue("Entrer une valeur: ");
        // Variable offsetSup = new Variable();
        // offsetSup.setValue("C'est supérieur!\n");
        // Variable offsetInf = new Variable();
        // offsetInf.setValue("C'est inférieur!\n");
        // Variable offsetEga = new Variable();
        // offsetEga.setValue("Bravo!\n");

        // Afficher afficher = new Afficher();
        // ((Sequence) header.getElement(0)).ajouterFils(afficher);
        // afficher.setAfficher(offsetAfficher);
        //
        // Saisir saisir = new Saisir();
        // ((Sequence) header.getElement(0)).ajouterFils(saisir);
        // saisir.setVariable(header.getVariable("devine"));
        //
        // Afficher afficher_2 = new Afficher();
        // ((Sequence) header.getElement(0)).ajouterFils(afficher_2);
        // afficher_2.setAfficher(offsetAfficher);
        //
        // Saisir saisir_2 = new Saisir();
        // ((Sequence) header.getElement(0)).ajouterFils(saisir_2);
        // saisir_2.setVariable(header.getVariable("donner"));
        //
        // While whileInstruct = new While();
        // ((Sequence) header.getElement(0)).ajouterFils(whileInstruct);
        // ((While) header.getElement(5)).getCondition().setArg1(header.getVariable("devine"));
        // ((While) header.getElement(5)).getCondition().setArg2(header.getVariable("donner"));
        // ((While) header.getElement(5)).getCondition().setOperateur(Different.getInstance());
        //
        // If ifInstruction = new If();
        // ((While) header.getElement(5)).getSequence().ajouterFils(ifInstruction);
        // ((If) header.getElement(6)).getCondition().setArg1(header.getVariable("devine"));
        // ((If) header.getElement(6)).getCondition().setArg2(header.getVariable("donner"));
        // ((If) header.getElement(6)).getCondition().setOperateur(Superieur.getInstance());
        //
        // Afficher affichage_sup = new Afficher();
        // // Les gets seront fait graphiquement!
        // // Quand on cliquera sur un objet, on aura sa view, on aura juste qu'a demander au controleur le model.
        // // Donc faut pas avoir peur du multicastage.
        // ((If) header.getElement(6)).getSi_vrai().ajouterFils(affichage_sup);
        // affichage_sup.setAfficher(offsetSup);
        //
        // If ifBisInstruction = new If();
        // // Les gets seront fait graphiquement!
        // // Quand on cliquera sur un objet, on aura sa view, on aura juste qu'a demander au controleur le model.
        // // Donc faut pas avoir peur du multicastage.
        // ((If) header.getElement(6)).getSi_faux().ajouterFils(ifBisInstruction);
        // ((If) header.getElement(8)).getCondition().setArg1(header.getVariable("devine"));
        // ((If) header.getElement(8)).getCondition().setArg2(header.getVariable("donner"));
        // ((If) header.getElement(8)).getCondition().setOperateur(Inferieur.getInstance());
        //
        // Afficher afficher_inf = new Afficher();
        // ((If) header.getElement(8)).getSi_vrai().ajouterFils(afficher_inf);
        // afficher_inf.setAfficher(offsetInf);
        //
        // Afficher afficher_3 = new Afficher();
        // ((While) header.getElement(5)).getSequence().ajouterFils(afficher_3);
        // afficher_3.setAfficher(offsetAfficher);
        //
        // Saisir saisir_3 = new Saisir();
        // ((While) header.getElement(5)).getSequence().ajouterFils(saisir_3);
        // saisir_3.setVariable(header.getVariable("donner"));
        //
        // Afficher bravo = new Afficher();
        // ((Sequence) header.getElement(0)).ajouterFils(bravo);
        // bravo.setAfficher(offsetEga);

        // header.launchArbre();

        // Header header = new Header();
        //
        // header.newVariable(new Variable(new BigDecimal(4), "a"));
        // header.newVariable(new Variable(new BigDecimal(-2), "b"));
        // header.newVariable(new Variable(new BigDecimal(0), "c"));
        //
        // Calcul calcul = new Calcul(new Variable[] { header.getVariable("a"), header.getVariable("b") }, Soustraction.getInstance());
        // Affectation affectation = new Affectation(header.getVariable("c"), calcul);
        // Afficher afficher = new Afficher(header.getVariable("c"));
        //
        // Sequence firstSequence = (Sequence) header.getElements().get(0);
        // firstSequence.ajouterFils(affectation);
        // firstSequence.ajouterFils(afficher);
        //
        // Condition condition = new Condition(new Variable[] { header.getVariable("c"), header.getVariable("a") }, Superieur.getInstance());
        //
        // Sequence si_vrai = new Sequence();
        // Afficher afficher_superieur = new Afficher(new Variable("Supérieur!", "tempVariable"));
        // si_vrai.ajouterFils(afficher_superieur);
        //
        // Sequence si_faux = new Sequence();
        // Afficher afficher_inferieur = new Afficher(new Variable("Inférieur ou égal!", "tempVariable"));
        // si_faux.ajouterFils(afficher_inferieur);
        //
        // If ifStatement = new If(condition, si_vrai, si_faux);
        //
        // firstSequence.ajouterFils(ifStatement);
        //
        // header.launchArbre();

    }
}
