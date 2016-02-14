/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author steffend
 */
class VarRow extends VarBase {

    private HashMap<String, VarCellBase> cellList = null;

    public VarRow() {
        cellList = new HashMap<String, VarCellBase>();
    }

    public VarRow(VarRow copyFromRow) {
        cellList = new HashMap<String, VarCellBase>();
        cellList.putAll(copyFromRow.cellList);
    }

    public void addCell(VarCellBase cell){
        cellList.put(cell.getId(), cell);
    }    
    
    public VarCellBase getCell(String name){
        return cellList.get(name);
    }    
    
    void addRow(VarRow row) {
        cellList.putAll(row.cellList);
    }
    
    public void print(){
        Iterator i = cellList.entrySet().iterator();
        System.out.print("<");
        while(i.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)i.next();
            VarCellBase cell = (VarCellBase)pair.getValue();
            System.out.print("<");
            cell.print();
            System.out.print("> ");
        }
        System.out.println(">");
        
    }


}
