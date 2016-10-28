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
import org.thingml.rcd_scripter3.SymbolTable;
import org.thingml.rcd_scripter3.proc.ProcBaseIf;
import org.thingml.rcd_scripter3.variables.VarBase;

public class ASTRcdDeclProc extends ASTRcdBase implements ProcBaseIf {

    private class Param {
        public String id;
        public Class type;
        
        public Param(String id, Class type) {
            this.id = id;
            this.type = type;
        }
    }
    /**
     * Constructor.
     * @param id the id
     */
    private SymbolTable mySymTab = null;
    private Param[] myParams = null;
    private Class retType = null;
    private ASTRcdBase script = null;
    
    public ASTRcdDeclProc(int id) {
      super(id);
    }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        mySymTab = ctx.getSymTab();
        ctx.declProc(this, getName(), this);
        
        // Build paramlist
        if (children == null) throw generateExecuteException("ERROR procedure declaration without children");
        int len = children.length;
        if (len < 2) throw generateExecuteException("ERROR procedure declaration with <"+len+"> children expected at least 2");
        retType = ((ASTRcdType) children[0]).getTypeClass();
        script = (ASTRcdBase) children[len-1];
        if (!(script instanceof ASTRcdTrueScript)) throw generateExecuteException("ERROR procedure declaration cannot find script got <"+script.getClass().getName()+">");
        myParams = new Param[len-2];
        for (int i = 1; i < len-1; ++i) {
            ASTRcdBase p = (ASTRcdBase) children[i];
            if (!(p instanceof ASTRcdParam))  throw generateExecuteException("ERROR procedure declaration cannot find param got <"+p.getClass().getName()+">");
            if (p.children == null) throw generateExecuteException("ERROR procedure param without children");
            ASTRcdBase t = (ASTRcdBase) p.children[0];
            if (!(t instanceof ASTRcdType))  throw generateExecuteException("ERROR procedure declaration cannot find param type got <"+p.getClass().getName()+">");
            myParams[i-1] = new Param(p.getName(), ((ASTRcdType) t).getTypeClass());
        }
    }

    public VarBase executeProc(ExecuteContext ctx, ASTRcdBase callersBase, String id, VarBase[] args) throws ExecuteException {
        VarBase ret = null;
        
        if (!id.contentEquals(getName())) throw callersBase.generateExecuteException("ERROR procedure <"+id+"> is not defined expected <"+getName()+">");
        // Fetch params and push into symtab
        ctx.pushSymTab(mySymTab);
        ctx.blockStart();
        
        if (args.length != myParams.length) throw callersBase.generateExecuteException("ERROR procedure <"+getName()+"> called with <"+args.length+"> params expected <"+myParams.length+">");
        for( int i = 0; i < args.length; i++) {
            Param p = myParams[i];
            if(!(p.type.isAssignableFrom(args[i].getClass()))) throw callersBase.generateExecuteException("ERROR procedure <"+getName()+"> called with <"+args[i].getClass().getSimpleName()+"> not compatible with <"+p.type.getName()+">");
            ctx.declVar(this, p.id, args[i]);
        }

        script.execute(ctx);
        ctx.blockEnd();
        ctx.popSymTab(this);
        return ret;
    }
}
