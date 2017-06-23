package controller;

import java.util.ArrayList;
import java.util.Observable;

import org.json.JSONObject;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import model.old.Condition;
import model.old.Element;
import model.old.While;

public class FigureWhile implements Figure
{
    private While whileInstruct;

    private mxCell whileCell;
    private mxCell conditionCell;
    private FigureSequence sequence;

    private ArrayList<Figure> fils;

    private Figure father;

    public FigureWhile()
    {
        this.whileInstruct = new While();
        this.whileInstruct.addObserver(this);
        this.whileInstruct.getCondition().addObserver(this);
        this.fils = new ArrayList<>();
    }
    
    public JSONObject sauvegarde()
    {
    	JSONObject header = new JSONObject();
    	header.put("type", "While");
    	return header;
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

    @Override
    public Element getElement()
    {
        // TODO Auto-generated method stub
        return this.whileInstruct;
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
            this.whileCell = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, "Tant que", x - 50, y + 50, 100, 50, mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_ELLIPSE);

            this.conditionCell = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, "", x - 200, y + 125, 100, 50, mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_HEXAGON);

            this.sequence = new FigureSequence();
            this.sequence.setSequence(whileInstruct.getSequence());
            this.sequence.getSequence().addObserver(this.sequence);
            this.sequence.printToCanvas(this, Arbre.getInstance().getCanvas().getGraph());

            graph.insertEdge(graph.getDefaultParent(), null, null, figureSequence.getCell(), this.whileCell);
            graph.insertEdge(graph.getDefaultParent(), null, null, this.whileCell, this.conditionCell);
        }
        else
        {
            throw new UnknownError();
        }
    }

    public While getWhileInstruct()
    {
        return whileInstruct;
    }

    public void setWhileInstruct(While whileInstruct)
    {
        this.whileInstruct = whileInstruct;
    }

    public mxCell getWhileCell()
    {
        return whileCell;
    }

    public void setWhileCell(mxCell whileCell)
    {
        this.whileCell = whileCell;
    }

    public mxCell getConditionCell()
    {
        return conditionCell;
    }

    public void setConditionCell(mxCell conditionCell)
    {
        this.conditionCell = conditionCell;
    }

    public FigureSequence getSequence()
    {
        return sequence;
    }

    public void setSequence(FigureSequence sequence)
    {
        this.sequence = sequence;
    }

    @Override
    public void deleteFromCanvas(mxGraph graph)
    {
        this.getSequence().deleteFromCanvas(graph);
        graph.removeCells(new Object[] { this.getConditionCell(), this.getWhileCell() }, true);
        graph.repaint();

    }

    @Override
    public mxCell getMainCell()
    {
        // TODO Auto-generated method stub
        return this.whileCell;
    }

    public void update(Observable arg0, Object arg1)
    {
        if (arg1 == null)
        {
            if (Arbre.getInstance().getHeader().getHeader().isFlagPasPas())
            {
                Arbre.getInstance().getCanvas().getGraph().setCellStyles(mxConstants.STYLE_FILLCOLOR, "#01D758", new Object[] { this.getMainCell() });
                while (Arbre.getInstance().getHeader().getHeader().isFlagPasPas())
                {
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                Arbre.getInstance().getHeader().getHeader().setFlagPasPas(true);
                Arbre.getInstance().getCanvas().getGraph().setCellStyles(mxConstants.STYLE_FILLCOLOR, "#C3D9FF", new Object[] { this.getMainCell() });
            }
        }
        else if (arg1 instanceof Condition)
        {
            if (Arbre.getInstance().getHeader().getHeader().isFlagPasPas())
            {
                Arbre.getInstance().getCanvas().getGraph().setCellStyles(mxConstants.STYLE_FILLCOLOR, "#01D758", new Object[] { this.getConditionCell() });
                while (Arbre.getInstance().getHeader().getHeader().isFlagPasPas())
                {
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                Arbre.getInstance().getHeader().getHeader().setFlagPasPas(true);
                Arbre.getInstance().getCanvas().getGraph().setCellStyles(mxConstants.STYLE_FILLCOLOR, "#C3D9FF", new Object[] { this.getConditionCell() });
            }
        }
    }
    
	@Override
	public String onche() 
	{
		return "While";
	}

}
