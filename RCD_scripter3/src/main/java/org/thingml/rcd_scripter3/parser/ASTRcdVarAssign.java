
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
    public void execute(ExecuteContext ctx) throws ExecuteException {
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
    }
}
