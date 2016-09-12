
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarBase;

public class ASTRcdPrint extends ASTRcdBase {

  /**
   * Constructor.
   * @param id the id
   */
  public ASTRcdPrint(int id) {
    super(id);
  }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        executeChildren(ctx);
        VarBase text = ctx.popVar(this);
        String printString = text.getString();
        System.out.print("PRINT:"+printString);
    }
			
}
