
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
    public void execute(ExecuteContext ctx) throws ExecuteException {
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
                        if (c instanceof ASTRcdTrueScript) {
                            ctx.blockStart();
                            c.execute(ctx);
                            ctx.blockEnd();
                        } else {
                            throw generateExecuteException("ERROR IfBlock cannot find true-script got <"+c.getClass().getName()+">");
                        }
                    }
                    break;
                case 3: // IF (test) script ELSE script
                    c = (ASTRcdBase) children[0];
                    c.execute(ctx);
                    test = ctx.popVarX(this, VarValueBool.class);
                    if (test.getBool()) {
                        c = (ASTRcdBase) children[1];
                        if (c instanceof ASTRcdTrueScript) {
                            ctx.blockStart();
                            c.execute(ctx);
                            ctx.blockEnd();
                        } else {
                            throw generateExecuteException("ERROR IfBlock cannot find true-script got <"+c.getClass().getName()+">");
                        }
                    } else {
                        c = (ASTRcdBase) children[2];
                        if (c instanceof ASTRcdFalseScript) {
                            ctx.blockStart();
                            c.execute(ctx);
                            ctx.blockEnd();
                        } else {
                            throw generateExecuteException("ERROR IfBlock cannot find false-script got <"+c.getClass().getName()+">");
                        }
                    }
                    break;
                default:
                    throw generateExecuteException("ERROR IfBlock with <"+children.length+"> child nodes");
            }
        } else {
            throw generateExecuteException("ERROR IfBlock without parameters");
        }
        return ;
    }
}
