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

import java.util.List;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.proc.ProcBaseIf;
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarId;

public class ASTRcdDeclProc extends ASTRcdBase implements ProcBaseIf {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdDeclProc(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
    }

    public VarBase executeProc(ExecuteContext ctx, ASTRcdBase callersBase, List<VarBase> args) throws ExecuteException {
        VarBase ret = null;

        if (children == null) throw generateExecuteException("ERROR procedure declaration without parameters");
        
        // Fetch params and push into symtab
        ctx.blockStart();
        VarBase valueElem = varValArray.getValue(i);
        ctx.declVar(this, loopVarName, valueElem);
        script.execute(ctx);
        ctx.blockEnd();
        
        return ret;
    }
}
