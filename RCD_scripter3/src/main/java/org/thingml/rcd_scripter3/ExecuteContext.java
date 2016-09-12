/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.parser.Token;
//import org.thingml.rcd_scripter2.variables.VarArray;
//import org.thingml.rcd_scripter2.variables.VarBase;
//import org.thingml.rcd_scripter2.variables.VarCell;
//import org.thingml.rcd_scripter2.variables.VarFile;
//import org.thingml.rcd_scripter2.variables.VarRow;
//import org.thingml.rcd_scripter2.variables.VarRowList;
import org.thingml.rcd_scripter3.variables.*;

/**
 *
 * @author steffend
 */
public class ExecuteContext {
    private HashMap<String, VarBase> varList = new HashMap<String, VarBase>();
    private boolean trace = false;
    private Stack<Token> executingTokenStack = new Stack<Token>();
    private Stack<VarBase> varStack = new Stack<VarBase>();

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
    
    public void pushVar(VarBase v) {
        varStack.push(v);
    }
    
    public VarBase popVar(ASTRcdBase b)  throws ExecuteException {
        VarBase var = null;
        try {
            var = varStack.pop();
        } catch (Exception ex) {
            if (b != null) {
                throw b.generateExecuteException("Error popVar failed : No value on stack.\n" + ex);
            }
        }
        return var;
    }
    
    public VarBase peekVar() {
        return varStack.peek();
    }
    
    public void addVar(String name, VarBase var) {
        VarBase oldVar = varList.get(name);
        if (oldVar != null) {
            System.out.println("Warning variable <"+name+"> is replaced");
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
    
    public VarBase getVarBase(ASTRcdBase b, String name)  throws ExecuteException {
        VarBase var = varList.get(name);
        if (var == null) {
            throw b.generateExecuteException("Error variable <"+name+"> is not defined");
        }
        return var;
    }
    
    public VarBase getVarBaseSilent(String name) {
        VarBase var = varList.get(name);
        return var;
    }
    
    public <T> T popVarX(ASTRcdBase b, Class test) throws ExecuteException {
        VarBase var = null;
        
        var = popVar(b);

        if (var != null) {
            if (!test.isAssignableFrom(var.getClass())) {
                throw b.generateExecuteException("Error popVarX failed : Got " + var.getTypeString() + " cannot be cast to " + test.getName());
            }
        }
        return (T)var;
    }

    public <T> T getVarX(ASTRcdBase b, String name, Class test) throws ExecuteException {
        VarBase var = getVarBase(b, name);

        if (var != null) {
            if (!test.isAssignableFrom(var.getClass())) {
                throw b.generateExecuteException("Error getVarX failed : Got " + var.getTypeString() + " cannot be cast to " + test.getName());
            }
        }
        return (T)var;
    }

    public String printStringAll() {
        String ret = "<ExecuteContext() \n";
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
        return ret;
    }
}
