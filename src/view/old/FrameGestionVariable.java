package view.old;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.Arbre;
import model.old.Variable;

public class FrameGestionVariable extends JFrame
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTable jListVariable;
    private VariablesModele modelJListVariables;
    private Container c;
    // JButton pour le menu principal
    private JButton buttonAjouter, buttonSupprimer, buttonModifier;
    // Element pour le menu d'ajout de variable
    private JButton buttonCreer, buttonRetour;
    private JTextField nomVariable, valeurVariable;
    private JComboBox<?> boxTypeVariable;

    public FrameGestionVariable()
    {
        this.setLocationRelativeTo(null);
        this.setSize(600, 400);
        this.initMenuPrincipal();

    }

    public void initMenuPrincipal()
    {
        this.getContentPane().repaint();
        this.getContentPane().removeAll();
        c = this.getContentPane();

        this.buttonAjouter = new JButton("Ajouter");
        this.buttonModifier = new JButton("Modifier");
        this.buttonSupprimer = new JButton("Supprimer");

        GridLayout gridButton = new GridLayout(1, 3);
        JPanel panelButtons = new JPanel();

        this.buttonAjouter.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                initCreerVariable();
            }
        });
        this.buttonModifier.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (jListVariable.getSelectedRow() == -1)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Pas de variable selectionnée");
                }
                else
                {
                    Variable v = modelJListVariables.getData().get(jListVariable.getSelectedRow());
                    FrameGestionVariable.this.initModifVariable(v);
                }
            }
        });

        this.buttonSupprimer.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (jListVariable.getSelectedRow() == -1)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Pas de variable selectionnée");
                }
                else
                {
                    Variable v = modelJListVariables.getData().get(jListVariable.getSelectedRow());
                    modelJListVariables.getData().remove(v);
                    Arbre.getInstance().getHeader().getHeader().deleteVariable(v.getNom());
                    modelJListVariables.fireTableDataChanged();
                }
            }
        });

        panelButtons.setLayout(gridButton);
        panelButtons.add(buttonAjouter);
        panelButtons.add(buttonModifier);
        panelButtons.add(buttonSupprimer);

        modelJListVariables = new VariablesModele();
        jListVariable = new JTable(modelJListVariables);
        this.add(new JScrollPane(jListVariable));
        c.setLayout(new BorderLayout());
        c.add(new JScrollPane(jListVariable), BorderLayout.CENTER);
        c.add(panelButtons, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void initCreerVariable()
    {
        this.getContentPane().repaint();
        this.getContentPane().removeAll();
        c = this.getContentPane();

        this.buttonCreer = new JButton("Créer");
        this.buttonRetour = new JButton("Retour");
        GridLayout gridButton = new GridLayout(1, 4);
        JPanel panelButtons = new JPanel(gridButton);

        this.buttonRetour.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                initMenuPrincipal();

            }
        });
        panelButtons.add(buttonRetour);
        panelButtons.add(new JPanel());
        panelButtons.add(new JPanel());
        panelButtons.add(buttonCreer);

        JPanel panelFormulaire = new JPanel();
        GridLayout gridFormulaire = new GridLayout(3, 1);
        gridFormulaire.setVgap(100);
        panelFormulaire.setLayout(gridFormulaire);

        JPanel panelTypeVariable = new JPanel();
        JPanel panelNomVariable = new JPanel();
        JPanel panelValeurVariable = new JPanel();

        GridLayout grid2 = new GridLayout(1, 2);
        panelTypeVariable.setLayout(grid2);
        panelNomVariable.setLayout(grid2);
        panelValeurVariable.setLayout(grid2);

        String[] type = { "Text", "Integer", "Float", "Boolean" };
        this.boxTypeVariable = new JComboBox<Object>(type);
        panelTypeVariable.add(new JLabel("Type de la variable : "));
        panelTypeVariable.add(boxTypeVariable);
        panelFormulaire.add(panelTypeVariable);

        this.nomVariable = new JTextField();
        panelNomVariable.add(new JLabel("Nom de la variable : "));
        panelNomVariable.add(nomVariable);
        panelFormulaire.add(panelNomVariable);

        this.valeurVariable = new JTextField();
        panelValeurVariable.add(new JLabel("Valeur de la variable : "));
        panelValeurVariable.add(valeurVariable);
        panelFormulaire.add(panelValeurVariable);

        c.setLayout(new BorderLayout());
        c.add(panelFormulaire, BorderLayout.CENTER);
        c.add(panelButtons, BorderLayout.SOUTH);
        this.setVisible(true);

        this.buttonCreer.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (nomVariable.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Pas de nom choisi pour la variable.");
                }
                else if (valeurVariable.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Pas de valeur choisi pour la variable.");

                }
                else
                {
                    // Cr�ation de la variable
                    String type = boxTypeVariable.getSelectedItem().toString();
                    String nom = nomVariable.getText();
                    String valeur = valeurVariable.getText();
                    Variable v = new Variable();
                    v.setNom(nom);
                    v.setType(type);
                    boolean flag = false;
                    switch (type)
                    {
                        case "Integer":
                            v.setValue(new Integer(valeur));
                            break;
                        case "Float":
                            v.setValue(new Float(valeur));
                            break;
                        default:
                            flag = true;
                            break;
                    }
                    if (!flag)
                    {
                        Arbre.getInstance().getHeader().getHeader().newVariable(v);
                        FrameGestionVariable.this.dispose();
                        new FrameGestionVariable();
                    }
                    else
                    {
                        throw new UnknownError();
                    }
                }

            }
        });

    }

    public void initModifVariable(Variable v)
    {
        String nom_last = v.getNom();
        this.getContentPane().repaint();
        this.getContentPane().removeAll();
        c = this.getContentPane();

        this.buttonCreer = new JButton("Modifier");
        this.buttonRetour = new JButton("Retour");
        GridLayout gridButton = new GridLayout(1, 4);
        JPanel panelButtons = new JPanel(gridButton);

        this.buttonRetour.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                initMenuPrincipal();

            }
        });
        panelButtons.add(buttonRetour);
        panelButtons.add(new JPanel());
        panelButtons.add(new JPanel());
        panelButtons.add(buttonCreer);

        JPanel panelFormulaire = new JPanel();
        GridLayout gridFormulaire = new GridLayout(3, 1);
        gridFormulaire.setVgap(100);
        panelFormulaire.setLayout(gridFormulaire);

        JPanel panelTypeVariable = new JPanel();
        JPanel panelNomVariable = new JPanel();
        JPanel panelValeurVariable = new JPanel();

        GridLayout grid2 = new GridLayout(1, 2);
        panelTypeVariable.setLayout(grid2);
        panelNomVariable.setLayout(grid2);
        panelValeurVariable.setLayout(grid2);

        String[] type = { "Text", "Integer", "Float", "Boolean" };
        this.boxTypeVariable = new JComboBox<Object>(type);
        panelTypeVariable.add(new JLabel("Type de la variable : "));
        panelTypeVariable.add(boxTypeVariable);

        this.boxTypeVariable.setSelectedItem(v.getType());

        panelFormulaire.add(panelTypeVariable);

        this.nomVariable = new JTextField();
        panelNomVariable.add(new JLabel("Nom de la variable : "));
        panelNomVariable.add(nomVariable);

        this.nomVariable.setText(v.getNom());

        panelFormulaire.add(panelNomVariable);

        this.valeurVariable = new JTextField();
        panelValeurVariable.add(new JLabel("Valeur de la variable : "));
        panelValeurVariable.add(valeurVariable);

        this.valeurVariable.setText(v.getValue().toString());

        panelFormulaire.add(panelValeurVariable);

        c.setLayout(new BorderLayout());
        c.add(panelFormulaire, BorderLayout.CENTER);
        c.add(panelButtons, BorderLayout.SOUTH);
        this.setVisible(true);

        this.buttonCreer.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (nomVariable.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Pas de nom choisi pour la variable.");
                }
                else if (valeurVariable.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Pas de valeur choisi pour la variable.");

                }
                else
                {
                    // Cr�ation de la variable
                    String type = boxTypeVariable.getSelectedItem().toString();
                    String nom = nomVariable.getText();
                    String valeur = valeurVariable.getText();
                    Variable v = new Variable();
                    v.setNom(nom);
                    v.setType(type);
                    boolean flag = false;
                    switch (type)
                    {
                        case "Integer":
                            v.setValue(new BigDecimal(valeur));
                            break;
                        default:
                            flag = true;
                            break;
                    }
                    if (!flag)
                    {
                        Arbre.getInstance().getHeader().getHeader().modifVariable(nom_last, v);
                        FrameGestionVariable.this.dispose();
                        new FrameGestionVariable();
                    }
                    else
                    {
                        throw new UnknownError();
                    }
                }

            }
        });

    }

    public JTable getjListVariable()
    {
        return jListVariable;
    }

    public void setjListVariable(JTable jListVariable)
    {
        this.jListVariable = jListVariable;
    }

    public VariablesModele getModelJListVariables()
    {
        return modelJListVariables;
    }

    public void setModelJListVariables(VariablesModele modelJListVariables)
    {
        this.modelJListVariables = modelJListVariables;
    }

    public Container getC()
    {
        return c;
    }

    public void setC(Container c)
    {
        this.c = c;
    }

    public JButton getButtonAjouter()
    {
        return buttonAjouter;
    }

    public void setButtonAjouter(JButton buttonAjouter)
    {
        this.buttonAjouter = buttonAjouter;
    }

    public JButton getButtonSupprimer()
    {
        return buttonSupprimer;
    }

    public void setButtonSupprimer(JButton buttonSupprimer)
    {
        this.buttonSupprimer = buttonSupprimer;
    }

    public JButton getButtonModifier()
    {
        return buttonModifier;
    }

    public void setButtonModifier(JButton buttonModifier)
    {
        this.buttonModifier = buttonModifier;
    }

    public JButton getButtonCreer()
    {
        return buttonCreer;
    }

    public void setButtonCreer(JButton buttonCreer)
    {
        this.buttonCreer = buttonCreer;
    }

    public JButton getButtonRetour()
    {
        return buttonRetour;
    }

    public void setButtonRetour(JButton buttonRetour)
    {
        this.buttonRetour = buttonRetour;
    }

    public JTextField getNomVariable()
    {
        return nomVariable;
    }

    public void setNomVariable(JTextField nomVariable)
    {
        this.nomVariable = nomVariable;
    }

    public JTextField getValeurVariable()
    {
        return valeurVariable;
    }

    public void setValeurVariable(JTextField valeurVariable)
    {
        this.valeurVariable = valeurVariable;
    }

    public JComboBox<?> getBoxTypeVariable()
    {
        return boxTypeVariable;
    }

    public void setBoxTypeVariable(JComboBox<?> boxTypeVariable)
    {
        this.boxTypeVariable = boxTypeVariable;
    }

}
