package controller;

import java.util.ArrayList;

import org.json.JSONObject;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import model.old.Element;
import model.old.Sequence;

public class FigureSequence implements Figure
{
    private Sequence sequence;
    private mxCell cell;
    private ArrayList<Figure> fils;

    private Figure father;

    private static boolean ifFix = false;

    public FigureSequence()
    {
        this.sequence = new Sequence();
        this.sequence.addObserver(this);
        this.fils = new ArrayList<>();

    }
    
    public JSONObject sauvegarde()
    {
    	JSONObject header = new JSONObject();
    	header.put("type", "Sequence");
    	return header;
    }

    @Override
    public Element getElement()
    {
        // TODO Auto-generated method stub
        return this.sequence;
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

    @Override
    public void printToCanvas(Figure father, mxGraph graph)
    {
        father.addToFather(this);
        this.father = father;
        if (father instanceof FigureHeader)
        {
            FigureHeader figure = (FigureHeader) father;
            double x = graph.getCellBounds(figure.getSecondCell()).getCenterX();
            double y = graph.getCellBounds(figure.getSecondCell()).getCenterY();
            this.cell = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, "->", x - 25, y + 100, 50, 50, mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_ELLIPSE + ";" + mxConstants.STYLE_RESIZABLE + "=0;");
            graph.insertEdge(graph.getDefaultParent(), null, null, figure.getSecondCell(), cell, mxConstants.STYLE_MOVABLE + "=0;" + mxConstants.STYLE_EDITABLE + "=0;");
        }
        else if (father instanceof FigureSequence)
        {
            FigureSequence figure = (FigureSequence) father;
            double x = graph.getCellBounds(figure.getCell()).getCenterX();
            double y = graph.getCellBounds(figure.getCell()).getCenterY();
            this.cell = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, "->", x - 25, y + 100, 50, 50, mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_ELLIPSE + ";" + mxConstants.STYLE_RESIZABLE + "=0;");
            graph.insertEdge(graph.getDefaultParent(), null, null, figure.getCell(), cell, mxConstants.STYLE_MOVABLE + "=0;" + mxConstants.STYLE_EDITABLE + "=0;");
        }
        else if (father instanceof FigureIf)
        {
            FigureIf figure = (FigureIf) father;
            double x = graph.getCellBounds(figure.getIfCell()).getCenterX();
            double y = graph.getCellBounds(figure.getIfCell()).getCenterY();
            this.cell = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, "->", x - 25, y + 100, 50, 50, mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_ELLIPSE + ";" + mxConstants.STYLE_RESIZABLE + "=0;");
            if (!ifFix)
            {
                ifFix = true;
                graph.insertEdge(graph.getDefaultParent(), null, "vrai", figure.getIfCell(), cell, mxConstants.STYLE_MOVABLE + "=0;" + mxConstants.STYLE_EDITABLE + "=0;");
            }
            else
            {
                ifFix = false;
                graph.insertEdge(graph.getDefaultParent(), null, "faux", figure.getIfCell(), cell, mxConstants.STYLE_MOVABLE + "=0;" + mxConstants.STYLE_EDITABLE + "=0;");
            }
        }
        else if (father instanceof FigureWhile)
        {
            FigureWhile figure = (FigureWhile) father;
            double x = graph.getCellBounds(figure.getWhileCell()).getCenterX();
            double y = graph.getCellBounds(figure.getWhileCell()).getCenterY();
            this.cell = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, "->", x - 25, y + 100, 50, 50, mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_ELLIPSE + ";" + mxConstants.STYLE_RESIZABLE + "=0;");
            graph.insertEdge(graph.getDefaultParent(), null, "", figure.getWhileCell(), cell, mxConstants.STYLE_MOVABLE + "=0;" + mxConstants.STYLE_EDITABLE + "=0;");

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

    public Sequence getSequence()
    {
        return sequence;
    }

    public void setSequence(Sequence sequence)
    {
        this.sequence = sequence;
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
		return "Sequence";
	}

}
