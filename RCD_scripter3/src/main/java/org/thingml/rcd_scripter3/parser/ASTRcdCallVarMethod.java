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
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarFile;
import org.thingml.rcd_scripter3.variables.VarHash;
import org.thingml.rcd_scripter3.variables.VarHashList;
import org.thingml.rcd_scripter3.variables.VarId;
import org.thingml.rcd_scripter3.variables.VarValArray;
import org.thingml.rcd_scripter3.variables.VarValueBase;
import org.thingml.rcd_scripter3.variables.VarValueInt;
import org.thingml.rcd_scripter3.variables.VarValueString;

public class ASTRcdCallVarMethod extends ASTRcdBase {

    private boolean returnValue = false;
    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdCallVarMethod(int id) {
      super(id);
    }

    public void setReturnValue(boolean val) {
        returnValue = val;
    }
    
    @Override
    public boolean execute(ExecuteContext ctx) throws ExecuteException {
        boolean execContinue = true;
        int baseStackSize = ctx.getVarStackSize();
        
        executeChildren(ctx);
        int addedStackElems = ctx.getVarStackSize() - baseStackSize;
        int argNum = addedStackElems-2;
        VarBase[] args = new VarBase[argNum];
        for (int i = 0; i < argNum; i++) {
            args[argNum - i - 1] = ctx.popVar(this);
        }
        
        VarId id = ctx.popVarX(this, VarId.class);
        VarBase var = ctx.popVar(this);
        
        String methodId = id.getString();

        VarBase ret;
        ret = var.executeProc(ctx, this, methodId, args);
        if (returnValue) ctx.pushVar(ret);
        return execContinue;
    }
}
