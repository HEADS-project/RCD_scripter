
package org.thingml.rcd_scripter3.parser;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarValueBase;
import org.thingml.rcd_scripter3.variables.VarValueString;

public class ASTRcdDefValue extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdDefValue(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        int children = executeChildren(ctx);
        VarValueBase newValue;
        if (children > 0) {
            VarValueBase initVarValue = ctx.popVarX(this, VarValueBase.class);
            newValue = initVarValue;
        } else {
            newValue = new VarValueString("NULL("+name+")");
        }
        ctx.addVar(name, newValue);
    }

}


