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
import org.thingml.rcd_scripter3.variables.VarContainer;
import org.thingml.rcd_scripter3.variables.VarInt;

public class ASTRcdCallMethod extends ASTRcdBase {

    private boolean returnValue = false;
    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdCallMethod(int id) {
      super(id);
    }

    public void setReturnValue(boolean val) {
        returnValue = val;
    }
    
    @Override
    public ExecResult execute(ExecuteContext ctx) throws ExecuteException {
        ExecResult ret;
        VarContainer var = ctx.popContainer(this);
  
        int baseStackSize = ctx.getContainerStackSize();
        ret = executeChildren(ctx);
        int addedStackElems = ctx.getContainerStackSize() - baseStackSize;
        int argNum = addedStackElems-1;

        VarContainer[] args = new VarContainer[argNum];
        for (int i = 0; i < argNum; i++) {
            args[argNum - i - 1] = ctx.popContainer(this);
        }
        
        VarContainer id = ctx.popContainer(this);

        if (ret != ExecResult.NORMAL) return ret;

        String methodId = var.getType().toString() +":"+ id.stringVal();

        ProcBaseIf method = ctx.getProcBase(this, methodId, args.length);
        if (method != null) {
            ret = method.executeMethod(ctx, this, var, args);
            if (!returnValue) {
                ctx.popContainer(this); // Remove from stack if not used by caller
            }
        } else {
            throw generateExecuteException("ERROR method <"+methodId+"> is not defined");
        }

        return ret;
    }
}
