
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;

public class ASTRcdBase extends SimpleNode {
    public String name = "";
    public Token token = null;

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdBase(int id) {
        super(id);
    }

    public ASTRcdBase(RcdScript3Parser p, int id) {
        super(p, id);
    }

    /**
     * Set the name.
     * @param n the name
     */
    public void setName(String n) {
        name = n;
    }
    
    public String getName() {
        return name;
    }

  /**
   * Set the token that generated this node.
   * To be used for error reporting
   */
  public void setToken(Token t) {
    token = t;
  }

    public boolean executeChildren(ExecuteContext ctx) throws ExecuteException {
        boolean ret = false;
        if (children != null) {
            ret = true;
            for (int i = 0; i < children.length; ++i) {
                ASTRcdBase c = (ASTRcdBase) children[i];
                c.execute(ctx);
            }
        }
        return ret;
    }
    
    public void execute(ExecuteContext ctx) throws ExecuteException {
        String nodeName = super.toString();  
        throw generateExecuteException(nodeName+".execute() is not implemented");
        //executeChildren(ctx);
    }
			
    /** Generate ExecuteException. */
    public ExecuteException generateExecuteException(String message) {
        return new ExecuteException(token, message);
    }


  /**
   * {@inheritDoc}
   * @see org.javacc.examples.jjtree.eg2.SimpleNode#toString()
   */
  public String toString() {
    String nodeName = super.toString();  
    return nodeName +": " + name;
  }

}
