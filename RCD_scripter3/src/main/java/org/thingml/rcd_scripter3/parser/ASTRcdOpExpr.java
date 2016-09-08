
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarValueBase;

public class ASTRcdOpExpr extends ASTRcdBase {

  /**
   * Constructor.
   * @param id the id
   */
  public ASTRcdOpExpr(int id) {
    super(id);
  }

    @Override
    public void execute(ExecuteContext ctx) throws Exception {
        executeChildren(ctx);
        VarValueBase rightValue = ctx.popVarValue();
        VarValueBase leftValue = ctx.popVarValue();
        VarValueBase.Operation operation;
        String image = getName();
 
        if (image.contentEquals("+")==true) {
            operation = VarValueBase.Operation.PLUS;
            
        } else if (image.contentEquals("-")==true) {
            operation = VarValueBase.Operation.MINUS;
            
        } else if (image.contentEquals("*")==true) {
            operation = VarValueBase.Operation.MUL;
            
        } else if (image.contentEquals("/")==true) {
            operation = VarValueBase.Operation.DIV;
            
        } else {
            throw new Error("Operation <"+image+"> is not supported on Values");
        }
        
        VarValueBase result = VarValueBase.doOperation(leftValue, operation, rightValue);
        ctx.pushVar(result);
    }
    
    


}
