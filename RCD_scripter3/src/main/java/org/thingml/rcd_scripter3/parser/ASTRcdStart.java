
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;

public class ASTRcdStart extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdStart(int id) {
      super(id);
    }

    public ASTRcdStart(RcdScript3Parser p, int id) {
      super(p, id);
    }

    public void execute(ExecuteContext ctx) throws ExecuteException {
        executeChildren(ctx);
    }
			

}
