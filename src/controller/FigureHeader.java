package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import model.Header;
import model.old.Element;

public class FigureHeader implements Figure
{
    private Header header;
    private mxCell firstCell;
    private mxCell secondCell;
    private mxCell thirdCell;

    private ArrayList<Figure> fils;

    private Figure father;

    public FigureHeader()
    {
        this.header = new Header();
        this.fils = new ArrayList<>();
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

    @Override
    public Element getElement()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Figure getFather()
    {
        return father;
    }

    public void setFather(Figure father)
    {
        this.father = father;
    }

    public void printToCanvas(Figure father, mxGraph graph)
    {
        Object parent = graph.getDefaultParent();
        // Object v1 = graph.insertVertex(parent, null, "", 150, 30, 500, 80, mxConstants.STYLE_SHAPE + "=" + "square;" + mxConstants.STYLE_RESIZABLE + "=0;");
        graph.getModel().beginUpdate();
        this.firstCell = (mxCell) graph.insertVertex(parent, null, "int: arg1\nchar: arg2\nint: arg3", 150, 30, 200, 80, mxConstants.STYLE_SHAPE + "=" + "square;" + mxConstants.STYLE_RESIZABLE + "=0;" + mxConstants.STYLE_MOVABLE + "=0");
        graph.getModel().endUpdate();

        graph.getModel().beginUpdate();
        this.secondCell = (mxCell) graph.insertVertex(parent, null, "FONCTION_TEST", 300, 30, 200, 80, mxConstants.STYLE_SHAPE + "=" + "square;" + mxConstants.STYLE_RESIZABLE + "=0;" + mxConstants.STYLE_MOVABLE + "=0");
        graph.getModel().endUpdate();

        graph.getModel().beginUpdate();
        this.thirdCell = (mxCell) graph.insertVertex(parent, null, "void", 450, 30, 200, 80, mxConstants.STYLE_SHAPE + "=" + "square;" + mxConstants.STYLE_RESIZABLE + "=0;" + mxConstants.STYLE_MOVABLE + "=0");
        graph.getModel().endUpdate();
        // System.out.println(String.format("%.1f %.1f", graph.getCellBounds(middleCell).getX(), graph.getCellBounds(middleCell).getY()));
    }

    public Header getHeader()
    {
        return header;
    }

    public void setHeader(Header header)
    {
        this.header = header;
    }

    public mxCell getFirstCell()
    {
        return firstCell;
    }

    public void setFirstCell(mxCell firstCell)
    {
        this.firstCell = firstCell;
    }

    public mxCell getSecondCell()
    {
        return secondCell;
    }

    public void setSecondCell(mxCell secondCell)
    {
        this.secondCell = secondCell;
    }

    public mxCell getThirdCell()
    {
        return thirdCell;
    }

    public void setThirdCell(mxCell thirdCell)
    {
        this.thirdCell = thirdCell;
    }

    @Override
    public void deleteFromCanvas(mxGraph graph)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public mxCell getMainCell()
    {
        // TODO Auto-generated method stub
        return this.secondCell;
    }
    
    public JSONObject sauvegarde()
    {
    	JSONObject header = new JSONObject();
    	header.put("type", "Header");
    	this.header.getVariables_locals();
    	
    	header.put("nom", this.header.getName());
    	header.put("retour", this.header.getType());
    	
    	JSONArray variables = new JSONArray();
    	JSONArray arguments = new JSONArray();
    
    	for(String key : this.header.getVariables_locals().keySet())
    	{
    		JSONObject var = new JSONObject();
    		var.put("name", key);
    		var.put("type", this.header.getVariables_locals().get(key).getType());
    		var.put("value", this.header.getVariables_locals().get(key).getValue());
    		variables.put(var);
    	}
    	
    	for(String key : this.header.getArguments().keySet())
    	{
    		JSONObject arg = new JSONObject();
    		arg.put("name", key);
    		arg.put("type", this.header.getArguments().get(key).getType());
    		arguments.put(arg);
    	}
    	
    	header.put("variables", variables);
    	header.put("arguments", arguments);
    	
    	return header;
    }

    public JSONArray recursion(LinkedList<Figure> queue)
    {
    	JSONArray json = new JSONArray();
    	while(queue.size() != 0)
    	{
    		Figure son = queue.poll();
    		if(son instanceof FigureIf || son instanceof FigureWhile)
    		{
    			json.put(son.sauvegarde());
    			queue.addAll(0, son.getFils());
    		}
    		else if(son instanceof FigureSequence)
    		{
    			LinkedList<Figure> recursif = new LinkedList<>();
    			recursif.addAll(son.getFils());
    			json.put(recursion(recursif));
    		}
    		else
    		{
        		queue.addAll(son.getFils());
    			json.put(son.sauvegarde());
    		}
    	}
    	return json;
    }
    
    public void save()
    {
    	JSONArray json = new JSONArray();
    	LinkedList<Figure> queue = new LinkedList<>();
    	queue.add(this);
    	while(queue.size() != 0)
    	{
    		Figure son = queue.poll();
    		if(son instanceof FigureIf || son instanceof FigureWhile)
    		{
    			json.put(son.sauvegarde());
    			queue.addAll(0, son.getFils());
    		}
    		else if(son instanceof FigureSequence)
    		{
    			LinkedList<Figure> recursif = new LinkedList<>();
    			recursif.addAll(son.getFils());
    			json.put(recursion(recursif));
    		}
    		else
    		{
        		queue.addAll(son.getFils());
    			json.put(son.sauvegarde());
    		}
    	}
    	System.out.println(json.toString());
    }
    
    public void loadRecursion(Figure father, JSONArray json)
    {
    	for(int i = 0 ; i < json.length() ; i++)
    	{
    		if(json.get(i) instanceof JSONArray)
    		{
    			;
    		}
    		else
    		{
    			if(json.getString(i).equals("Header"))
    			{
    				father = this.getFils().get(0);
    			}
    			else if(json.getString(i).equals("Afficher"))
    			{
    				ElementManager.getInstance().ajouterAfficher(father);
    			}
    			else if(json.getString(i).equals("Saisir"))
    			{
    				ElementManager.getInstance().ajouterSaisir(father);
    			}
    			else if(json.getString(i).equals("If"))
    			{
    				FigureIf figureIf = ElementManager.getInstance().ajouterIf(father);
    				loadRecursion(figureIf.getSi_vrai(), (JSONArray) json.get(i + 1));
    				loadRecursion(figureIf.getSi_faux(), (JSONArray) json.get(i + 2));
    			}
    			else if(json.getString(i).equals("While"))
    			{
    				FigureWhile figureWhile = ElementManager.getInstance().ajouterWhile(father);
    				loadRecursion(figureWhile.getSequence(), (JSONArray) json.get(i + 1));
    			}
    			else
    			{
    				assert(false);
    			}
    		}
    	}
    }
    
    public void load(JSONArray json)
    {
    	Figure father = this.getFils().get(0);
    	for(int i = 0 ; i < json.length() ; i++)
    	{
    		if(json.get(i) instanceof JSONArray)
    		{
    			loadRecursion(father, json.getJSONArray(i));
    		}
    		else
    		{
    			if(json.getString(i).equals("Header"))
    			{
    				father = this.getFils().get(0);
    			}
    			else if(json.getString(i).equals("Afficher"))
    			{
    				ElementManager.getInstance().ajouterAfficher(father);
    			}
    			else if(json.getString(i).equals("Saisir"))
    			{
    				ElementManager.getInstance().ajouterSaisir(father);
    			}
    			else if(json.getString(i).equals("If"))
    			{
    				FigureIf figureIf = ElementManager.getInstance().ajouterIf(father);
    				loadRecursion(figureIf.getSi_vrai(), (JSONArray) json.get(i + 1));
    				loadRecursion(figureIf.getSi_faux(), (JSONArray) json.get(i + 2));
    			}
    			else if(json.getString(i).equals("While"))
    			{
    				FigureWhile figureWhile = ElementManager.getInstance().ajouterWhile(father);
    				loadRecursion(figureWhile.getSequence(), (JSONArray) json.get(i + 1));
    			}
    			else
    			{
    				assert(false);
    			}
    		}
    	}
    }
    
	@Override
	public String onche() 
	{
		return "Header";
	}

}
