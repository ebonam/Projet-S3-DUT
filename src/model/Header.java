package model;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import controller.Arbre;
import model.old.Element;
import model.old.Variable;

public class Header implements Runnable
{
    private ArrayList<Element> elements;
    private Hashtable<String, Variable> variables_locals;
    private Hashtable<String, Variable> locals_copy;
    private Hashtable<String, Variable> arguments;
    private Hashtable<String, Variable> arguments_copy;
    
    private String name;
    private String type;

    private boolean flagPasPas;

    public Header()
    {
    	this.name = "Automate";
    	this.type = "void";
        this.elements = new ArrayList<>();
        this.variables_locals = new Hashtable<>();
        this.arguments = new Hashtable<>();
        this.flagPasPas = false;
    }
    
    public String toCode(String string) {

        String r = new String();
        r += "void main(){\n";

        for (Entry<String, Variable> entry : this.variables_locals.entrySet()) {
            String key = entry.getKey();
            r += entry.getValue().toCode();

        }

        this.copyHashtables();

        for (Element elem : elements) {
            r += elem.toCode();
        }
        r += "return 0;" + "\n";
        r += "\n" + "}";

        try {
            FileWriter fw = new FileWriter(string);
            fw.write(r);

            System.out.println(r);

            fw.close();
        } catch (IOException exception) {
            System.out.println("Erreur lors de la lecture : " + exception.getMessage());
        }
        this.resetHashtables();
        return r;
    }

    public void newVariable(Variable variable)
    {
        if (!arguments.containsKey(variable.getNom().toLowerCase()) && !variables_locals.containsKey(variable.getNom().toLowerCase()))
        {
            variables_locals.put(variable.getNom().toLowerCase(), variable);
        }
        else
        {
            // Il existe une variable qui a déjà ce nom là.
            throw new UnknownError();
        }
    }

    public void setNameVariable(String from, String to)
    {
        if (this.variables_locals.containsKey(from.toLowerCase()))
        {
            Variable temp = this.getVariable(from);
            temp.setNom(from);
            this.variables_locals.remove(from.toLowerCase());
            this.variables_locals.put(to.toLowerCase(), temp);
        }
        else if (this.arguments.containsKey(from.toLowerCase()))
        {
            Variable temp = this.getVariable(from);
            temp.setNom(from);
            this.arguments.remove(from.toLowerCase());
            this.arguments.put(to.toLowerCase(), temp);
        }
        else
        {
            throw new UnknownError();
        }
    }

    public void modifVariable(String last, Variable newVariable)
    {
        Variable oldVariable = this.getVariable(last);
        oldVariable.setNom(newVariable.getNom());
        oldVariable.setType(newVariable.getType());
        oldVariable.setValue(newVariable.getValue());
        this.deleteVariable(last);
        this.newVariable(oldVariable);
    }

    public Variable getVariable(String nom)
    {
        if (arguments.containsKey(nom.toLowerCase()))
        {
            return arguments.get(nom.toLowerCase());
        }
        else if (variables_locals.containsKey(nom.toLowerCase()))
        {
            return variables_locals.get(nom.toLowerCase());
        }
        else
        {
            // Il n'existe pas de variable de ce nom là.
            throw new UnknownError();
        }
    }

    public void deleteVariable(String nom)
    {
        if (arguments.containsKey(nom.toLowerCase()))
        {
            arguments.remove(nom.toLowerCase());
        }
        else if (variables_locals.containsKey(nom.toLowerCase()))
        {
            variables_locals.remove(nom.toLowerCase());
        }
        else
        {
            throw new UnknownError();
        }
    }

    public void launchArbre()
    {
        this.copyHashtables();
        this.elements.get(0).invoke();
        this.resetHashtables();
    }

    public void launchArbrePasPas()
    {
        Thread t = new Thread(this);
        t.start();
        // this.flagPasPas = true;
        // this.copyHashtables();
        // this.elements.get(0).invoke();
        // this.resetHashtables();
        // this.flagPasPas = false;
    }

    private void resetHashtables()
    {
        // this.variables_locals = this.locals_copy;
        // this.arguments = this.arguments_copy;
        Iterator<Entry<String, Variable>> it = Arbre.getInstance().getHeader().getHeader().getVariables_locals().entrySet().iterator();

        while (it.hasNext())
        {
            Entry<String, Variable> entry = it.next();
            entry.getValue().setValue(this.locals_copy.get(entry.getKey()).getValue());

        }

        it = Arbre.getInstance().getHeader().getHeader().getArguments().entrySet().iterator();

        while (it.hasNext())
        {
            Entry<String, Variable> entry = it.next();
            entry.getValue().setValue(this.arguments_copy.get(entry.getKey()).getValue());

        }
    }

    private void copyHashtables()
    {
        Iterator<Entry<String, Variable>> it = Arbre.getInstance().getHeader().getHeader().getVariables_locals().entrySet().iterator();

        this.locals_copy = new Hashtable<>();

        while (it.hasNext())
        {
            Entry<String, Variable> entry = it.next();
            Variable oldVariable = entry.getValue();
            Variable newVariable = new Variable();
            newVariable.setNom(oldVariable.getNom());
            newVariable.setType(oldVariable.getType());
            // TODO: Faire les autres types !!!
            if (oldVariable.getValue() instanceof BigDecimal)
            {
                newVariable.setValue(new BigDecimal(((BigDecimal) oldVariable.getValue()).toString()));

            }
            this.locals_copy.put(entry.getKey(), newVariable);
        }

        it = Arbre.getInstance().getHeader().getHeader().getArguments().entrySet().iterator();

        this.arguments_copy = new Hashtable<>();

        while (it.hasNext())
        {
            Entry<String, Variable> entry = it.next();
            Variable oldVariable = entry.getValue();
            Variable newVariable = new Variable();
            newVariable.setNom(oldVariable.getNom());
            newVariable.setType(oldVariable.getType());
            // TODO: Faire les autres types !!!
            if (oldVariable.getValue() instanceof BigDecimal)
            {
                newVariable.setValue(new BigDecimal(((BigDecimal) oldVariable.getValue()).toString()));
            }
            this.arguments_copy.put(entry.getKey(), newVariable);
        }

    }

    public ArrayList<Element> getElements()
    {
        return elements;
    }

    public void setElements(ArrayList<Element> elements)
    {
        this.elements = elements;
    }

    public Hashtable<String, Variable> getVariables_locals()
    {
        return variables_locals;
    }

    public void setVariables_locals(Hashtable<String, Variable> variables_locals)
    {
        this.variables_locals = variables_locals;
    }

    public Hashtable<String, Variable> getArguments()
    {
        return arguments;
    }

    public void setArguments(Hashtable<String, Variable> arguments)
    {
        this.arguments = arguments;
    }

    public Hashtable<String, Variable> getLocals_copy()
    {
        return locals_copy;
    }

    public void setLocals_copy(Hashtable<String, Variable> locals_copy)
    {
        this.locals_copy = locals_copy;
    }

    public Hashtable<String, Variable> getArguments_copy()
    {
        return arguments_copy;
    }

    public void setArguments_copy(Hashtable<String, Variable> arguments_copy)
    {
        this.arguments_copy = arguments_copy;
    }

    public boolean isFlagPasPas()
    {
        return flagPasPas;
    }

    public void setFlagPasPas(boolean flagPasPas)
    {
        this.flagPasPas = flagPasPas;
    }

    @Override
    public void run()
    {
        this.flagPasPas = true;
        this.copyHashtables();
        this.elements.get(0).invoke();
        this.resetHashtables();
        this.flagPasPas = false;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}    

}
