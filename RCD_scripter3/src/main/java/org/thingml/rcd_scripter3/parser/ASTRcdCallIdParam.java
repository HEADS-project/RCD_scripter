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

package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.proc.ProcBaseIf;
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarContainer;
import org.thingml.rcd_scripter3.variables.VarString;

public class ASTRcdCallIdParam extends ASTRcdBase {

    private boolean returnValue = false;
    private String  idString = "";
    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdCallIdParam(int id) {
      super(id);
    }

    public void setReturnValue(boolean val) {
        returnValue = val;
    }
    
    public void setIdString(String id) {
        this.idString = id;
    }
    
    @Override
    public ExecResult execute(ExecuteContext ctx) throws ExecuteException {
        ExecResult ret;
        int baseStackSize = ctx.getContainerStackSize();
        
        ret = executeChildren(ctx);
        
        int addedStackElems = ctx.getContainerStackSize() - baseStackSize;
        int argNum = addedStackElems;
        VarContainer[] args = new VarContainer[argNum];
        for (int i = 0; i < argNum; i++) {
            args[argNum - i - 1] = ctx.popContainer(this);
        }
        
        VarContainer var = ctx.popContainer(this);
        // Var is a variable that can be an object with method idString
        //      or variable is an array with key idString that is a proc

        if (ret != ExecResult.NORMAL) return ret;
        
        //String procId = id.stringVal();
        
        // Try to fetch index idString from variable and use it as a id in a proc call
        // If no id is registered getProcBase will be throw an exception 
        try {
            VarContainer procId = var.fetchFromIndex(this, new VarContainer(new VarString(idString)));
            ProcBaseIf proc = ctx.getProcBase(this, procId.stringVal(), args.length);
            ret = proc.executeProc(ctx, this, idString, args);
            if (!returnValue) {
                ctx.popContainer(this); // Remove from stack if not used by caller
            }
            return ret;
        } catch (ExecuteException ex) {
            // No proc registered with its name registered at the index
        }
        
        String methodId = var.getType().toString() +":"+ idString;

        ProcBaseIf method = ctx.getProcBase(this, methodId, args.length);
        ret = method.executeMethod(ctx, this, var, args);
        if (!returnValue) {
            ctx.popContainer(this); // Remove from stack if not used by caller
        }

        return ret;
    }
}
