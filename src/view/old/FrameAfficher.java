package view.old;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.old.Variable;

public class FrameAfficher extends JFrame
{

    // private JLabel deroulVar1;
    private JLabel deroulVar2;
    private JLabel text, egal;
    private JButton valide;

    public FrameAfficher(Variable v)
    {

        this.setTitle("Affichage");
        this.setSize(400, 150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // deroulVar1 = new JLabel("Type");
        deroulVar2 = new JLabel(v.getNom());
        text = new JLabel(v.getValue().toString());
        egal = new JLabel("=");
        valide = new JButton("Valider");

        Font font = new Font("Arial", Font.PLAIN, 20);
        // deroulVar1.setFont(font);
        deroulVar2.setFont(font);
        text.setFont(font);
        egal.setFont(font);

        // deroulVar1.setPreferredSize(new Dimension(60, 30));
        // deroulVar2.setPreferredSize(new Dimension(60, 30));
        // text.setPreferredSize(new Dimension(60, 30));
        // egal.setPreferredSize(new Dimension(30, 30));

        JPanel containerV1 = new JPanel();
        // containerV1.add(deroulVar1);
        containerV1.add(deroulVar2);
        containerV1.add(egal);
        containerV1.add(text);

        JPanel cont = new JPanel();
        cont.add(valide);

        this.setVisible(true);
        this.setLayout(new GridLayout(2, 4));

        this.getContentPane().add(containerV1);
        this.getContentPane().add(cont);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args)
    {

        FrameAfficher f = new FrameAfficher(new Variable());

    }

}
