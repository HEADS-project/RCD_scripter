
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarHash;
import org.thingml.rcd_scripter3.variables.VarKeyValue;

public class ASTRcdHash extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdHash(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws Exception {
        VarHash hash = new VarHash();
        VarKeyValue kv;
        
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                ASTRcdBase c = (ASTRcdBase) children[i];
                c.execute(ctx);
                kv = (VarKeyValue) ctx.popVar();
                hash.addKeyValue(kv);
            }
        }
        
        ctx.pushVar(hash);
    }
    

}
