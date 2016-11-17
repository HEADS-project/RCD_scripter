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
import org.thingml.rcd_scripter3.variables.VarContainer;

public class ASTRcdAssign extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdAssign(int id) {
      super(id);
    }
    
    @Override
    public ExecResult execute(ExecuteContext ctx) throws ExecuteException {
        // Expect the left container to be on top of the stack
        // Execute the expression and assign the result to the container
        VarContainer leftContainer = ctx.popContainer(this);
        ExecResult ret;

        if (numChildren() == 1) {
            ret = executeChildren(ctx);
            VarContainer rightContainer = ctx.popContainer(this);
            leftContainer.setInst(rightContainer.getInst());
        } else {
            throw generateExecuteException("ERROR Assign got <"+numChildren()+"> children, expected 1.");
        }
        return ret;
    }
}
