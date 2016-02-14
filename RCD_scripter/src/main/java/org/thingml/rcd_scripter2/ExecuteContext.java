/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2;

import java.util.HashMap;

/**
 *
 * @author steffend
 */
public class ExecuteContext {
    private HashMap<String, VarBase> varList = null;
    
    public void addVar(String name, VarBase var) {
        VarBase oldVar = varList.get(name);
        if (oldVar != null) {
            System.out.println("Warning variable <"+name+"> is replaced");
        }
        varList.put(name, var);
    }
    
    public VarBase getVar(String name) {
        VarBase var = varList.get(name);
        if (var == null) {
            System.out.println("Warning variable <"+name+"> is not defined");
        }
        return var;
    }
    
    public VarCellBase getVarId(String var, String id) {
        VarCellBase varCellBase = null;
        VarBase varBase = varList.get(var);
        if (varBase != null) {
            if (varBase instanceof VarRow) {
                VarRow varRow = (VarRow) varBase;
                varCellBase = varRow.getCell(id);
                if (varCellBase == null) {
                    System.out.println("Warning variable <"+var+"."+id+"> is not in ROW");
                }
            } else {
                System.out.println("Warning variable <"+var+"> is not of type ROW");
            }
        } else {
            System.out.println("Warning variable <"+var+"> is not defined");
        } 
        return varCellBase;
    }
}
