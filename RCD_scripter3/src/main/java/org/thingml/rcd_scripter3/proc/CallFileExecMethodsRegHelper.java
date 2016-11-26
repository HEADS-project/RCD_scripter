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
package org.thingml.rcd_scripter3.proc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ASTRcdBase.ExecResult;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarBool;
import org.thingml.rcd_scripter3.variables.VarContainer;
import org.thingml.rcd_scripter3.variables.VarFile;
import org.thingml.rcd_scripter3.variables.VarInt;
import org.thingml.rcd_scripter3.variables.VarReal;
import org.thingml.rcd_scripter3.variables.VarString;

/**
 *
 * @author steffend
 */
public class CallFileExecMethodsRegHelper implements ProcBaseIf {
    private String methodName;
    private int numArgs;
    private boolean variArgs;

    public CallFileExecMethodsRegHelper(String methodName, int numArgs, boolean variArgs) throws Exception{
        this.methodName = methodName.toLowerCase();
        this.numArgs = numArgs;
        this.variArgs = variArgs;
    }

    public int getNumArgs() {
        return numArgs;
    }
    
    public boolean acceptNumArgs(int numArgs){
        if (variArgs) return true;
        return numArgs == this.numArgs;
    }
    
    public boolean isVariArgs(){
        return variArgs;
    }
        
    public ExecResult executeMethod(ExecuteContext ctx, ASTRcdBase callersBase, VarContainer varInst, VarContainer[] args) throws ExecuteException {
        VarFile vf = (VarFile)varInst.getInst();

        vf.executeMethods(ctx, callersBase, methodName, args);
        return ExecResult.NORMAL;
    }

    public ExecResult executeProc(ExecuteContext ctx, ASTRcdBase callersBase, String procId, VarContainer[] args) throws ExecuteException {
        throw callersBase.generateExecuteException("ERROR method "+methodName+"() cannot be called as procedure.");
    }
}
