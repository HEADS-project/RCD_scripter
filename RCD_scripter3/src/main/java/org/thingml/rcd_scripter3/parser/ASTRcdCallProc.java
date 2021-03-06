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
import org.thingml.rcd_scripter3.variables.VarId;

public class ASTRcdCallProc extends ASTRcdBase {

    private boolean returnValue = false;
    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdCallProc(int id) {
      super(id);
    }

    public void setReturnValue(boolean val) {
        returnValue = val;
    }
    
    @Override
    public ExecResult execute(ExecuteContext ctx) throws ExecuteException {
        ExecResult ret;
        int baseStackSize = ctx.getVarStackSize();
        
        ret = executeChildren(ctx);
        
        int addedStackElems = ctx.getVarStackSize() - baseStackSize;
        int argNum = addedStackElems-1;
        VarBase[] args = new VarBase[argNum];
        for (int i = 0; i < argNum; i++) {
            args[argNum - i - 1] = ctx.popVar(this);
        }
        
        VarId id = ctx.popVarX(this, VarId.class);

        if (ret != ExecResult.NORMAL) return ret;
        
        String procId = id.getString();

        ProcBaseIf proc = ctx.getProcBase(this, procId);
        if (proc != null) {
            baseStackSize = ctx.getVarStackSize();
            ret = proc.executeProc(ctx, this, procId, args);
            int retStackElems = ctx.getVarStackSize() - baseStackSize; // Does the proc return value
            if (returnValue) {
                if (retStackElems == 0) throw generateExecuteException("ERROR CallProc expected return value");
            } else {
                if (retStackElems > 0) ctx.popVar(this); // Remove from stack if not used by caller
            }
        } else {
            throw generateExecuteException("ERROR method <"+procId+"> is not defined");
        }
        return ret;
    }
}
