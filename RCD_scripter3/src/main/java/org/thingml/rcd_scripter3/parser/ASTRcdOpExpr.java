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
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarContainer;
import org.thingml.rcd_scripter3.variables.VarValueBase;

public class ASTRcdOpExpr extends ASTRcdBase {

    private VarValueBase.Operation operation = null;
    
    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdOpExpr(int id) {
        super(id);
    }
    
    private void calcOperation (){

        if (operation == null) {
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

            } else if (image.contentEquals("<=")==true) {
                operation = VarValueBase.Operation.LTE;

            } else if (image.contentEquals("!=")==true) {
                operation = VarValueBase.Operation.NOTEQUAL;

            }
        }        
    }

    @Override
    public ExecResult execute(ExecuteContext ctx) throws ExecuteException {
        ExecResult ret;

        ret = executeChildren(ctx);
        VarContainer rightCont = ctx.popContainer(this);
        VarContainer leftCont = ctx.popContainer(this);
 
        calcOperation();
        if (operation == null) {
            throw generateExecuteException("Operation <"+getName()+"> is not supported on Values");
        }        
        VarBase result = VarBase.doOperation(this, leftCont.getInst(), operation, rightCont.getInst());
        ctx.pushContainer(new VarContainer(result));
        return ret;
    }
    
    


}
