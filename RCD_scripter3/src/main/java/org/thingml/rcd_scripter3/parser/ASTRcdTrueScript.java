
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;

public class ASTRcdTrueScript extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdTrueScript(int id) {
      super(id);
    }

    public void execute(ExecuteContext ctx) throws ExecuteException {
        executeChildren(ctx);
    }

}
