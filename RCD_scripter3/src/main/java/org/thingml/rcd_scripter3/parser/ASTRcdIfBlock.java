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
import org.thingml.rcd_scripter3.variables.VarValueBool;

public class ASTRcdIfBlock extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdIfBlock(int id) {
      super(id);
    }

    @Override
    public ExecResult execute(ExecuteContext ctx) throws ExecuteException {
        ExecResult ret = ExecResult.NORMAL;
        ASTRcdBase c = null;
        VarValueBool test;
        if (children != null) {
            switch (children.length) {
                case 2: // IF (test) script
                    c = (ASTRcdBase) children[0];
                    c.execute(ctx);
                    test = ctx.popVarX(this, VarValueBool.class);
                    if (test.getBool()) {
                        c = (ASTRcdBase) children[1];
                        if (c.getName().contentEquals("TrueScript")) {
                            ctx.blockStart();
                            ret = c.execute(ctx);
                            ctx.blockEnd();
                        } else {
                            throw generateExecuteException("ERROR IfBlock cannot find true-script got <"+c.getName()+"><"+c.getClass().getName()+">");
                        }
                    }
                    break;
                case 3: // IF (test) script ELSE script
                    c = (ASTRcdBase) children[0];
                    c.execute(ctx);
                    test = ctx.popVarX(this, VarValueBool.class);
                    if (test.getBool()) {
                        c = (ASTRcdBase) children[1];
                        if (c.getName().contentEquals("TrueScript")) {
                            ctx.blockStart();
                            ret = c.execute(ctx);
                            ctx.blockEnd();
                        } else {
                            throw generateExecuteException("ERROR IfBlock cannot find true-script got <"+c.getName()+"><"+c.getClass().getName()+">");
                        }
                    } else {
                        c = (ASTRcdBase) children[2];
                        if (c.getName().contentEquals("FalseScript")) {
                            ctx.blockStart();
                            ret = c.execute(ctx);
                            ctx.blockEnd();
                        } else {
                            throw generateExecuteException("ERROR IfBlock cannot find false-script got <"+c.getName()+"><"+c.getClass().getName()+">");
                        }
                    }
                    break;
                default:
                    throw generateExecuteException("ERROR IfBlock with <"+children.length+"> child nodes");
            }
        } else {
            throw generateExecuteException("ERROR IfBlock without parameters");
        }
        return ret;
    }
}
