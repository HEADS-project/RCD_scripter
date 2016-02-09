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

    public void createTableJoin(String newtab_name, String orgtab_name, String orgcol_name, String jointab_name, String joincol_name) {
        TableObj newtab = new TableObj();
        tableList.put(newtab_name, newtab);

        TableObj orgtab = tableList.get(orgtab_name);
        TableObj jointab = tableList.get(jointab_name);
        
        newtab.join(orgtab, orgcol_name, jointab, joincol_name);
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

    void createTableIfEq(String newtab_name, String orgtab_name, CellObj match_cell) {
        TableObj newtab = new TableObj();
        tableList.put(newtab_name, newtab);

        TableObj orgtab = tableList.get(orgtab_name);
        
        newtab.copyRowsIfEq(orgtab, match_cell);
    }

    void tableCreateColumnsConcat(String tab_name, String newcol_name, String col1_name, String col2_name) {
        TableObj tab = tableList.get(tab_name);
        
        tab.createColumnsConcat(newcol_name, col1_name, col2_name);
    }

    void printEachRow(String tab_name, JobList printList) {
        TableObj tab = tableList.get(tab_name);
        
        tab.printEachRow(printList);
        
    }

}
