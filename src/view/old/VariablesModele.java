package view.old;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import controller.Arbre;
import model.old.Variable;

public class VariablesModele extends AbstractTableModel
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String[] entetes = { "Type", "Nom", "Valeur" };
    private ArrayList<Variable> data;
    private JTableHeader header;

    public VariablesModele()
    {
        data = new ArrayList<>();
        Iterator<Entry<String, Variable>> it = Arbre.getInstance().getHeader().getHeader().getVariables_locals().entrySet().iterator();

        while (it.hasNext())
        {
            Entry<String, Variable> entry = it.next();
            Variable v = entry.getValue();
            data.add(v);
        }
        // data = new Object[][] { { "String", "pseudo", "komalis" }, { "Int", "nbCarottes", 5 }, { "bool", "onche", "true" }, };

    }

    public boolean isCellEditable(int row, int column)
    {
        // all cells false
        return false;
    }

    @Override
    public int getColumnCount()
    {
        return entetes.length;
    }

    @Override
    public int getRowCount()
    {

        return this.data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        // data = new ArrayList<>();

        if (columnIndex == 0)
        {
            return this.data.get(rowIndex).getType();
        }
        else if (columnIndex == 1)
        {
            return this.data.get(rowIndex).getNom();

        }
        else
        {
            return this.data.get(rowIndex).getValue().toString();

        }
    }

    public String getColumnName(int columnIndex)
    {
        return entetes[columnIndex];
    }

    public String[] getEntetes()
    {
        return entetes;
    }

    public void setEntetes(String[] entetes)
    {
        this.entetes = entetes;
    }

    public ArrayList<Variable> getData()
    {
        return data;
    }

    public void setData(ArrayList<Variable> data)
    {
        this.data = data;
    }

    public JTableHeader getHeader()
    {
        return header;
    }

    public void setHeader(JTableHeader header)
    {
        this.header = header;
    }

}
