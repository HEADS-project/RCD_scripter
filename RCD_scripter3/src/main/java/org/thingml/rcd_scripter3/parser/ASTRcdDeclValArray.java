
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarValArray;

public class ASTRcdDeclValArray extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdDeclValArray(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        int children = executeChildren(ctx);
        VarValArray newValArray;
        if (children > 0) {
            VarValArray initValArray = ctx.popVarX(this, VarValArray.class);
            newValArray = new VarValArray(initValArray);
        } else {
            newValArray = new VarValArray();
        }
        ctx.declVar(this, name, newValArray);
    }

}
