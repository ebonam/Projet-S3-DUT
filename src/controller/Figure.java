package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import model.old.Element;

public interface Figure extends Observer
{
    public void printToCanvas(Figure father, mxGraph graph);

    public void deleteFromCanvas(mxGraph graph);

    public void addToFather(Figure son);

    public Element getElement();

    public mxCell getMainCell();

    public Figure getFather();
    
    public JSONObject sauvegarde();
    
    public String onche();

    public ArrayList<Figure> getFils();

    public default void update(Observable arg0, Object arg1)
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
    }
}
