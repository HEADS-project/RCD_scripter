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

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ASTRcdBase.ExecResult;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.variables.VarBase;

/**
 *
 * @author steffend
 */
public class ProcPrintf implements ProcBaseIf {
    
    Method method;
    
    public ProcPrintf(ExecuteContext ctx) {
        try {
            ctx.declProc(null, "printf", this);
            ctx.declProc(null, "PRINTF", this);
            
            try {
                Class<?> c = Class.forName("java.io.PrintStream");
                Class[] cArg = new Class[2];
                cArg[0] = java.lang.String.class;
                cArg[1] = java.lang.Object[].class;
                method = c.getMethod("printf", cArg);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } catch (ExecuteException ex) {
            System.out.println(ex);
        }
    }
    
    public ExecResult executeProc(ExecuteContext ctx, ASTRcdBase callersBase, String procId, VarBase[] args) throws ExecuteException {
        int argNum = args.length;
        
        // Fetch params and push into symtab
        if (procId.equalsIgnoreCase("printf")) {
            if (argNum >= 1) {
                Object[] baseArg = new Object[2];
                Object[] varArg = new Object[argNum-1];
                baseArg[0] = args[0].getValObj();
                baseArg[1] = varArg;
                for (int i = 1; i < args.length; i++) {
                    varArg[i-1] = args[i].getValObj();
                }
                try {
                    method.invoke(System.out, baseArg);
                } catch (Exception ex) {
                    System.out.println(ex);
                    throw callersBase.generateExecuteException("ERROR function printf() wring args <"+ex+">");
                } 

            } else {
                throw callersBase.generateExecuteException("ERROR function printf() accepts >=1  arg given "+argNum+" arg(s)");
            }
        } else {
            callersBase.generateExecuteException("ERROR procedure <"+procId+"> is not defined expected <printf>");
        }
        return ExecResult.NORMAL;
    }
    
}
