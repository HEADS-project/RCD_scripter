
package org.thingml.rcd_scripter3.parser;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarHash;

public class ASTRcdDeclHash extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdDeclHash(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        int children = executeChildren(ctx);
        VarHash newHash;
        if (children > 0) {
            VarHash initHash = ctx.popVarX(this, VarHash.class);
            newHash = new VarHash(initHash);
        } else {
            newHash = new VarHash();
        }
        ctx.declVar(this, name, newHash);
    }

}


