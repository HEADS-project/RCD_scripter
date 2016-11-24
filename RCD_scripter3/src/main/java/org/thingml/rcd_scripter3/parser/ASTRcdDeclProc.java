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
import org.thingml.rcd_scripter3.variables.VarContainer;

public class ASTRcdDeclProc extends ASTRcdBase implements ProcBaseIf {

    private class Param {
        public String id;
        public Class type;
        
        public Param(String id) {
            this.id = id;
        }
    }
    /**
     * Constructor.
     * @param id the id
     */
    private SymbolTable mySymTab = null;
    private Param[] myParams = null;
    private ASTRcdBase script = null;
    
    public ASTRcdDeclProc(int id) {
      super(id);
    }

    @Override
    public int getNumArgs() {
        return myParams.length;
    }

    @Override
    public boolean acceptNumArgs(int numArgs) {
        return numArgs == myParams.length;
    }

    @Override
    public boolean isVariArgs() {
        return false;
    }

    @Override
    public ExecResult execute(ExecuteContext ctx) throws ExecuteException {
        
        ASTRcdBase params = null;
        mySymTab = ctx.getSymTab();
        
        int len = numChildren();
        if (len != 2) throw generateExecuteException("ERROR procedure declaration with <"+len+"> children expected 2");
        script = (ASTRcdBase) children[1];
        if (!(script.getName().contentEquals("ProcScript"))) throw generateExecuteException("ERROR procedure declaration cannot find script got <"+script.getName()+"><"+script.getClass().getName()+">");
        params = (ASTRcdBase) children[0];
        if (!(params.getName().contentEquals("FormParam"))) throw generateExecuteException("ERROR procedure declaration cannot find params got <"+params.getName()+"><"+params.getClass().getName()+">");
        
        // Build paramlist
        int paramLen = params.numChildren();
        myParams = new Param[paramLen];
        for (int i = 0; i < paramLen; i++) {
            ASTRcdBase p = (ASTRcdBase) params.children[i];
            if (!(p instanceof ASTRcdFormParam))  throw generateExecuteException("ERROR procedure declaration cannot find param got <"+p.getClass().getName()+">");
            myParams[i] = new Param(p.getName());
        }
        ctx.declProc(this, getName(), this);
        return ExecResult.NORMAL;
    }

    public ExecResult executeProc(ExecuteContext ctx, ASTRcdBase callersBase, String id, VarContainer[] args) throws ExecuteException {
        ExecResult ret;
        
        // Check if correct proc name
        if (!id.contentEquals(getName())) throw callersBase.generateExecuteException("ERROR procedure <"+id+"> is not defined expected <"+getName()+">");
        
        // Create new symtab
        ctx.pushSymTab(mySymTab);
        ctx.blockStart();
        
        if (args.length != myParams.length) throw callersBase.generateExecuteException("ERROR procedure <"+getName()+"> called with <"+args.length+"> params expected <"+myParams.length+">");
        // Put args into local symtab
        for( int i = 0; i < args.length; i++) {
            Param p = myParams[i];
            VarContainer localContainer = ctx.getContainer( this, p.id);
            localContainer.setInst(args[i].getInst());
        }

        ret = script.execute(ctx);
        
        // Fetch the return value from local symtab
        VarContainer retVal = ctx.getContainer(this, "returnVal");
        
        // Restore callers symtab
        ctx.blockEnd();
        ctx.popSymTab(this);
        
        // Push the return value to callers symtab stack
        ctx.pushContainer(retVal);
        switch (ret) {
            case BREAK_LOOP:
                throw callersBase.generateExecuteException("ERROR procedure <"+getName()+"> ended with unpaired break() statement");
            case CONTINUE_LOOP:
                throw callersBase.generateExecuteException("ERROR procedure <"+getName()+"> ended with unpaired continue() statement");
            case RETURN_PROC:
                ret = ExecResult.NORMAL;
                break;
        }
        return ret;
    }
    
    public ExecResult executeMethod(ExecuteContext ctx, ASTRcdBase callersBase, VarContainer inst, VarContainer[] args) throws ExecuteException {
        throw callersBase.generateExecuteException("ERROR procedure <"+getName()+"> cannot be called as method for <"+inst.getTypeString()+">");
    }
}
