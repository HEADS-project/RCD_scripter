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
package org.thingml.rcd_scripter3.variables;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ASTRcdBase.ExecResult;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.parser.Token;
import org.thingml.rcd_scripter3.proc.ProcBaseIf;

/**
 *
 * @author steffend
 */
abstract public class VarBase implements ProcBaseIf {
    public enum VarType { KEYVALUE, HASHLIST, ID, HASH, INT, STRING, BOOL, FILE, VALARRAY, VOID };

    abstract public String getString();
    abstract public String printString();
    abstract public String getTypeString();
    abstract public VarType getType();
    
    public VarBase fetchFromIndex(ASTRcdBase b, VarBase idx) throws ExecuteException {
        throw b.generateExecuteException("ERROR indexing not supported for "+getTypeString());
    }
    
    public void storeToIndex(ASTRcdBase b, VarBase idx, VarBase expr) throws ExecuteException {
        throw b.generateExecuteException("ERROR indexing not supported for "+getTypeString());
    }

    public ExecResult executeProc(ExecuteContext ctx, ASTRcdBase callersBase, String methodId, VarBase[] args) throws ExecuteException {
        throw callersBase.generateExecuteException("ERROR method <"+methodId+"> is not defined for type <"+getTypeString()+">");
    }
    
    public Object getValObj(){
        Object ret;
        
        ret = getString();
        return ret;
    }
}
