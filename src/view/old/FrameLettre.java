package view.old;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ElementManager;
import controller.FigureIf;
import model.old.Condition;

public class FrameLettre  extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<?> deroulVar1, deroulVar2, deroulOpe;
	private JButton valide;
	private Condition condition;
	private FigureIf figureIf;

	public static void main(String[] argvs) {

		new FrameLettre();

	}

	
	private JTextField jf;

	public FrameLettre() {
		this.setTitle("Ajout Letter");
		// this.condition = figureIf.getIfInstruct().getCondition();
		// this.figureIf = figureIf;
		this.setSize(500, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		valide = new JButton("Valider");

		jf = new JTextField();
		jf.setPreferredSize(new Dimension(200, 30));
		
		this.setVisible(true);
		this.setLayout(new GridLayout(3, 3));

		// this.getContentPane().add(new JPanel());
		this.getContentPane().add(new JLabel());
		this.getContentPane().add(new JLabel("Lettre:"));
		this.getContentPane().add(new JLabel());
		
		this.getContentPane().add(new JLabel());
		this.getContentPane().add(jf);
		this.getContentPane().add(new JLabel());

		this.getContentPane().add(new JLabel());
		this.getContentPane().add(valide);
		this.getContentPane().add(new JLabel());
		valide.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			

				ElementManager.getInstance().ajoutLetter(FrameLettre.this.jf.getText());
				
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



