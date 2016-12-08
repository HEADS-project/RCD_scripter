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

public class ASTRcdWhileBlock extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdWhileBlock(int id) {
      super(id);
    }

    @Override
    public ExecResult execute(ExecuteContext ctx) throws ExecuteException {
        ExecResult execRet = ExecResult.NORMAL;
        ExecResult scriptRet = ExecResult.NORMAL;
        ASTRcdBase testExpr = null;
        VarContainer testRes;
        ASTRcdBase loopScript = null;
        switch (numChildren()) {
            case 2: // WHILE (test) script
                testExpr = (ASTRcdBase) children[0];
                loopScript = (ASTRcdBase) children[1];
                if (!loopScript.getName().contentEquals("TrueScript")) {
                    throw generateExecuteException("ERROR WhileBlock cannot find true-script got <"+loopScript.getName()+"><"+loopScript.getClass().getName()+">");
                }
                testExpr.execute(ctx);
                testRes = ctx.popContainer(this);
                while (testRes.boolVal()) {
                    scriptRet = loopScript.execute(ctx);
                    if (scriptRet == ExecResult.BREAK_LOOP) break;
                    if (scriptRet == ExecResult.RETURN_PROC) { execRet = scriptRet; break;}
                    if (scriptRet == ExecResult.EXIT_PROGRAM) { execRet = scriptRet; break;}
                    testExpr.execute(ctx);
                    testRes = ctx.popContainer(this);
                }
                break;
            default:
                throw generateExecuteException("ERROR WhileBlock with <"+numChildren()+"> child nodes");
        }
        return execRet;
    }
}
