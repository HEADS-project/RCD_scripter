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
import org.thingml.rcd_scripter3.proc.ProcBaseIf;
import org.thingml.rcd_scripter3.variables.VarContainer;

/**
 *
 * @author steffend
 */
public class SymbolTable {
    private SymbolTable parentTable = null;
    private int level = 0;
    private HashMap<String, VarContainer> contList = new HashMap<String, VarContainer>();
    private HashMap<String, ProcArg> procArgList = new HashMap<String, ProcArg>();
    
    private Stack<VarContainer> containerStack = new Stack<VarContainer>();
    
    private class ProcArg {
        boolean hasVariArgs;
        private HashMap<Integer, ProcBaseIf> procList = new HashMap<Integer, ProcBaseIf>();
        
        public ProcArg(ProcBaseIf pbi) {
            hasVariArgs = pbi.isVariArgs();
            if (hasVariArgs) {
                procList.put(0, pbi);
            } else {
                procList.put(pbi.getNumArgs(), pbi);
            }
        }
        
        public void addProc(ProcBaseIf pbi) {
            procList.put(pbi.getNumArgs(), pbi);
        }

        public ProcBaseIf getProc(int numArgs) {
            if (hasVariArgs) numArgs = 0;
            
            ProcBaseIf ret = procList.get(numArgs);
            return ret;
        }
        
        public boolean acceptNumArgs(int numArgs) {
            ProcBaseIf pbi = getProc(numArgs);
            return pbi != null;
        }

        public boolean getHasVariArgs() {
            return hasVariArgs;
        }
    }
    
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
    
    private void addProc(String name, ProcBaseIf newProc) {
        // Store in current symbol table
        ProcArg pa = procArgList.get(name);
        if (pa == null) {
            procArgList.put(name, new ProcArg(newProc));
        } else {
            pa.addProc(newProc);
        }
    }
    
    public void declProc(ASTRcdBase b, String name, ProcBaseIf newProc)  throws ExecuteException {
        if (existVarArgProcNameCheckAllLevels(name)) {
            throw b.generateExecuteException("Error multiple proc <"+name+"> cannot distinguished when using variable arguments.");
        }
        if (newProc.isVariArgs()) {
            // Try to declare variable arg proc ... must be alone
            if (!anyProcNameCheckAllLevels(name)) {
                // Declare variable arg proc
                addProc(name, newProc);
            } else {
                throw b.generateExecuteException("Error multiple proc <"+name+"> cannot distinguished when using variable arguments.");
            }
        } else {
            ProcBaseIf currentProc = getProcNameArgCheckAllLevels(name, newProc.getNumArgs());
                // Declare single arg proc
                addProc(name, newProc);
            if (currentProc == null) {
            } else {
                // Exact match
                throw b.generateExecuteException("Error multiple proc <"+name+"> with "+newProc.getNumArgs()+" arguments.");
            }
        }
    }
    
    public ProcBaseIf getProcNameArgCheckAllLevels(String name, int numArgs) {
        // Search the hierarchy of symbol tables
        ProcBaseIf ret = null;
        ProcArg pa = procArgList.get(name);
        if (pa != null) {
            ret = pa.getProc(numArgs);
        }

        if (ret == null) {
            // Not found in current table
            if (parentTable != null) {
                // Search parent
                ret = parentTable.getProcNameArgCheckAllLevels(name, numArgs);
            }
        }
        return ret;
    }    

    private boolean existVarArgProcNameCheckAllLevels(String name) {
        // Search the hierarchy of symbol tables
        boolean exist = false;
        ProcArg pa = procArgList.get(name);
        if (pa != null) {
            exist = pa.getHasVariArgs();
        }

        if (!exist) {
            // Not found in current table
            if (parentTable != null) {
                // Search parent
                exist = parentTable.existVarArgProcNameCheckAllLevels(name);
            }
        }
        return exist;
    }    

    private boolean anyProcNameCheckAllLevels(String name) {
        // Search the hierarchy of symbol tables
        boolean any = false;
        ProcArg pa = procArgList.get(name);
        if (pa != null) {
            any = true;
        }

        if (!any) {
            // Not found in current table
            if (parentTable != null) {
                // Search parent
                any = parentTable.anyProcNameCheckAllLevels(name);
            }
        }
        return any;
    }    

//    public VarBase getVarCheckAllLevels(String name) {
//        // Search the hierarchy of symbol tables
//        VarBase ret = varList.get(name);
//        if (ret == null) {
//            // Not found in current table
//            if (parentTable != null) {
//                // Search parent
//                ret = parentTable.getVarCheckAllLevels(name);
//            }
//        }
//        return ret;
//    }    

    public VarContainer getContainerCheckThisLevel(String name) {
        // Only search current table
        VarContainer ret = contList.get(name);
        return ret;
    }    

    public void putContainer(String name, VarContainer newVar) {
        // Store in current table
        contList.put(name, newVar);
    }

//    public String getVarName(VarBase obj){
//        Iterator i = varList.entrySet().iterator();
//        while(i.hasNext()) {
//            HashMap.Entry pair = (HashMap.Entry)i.next();
//            VarBase base = (VarBase)pair.getValue();
//            if (base == obj) {
//                return ""+pair.getKey();
//            }
//        }
//        if (parentTable != null) {
//            // Try parent
//            parentTable.getVarName(obj);
//        }
//        return "???";
//    }
    
    public String printString() {
        String ret = "<SymbolTable("+level+") \n";
        Iterator i = contList.entrySet().iterator();
        while(i.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)i.next();
            VarContainer base = (VarContainer)pair.getValue();
            ret += "<Content of id("+pair.getKey()+")\n";
            if (base != null) {
                ret += base.printString();
            } else {
                ret += "NULL_PTR";
            }
            ret += ">\n";
        }
        ret += ">\n";
        return ret;
    }
    
    public int getContainerStackSizeThisLevel() {
        return containerStack.size();
    }
    
    public void pushContainerThisLevel(VarContainer c) {
        containerStack.push(c);
    }
    
    public VarContainer popContainerThisLevel(ASTRcdBase b)  throws ExecuteException {
        VarContainer var = null;
        try {
            var = containerStack.pop();
        } catch (Exception ex) {
            if (b != null) {
                throw b.generateExecuteException("Error popContainerThisLevel failed : No value on stack.\n" + ex);
            }
        }
        return var;
    }
    
}
