/**
 * Copyright (C) 2016 SINTEF <steffen.dalgard@sintef.no>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import org.thingml.rcd_scripter3.proc.ProcBaseIf;
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
    private boolean trace = false;
    private SymbolTable symTab = new SymbolTable();
    //private Stack<Token> executingTokenStack = new Stack<Token>();
    private Stack<SymbolTable> symStack = new Stack<SymbolTable>();

    public boolean getTrace() {
        return trace;
    }
    
    public void setTrace(boolean trace) {
        this.trace = trace;
    }
    
//    public void printExecutingInfo() {
//        Token t = executingTokenStack.peek();
//        System.out.println("Executing line => Image:<"+t.image+"> beginline:"+t.beginLine+" beginColumn:"+t.beginColumn+" endLine:"+t.endLine+" endColumn:"+t.endColumn+"\n");
//    }
    
//    public void pushExecutingToken(Token t) {
//        executingTokenStack.push(t);
//    }
    
//    public Token popExecutingToken() {
//        return executingTokenStack.pop();
//    }
    
//    public Token peekExecutingToken() {
//        return executingTokenStack.peek();
//    }
    
    public void pushSymTab(SymbolTable newSymTab) {
        symStack.push(symTab);
        symTab = newSymTab;
    }
    
    public void popSymTab(ASTRcdBase b)  throws ExecuteException {
        SymbolTable tmp = null;
        try {
            tmp = symStack.pop();
        } catch (Exception ex) {
            if (b != null) {
                throw b.generateExecuteException("Error popSymTab failed : No value on stack.\n" + ex);
            }
        }
        symTab = tmp;
    }
    
    public SymbolTable getSymTab() {
        return symTab;
    }
    
    public void blockStart() {
        symTab = symTab.createSubTable();
    }
    
    public void blockEnd() {
        symTab = symTab.discardSubTable();
    }
    
    public ProcBaseIf getProcBase(ASTRcdBase b, String name)  throws ExecuteException {
        ProcBaseIf proc = symTab.getProcCheckAllLevels(name);
        if (proc == null) {
            throw b.generateExecuteException("Error proc <"+name+"> is not defined");
        }
        return proc;
    }
    
    public void declProc(ASTRcdBase b, String name, ProcBaseIf newProc)  throws ExecuteException {
        ProcBaseIf oldProc = symTab.getProcCheckThisLevel(name);
        if (oldProc != null) {
            throw b.generateExecuteException("Error proc <"+name+"> is already declared");
        }
        symTab.declProc(name, newProc);
    }
    
    public int getContainerStackSize() {
        return symTab.getContainerStackSizeThisLevel();
    }
    
    public void pushContainer(VarContainer c) {
        symTab.pushContainerThisLevel(c);
    }
    
    public VarContainer popContainer(ASTRcdBase b)  throws ExecuteException {
        return symTab.popContainerThisLevel(b);
    }
    
//    public void declVar(ASTRcdBase b, String name, VarBase newVar)  throws ExecuteException {
//        VarBase oldVar = symTab.getVarCheckThisLevel(name);
//        if (oldVar != null) {
//            throw b.generateExecuteException("Error variable <"+name+"> is already declared");
//        }
//        symTab.declVar(name, newVar);
//    }
    
//    public void assignVar(ASTRcdBase b, String name, VarContainer newCont)  throws ExecuteException {
//        VarContainer oldCont = symTab.getContainerCheckThisLevel(name);
//        if (oldCont != null) {
//            oldCont.setInst(newCont.getInst());
//        } else {
//            symTab.putContainer(name, newCont);
//        }
//    }
    
//    public void addVarSilent(String name, VarBase var) {
//        varList.put(name, var);
//    }

//    public String getVarName(VarBase obj){
//        return symTab.getVarName(obj);
//    }
    
    public VarContainer getContainer(ASTRcdBase b, String name)  throws ExecuteException {
        VarContainer var = symTab.getContainerCheckThisLevel(name);
        if (var == null) {
            var = new VarContainer();
            symTab.putContainer(name, var);
        }
        return var;
    }
    
//    public VarBase getVarBaseSilent(String name) {
//        VarBase var = symTab.getVarCheckAllLevels(name);
//        return var;
//    }
    
//    public <T> T popVarX(ASTRcdBase b, Class test) throws ExecuteException {
//        VarBase var = null;
//        
//        var = popContainer(b);
//
//        if (var != null) {
//            if (!test.isAssignableFrom(var.getClass())) {
//                throw b.generateExecuteException("Error popVarX failed : Got " + var.getTypeString() + " cannot be cast to " + test.getName()+"\n"+var.printString());
//            }
//        }
//        return (T)var;
//    }

//    public <T> T getVarX(ASTRcdBase b, String name, Class test) throws ExecuteException {
//        VarBase var = getContainer(b, name);
//
//        if (var != null) {
//            if (!test.isAssignableFrom(var.getClass())) {
//                throw b.generateExecuteException("Error getVarX failed : Got " + var.getTypeString() + " cannot be cast to " + test.getName()+"\n"+var.printString());
//            }
//        }
//        return (T)var;
//    }

    public String printStringAll() {
        String ret = "<ExecuteContext() \n";
        ret += symTab.printString();
        ret += ">\n";

        return ret;
    }
}
