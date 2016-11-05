/**
 * Copyright (C) 2016 SINTEF <steffen.dalgard@sintef.no>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    public int numChildren(){
        int ret = 0;
        if (children != null) {
            ret = children.length;
        }
        return ret;
    }
    
    public boolean executeChildren(ExecuteContext ctx) throws ExecuteException {
        boolean execContinue = true;
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                ASTRcdBase c = (ASTRcdBase) children[i];
                execContinue = c.execute(ctx);
                if (execContinue == false) return execContinue;
            }
        }
        return execContinue;
    }
    
    public boolean execute(ExecuteContext ctx) throws ExecuteException {
        String nodeName = super.toString();  
        throw generateExecuteException(nodeName+".execute() is not implemented");
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
