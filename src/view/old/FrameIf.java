
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

import controller.Arbre;
import controller.ElementManager;
import controller.FigureIf;
import model.old.Condition;
import model.old.Different;
import model.old.Egal;
import model.old.Inferieur;
import model.old.OperateurBoolean;
import model.old.Superieur;
import model.old.Variable;

public class FrameIf extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<?> deroulVar1, deroulVar2, deroulOpe;
	private JButton valide;
	private Condition condition;
	private FigureIf figureIf;

	public static void main(String[] argvs) {

		new FrameIf();

	}

	JCheckBox EI;
	JCheckBox EF;
	private JTextField jf;

	public FrameIf() {
		this.setTitle("Nouveau sommet");
		// this.condition = figureIf.getIfInstruct().getCondition();
		// this.figureIf = figureIf;
		this.setSize(500, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		valide = new JButton("Valider");
//TODO ENABLE 
		EI = new JCheckBox("Etat initial?");
		EI.setEnabled(false);

		EF = new JCheckBox("etat final?");
		EF.setEnabled(true);

		jf = new JTextField();
		jf.setPreferredSize(new Dimension(200, 30));
		JPanel containerOpe = new JPanel();
		containerOpe.add(new JLabel("Nom du sommet :"));
		containerOpe.add(jf);

		this.setVisible(true);
		this.setLayout(new GridLayout(4, 4));

		// this.getContentPane().add(new JPanel());
		this.getContentPane().add(new JLabel());
		this.getContentPane().add(EI);
		this.getContentPane().add(new JLabel());
		this.getContentPane().add(new JLabel());
		this.getContentPane().add(new JLabel());

		this.getContentPane().add(new JLabel());
		this.getContentPane().add(EF);
		this.getContentPane().add(new JLabel());
		this.getContentPane().add(new JLabel());
		this.getContentPane().add(new JLabel());

		this.getContentPane().add(new JLabel());
		this.getContentPane().add(new JLabel("fff"));
		this.getContentPane().add(jf);
		this.getContentPane().add(new JLabel());
		this.getContentPane().add(new JLabel());
		this.getContentPane().add(new JLabel());

		this.getContentPane().add(new JLabel());
		this.getContentPane().add(new JLabel());
		this.getContentPane().add(valide);
		this.getContentPane().add(new JLabel());
		valide.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// if (FrameIf.this.deroulVar1.getItemCount() == 0 ||
				// FrameIf.this.deroulVar2.getItemCount() == 0)
				// {
				// // Il n'existe pas de variable, donc impossible de set une
				// condition
				// }
				// else
				// {

				ElementManager.getInstance().ajoutSommet(null,FrameIf.this.EI.isSelected() ,FrameIf.this.EF.isSelected() , FrameIf.this.jf.getText());
				// }
dispose();
			}
		});

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public JComboBox<?> getDeroulVar1() {
		return deroulVar1;
	}

	public void setDeroulVar1(JComboBox<?> deroulVar1) {
		this.deroulVar1 = deroulVar1;
	}

	public JComboBox<?> getDeroulVar2() {
		return deroulVar2;
	}

	public void setDeroulVar2(JComboBox<?> deroulVar2) {
		this.deroulVar2 = deroulVar2;
	}

	public JComboBox<?> getDeroulOpe() {
		return deroulOpe;
	}

	public void setDeroulOpe(JComboBox<?> deroulOpe) {
		this.deroulOpe = deroulOpe;
	}

	public JButton getValide() {
		return valide;
	}

	public void setValide(JButton valide) {
		this.valide = valide;
	}

}
