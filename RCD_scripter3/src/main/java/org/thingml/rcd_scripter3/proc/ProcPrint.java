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
public class ProcPrint implements ProcBaseIf {
    public ProcPrint(ExecuteContext ctx) {
        try {
            ctx.declProc(null, "print", this);
            ctx.declProc(null, "PRINT", this);
            ctx.declProc(null, "println", this);
            ctx.declProc(null, "PRINTLN", this);
        } catch (ExecuteException ex) {
            Logger.getLogger(ProcPrint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ExecResult executeProc(ExecuteContext ctx, ASTRcdBase callersBase, String procId, VarBase[] args) throws ExecuteException {
        int argNum = args.length;
        
        // Fetch params and push into symtab
        if (procId.equalsIgnoreCase("print")) {
            if (argNum == 1) {
                VarBase arg = args[0];
                System.out.print(arg.getString());
            } else {
                throw callersBase.generateExecuteException("ERROR function print() accepts 1 arg given "+argNum+" arg(s)");
            }
        } else if (procId.equalsIgnoreCase("println")) {
            if (argNum == 1) {
                VarBase arg = args[0];
                System.out.println(arg.getString());
            } else if (argNum == 0) {
                System.out.println();
            } else {
                throw callersBase.generateExecuteException("ERROR method println() accepts 1 arg given "+argNum+" arg(s)");
            }
        } else {
            callersBase.generateExecuteException("ERROR procedure <"+procId+"> is not defined expected <print, println>");
        }
        return ExecResult.NORMAL;
    }
    
}
