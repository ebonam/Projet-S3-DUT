package controller;

import java.util.ArrayList;
import java.util.Observable;

import org.json.JSONObject;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import model.old.Afficher;
import model.old.Element;

public class FigureAfficher implements Figure
{
    private Afficher afficher;
    private mxCell cell;
    private ArrayList<Figure> fils;
    private Figure father;

    public FigureAfficher()
    {
        this.afficher = new Afficher();
        this.afficher.addObserver(this);
        this.fils = new ArrayList<>();
    }

    @Override
    public void addToFather(Figure son)
    {
        this.fils.add(son);
    }

    public Figure getFather()
    {
        return father;
    }

    public void setFather(Figure father)
    {
        this.father = father;
    }
    
    public JSONObject sauvegarde()
    {
    	JSONObject header = new JSONObject();
    	header.put("type", "Afficher");
    	return header;
    }

    @Override
    public void printToCanvas(Figure father, mxGraph graph)
    {
        this.father = father;
        father.addToFather(this);
        if (father instanceof FigureSequence)
        {
            FigureSequence figureSequence = (FigureSequence) father;
            double x = graph.getCellBounds(figureSequence.getCell()).getCenterX();
            double y = graph.getCellBounds(figureSequence.getCell()).getCenterY();
            this.cell = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, "Afficher _", x - 50, y + 100, 100, 50, mxConstants.STYLE_RESIZABLE + "=0;");
            graph.insertEdge(graph.getDefaultParent(), null, null, figureSequence.getCell(), this.cell);
        }
        else
        {
            throw new UnknownError();
        }

    }

    public ArrayList<Figure> getFils()
    {
        return fils;
    }

    public void setFils(ArrayList<Figure> fils)
    {
        this.fils = fils;
    }

    public Afficher getAfficher()
    {
        return afficher;
    }

    public void setAfficher(Afficher afficher)
    {
        this.afficher = afficher;
    }

    public mxCell getCell()
    {
        return cell;
    }

    public void setCell(mxCell cell)
    {
        this.cell = cell;
    }

    @Override
    public void deleteFromCanvas(mxGraph graph)
    {
        graph.removeCells(new Object[] { this.getCell() }, true);
        graph.repaint();

    }

    @Override
    public mxCell getMainCell()
    {
        // TODO Auto-generated method stub
        return this.cell;
    }

    @Override
    public Element getElement()
    {
        // TODO Auto-generated method stub
        return this.afficher;
    }

	@Override
	public String onche() 
	{
		return "Afficher";
	}

}
