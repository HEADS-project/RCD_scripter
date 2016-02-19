/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.variables;

import java.util.ArrayList;
import java.util.Iterator;
import org.thingml.rcd_scripter2.ExecuteContext;

/**
 *
 * @author steffend
 */
public class VarTable extends VarBase {

    private ArrayList<VarRow> rowList = null;
    private VarRow defaultRow = null;

    public VarTable() {
        rowList = new ArrayList<VarRow>();
        defaultRow = new VarRow();
    }

    public VarTable(VarTable copyFromTable) {
        rowList = new ArrayList<VarRow>();
        defaultRow = new VarRow();
        copyTable(copyFromTable); 
    }

    private void copyTable(VarTable copyFromTable) {
        if (copyFromTable != null) {
            defaultRow = new VarRow(copyFromTable.defaultRow);
                
            Iterator i1 = copyFromTable.rowList.iterator();
            int n1 = 0;
            while(i1.hasNext()) {
                VarRow  row1  = (VarRow)i1.next();

                VarRow newRow = new VarRow(row1);
                rowList.add(newRow);
                n1++;
            }
        }
    }
    
    public VarRow getDefaultRowObj() {
        return defaultRow;
    }
    
    @Override
    public void setDefault(ExecuteContext ctx, String idName) {
        VarRow row  = ctx.getRowVar(idName);
        defaultRow = row;
    }
    
    @Override
    public void add(ExecuteContext ctx, String idName) {
        VarRow newRow = ctx.getRowVar(idName);
        rowList.add(newRow);
    }

    @Override
    public String printString(){
        String ret = "<"+getTypeString()+" Default row:"+defaultRow.printString()+"\n";
        Iterator i = rowList.iterator();
        int n = 0;
        while(i.hasNext()) {
            ret += "Row #" + n + ":"+((VarRow)(i.next())).printString()+"\n";
            n++;
        }

        ret += ">\n";
        return ret;
    }

    @Override
    public String getTypeString() {
        return "VarTable";
    }

}
