/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lol_stat;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Mathéo Dumont
 */
public class ModeleDonnee extends AbstractTableModel implements TableModel{
    
    protected String[]nomColonnes = null;
    protected Object [][]données = new Object[0][0];
    
    public ModeleDonnee()
    {
        super();
    }
    public ModeleDonnee(String[]nom,Object[][]données)
    {
        super();
        nomColonnes = nom;
        this.données = données;
    }
    
    public ModeleDonnee(ModeleDonnee mod)
    {
        super();
        this.nomColonnes = mod.nomColonnes;
        this.données = mod.données;
        
    }

    @Override
    public int getRowCount() {
        return données.length;
    }

    @Override
    public int getColumnCount() {
        return nomColonnes.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return données[rowIndex][columnIndex];
    }
    
    @Override
    public String getColumnName(int col)
    {
        return nomColonnes[col];
    }
    
    @Override
    public boolean isCellEditable(int row,int col)
    {
        return false;
    }
    
    @Override
    public void setValueAt(Object value,int row,int col)
    {
        données[row][col] = value;
    }
    
}

