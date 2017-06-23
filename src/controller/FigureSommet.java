package controller;

import java.util.ArrayList;
import java.util.Observable;

import org.json.JSONObject;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import model.Sommet;
import model.old.Condition;
import model.old.Element;
import model.old.If;

public class FigureSommet implements Figure {

	private Sommet sommetModel;

	private mxCell ifCell;

	private FigureSequence si_vrai;
	private FigureSequence si_faux;
	private ArrayList<Figure> fils;

	private Figure father;
public String Name;
	public FigureSommet(boolean a, boolean b, String n) {
		this.fina = a;
		this.init = b;
this.Name=n;
		this.sommetModel = new Sommet(null, null, null);
		this.sommetModel.addObserver(this);
		this.fils = new ArrayList<>();

	}

	public JSONObject sauvegarde() {
		JSONObject header = new JSONObject();
		header.put("type", "sommet");

		JSONObject position_if_cell = new JSONObject();
		position_if_cell.put("x", ifCell.getGeometry().getX());
		position_if_cell.put("y", ifCell.getGeometry().getY());
		position_if_cell.put("w", ifCell.getGeometry().getWidth());
		position_if_cell.put("h", ifCell.getGeometry().getHeight());

		header.put("position_if_cell", position_if_cell);

		/*
		 * JSONObject position_condition_cell = new JSONObject();
		 * position_condition_cell.put("x", conditionCell.getGeometry().getX());
		 * position_condition_cell.put("y", conditionCell.getGeometry().getY());
		 * position_condition_cell.put("w",
		 * conditionCell.getGeometry().getWidth());
		 * position_condition_cell.put("h",
		 * conditionCell.getGeometry().getHeight());
		 * 
		 * header.put("position_condition_cell", position_condition_cell);
		 */

		/*
		 * JSONObject position_si_vrai_cell = new JSONObject();
		 * position_si_vrai_cell.put("x",
		 * si_vrai.getCell().getGeometry().getX());
		 * position_si_vrai_cell.put("y",
		 * si_vrai.getCell().getGeometry().getY());
		 * position_si_vrai_cell.put("w",
		 * si_vrai.getCell().getGeometry().getWidth());
		 * position_si_vrai_cell.put("h",
		 * si_vrai.getCell().getGeometry().getHeight());
		 * 
		 * header.put("position_si_vrai_cell", position_si_vrai_cell);
		 * 
		 * JSONObject position_si_faux_cell = new JSONObject();
		 * position_si_faux_cell.put("x",
		 * si_faux.getCell().getGeometry().getX());
		 * position_si_faux_cell.put("y",
		 * si_faux.getCell().getGeometry().getY());
		 * position_si_faux_cell.put("w",
		 * si_faux.getCell().getGeometry().getWidth());
		 * position_si_faux_cell.put("h",
		 * si_faux.getCell().getGeometry().getHeight());
		 */
		// header.put("position_si_faux_cell", position_si_faux_cell);

		// TODO: Sauvegarder les informations du sommetModel

		// position_if_cell.put("x", );

		return header;
	}

	@Override
	public void addToFather(Figure son) {
		this.fils.add(son);
	}

	public ArrayList<Figure> getFils() {
		return fils;
	}

	public void setFils(ArrayList<Figure> fils) {
		this.fils = fils;
	}

	boolean init = false;
	boolean fina = false;

	@Override
	public void printToCanvas(Figure father, mxGraph graph) {
		if (init) {

			if (fina) {
				// mxConstants.STYLE_SHAPE
				String styleEnfant1 = mxConstants.STYLE_FILLCOLOR + "=#00ff00";
				this.ifCell = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, Name, 50, 50, 50, 50,
						mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_DOUBLE_ELLIPSE);
			} else {
				this.ifCell = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, Name, 50, 50, 50, 50,
						mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_DOUBLE_ELLIPSE);
			}

		} else if (fina) {
			// mxConstants.STYLE_SHAPE
			String styleEnfant1 = mxConstants.STYLE_FILLCOLOR + "=#00ff00";
			this.ifCell = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, Name, 50, 50, 50, 50,
					styleEnfant1 + "=" + mxConstants.SHAPE_ELLIPSE);
		} else {
			this.ifCell = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, Name, 50, 50, 50, 50,
					mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_ELLIPSE);
		}
		// this.conditionCell = (mxCell)
		// graph.insertVertex(graph.getDefaultParent(), null, "", x - 200, y +
		// 125, 100, 50, mxConstants.STYLE_SHAPE + "=" +
		// mxConstants.SHAPE_HEXAGON);

		/*
		 * this.si_vrai = new FigureSequence();
		 * this.si_vrai.setSequence(sommetModel.getSi_vrai());
		 * this.si_vrai.getSequence().addObserver(this.si_vrai);
		 * this.si_vrai.printToCanvas(this,
		 * Arbre.getInstance().getCanvas().getGraph());
		 * 
		 * this.si_faux = new FigureSequence();
		 * this.si_faux.setSequence(sommetModel.getSi_faux());
		 * this.si_faux.getSequence().addObserver(this.si_faux);
		 * this.si_faux.printToCanvas(this,
		 * Arbre.getInstance().getCanvas().getGraph());
		 */
		// graph.getModel().beginUpdate();
		// graph.moveCells(new Object[] { this.si_faux.getCell() }, 100, -20);
		// graph.getModel().endUpdate();

		// graph.insertEdge(graph.getDefaultParent(), null, null,
		// figureSequence.getCell(), this.ifCell);
		// graph.insertEdge(graph.getDefaultParent(), null, null, this.ifCell,
		// this.conditionCell);

	}

	@Override
	public Element getElement() {
		// TODO Auto-generated method stub
		return this.sommetModel;
	}

	public Sommet getIfInstruct() {
		return sommetModel;
	}

	public void setIfInstruct(Sommet sommetModel) {
		this.sommetModel = sommetModel;
	}

	public mxCell getIfCell() {
		return ifCell;
	}

	public void setIfCell(mxCell ifCell) {
		this.ifCell = ifCell;
	}


	public FigureSequence getSi_vrai() {
		return si_vrai;
	}

	public void setSi_vrai(FigureSequence si_vrai) {
		this.si_vrai = si_vrai;
	}

	public FigureSequence getSi_faux() {
		return si_faux;
	}

	public void setSi_faux(FigureSequence si_faux) {
		this.si_faux = si_faux;
	}

	@Override
	public void deleteFromCanvas(mxGraph graph) {
		this.getSi_faux().deleteFromCanvas(graph);
		this.getSi_vrai().deleteFromCanvas(graph);
		graph.removeCells(new Object[] { this.getIfCell() }, true);
		graph.repaint();

	}

	@Override
	public mxCell getMainCell() {
		// TODO Auto-generated method stub
		return this.ifCell;
	}

	@Override
	public Figure getFather() {
		return this.father;
	}

	public void update(Observable arg0, Object arg1) {
		if (arg1 == null) {
			if (Arbre.getInstance().getHeader().getHeader().isFlagPasPas()) {
				Arbre.getInstance().getCanvas().getGraph().setCellStyles(mxConstants.STYLE_FILLCOLOR, "#01D758",
						new Object[] { this.getMainCell() });
				while (Arbre.getInstance().getHeader().getHeader().isFlagPasPas()) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Arbre.getInstance().getHeader().getHeader().setFlagPasPas(true);
				Arbre.getInstance().getCanvas().getGraph().setCellStyles(mxConstants.STYLE_FILLCOLOR, "#C3D9FF",
						new Object[] { this.getMainCell() });
			}
		} else if (arg1 instanceof Condition) {
			if (Arbre.getInstance().getHeader().getHeader().isFlagPasPas()) {
				Arbre.getInstance().getCanvas().getGraph().setCellStyles(mxConstants.STYLE_FILLCOLOR, "#01D758",
						new Object[] { });
				while (Arbre.getInstance().getHeader().getHeader().isFlagPasPas()) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Arbre.getInstance().getHeader().getHeader().setFlagPasPas(true);
				Arbre.getInstance().getCanvas().getGraph().setCellStyles(mxConstants.STYLE_FILLCOLOR, "#C3D9FF",
						new Object[] {  });
			}
		}
	}

	@Override
	public String onche() {
		return "If";
	}

	public mxCell getCell() {
		return this.ifCell;
	}

	public String toSave() {
		// TODO Auto-generated method stub
		return null;
	}

}
