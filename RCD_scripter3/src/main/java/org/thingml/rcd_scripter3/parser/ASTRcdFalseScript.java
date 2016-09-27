
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;

public class ASTRcdFalseScript extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdFalseScript(int id) {
      super(id);
    }

    public void execute(ExecuteContext ctx) throws ExecuteException {
        executeChildren(ctx);
    }
			

}
