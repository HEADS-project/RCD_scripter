
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarKeyValue;
import org.thingml.rcd_scripter3.variables.VarValueBase;

public class ASTRcdTupl extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdTupl(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        executeChildren(ctx);
        VarValueBase val = ctx.popVarX(this);
        VarBase key = ctx.popVar();
        
        VarKeyValue kv = new VarKeyValue(key.getString(), val);
        
        ctx.pushVar(kv);
    }
  
    

}
