/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author steffend
 */
class TableObj {

    private ArrayList<RowObj> rowList = null;
    private RowObj defaultRow = null;

    public TableObj() {
        rowList = new ArrayList<RowObj>();
        defaultRow = new RowObj();
    }

    public RowObj getDefaultRowObj() {
        return defaultRow;
    }
    
    public RowObj getNewRowObj() {
        RowObj newRow = defaultRow.makeCopy();
        rowList.add(newRow);
        return newRow;
    }
    
    public void print(){
        System.out.print("Default row entries : ");
        defaultRow.print();
        Iterator i = rowList.iterator();
        int n = 0;
        while(i.hasNext()) {
            System.out.print("Row #" + n + " entries :");
            ((RowObj)(i.next())).print();
            n++;
        }

    }

}
