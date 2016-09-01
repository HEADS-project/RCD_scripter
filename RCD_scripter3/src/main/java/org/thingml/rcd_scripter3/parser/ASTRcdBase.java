
package org.thingml.rcd_scripter3.parser;

public class ASTRcdBase extends SimpleNode {
  private String name = "";

  /**
   * Constructor.
   * @param id the id
   */
  public ASTRcdBase(int id) {
    super(id);
  }


  /**
   * Set the name.
   * @param n the name
   */
  public void setName(String n) {
    name = n;
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
