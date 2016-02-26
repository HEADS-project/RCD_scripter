/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.variables;

import java.util.HashMap;
import java.util.Iterator;
import org.thingml.rcd_scripter2.ExecuteContext;

/**
 *
 * @author steffend
 */
public class VarRow extends VarBase {

    private HashMap<String, VarCell> cellList = null;

    public VarRow() {
        cellList = new HashMap<String, VarCell>();
    }

    public VarRow(VarRow copyFromRow) {
        cellList = new HashMap<String, VarCell>();
        cellList.putAll(copyFromRow.cellList);
    }

    public void addCell(VarCell cell){
        cellList.put(cell.getId(), cell);
    }    
    
    public VarCell getCell(String name){
        return cellList.get(name);
    }    
    
    void addRow(VarRow row) {
        cellList.putAll(row.cellList);
    }

    @Override
    public void add(ExecuteContext ctx, String idName) {
        VarBase newVar = ctx.getVarBase(idName);
        if (newVar != null) {
            if (newVar instanceof VarRow) {
                addRow((VarRow)newVar);
            }
            if (newVar instanceof VarCell) {
                addCell((VarCell)newVar);
            }
        } else {
            System.out.println("Warning variable <"+idName+"> is not instanceof VarRow or VarCell");
            ctx.printExecutingInfo();
        }
    }


    
    @Override
    public String printString(){
        String ret = "<"+getTypeString();
        Iterator i = cellList.entrySet().iterator();
        while(i.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)i.next();
            VarCell cell = (VarCell)pair.getValue();
            ret += cell.printString();
        }

        ret += ">";
        return ret;
    }

    @Override
    public String getTypeString() {
        return "VarRow";
    }
    

}
