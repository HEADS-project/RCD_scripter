/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3;

import java.util.HashMap;
import java.util.Iterator;
import org.thingml.rcd_scripter3.variables.VarBase;

/**
 *
 * @author steffend
 */
public class SymbolTable {
    private SymbolTable parentTable = null;
    private int level = 0;
    private HashMap<String, VarBase> varList = new HashMap<String, VarBase>();
    
    public SymbolTable createSubTable() {
        SymbolTable newTable = new SymbolTable();
        newTable.parentTable = this;
        newTable.level = level+1;
        return newTable;
    }
    
    public SymbolTable discardSubTable() {
        SymbolTable ret = parentTable;
        if (ret == null) {
            System.out.println("ERROR Cannot discard main symbol table");
            ret = this;
        }
        
        return ret;
    }
    
    public VarBase getVar(String name) {
        // Search the hierarchy of symbol tables
        VarBase ret = varList.get(name);
        if (ret == null) {
            // Not found in current table
            if (parentTable != null) {
                // Search parent
                ret = parentTable.getVar(name);
            }
        }
        return ret;
    }    

    public void declVar(String name, VarBase newVar) {
        // Store in current symbol table
        varList.put(name, newVar);
    }

    public void assignVar(String name, VarBase newVar) {
        // Store in symbol table where it is declared
        VarBase currVal = varList.get(name);
        if (currVal == null) {
            varList.put(name, newVar);
        } else {
            // Not found in current table
            if (parentTable != null) {
                // Try parent
                parentTable.assignVar(name, newVar);
            }
        }
    }

    public String getVarName(VarBase obj){
        Iterator i = varList.entrySet().iterator();
        while(i.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)i.next();
            VarBase base = (VarBase)pair.getValue();
            if (base == obj) {
                return ""+pair.getKey();
            }
        }
        if (parentTable != null) {
            // Try parent
            parentTable.getVarName(obj);
        }
        return "???";
    }
    
    public String printStringAll() {
        String ret = "<SymbolTable("+level+") \n";
        Iterator i = varList.entrySet().iterator();
        while(i.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)i.next();
            VarBase base = (VarBase)pair.getValue();
            ret += "<Content of id("+pair.getKey()+")\n";
            if (base != null) {
                ret += base.printString();
            } else {
                ret += "NULL_PTR";
            }
            ret += ">\n";
        }
        ret += ">\n";
        if (parentTable != null) {
            // Try parent
            ret += parentTable.printStringAll();
        }
        return ret;
    }
}
