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
    private HashMap<String, VarBase> varList = new HashMap<String, VarBase>();
    
    public void addVar(String name, VarBase var) {
        VarBase oldVar = varList.get(name);
        if (oldVar != null) {
            System.out.println("Warning variable <"+name+"> is replaced");
        }
        varList.put(name, var);
    }
    
    public VarBase getBaseVar(String name) {
        VarBase var = varList.get(name);
        if (var == null) {
            System.out.println("Warning variable <"+name+"> is not defined");
        }
        return var;
    }
    
    public VarTable getTableVar(String name) {
        VarBase var = getBaseVar(name);
        VarTable ret = null;
        if (var != null) {
            if (var instanceof VarTable) {
                ret = (VarTable) var;
            } else  {
                System.out.println("Warning variable <"+name+"> is not instanceof VarTable");
            }
        }
        return ret;
    }
    
    public VarTable copyTableVar(String name) {
        VarTable ret = null;
        if(name != null) {
            VarTable orig = getTableVar(name);
            ret = new VarTable(orig);
        } else {
            ret = new VarTable();
        }
        return ret;
    }
    
    public VarRow getRowVar(String name) {
        VarBase var = getBaseVar(name);
        VarRow ret = null;
        if (var != null) {
            if (var instanceof VarRow) {
                ret = (VarRow) var;
            } else  {
                System.out.println("Warning variable <"+name+"> is not instanceof VarRow");
            }
        }
        return ret;
    }
    
    public VarRow copyRowVar(String name) {
        VarRow ret = null;
        if (name != null) {
            VarRow orig = getRowVar(name);
            ret = new VarRow(orig);
        } else {
            ret = new VarRow();
        }
        return ret;
    }
    
    public VarCell getCellVar(String name) {
        VarBase var = getBaseVar(name);
        VarCell ret = null;
        if (var != null) {
            if (var instanceof VarCell) {
                ret = (VarCell) var;
            } else  {
                System.out.println("Warning variable <"+name+"> is not instanceof VarCell");
            }
        }
        return ret;
    }
    
    public VarCell getCellVarId(String var, String id) {
        VarCell varCell = null;
        //VarBase varBase = varList.get(var);
        VarRow varRow = getRowVar(var);
        if (varRow != null) {
            varCell = varRow.getCell(id);
            if (varCell == null) {
                System.out.println("Warning variable <"+var+"."+id+"> is not in ROW");
            }
        } else {
            System.out.println("Warning variable <"+var+"> is not defined");
        } 
        return varCell;
    }

    public VarValueBase getValueVarId(String var, String id) {
        VarCell varCell = getCellVarId(var, id);
        VarValueBase ret = new VarValueString("ERROR Empty");

        if (varCell != null) {
            ret = varCell.getValue();
        } 

        return ret;
    }

    public VarValueBase getValueVar(String var) {
        VarCell varCell = getCellVar(var);
        VarValueBase ret = new VarValueString("ERROR Empty");

        if (varCell != null) {
            ret = varCell.getValue();
        } 

        return ret;
    }

    public String getStringVarId(String var, String id) {
        VarCell varCell = getCellVarId(var, id);
        String ret = "ERROR Empty";
        //VarBase varBase = varList.get(var);
        if (varCell != null) {
            ret = varCell.getString();
        } 

        return ret;
    }

    public VarArray getArrayVar(String name) {
        VarBase var = getBaseVar(name);
        VarArray ret = null;
        if (var != null) {
            if (var instanceof VarArray) {
                ret = (VarArray) var;
            } else  {
                System.out.println("Warning variable <"+name+"> is not instanceof VarArray");
            }
        }
        return ret;
    }
    
    public VarArray copyArrayVar(String name) {
        VarArray orig = getArrayVar(name);
        VarArray ret = new VarArray(orig);
        return ret;
    }
    
}
