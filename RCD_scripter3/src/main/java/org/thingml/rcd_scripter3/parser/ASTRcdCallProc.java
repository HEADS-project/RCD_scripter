
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarId;

public class ASTRcdCallProc extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdCallProc(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        boolean found = false;
        int baseStackSize = ctx.getVarStackSize();
        
        executeChildren(ctx);
        int addedStackElems = ctx.getVarStackSize() - baseStackSize;
        int argNum = addedStackElems-1;
        VarBase[] args = new VarBase[argNum];
        for (int i = 0; i < argNum; i++) {
            args[argNum - i - 1] = ctx.popVar(this);
        }
        
        VarId id = ctx.popVarX(this, VarId.class);
        
        String proc = id.getString();

        if (proc.equalsIgnoreCase("print")) {
            found = true;
            if (argNum == 1) {
                VarBase arg = args[0];
                System.out.print(arg.getString());
            } else {
                throw generateExecuteException("ERROR function print() accepts 1 arg given "+argNum+" arg(s)");
            }
        }

        if (proc.equalsIgnoreCase("println")) {
            found = true;
            if (argNum == 1) {
                VarBase arg = args[0];
                System.out.println(arg.getString());
            } else if (argNum == 0) {
                System.out.println();
            } else {
                throw generateExecuteException("ERROR method println() accepts 1 arg given "+argNum+" arg(s)");
            }
        }

        if (!found) {
            throw generateExecuteException("ERROR method <"+proc+"> is not defined");
        }
    }
}
