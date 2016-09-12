
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarId;

public class ASTRcdVarMethod extends ASTRcdBase {

  /**
   * Constructor.
   * @param id the id
   */
  public ASTRcdVarMethod(int id) {
    super(id);
  }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        boolean found = false;
        
        executeChildren(ctx);
        VarBase expr = ctx.popVar(this);
        VarId id = ctx.popVarX(this, VarId.class);
        VarBase var = ctx.popVar(this);
        
        String method = id.getString();
        if (method.equalsIgnoreCase("add")) {
            found = true;
            var.add(this, expr);
        }
        
        if (!found) {
            throw generateExecuteException("ERROR method <"+method+"> is not defined");
        }
    }
			
}
