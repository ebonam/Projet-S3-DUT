package controller;

import java.util.ArrayList;

import org.json.JSONObject;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import model.old.Element;
import model.old.Saisir;

public class FigureSaisir implements Figure
{

    private Saisir saisir;
    private mxCell cell;
    private ArrayList<Figure> fils;
    private Figure father;

    public FigureSaisir()
    {
        this.saisir = new Saisir();
        this.saisir.addObserver(this);
        this.fils = new ArrayList<>();

    }
    
    public JSONObject sauvegarde()
    {
    	JSONObject header = new JSONObject();
    	header.put("type", "Saisir");
    	return header;
    }

    @Override
    public Element getElement()
    {
        // TODO Auto-generated method stub
        return this.saisir;
    }

    @Override
    public void addToFather(Figure son)
    {
        this.fils.add(son);
    }

    public ArrayList<Figure> getFils()
    {
        return fils;
    }

    public void setFils(ArrayList<Figure> fils)
    {
        this.fils = fils;
    }

    public Figure getFather()
    {
        return father;
    }

    public void setFather(Figure father)
    {
        this.father = father;
    }

    @Override
    public void printToCanvas(Figure father, mxGraph graph)
    {
        father.addToFather(this);
        this.father = father;
        if (father instanceof FigureSequence)
        {
            FigureSequence figureSequence = (FigureSequence) father;
            double x = graph.getCellBounds(figureSequence.getCell()).getCenterX();
            double y = graph.getCellBounds(figureSequence.getCell()).getCenterY();
            this.cell = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, "Saisir vers _", x - 50, y + 100, 100, 50);
            graph.insertEdge(graph.getDefaultParent(), null, null, figureSequence.getCell(), this.cell, mxConstants.STYLE_MOVABLE + "=0;" + mxConstants.STYLE_EDITABLE + "=0;");
        }
        else
        {
            throw new UnknownError();
        }

    }

    public Saisir getSaisir()
    {
        return saisir;
    }

    public void setSaisir(Saisir saisir)
    {
        this.saisir = saisir;
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
	public String onche() 
	{
		return "Saisir";
	}

}
