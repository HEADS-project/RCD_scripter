
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarHash;
import org.thingml.rcd_scripter3.variables.VarHashList;
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
        int baseStackSize = ctx.getVarStackSize();
        
        executeChildren(ctx);
        int addedStackElems = ctx.getVarStackSize() - baseStackSize;
        int argNum = addedStackElems-2;
        VarBase[] args = new VarBase[argNum];
        for (int i = 0; i < argNum; i++) {
            args[i] = ctx.popVar(this);
        }
        
        VarId id = ctx.popVarX(this, VarId.class);
        VarBase var = ctx.popVar(this);
        
        String method = id.getString();

        if (var instanceof VarHash) {
            VarHash varHash = (VarHash) var;

            if (method.equalsIgnoreCase("add")) {
                found = true;
                if (argNum == 1) {
                    VarBase arg = args[0];
                    if (arg instanceof VarHash) {
                        varHash.addHash((VarHash) arg);
                    } else {
                        throw generateExecuteException("ERROR method Hash.add() cannot add <"+arg.getType()+">");
                    }
                } else {
                    throw generateExecuteException("ERROR method Hash.add() accepts 1 arg given "+argNum+" arg(s)");
                }
            }
        }
        
        if (var instanceof VarHashList) {
            VarHashList varHashList = (VarHashList) var;
            
            if (method.equalsIgnoreCase("add")) {
                found = true;
                if (argNum == 1) {
                    VarBase arg = args[0];
                    if (arg instanceof VarHash) {
                        varHashList.addHash((VarHash) arg);
                    } else {
                        throw generateExecuteException("ERROR method HashList.add() cannot add <"+arg.getType()+">");
                    }
                } else {
                    throw generateExecuteException("ERROR method HashList.add() accepts 1 arg given "+argNum+" arg(s)");
                }
            }

            if (method.equalsIgnoreCase("setdef")) {
                found = true;
                if (argNum == 1) {
                    VarBase arg = args[0];
                    if (arg instanceof VarHash) {
                        varHashList.setDefaultHash((VarHash) arg);
                    } else {
                        throw generateExecuteException("ERROR method HashList.setdef() cannot add <"+arg.getType()+">");
                    }
                } else {
                    throw generateExecuteException("ERROR method HashList.setdef() accepts 1 arg given "+argNum+" arg(s)");
                }
            }
        }


        if (!found) {
            throw generateExecuteException("ERROR method <"+method+"> is not defined for "+var.getTypeString());
        }
    }
			
}
