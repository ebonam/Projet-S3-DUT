
package view.old;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

import controller.Arbre;
import controller.FigureWhile;
import model.old.Condition;
import model.old.Different;
import model.old.Egal;
import model.old.Inferieur;
import model.old.OperateurBoolean;
import model.old.Superieur;
import model.old.Variable;
import model.old.*;
public class FrameWhile extends JFrame
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JComboBox<?> deroulVar1, deroulVar2, deroulOpe;
    private JButton valide;
    private Condition condition;
    private FigureWhile figureWhile;

    public FrameWhile(FigureWhile figureWhile)
    {
        this.setTitle("If");
        this.condition = figureWhile.getWhileInstruct().getCondition();
        this.figureWhile = figureWhile;
        this.setSize(400, 150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        ArrayList<Variable> data = new ArrayList<>();
        Iterator<Entry<String, Variable>> it = Arbre.getInstance().getHeader().getHeader().getVariables_locals().entrySet().iterator();

        while (it.hasNext())
        {
            Entry<String, Variable> entry = it.next();
            Variable v = entry.getValue();
            data.add(v);
        }

        deroulVar1 = new JComboBox<Object>(data.toArray());
        deroulVar1.setEditable(true);
        deroulVar1.setSelectedItem(condition.getArg1());
        deroulVar2 = new JComboBox<Object>(data.toArray());
        deroulVar2.setEditable(true);
        deroulVar2.setSelectedItem(condition.getArg2());
        deroulOpe = new JComboBox<Object>(new Object[] { Inferieur.getInstance(), Superieur.getInstance(), Egal.getInstance(), Different.getInstance() });
        deroulOpe.setSelectedItem(condition.getOperateur());
        valide = new JButton("Valider");

        deroulVar1.setPreferredSize(new Dimension(100, 20));
        deroulVar2.setPreferredSize(new Dimension(100, 20));
        deroulOpe.setPreferredSize(new Dimension(100, 20));

        JPanel containerV1 = new JPanel();
        containerV1.add(new JLabel("Var 1 :"));
        containerV1.add(deroulVar1);

        JPanel containerV2 = new JPanel();
        containerV2.add(new JLabel("Var 2 :"));
        containerV2.add(deroulVar2);

        JPanel containerOpe = new JPanel();
        containerOpe.add(new JLabel("Operateur :"));
        containerOpe.add(deroulOpe);

        this.setVisible(true);
        this.setLayout(new GridLayout(2, 3));

        // this.getContentPane().add(new JPanel());
        this.getContentPane().add(containerV1);
        this.getContentPane().add(containerOpe);
        this.getContentPane().add(containerV2);
        this.getContentPane().add(new JLabel());
        this.getContentPane().add(valide);

        valide.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                if (FrameWhile.this.deroulVar1.getSelectedItem() instanceof String)
                {
                    Variable v = new Variable();
                    v.setNom((String) FrameWhile.this.deroulVar1.getSelectedItem());
                    v.setValue(new BigDecimal((String) FrameWhile.this.deroulVar1.getSelectedItem()));
                    condition.setArg1(v);
                }
                else
                {
                    condition.setArg1((Variable) FrameWhile.this.deroulVar1.getSelectedItem());
                }
                if (FrameWhile.this.deroulVar2.getSelectedItem() instanceof String)
                {
                    Variable v = new Variable();
                    v.setNom((String) FrameWhile.this.deroulVar2.getSelectedItem());
                    v.setValue(new BigDecimal((String) FrameWhile.this.deroulVar2.getSelectedItem()));
                    condition.setArg2(v);
                }
                else
                {
                    condition.setArg2((Variable) FrameWhile.this.deroulVar2.getSelectedItem());
                }
                condition.setOperateur((OperateurBoolean) FrameWhile.this.deroulOpe.getSelectedItem());

                mxGraph graph = Arbre.getInstance().getCanvas().getGraph();
                mxCell cell = FrameWhile.this.figureWhile.getConditionCell();
                graph.getModel().beginUpdate();
                mxCellState cellState = graph.getView().getState(cell);
                cell.setValue(condition.getArg1().getNom() + " " + condition.getOperateur().toString() + " " + condition.getArg2().getNom());
                cellState.setLabel(condition.getArg1().getNom() + " " + condition.getOperateur().toString() + " " + condition.getArg2().getNom());
                graph.getModel().endUpdate();
                graph.repaint();

                FrameWhile.this.dispose();

                // if (FrameWhile.this.deroulVar1.getItemCount() == 0 || FrameWhile.this.deroulVar2.getItemCount() == 0)
                // {
                // // Il n'existe pas de variable, donc impossible de set une condition
                // }
                // else
                // {
                // condition.setArg1((Variable) FrameWhile.this.deroulVar1.getSelectedItem());
                // condition.setArg2((Variable) FrameWhile.this.deroulVar2.getSelectedItem());
                // condition.setOperateur((OperateurBoolean) FrameWhile.this.deroulOpe.getSelectedItem());
                //
                // mxGraph graph = Arbre.getInstance().getCanvas().getGraph();
                // mxCell cell = FrameWhile.this.figureWhile.getConditionCell();
                // graph.getModel().beginUpdate();
                // mxCellState cellState = graph.getView().getState(cell);
                // cell.setValue(condition.getArg1().getNom() + " " + condition.getOperateur().toString() + " " + condition.getArg2().getNom());
                // cellState.setLabel(condition.getArg1().getNom() + " " + condition.getOperateur().toString() + " " + condition.getArg2().getNom());
                // graph.getModel().endUpdate();
                // graph.repaint();
                //
                // FrameWhile.this.dispose();
                // }

            }
        });

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public JComboBox<?> getDeroulVar1()
    {
        return deroulVar1;
    }

    public void setDeroulVar1(JComboBox<?> deroulVar1)
    {
        this.deroulVar1 = deroulVar1;
    }

    public JComboBox<?> getDeroulVar2()
    {
        return deroulVar2;
    }

    public void setDeroulVar2(JComboBox<?> deroulVar2)
    {
        this.deroulVar2 = deroulVar2;
    }

    public JComboBox<?> getDeroulOpe()
    {
        return deroulOpe;
    }

    public void setDeroulOpe(JComboBox<?> deroulOpe)
    {
        this.deroulOpe = deroulOpe;
    }

    public JButton getValide()
    {
        return valide;
    }

    public void setValide(JButton valide)
    {
        this.valide = valide;
    }

}
