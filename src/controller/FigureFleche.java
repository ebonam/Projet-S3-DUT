package controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;

import org.json.JSONObject;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxEdgeStyle;
import com.mxgraph.view.mxGraph;

import model.Letter;
import model.arc;
import model.old.Element;

public class FigureFleche implements Figure {
	private arc e; 

public FigureFleche(){
	
	
}
public FigureFleche(FigureSommet S1,FigureSommet S2, Letter l){
	
	f1=S1;
	f2=S2;
	lettre= l;
}
FigureSommet f1;
FigureSommet f2;
Letter lettre;

	public void test(){

		FigureSommet f1=new FigureSommet(true,false,"");
		FigureSommet f2=new FigureSommet(false,true,"");
		mxGraph s=Arbre.getInstance().getCanvas().getGraph();
		f1.printToCanvas(null, s);
		f2.printToCanvas(null, s);
		printArrow(f1,f2,s);
	}
	
	public void printArrow( mxGraph graph) {
		Map<String, Object> style = graph.getStylesheet().getDefaultEdgeStyle();
		style.put(mxConstants.STYLE_EDGE, mxEdgeStyle.BOTTOM);
		 graph.setConnectableEdges(true);
		graph.getModel().beginUpdate();
		graph.insertEdge(graph.getDefaultParent(), null, "test", f1.getIfCell(), f2.getIfCell());
		graph.getModel().endUpdate();
	}
	
	
static	public void printArrow(FigureSommet f1, FigureSommet f2, mxGraph graph) {
	
		graph.getModel().beginUpdate();
		graph.insertEdge(graph.getDefaultParent(), null, "test", f1.getIfCell(), f2.getIfCell());
		graph.getModel().endUpdate();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printToCanvas(Figure father, mxGraph graph) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFromCanvas(mxGraph graph) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addToFather(Figure son) {
		// TODO Auto-generated method stub

	}

	@Override
	public Element getElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public mxCell getMainCell() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Figure getFather() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject sauvegarde() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String onche() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Figure> getFils() {
		// TODO Auto-generated method stub
		return null;
	}
	public String toSave() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
