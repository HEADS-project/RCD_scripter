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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarArray;
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarBase.VarType;
import org.thingml.rcd_scripter3.variables.VarContainer;
import org.thingml.rcd_scripter3.variables.VarKeyContainer;
import org.thingml.rcd_scripter3.variables.VarString;

public class ASTRcdForBlock extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdForBlock(int id) {
      super(id);
    }

    @Override
    public ExecResult execute(ExecuteContext ctx) throws ExecuteException {
        ExecResult scriptRet;
        ExecResult execRet = ExecResult.NORMAL;
        ASTRcdBase script = null;

        switch(numChildren()){
            case 3:
                // ForBlock1 -> <FOR> <(> VarArray <:> Expr <)> <{> Script <}>
                // Create the entry variable and store it in symtab
                String     entryVarName = ((ASTRcdBase)children[0]).getName();
                VarContainer entryVarCont = ctx.getContainer(this, entryVarName);
                VarArray entryVar = new VarArray();
                entryVarCont.setInst(entryVar);

                // Get the array to iterate over
                ((ASTRcdBase)children[1]).execute(ctx);
                VarContainer  sourceCont = ctx.popContainer(this);

                // Check that there are statements to execute
                script = (ASTRcdBase) children[2];
                if (!(script.getName().contentEquals("ForScript"))) throw generateExecuteException("ERROR ForBlock cannot find script got <"+script.getName()+"><"+script.getClass().getName()+">");

                // Iterate over elements in ARRAY
                if (sourceCont.getType() == VarType.ARRAY) { 
                    VarArray array = (VarArray)sourceCont.getInst();

                    // Populate entry variable with key container
                    VarContainer keyCont = new VarContainer();
                    VarKeyContainer varKeyCont = new VarKeyContainer("key", keyCont);
                    entryVar.hash.put("key", varKeyCont);

                    // Populate entry variable with value container
                    VarContainer valCont = new VarContainer();
                    VarKeyContainer varValCont = new VarKeyContainer("val", valCont);
                    entryVar.hash.put("val", varValCont);

                    Iterator i = array.hash.entrySet().iterator();
                    while(i.hasNext()) {
                        HashMap.Entry pair  = (HashMap.Entry)i.next();

                        // Update key info in entry variable
                        String key = (String)pair.getKey();
                        keyCont.setInst(new VarString(key));

                        // Update value info in entry variable
                        VarKeyContainer value = (VarKeyContainer)pair.getValue();
                        valCont.setInst(value.getContainer().getInst());

                        scriptRet = script.execute(ctx);

                        if (scriptRet == ExecResult.BREAK_LOOP) break;
                        if (scriptRet == ExecResult.RETURN_PROC) { execRet = scriptRet; break;}
                        if (scriptRet == ExecResult.EXIT_PROGRAM) { execRet = scriptRet; break;}
                    }

                } else {
                    throw this.generateExecuteException("ERROR ForBlock cannot iterate over var type <"+sourceCont.getTypeString()+">");
                }
                break;
            case 5:
                // ForBlock2 -> <FOR> <(> VarAssign <;> Expr <;> Expr <)> <{> Script <}>
                ASTRcdBase loopVar = (ASTRcdBase) children[0];
                ASTRcdBase assignExpr = (ASTRcdBase) children[1];
                ASTRcdBase testExpr = (ASTRcdBase) children[2];
                ASTRcdBase incExpr = (ASTRcdBase) children[3];
                script = (ASTRcdBase) children[4];
                if (!(script.getName().contentEquals("ForScript"))) throw generateExecuteException("ERROR ForBlock cannot find script got <"+script.getName()+"><"+script.getClass().getName()+">");

                // Init loop
                loopVar.execute(ctx);
                assignExpr.execute(ctx);
                testExpr.execute(ctx);
                VarContainer testRes = ctx.popContainer(this);
                
                // Loop until test is false
                while (testRes.boolVal()) {
                    scriptRet = script.execute(ctx);

                    if (scriptRet == ExecResult.BREAK_LOOP) break;
                    if (scriptRet == ExecResult.RETURN_PROC) { execRet = scriptRet; break;}
                    if (scriptRet == ExecResult.EXIT_PROGRAM) { execRet = scriptRet; break;}
                    
                    incExpr.execute(ctx);
                    testExpr.execute(ctx);
                    testRes = ctx.popContainer(this);
                }
                break;
            default:
                throw this.generateExecuteException("ERROR ForBlock with <"+numChildren()+"> child nodes expected 3 or 5");
        }

        
        return execRet;
    }
}
