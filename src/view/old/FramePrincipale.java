package view.old;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONArray;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

import controller.Arbre;
import controller.ElementManager;
import controller.Figure;
import controller.FigureAfficher;
import controller.FigureFleche;
import controller.FigureIf;
import controller.FigureSaisir;
import controller.FigureSommet;
import controller.FigureWhile;
import model.Header;
import model.old.Variable;

//TODO : Remplir les  evenements des boutons de FrameElements
public class FramePrincipale extends JFrame
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JMenuBar menuBar;
    private JMenu menuFichier, menuExecuter;
    private JMenuItem newArbre, saveArbre, openArbre, importCode, exportCode, executerArbre, executerArbrePasAPas;
    private FrameElements frameElements;
    private FrameCanvas frameAffichageGraphique;
    private JFileChooser fileChooser;
    private Container c;
	private JMenuItem ExportPng;

    public FramePrincipale()
    {

        //ElementManager.getInstance().ajouterHeader();
       // ElementManager.getInstance().ajouterSequence(ElementManager.getInstance().getHeader());
        // ElementManager.getInstance().getHeader().getHeader().launchArbre();

        this.menuBar = new JMenuBar();
        frameElements = new FrameElements();
        frameAffichageGraphique = Arbre.getInstance().getCanvas();

        // Gestion du FileChooser
        this.c = this.getContentPane();
        this.fileChooser = new JFileChooser();
        FileNameExtensionFilter filtreJSON = new FileNameExtensionFilter("json", "json");
        FileNameExtensionFilter filtreC = new FileNameExtensionFilter("c", "c");
		FileNameExtensionFilter filtrePng = new FileNameExtensionFilter("png", "png");
        // ajout des 2 jpanels (FrameElement/FrameAffichageGraphique
        // this.setResizable(false);
        c.setLayout(new BorderLayout());
        c.add(frameElements, BorderLayout.WEST);
        c.add(frameAffichageGraphique.getJframe(), BorderLayout.CENTER);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);

        // Gestion de la barre de menu
        this.menuFichier = new JMenu("Fichier");
        this.newArbre = new JMenuItem("Créer un nouvel arbre");
        this.newArbre.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
            	
            	
                fileChooser.setFileFilter(filtreJSON);
                int returnVal = fileChooser.showSaveDialog(rootPane);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File savedArbre = fileChooser.getSelectedFile();
                    
                    System.out.println("Enregistrement : " + savedArbre.getName() + " --> " + savedArbre.getAbsolutePath());
                    Figure first = Arbre.getInstance().getHeader().getFils().get(0);
                    
                    ElementManager.getInstance().supprimerFigure(first);
                    Arbre.getInstance().getHeader().getHeader().getVariables_locals().clear();
                }
            }
        });

        this.saveArbre = new JMenuItem("Sauvegarder automate");
        this.saveArbre.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                fileChooser.setFileFilter(filtreJSON);
                int returnVal = fileChooser.showSaveDialog(rootPane);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File savedArbre = fileChooser.getSelectedFile();
                    System.out.println("Enregistrement : " + savedArbre.getName() + " --> " + savedArbre.getAbsolutePath());
                    
                }
            }
        });

        this.openArbre = new JMenuItem("Ouvrir un automate");
        this.openArbre.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                fileChooser.setFileFilter(filtreJSON);
                int returnVal = fileChooser.showOpenDialog(rootPane);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File savedArbre = fileChooser.getSelectedFile();
                    System.out.println("Ouverture : " + savedArbre.getName() + " --> " + savedArbre.getAbsolutePath());
                }
            }
        });

/*        this.importCode = new JMenuItem("Importer un code");
        this.importCode.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                fileChooser.setFileFilter(filtreC);
                int returnVal = fileChooser.showOpenDialog(rootPane);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File savedArbre = fileChooser.getSelectedFile();
                    System.out.println("Importation d'un fichier .c : " + savedArbre.getName() + " --> " + savedArbre.getAbsolutePath());
                }
            }
        });

*/
		this.ExportPng = new JMenuItem("exporter en png");
		this.ExportPng.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Arbre.getInstance().getHeader().save();
				JSONArray json = new JSONArray("[\"Header\",[\"Afficher\",\"If\",[\"While\",[\"Afficher\"]],[\"Saisir\"],\"Saisir\"]]");
				//Arbre.getInstance().getHeader().load(json);
				fileChooser.setFileFilter(filtrePng);
				int returnVal = fileChooser.showOpenDialog(rootPane);
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					File savedArbre = fileChooser.getSelectedFile();
					
					CreatePng(savedArbre.getAbsolutePath()+".png");
				}}
		});

/*
        this.exportCode = new JMenuItem("Exporter en code");
        this.exportCode.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                fileChooser.setFileFilter(filtreC);
                int returnVal = fileChooser.showSaveDialog(rootPane);
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    File savedArbre = fileChooser.getSelectedFile();
					Arbre.getInstance().getHeader().getHeader().toCode(savedArbre.getAbsolutePath()+".c");//ajouter le file
                    System.out.println("Exportation d'un fichier .c : " + savedArbre.getName() + " --> " + savedArbre.getAbsolutePath());
                }
            }
        });*/
		this.menuFichier.add(ExportPng);
        this.menuFichier.add(newArbre);
        this.menuFichier.add(saveArbre);
        this.menuFichier.add(openArbre);
       
/*        this.menuExecuter = new JMenu("Exécuter");
        this.executerArbre = new JMenuItem("Exécuter l'arbre");

        this.executerArbre.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // new FrameDebug();
                Arbre.getInstance().getHeader().getHeader().launchArbre();
            }
        });
        this.executerArbrePasAPas = new JMenuItem("Exécuter l'arbre pas à pas");
        this.executerArbrePasAPas.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                // new FrameDebug();
                Arbre.getInstance().getHeader().getHeader().launchArbrePasPas();

            }
        });

        this.menuExecuter.add(executerArbre);
        this.menuExecuter.add(executerArbrePasAPas);
*/
        this.menuBar.add(menuFichier);
  //      this.menuBar.add(menuExecuter);

        this.setJMenuBar(menuBar);

        // gestion events sur la FrameElements
        this.frameElements.getButtonAffectation().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
              new FrameIf();

            }
        });

        this.frameElements.getButtonSequence().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
            	  new FrameTransition();
            }
        });
        FramePrincipale.this.frameAffichageGraphique.getGraph().setCellsEditable(false); 
        this.frameElements.getButtonIf().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                mxGraph graph = FramePrincipale.this.frameAffichageGraphique.getGraph();
                	
                  //  mxCell cell = (mxCell) graph.getSelectionCell();                	
                	
                   new FigureFleche().test();
                    
/*                    if (graph.getSelectionCell() instanceof mxCell)
                    {
                        mxCell cell = (mxCell) graph.getSelectionCell();
                        if (!cell.isEdge())
                        {
                            Figure figure = ElementManager.getInstance().getFigure(cell.getId());
                            ElementManager.getInstance().ajouterIf(figure);
                        }
                        else
                        {
                            graph.clearSelection();
                        }
                    }*/
                }

            
        });

        this.frameElements.getButtonFor().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                new FrameLettre();

            }
        });

        this.frameElements.getButtonWhile().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                mxGraph graph = FramePrincipale.this.frameAffichageGraphique.getGraph();
                if (graph.getSelectionCell() != null)
                {
                    if (graph.getSelectionCell() instanceof mxCell)
                    {
                        mxCell cell = (mxCell) graph.getSelectionCell();
                        if (!cell.isEdge())
                        {
                            Figure figure = ElementManager.getInstance().getFigure(cell.getId());
                            ElementManager.getInstance().ajouterWhile(figure);
                        }
                        else
                        {
                            graph.clearSelection();
                        }
                    }
                }

            }
        });

        this.frameElements.getButtonVariables().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                new FrameGestionVariable();

            }
        });

        this.frameElements.getButtonAfficher().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                mxGraph graph = FramePrincipale.this.frameAffichageGraphique.getGraph();
                if (graph.getSelectionCell() != null)
                {
                    if (graph.getSelectionCell() instanceof mxCell)
                    {
                        mxCell cell = (mxCell) graph.getSelectionCell();
                        if (!cell.isEdge())
                        {
                            Figure figure = ElementManager.getInstance().getFigure(cell.getId());
                            ElementManager.getInstance().ajouterAfficher(figure);
                        }
                        else
                        {
                            graph.clearSelection();
                        }
                    }
                }
            }
        });

        this.frameElements.getButtonLireVariable().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                mxGraph graph = FramePrincipale.this.frameAffichageGraphique.getGraph();
                if (graph.getSelectionCell() != null)
                {
                    if (graph.getSelectionCell() instanceof mxCell)
                    {
                        mxCell cell = (mxCell) graph.getSelectionCell();
                        if (!cell.isEdge())
                        {
                            Figure figure = ElementManager.getInstance().getFigure(cell.getId());
                            ElementManager.getInstance().ajouterSaisir(figure);
                        }
                        else
                        {
                            graph.clearSelection();
                        }
                    }
                }

            }
        });

        this.getFrameElements().getButtonEditerElement().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                mxGraph graph = FramePrincipale.this.frameAffichageGraphique.getGraph();
                if (graph.getSelectionCell() == null)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Aucun element de l'arbre selectionné.");

                }
                else
                {
                    mxCell cell = (mxCell) graph.getSelectionCell();
                    Figure figure = ElementManager.getInstance().getFigure(cell.getId());

                    if (figure instanceof FigureAfficher)
                    {
                        FigureAfficher afficher = (FigureAfficher) figure;
                        String lesTextes[] = { "Message", "Variable" };
                        int retourOptionDialog = JOptionPane.showOptionDialog(new JFrame(), "Que voulez-vous afficher?", "Afficher", 0, JOptionPane.QUESTION_MESSAGE, null, lesTextes, lesTextes[0]);
                        if (retourOptionDialog == 0)
                        {
                            String retour = JOptionPane.showInputDialog(new JFrame(), String.format("Saisir le texte à afficher."), "Saisir", 1);
                            Variable affichage = new Variable();
                            affichage.setType("String");
                            affichage.setValue(retour);
                            // affichage.setAffichage(true);
                            afficher.getAfficher().setAfficher(affichage);
                            graph.getModel().beginUpdate();
                            mxCellState cellState = graph.getView().getState(cell);
                            cell.setValue("Afficher \"" + retour + "\"");
                            cellState.setLabel("Afficher \"" + retour + "\"");
                            graph.getModel().endUpdate();
                            graph.repaint();
                        }
                        else
                        {
                            ArrayList<Variable> data = new ArrayList<>();
                            Iterator<Entry<String, Variable>> it = Arbre.getInstance().getHeader().getHeader().getVariables_locals().entrySet().iterator();

                            while (it.hasNext())
                            {
                                Entry<String, Variable> entry = it.next();
                                Variable v = entry.getValue();
                                data.add(v);
                            }

                            if (data.size() != 0)
                            {
                                Object retour = JOptionPane.showInputDialog(new JFrame(), "Choisir une variable", "Variable", JOptionPane.PLAIN_MESSAGE, null, data.toArray(), (data.size() == 0) ? null : data.toArray()[0]);
                                Variable retourVariable = (Variable) retour;
                                afficher.getAfficher().setAfficher(retourVariable);
                                graph.getModel().beginUpdate();
                                mxCellState cellState = graph.getView().getState(cell);
                                cell.setValue("Afficher " + retourVariable.getNom());
                                cellState.setLabel("Afficher " + retourVariable.getNom());
                                graph.getModel().endUpdate();
                                graph.repaint();
                            }
                            else
                            {
                                // TODO: Afficher erreur il n'existe pas de variable
                            }
                        }
                    }
                    else if (figure instanceof FigureSaisir)
                    {
                        FigureSaisir saisir = (FigureSaisir) figure;
                        ArrayList<Variable> data = new ArrayList<>();
                        Iterator<Entry<String, Variable>> it = Arbre.getInstance().getHeader().getHeader().getVariables_locals().entrySet().iterator();

                        while (it.hasNext())
                        {
                            Entry<String, Variable> entry = it.next();
                            Variable v = entry.getValue();
                            data.add(v);
                        }

                        if (data.size() != 0)
                        {
                            Object retour = JOptionPane.showInputDialog(new JFrame(), "Choisir une variable", "Variable", JOptionPane.PLAIN_MESSAGE, null, data.toArray(), (data.size() == 0) ? null : data.toArray()[0]);
                            Variable retourVariable = (Variable) retour;
                            saisir.getSaisir().setVariable(retourVariable);
                            graph.getModel().beginUpdate();
                            mxCellState cellState = graph.getView().getState(cell);
                            cell.setValue("Saisir vers " + retourVariable.getNom());
                            cellState.setLabel("Saisir vers " + retourVariable.getNom());
                            graph.getModel().endUpdate();
                            graph.repaint();
                        }
                        else
                        {
                            // TODO: Erreur il n'existe pas de variable
                        }
                    }
                    else if (figure instanceof FigureIf)
                    {
                        FigureIf figureIf = (FigureIf) figure;
                        if (cell.equals(figureIf.getConditionCell()))
                        {
                         //   @SuppressWarnings("unused")
                        //    FrameIf frameIf = new FrameIf(figureIf);
                        }
                    }
                    else if (figure instanceof FigureWhile)
                    {
                        FigureWhile figureWhile = (FigureWhile) figure;
                        if (cell.equals(figureWhile.getConditionCell()))
                        {
                            @SuppressWarnings("unused")
                            FrameWhile frameWhile = new FrameWhile(figureWhile);
                        }
                    }

                    // DONE : Ouvrir la fenetre d'edition de l'element

                }

            }
        });

        this.getFrameElements().getButtonSupprimerElement().addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                mxGraph graph = FramePrincipale.this.frameAffichageGraphique.getGraph();
                if (graph.getSelectionCell() == null)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Aucun element de l'arbre selectionné.");

                }
                else
                {
                    // DONE : supprimer l'element selectionn�
                    mxCell cell = (mxCell) graph.getSelectionCell();
                    Figure figure = ElementManager.getInstance().getFigure(cell.getId());
                    ElementManager.getInstance().supprimerFigure(figure);
                }

            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);

        this.setTitle("Fenetre principale");
        this.setVisible(true);

    }

	protected void CreatePng(String str) {
		// TODO Auto-generated method stub
		this.frameAffichageGraphique.toPng(str);
	}

    public void setMenuBar(JMenuBar menuBar)
    {
        this.menuBar = menuBar;
    }

    public JMenu getMenuFichier()
    {
        return menuFichier;
    }

    public void setMenuFichier(JMenu menuFichier)
    {
        this.menuFichier = menuFichier;
    }

    public JMenu getMenuExecuter()
    {
        return menuExecuter;
    }

    public void setMenuExecuter(JMenu menuExecuter)
    {
        this.menuExecuter = menuExecuter;
    }

    public JMenuItem getNewArbre()
    {
        return newArbre;
    }

    public void setNewArbre(JMenuItem newArbre)
    {
        this.newArbre = newArbre;
    }

    public JMenuItem getSaveArbre()
    {
        return saveArbre;
    }

    public void setSaveArbre(JMenuItem saveArbre)
    {
        this.saveArbre = saveArbre;
    }

    public JMenuItem getOpenArbre()
    {
        return openArbre;
    }

    public void setOpenArbre(JMenuItem openArbre)
    {
        this.openArbre = openArbre;
    }

    public JMenuItem getImportCode()
    {
        return importCode;
    }

    public void setImportCode(JMenuItem importCode)
    {
        this.importCode = importCode;
    }

    public JMenuItem getExportCode()
    {
        return exportCode;
    }

    public void setExportCode(JMenuItem exportCode)
    {
        this.exportCode = exportCode;
    }

    public JMenuItem getExecuterArbre()
    {
        return executerArbre;
    }

    public void setExecuterArbre(JMenuItem executerArbre)
    {
        this.executerArbre = executerArbre;
    }

    public JMenuItem getExecuterArbrePasAPas()
    {
        return executerArbrePasAPas;
    }

    public void setExecuterArbrePasAPas(JMenuItem executerArbrePasAPas)
    {
        this.executerArbrePasAPas = executerArbrePasAPas;
    }

    public FrameElements getFrameElements()
    {
        return frameElements;
    }

    public void setFrameElements(FrameElements frameElements)
    {
        this.frameElements = frameElements;
    }

    public FrameCanvas getFrameAffichageGraphique()
    {
        return frameAffichageGraphique;
    }

    public void setFrameAffichageGraphique(FrameCanvas frameAffichageGraphique)
    {
        this.frameAffichageGraphique = frameAffichageGraphique;
    }

    public JFileChooser getFileChooser()
    {
        return fileChooser;
    }

    public void setFileChooser(JFileChooser fileChooser)
    {
        this.fileChooser = fileChooser;
    }

    public Container getC()
    {
        return c;
    }

    public void setC(Container c)
    {
        this.c = c;
    }

    public static void main(String[] argvs)
    {
        @SuppressWarnings("unused")
        FramePrincipale fp = new FramePrincipale();

    }

}