package view.old;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class FrameElements extends JPanel
{
    private JButton buttonAffectation, buttonSequence, buttonIf, buttonFor, buttonWhile, buttonGestionVariables, buttonLireVariable, buttonAfficher, buttonEditerElement, buttonSupprimerElement;

    public FrameElements()
    {
        this.buttonAffectation = new JButton("Nouveau sommet");
        this.buttonSequence = new JButton("Nouvelle transition");
        this.buttonIf = new JButton("If");
        this.buttonFor = new JButton("Ajouter Lettre");
        this.buttonWhile = new JButton("While");
        this.buttonGestionVariables = new JButton("Gestion de variables");
        this.buttonLireVariable = new JButton("Saisir variable");
        this.buttonAfficher = new JButton("Afficher variable");
        this.buttonSupprimerElement = new JButton("Supprimer element");
        this.buttonEditerElement = new JButton("Editer element");

        GridLayout gridPrincipal = new GridLayout(4, 1);
        GridLayout gridElementsSimple = new GridLayout(3, 1);
        GridLayout gridElementBoucle = new GridLayout(4, 1);
        GridLayout gridVariables = new GridLayout(4, 1);

        // permet de gerer l'espacement entre les �lements dans un grid layout
        gridElementBoucle.setVgap(3);
        gridElementsSimple.setVgap(5);
        gridVariables.setVgap(3);

        this.setLayout(gridPrincipal);
        JPanel panelElementsSimple = new JPanel(gridElementsSimple);
        JPanel panelElementsBoucle = new JPanel(gridElementBoucle);
        JPanel panelVariables = new JPanel(gridVariables);
        JPanel panelGestionElements = new JPanel(gridElementsSimple);

        panelElementsSimple.add(buttonAffectation);
        panelElementsSimple.add(buttonSequence);

        TitledBorder titreInstruction = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Instruction", TitledBorder.CENTER, TitledBorder.CENTER, new Font("Helvetica", Font.BOLD, 19), Color.black);
        panelElementsSimple.setBorder(titreInstruction);

        panelGestionElements.add(buttonEditerElement);
        panelGestionElements.add(buttonSupprimerElement);
        TitledBorder titreGestionElements = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Gestion des elements", TitledBorder.CENTER, TitledBorder.CENTER, new Font("Helvetica", Font.BOLD, 15), Color.black);
        panelGestionElements.setBorder(titreGestionElements);

        panelElementsBoucle.add(buttonIf);
        panelElementsBoucle.add(buttonFor);
        panelElementsBoucle.add(buttonWhile);

        TitledBorder titreBoucles = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Boucles", TitledBorder.CENTER, TitledBorder.CENTER, new Font("Helvetica", Font.BOLD, 19), Color.black);
        panelElementsBoucle.setBorder(titreBoucles);

        panelVariables.add(buttonGestionVariables);
        panelVariables.add(buttonLireVariable);
        panelVariables.add(buttonAfficher);
        TitledBorder titreVariables = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Variables", TitledBorder.CENTER, TitledBorder.CENTER, new Font("Helvetica", Font.BOLD, 19), Color.black);
        panelVariables.setBorder(titreVariables);
        this.add(panelElementsSimple);
        this.add(panelElementsBoucle);
        this.add(panelGestionElements);
        this.add(panelVariables);

        this.setPreferredSize(new Dimension(200, 600));

    }

    public JButton getButtonAffectation()
    {
        return buttonAffectation;
    }

    public void setButtonAffectation(JButton buttonAffectation)
    {
        this.buttonAffectation = buttonAffectation;
    }

    public JButton getButtonSequence()
    {
        return buttonSequence;
    }

    public void setButtonSequence(JButton buttonSequence)
    {
        this.buttonSequence = buttonSequence;
    }

    public JButton getButtonIf()
    {
        return buttonIf;
    }

    public void setButtonIf(JButton buttonIf)
    {
        this.buttonIf = buttonIf;
    }

    public JButton getButtonFor()
    {
        return buttonFor;
    }

    public void setButtonFor(JButton buttonFor)
    {
        this.buttonFor = buttonFor;
    }

    public JButton getButtonWhile()
    {
        return buttonWhile;
    }

    public void setButtonWhile(JButton buttonWhile)
    {
        this.buttonWhile = buttonWhile;
    }

    public JButton getButtonVariables()
    {
        return buttonGestionVariables;
    }

    public void setButtonVariables(JButton buttonVariables)
    {
        this.buttonGestionVariables = buttonVariables;
    }

    public JButton getButtonGestionVariables()
    {
        return buttonGestionVariables;
    }

    public void setButtonGestionVariables(JButton buttonGestionVariables)
    {
        this.buttonGestionVariables = buttonGestionVariables;
    }

    public JButton getButtonLireVariable()
    {
        return buttonLireVariable;
    }

    public void setButtonLireVariable(JButton buttonLireVariable)
    {
        this.buttonLireVariable = buttonLireVariable;
    }

    public JButton getButtonAfficher()
    {
        return buttonAfficher;
    }

    public void setButtonAfficher(JButton buttonAfficher)
    {
        this.buttonAfficher = buttonAfficher;
    }

    public JButton getButtonEditerElement()
    {
        return buttonEditerElement;
    }

    public void setButtonEditerElement(JButton buttonEditerElement)
    {
        this.buttonEditerElement = buttonEditerElement;
    }

    public JButton getButtonSupprimerElement()
    {
        return buttonSupprimerElement;
    }

    public void setButtonSupprimerElement(JButton buttonSupprimerElement)
    {
        this.buttonSupprimerElement = buttonSupprimerElement;
    }

}
