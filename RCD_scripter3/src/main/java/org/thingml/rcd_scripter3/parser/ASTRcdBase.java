
package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;

public class ASTRcdBase extends SimpleNode {
    private String name = "";
    private Token token = null;

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

    public void executeChildren(ExecuteContext ctx) throws Exception {
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                ASTRcdBase c = (ASTRcdBase) children[i];
                c.execute(ctx);
            }
        }
    }

    public void execute(ExecuteContext ctx) throws Exception {
        String nodeName = super.toString();  
        System.out.println(nodeName+".execute() is not implemented");
        executeChildren(ctx);
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
