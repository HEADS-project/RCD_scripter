
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarBase;

public class ASTRcdVar extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdVar(int id) {
      super(id);
    }
    
    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        VarBase vb = ctx.getVarBase( this, name);
        if (executeChildren(ctx) == 0) {
            ctx.pushVar(vb);
        } else {
            ctx.pushVar(vb.fetchFromIndex(this, ctx.popVar()));
        };
    }
    

}
