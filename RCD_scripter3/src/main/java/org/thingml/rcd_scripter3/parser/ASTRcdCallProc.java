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
import org.thingml.rcd_scripter3.variables.VarId;

public class ASTRcdCallProc extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdCallProc(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        boolean found = false;
        int baseStackSize = ctx.getVarStackSize();
        
        executeChildren(ctx);
        int addedStackElems = ctx.getVarStackSize() - baseStackSize;
        int argNum = addedStackElems-1;
        VarBase[] args = new VarBase[argNum];
        for (int i = 0; i < argNum; i++) {
            args[argNum - i - 1] = ctx.popVar(this);
        }
        
        VarId id = ctx.popVarX(this, VarId.class);
        
        String proc = id.getString();

        if (proc.equalsIgnoreCase("print")) {
            found = true;
            if (argNum == 1) {
                VarBase arg = args[0];
                System.out.print(arg.getString());
            } else {
                throw generateExecuteException("ERROR function print() accepts 1 arg given "+argNum+" arg(s)");
            }
        }

        if (proc.equalsIgnoreCase("println")) {
            found = true;
            if (argNum == 1) {
                VarBase arg = args[0];
                System.out.println(arg.getString());
            } else if (argNum == 0) {
                System.out.println();
            } else {
                throw generateExecuteException("ERROR method println() accepts 1 arg given "+argNum+" arg(s)");
            }
        }

        if (!found) {
            throw generateExecuteException("ERROR method <"+proc+"> is not defined");
        }
    }
}
