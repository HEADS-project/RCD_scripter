
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarValueInt;

public class ASTRcdIntNum extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdIntNum(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws Exception {
        String image = getName();

        ctx.pushVar(new VarValueInt(image));
    }
  
}
