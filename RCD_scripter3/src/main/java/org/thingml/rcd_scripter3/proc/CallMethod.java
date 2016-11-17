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
public class CallMethod {
    private Class<?> c;
    private Class[] formalArgs;
    private String myName;
    private Method method;

    public CallMethod(String myName, Class<?> c, String methodName, Class[] formalArgs) throws Exception{
        this.myName = myName;
        this.c = c;
        this.formalArgs = formalArgs;
        method = c.getMethod(methodName, formalArgs);
    }

    public void Call(ExecuteContext ctx, ASTRcdBase callersBase, Object inst, VarBase[] args) throws ExecuteException {
        Object ret = null;
        if (args.length == formalArgs.length) {
            Object[] actArg = new Object[formalArgs.length];
            for (int i = 0; i < formalArgs.length; i++) {
                if (formalArgs[i].isAssignableFrom(args[i].getClass())) {
                    actArg[i] = args[i];
                } else if (formalArgs[i].isAssignableFrom(args[i].getValObj().getClass())) {
                    actArg[i] = args[i].getValObj();
                } else {
                    throw callersBase.generateExecuteException("ERROR method "+myName+"() param "+i+" got <"+args[i].getClass().getSimpleName()+"> expected <"+formalArgs[i].getClass().getSimpleName()+">");
                }
            }
            try {
                ret = method.invoke(inst, actArg);
            } catch (IllegalAccessException ex) {
                throw callersBase.generateExecuteException("ERROR method "+myName+"() call failed\n"+ex);
            } catch (IllegalArgumentException ex) {
                throw callersBase.generateExecuteException("ERROR method "+myName+"() call failed\n"+ex);
            } catch (InvocationTargetException ex) {
                throw callersBase.generateExecuteException("ERROR method "+myName+"() call failed\n"+ex);
            }
        } else {
            throw callersBase.generateExecuteException("ERROR method "+myName+"() accepts "+formalArgs.length+" given "+args.length+" arg(s)");
        }
        if (ret != null) {
            if (VarContainer.class.isAssignableFrom(ret.getClass())) {
                ctx.pushContainer((VarContainer)ret); // Return value
            } else if(ret instanceof Boolean) {
                ctx.pushContainer(new VarContainer (new VarBool(""+ret))); // Return value
            } else if(ret instanceof Integer) {
                ctx.pushContainer(new VarContainer (new VarInt(""+ret))); // Return value
            } else if(ret instanceof String) {
                ctx.pushContainer(new VarContainer (new VarString(""+ret))); // Return value
            } else if(ret instanceof Double) {
                ctx.pushContainer(new VarContainer (new VarReal(""+ret))); // Return value
            } else if(ret instanceof Float) {
                ctx.pushContainer(new VarContainer (new VarReal(""+ret))); // Return value
            }
        }
    }
}
