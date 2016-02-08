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
public class TableList {

    private HashMap<String, TableObj> tableList = null;

    public TableList() {
        tableList = new HashMap<String, TableObj>();
    }

    public void createTable(String name){
        tableList.put(name, new TableObj());
    }  

    public RowObj getDefaultRowObj(String name){
        TableObj tableObj = tableList.get(name);
        RowObj rowObj = tableObj.getDefaultRowObj();
        return rowObj;
    }  

    public RowObj getNewRowObj(String name){
        TableObj tableObj = tableList.get(name);
        RowObj rowObj = tableObj.getNewRowObj();
        return rowObj;
    }  

    public void print(){
        Iterator i = tableList.entrySet().iterator();
        System.out.println("Content of TableList() Start");
        while(i.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)i.next();
            TableObj tableObj = (TableObj)pair.getValue();
            System.out.println("Content of TableObj(" + pair.getKey() + ") Start");
            tableObj.print();
            System.out.println("Content of TableObj(" + pair.getKey() + ") End");
        }
        System.out.println("Content of TableList() End");
    }  

}
