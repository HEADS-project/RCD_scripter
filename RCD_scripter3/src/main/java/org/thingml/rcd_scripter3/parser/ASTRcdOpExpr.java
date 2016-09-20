
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarBase;
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
    public void execute(ExecuteContext ctx) throws ExecuteException {
        executeChildren(ctx);
        VarBase rightValue = ctx.popVar(this);
        VarBase leftValue = ctx.popVar(this);
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
            
        } else if (image.contentEquals("==")==true) {
            operation = VarValueBase.Operation.EQUAL;
            
        } else if (image.contentEquals(">")==true) {
            operation = VarValueBase.Operation.GT;
            
        } else if (image.contentEquals("<")==true) {
            operation = VarValueBase.Operation.LT;
            
        } else if (image.contentEquals(">=")==true) {
            operation = VarValueBase.Operation.GTE;
            
        } else if (image.contentEquals("LTE")==true) {
            operation = VarValueBase.Operation.LTE;
            
        } else if (image.contentEquals("!=")==true) {
            operation = VarValueBase.Operation.NOTEQUAL;
            
        } else {
            throw generateExecuteException("Operation <"+image+"> is not supported on Values");
        }
        
        VarValueBase result = VarValueBase.doOperation(this, leftValue, operation, rightValue);
        ctx.pushVar(result);
    }
    
    


}
