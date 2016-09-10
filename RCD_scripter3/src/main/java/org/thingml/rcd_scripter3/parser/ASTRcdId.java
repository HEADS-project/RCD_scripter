
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarId;

public class ASTRcdId extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdId(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        String image = getName();

        ctx.pushVar(new VarId(image));
    }

}
