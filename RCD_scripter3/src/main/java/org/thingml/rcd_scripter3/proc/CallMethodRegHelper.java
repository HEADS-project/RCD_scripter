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
public class CallMethodRegHelper implements ProcBaseIf {
    public enum InstClass {VARINST, STRING};
    private InstClass instClass;
    private Class<?> c;
    private Class[] formalArgs;
    private String myName;
    private Method method;

    public CallMethodRegHelper(String myName, Class<?> c, InstClass instClass, String methodName, Class[] formalArgs) throws Exception{
        this.instClass = instClass;
        this.myName = myName.toLowerCase();
        this.c = c;
        this.formalArgs = formalArgs;
        method = c.getMethod(methodName, formalArgs);
    }

    public int getNumArgs() {
        return formalArgs.length;
    }
    
    public boolean acceptNumArgs(int numArgs){
        return numArgs == formalArgs.length;
    }
    
    public boolean isVariArgs(){
        return false;
    }
    
    public static void printMethods(String fullClassName) {
        try {
            Class<?> c = Class.forName(fullClassName);
            Method[] methods = c.getMethods();
            for (int i = 0; i < methods.length; i++) {
                System.out.println(methods[i]);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR : "+ex);
        }
    }
    
    public ExecResult executeMethod(ExecuteContext ctx, ASTRcdBase callersBase, VarContainer varInst, VarContainer[] args) throws ExecuteException {
        Object ret = null;
        VarContainer retCont = new VarContainer();
        Object inst = null;
        
        switch (instClass) {
            case VARINST:
                inst = varInst.getInst();
                ((VarBase)inst).setCallersBase(callersBase);
                break;
            case STRING:
                inst = varInst.stringVal();
                break;
        }  

        if (args.length == formalArgs.length) {
            Object[] actArg = new Object[formalArgs.length];
            for (int i = 0; i < formalArgs.length; i++) {
                if (formalArgs[i].isAssignableFrom(args[i].getInst().getClass())) {
                    actArg[i] = args[i].getInst();
                } else if (formalArgs[i].isAssignableFrom(args[i].getValObj().getClass())) {
                    actArg[i] = args[i].getValObj();
                } else {
                    throw callersBase.generateExecuteException("ERROR method "+myName+"() param "+i+" got <"+args[i].getInst().getClass().getSimpleName()+"> expected <"+formalArgs[i].getClass().getSimpleName()+">");
                }
            }
            try {
                ret = method.invoke(inst, actArg);
            } catch (IllegalAccessException ex) {
                throw callersBase.generateExecuteException("ERROR method "+myName+"() call failed\n"+ex);
            } catch (IllegalArgumentException ex) {
                throw callersBase.generateExecuteException("ERROR method "+myName+"() call failed\n"+ex);
            } catch (InvocationTargetException ex) {
                Throwable cause = ex.getCause();
                System.out.format("Invocation of %s failed because of: %s%n",
                            myName, cause.getMessage());
                throw callersBase.generateExecuteException("ERROR method "+myName+"() call failed\n"+ex);
            }
        } else {
            throw callersBase.generateExecuteException("ERROR method "+myName+"() accepts "+formalArgs.length+" given "+args.length+" arg(s)");
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
