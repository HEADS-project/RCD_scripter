
package org.thingml.rcd_scripter3.parser;

import java.util.Iterator;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarHash;
import org.thingml.rcd_scripter3.variables.VarHashList;
import org.thingml.rcd_scripter3.variables.VarValArray;
import org.thingml.rcd_scripter3.variables.VarValueString;

public class ASTRcdForBlock extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdForBlock(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        // FOR ( loopVar : sourceVar ) script
        if (children == null) throw generateExecuteException("ERROR ForBlock without parameters");

        if(children.length != 3) throw this.generateExecuteException("ERROR ForBlock with <"+children.length+"> child nodes");
        
        String     loopVarName = ((ASTRcdBase)children[0]).getName();
        
        ((ASTRcdBase)children[1]).execute(ctx);
        VarBase     sourceVar = ctx.popVar(this);
        
        ASTRcdBase script = (ASTRcdBase) children[2];
        if (!(script instanceof ASTRcdTrueScript)) throw this.generateExecuteException("ERROR ForBlock cannot find script got <"+script.getClass().getName()+">");

        if (sourceVar instanceof VarHashList) { // Iterate over HASH in HASHLIST
            VarHashList varHashList = (VarHashList) sourceVar;

            Iterator i = varHashList.hashList.iterator();
            int n = 0;
            while(i.hasNext()) {
                ctx.blockStart();
                VarHash hash = (VarHash)i.next();
                ctx.declVar(this, loopVarName, hash);
                script.execute(ctx);
                ctx.blockEnd();
                n++;
            }
            
        } else if (sourceVar instanceof VarValArray) { // Iterate over VALUE in VALARRAY
            VarValArray varValArray = (VarValArray) sourceVar;

            for (int i = 0; i < varValArray.size(); i++) {
                ctx.blockStart();
                VarBase valueElem = varValArray.getValue(i);
                ctx.declVar(this, loopVarName, valueElem);
                script.execute(ctx);
                ctx.blockEnd();
            }
            
        } else {
            throw this.generateExecuteException("ERROR ForBlock cannot iterate over var type <"+sourceVar.getTypeString()+">");
        }
        
        return ;
    }
}
