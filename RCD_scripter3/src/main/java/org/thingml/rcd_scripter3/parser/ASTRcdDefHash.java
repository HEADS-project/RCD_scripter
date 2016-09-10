
package org.thingml.rcd_scripter3.parser;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarHash;

public class ASTRcdDefHash extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdDefHash(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        boolean hasInit = executeChildren(ctx);
        VarHash newHash;
        if (hasInit) {
            VarBase init = ctx.popVar();
            newHash = new VarHash();
        } else {
            newHash = new VarHash();
        }
    }

}


