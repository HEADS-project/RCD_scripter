
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarHashList;

public class ASTRcdDefHashList extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdDefHashList(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        int children = executeChildren(ctx);
        VarHashList newHashList;
        if (children > 0) {
            VarHashList initHashList = ctx.popVarX(this, VarHashList.class);
            newHashList = new VarHashList(initHashList);
        } else {
            newHashList = new VarHashList();
        }
        ctx.addVar(name, newHashList);
    }
}
