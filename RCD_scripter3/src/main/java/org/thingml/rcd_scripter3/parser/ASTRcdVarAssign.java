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
import org.thingml.rcd_scripter3.variables.VarValueBase;

public class ASTRcdVarAssign extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdVarAssign(int id) {
      super(id);
    }
    
    @Override
    public boolean execute(ExecuteContext ctx) throws ExecuteException {
        boolean execContinue = true;
        VarBase expr;

        int baseStackSize = ctx.getVarStackSize();
        executeChildren(ctx);
        int addedStackElems = ctx.getVarStackSize() - baseStackSize;

        switch (addedStackElems) {
            case 1: // No index, only expression
                expr = ctx.popVar(this);
                ctx.assignVar(this, name, expr);
                break;
            case 2: // Index and expression
                VarBase vb = ctx.getVarBase( this, name);
                expr = ctx.popVar(this);
                VarBase idx = ctx.popVar(this);
                vb.storeToIndex(this, idx, expr);
                break;
            default:
                throw generateExecuteException("ERROR VarAssign statement got <"+addedStackElems+"> expressions, expected 1 or 2.");
        }
        return execContinue;
    }
}
