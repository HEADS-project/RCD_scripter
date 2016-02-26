/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarArray;
import org.thingml.rcd_scripter2.variables.VarBase;
import org.thingml.rcd_scripter2.variables.VarCell;
import org.thingml.rcd_scripter2.variables.VarRow;
import org.thingml.rcd_scripter2.variables.VarRowList;
import org.thingml.rcd_scripter2.variables.VarValueBase;
import org.thingml.rcd_scripter2.variables.VarValueString;

/**
 *
 * @author steffend
 */
public class ExecuteContext {
    private HashMap<String, VarBase> varList = new HashMap<String, VarBase>();
    private boolean trace = false;
    private Stack<Token> executingTokenStack = new Stack<Token>();

    public boolean getTrace() {
        return trace;
    }
    
    public void setTrace(boolean trace) {
        this.trace = trace;
    }
    
    public void printExecutingInfo() {
        Token t = executingTokenStack.peek();
        System.out.println("Executing line => Image:<"+t.image+"> beginline:"+t.beginLine+" beginColumn:"+t.beginColumn+" endLine:"+t.endLine+" endColumn:"+t.endColumn+"\n");
    }
    
    public void pushExecutingToken(Token t) {
        executingTokenStack.push(t);
    }
    
    public Token popExecutingToken() {
        return executingTokenStack.pop();
    }
    
    public Token peekExecutingToken() {
        return executingTokenStack.peek();
    }
    
    public void addVar(String name, VarBase var) {
        VarBase oldVar = varList.get(name);
        if (oldVar != null) {
            //System.out.println("Warning variable <"+name+"> is replaced");
        }
        varList.put(name, var);
    }
    
    public void addVarSilent(String name, VarBase var) {
        varList.put(name, var);
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
        return "???";
    }
    
    public VarBase getVarBase(String name) {
        VarBase var = varList.get(name);
        if (var == null) {
            System.out.println("Warning variable <"+name+"> is not defined");
        }
        return var;
    }
    
    public VarRowList getVarRowList(String name) {
        VarBase var = getVarBase(name);
        VarRowList ret = null;
        if (var != null) {
            if (var instanceof VarRowList) {
                ret = (VarRowList) var;
            } else  {
                System.out.println("Warning variable <"+name+"> is not instanceof VarRowList");
            }
        }
        return ret;
    }
    
    public VarRowList copyVarRowList(String name) {
        VarRowList ret = null;
        if(name != null) {
            VarRowList orig = getVarRowList(name);
            ret = new VarRowList(orig);
        } else {
            ret = new VarRowList();
        }
        return ret;
    }
    
    public VarRow getVarRow(String name) {
        VarBase var = getVarBase(name);
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
    
    public VarRow copyVarRow(String name) {
        VarRow ret = null;
        if (name != null) {
            VarRow orig = getVarRow(name);
            ret = new VarRow(orig);
        } else {
            ret = new VarRow();
        }
        return ret;
    }
    
    public VarCell getVarCell(String name) {
        VarBase var = getVarBase(name);
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
    
    
    public VarCell getVarCellId(String var, String id) {
        VarCell varCell = null;
        //VarBase varBase = varList.get(var);
        VarRow varRow = getVarRow(var);
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

    public VarValueBase getVarValueIndex(String var, int index) {
        VarArray varArray = getVarArray(var);
        VarValueBase ret = varArray.getValue(index);

        return ret;
    }

    public VarValueBase getVarValueId(String var, String id) {
        VarCell varCell = getVarCellId(var, id);
        VarValueBase ret = new VarValueString("ERROR Empty");

        if (varCell != null) {
            ret = varCell.getValue();
        } 

        return ret;
    }

    public VarValueBase getVarValue(String name) {
        VarBase var = getVarBase(name);
        VarValueBase ret = null;

        if (var != null) {
            if (var instanceof VarValueBase) {
                ret = (VarValueBase) var;
            } else  {
                ret = new VarValueString(var.printString());
            }
        }
        return ret;
    }

    public String getStringVarId(String var, String id) {
        VarCell varCell = getVarCellId(var, id);
        String ret = "ERROR Empty";
        //VarBase varBase = varList.get(var);
        if (varCell != null) {
            ret = varCell.getString();
        } 

        return ret;
    }

    public VarArray getVarArray(String name) {
        VarBase var = getVarBase(name);
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
    
    public VarArray copyVarArray(String name) {
        VarArray orig = getVarArray(name);
        VarArray ret = new VarArray(orig);
        return ret;
    }
    
    public String printStringAll() {
        String ret = "<ExecuteContext() \n";
        Iterator i = varList.entrySet().iterator();
        while(i.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)i.next();
            VarBase base = (VarBase)pair.getValue();
            ret += "<Content of id("+pair.getKey()+")\n";
            ret += base.printString();
            ret += ">\n";
        }
        return ret;
    }
}
