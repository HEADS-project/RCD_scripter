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
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.proc.ProcBaseIf;
import org.thingml.rcd_scripter3.variables.VarBase;

/**
 *
 * @author steffend
 */
public class SymbolTable {
    private SymbolTable parentTable = null;
    private int level = 0;
    private HashMap<String, VarBase> varList = new HashMap<String, VarBase>();
    private HashMap<String, ProcBaseIf> procList = new HashMap<String, ProcBaseIf>();
    
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
    
    public void declProc(ASTRcdBase b, String name, ProcBaseIf newProc) throws ExecuteException {
        // Store in top symbol table
        if (parentTable == null) {
            procList.put(name, newProc);
        } else {
            throw b.generateExecuteException("Error declProc failed : PROC can only be declared on top level\n");    
        }
    }

    public ProcBaseIf getProcCheckTopLevel(String name) {
        ProcBaseIf ret = null;
        if (parentTable != null) {
            // Search parent
            ret = parentTable.getProcCheckTopLevel(name);
        } else {
            ret = procList.get(name);
        }
        return ret;
    }    

    public VarBase getVarCheckAllLevels(String name) {
        // Search the hierarchy of symbol tables
        VarBase ret = varList.get(name);
        if (ret == null) {
            // Not found in current table
            if (parentTable != null) {
                // Search parent
                ret = parentTable.getVarCheckAllLevels(name);
            }
        }
        return ret;
    }    

    public VarBase getVarCheckThisLevel(String name) {
        // Search the hierarchy of symbol tables
        VarBase ret = varList.get(name);
        return ret;
    }    

    public void declVar(String name, VarBase newVar) {
        // Store in current symbol table
        varList.put(name, newVar);
    }

    public void assignVar(String name, VarBase newVar) {
        // Store in symbol table where it is declared
        VarBase currVal = varList.get(name);
        if (currVal != null) {
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
