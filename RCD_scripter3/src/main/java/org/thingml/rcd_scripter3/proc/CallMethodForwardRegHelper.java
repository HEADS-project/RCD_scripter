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
import org.thingml.rcd_scripter3.variables.VarInt;
import org.thingml.rcd_scripter3.variables.VarReal;
import org.thingml.rcd_scripter3.variables.VarString;

/**
 *
 * @author steffend
 */
public class CallMethodForwardRegHelper implements ProcBaseIf {
    private Class<?> c;
    private String myName;
    private Method method;

    public CallMethodForwardRegHelper(String myName, Class<?> c, String methodName) throws Exception{
        this.myName = myName.toLowerCase();
        this.c = c;
        method = c.getMethod(methodName, new Class[] {ExecuteContext.class, ASTRcdBase.class, VarContainer[].class});
    }

    public int getNumArgs() {
        return 0;
    }
    
    public boolean acceptNumArgs(int numArgs){
        return true;
    }
    
    public boolean isVariArgs(){
        return true;
    }
        
    public ExecResult executeMethod(ExecuteContext ctx, ASTRcdBase callersBase, VarContainer varInst, VarContainer[] args) throws ExecuteException {
        Object ret = null;
        VarContainer retCont = new VarContainer();
        Object inst = null;
        
        inst = varInst.getInst();

        Object[] actArg = new Object[3];
        actArg[0] = ctx;
        actArg[1] = callersBase;
        actArg[2] = args;
        
        try {
            ret = method.invoke(inst, actArg);
        } catch (IllegalAccessException ex) {
            throw callersBase.generateExecuteException("ERROR method "+myName+"() call failed\n"+ex);
        } catch (IllegalArgumentException ex) {
            throw callersBase.generateExecuteException("ERROR method "+myName+"() call failed\n"+ex);
        } catch (InvocationTargetException ex) {
            throw callersBase.generateExecuteException("ERROR method "+myName+"() call failed\n"+ex);
        }
        if (ret != null) {
            if (VarBase.class.isAssignableFrom(ret.getClass())) {
                retCont.setInst((VarBase)ret); // Return value
            } else if(ret instanceof VarContainer) {
                retCont = (VarContainer)ret; // Return value
            } else if(ret instanceof Boolean) {
                retCont.setInst(new VarBool(""+ret)); // Return value
            } else if(ret instanceof Integer) {
                retCont.setInst(new VarInt(""+ret)); // Return value
            } else if(ret instanceof Long) {
                retCont.setInst(new VarInt(""+ret)); // Return value
            } else if(ret instanceof String) {
                retCont.setInst(new VarString(""+ret)); // Return value
            } else if(ret instanceof Double) {
                retCont.setInst(new VarReal(""+ret)); // Return value
            } else if(ret instanceof Float) {
                retCont.setInst(new VarReal(""+ret)); // Return value
            }
        }
        ctx.pushContainer(retCont);
        return ExecResult.NORMAL;
    }

    public ExecResult executeProc(ExecuteContext ctx, ASTRcdBase callersBase, String procId, VarContainer[] args) throws ExecuteException {
        throw callersBase.generateExecuteException("ERROR method "+myName+"() cannot be called as procedure.");
    }
}
