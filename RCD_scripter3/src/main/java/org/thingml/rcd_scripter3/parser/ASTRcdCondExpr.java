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
import org.thingml.rcd_scripter3.variables.VarContainer;

public class ASTRcdCondExpr extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdCondExpr(int id) {
      super(id);
    }

    @Override
    public ExecResult execute(ExecuteContext ctx) throws ExecuteException {
        ExecResult ret = ExecResult.NORMAL;
        ASTRcdBase c = null;
        VarContainer test;
        switch (numChildren()) {
            case 3: // Expr ? Expr : Expr
                c = (ASTRcdBase) children[0];
                c.execute(ctx);
                test = ctx.popContainer(this);
                if (test.boolVal()) {
                    c = (ASTRcdBase) children[1];
                    ret = c.execute(ctx);
                } else {
                    c = (ASTRcdBase) children[2];
                    ret = c.execute(ctx);
                }
                break;
            default:
                throw generateExecuteException("ERROR CondExpr with <"+numChildren()+"> child nodes");
        }
        return ret;
    }
}
