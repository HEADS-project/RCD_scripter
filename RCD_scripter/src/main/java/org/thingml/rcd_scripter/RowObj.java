/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author steffend
 */
class RowObj {
    
    private HashMap<String, CellObj> cellList = null;

    public RowObj() {
        cellList = new HashMap<String, CellObj>();
    }
    
    public void addCell(CellObj cell){
        cellList.put(cell.getId(), cell);
    }    
    
    public CellObj getCell(String name){
        return cellList.get(name);
    }    
    
    public RowObj makeCopy(){
        RowObj newRow = new RowObj();
        newRow.cellList.putAll(cellList);
        return newRow;
    }

    public void print(){
        Iterator i = cellList.entrySet().iterator();
        System.out.print("<");
        while(i.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)i.next();
            CellObj cellObj = (CellObj)pair.getValue();
            System.out.print("<");
            cellObj.print();
            System.out.print("> ");
        }
        System.out.println(">");
        
    }

    void addRow(RowObj row) {
        cellList.putAll(row.cellList);
    }
}
