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

public class ASTRcdOpExpr extends ASTRcdBase {

    private VarBase.Operation operation = null;
    private boolean unaryOp = false;
    private boolean incDecOp = false;
    
    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdOpExpr(int id) {
        super(id);
    }
    
    private void calcOperation (){

        if (operation == null) {
            String image = getName().toLowerCase();
            if (image.contentEquals("+")==true) {
                operation = VarBase.Operation.PLUS;

            } else if (image.contentEquals("-")==true) {
                operation = VarBase.Operation.MINUS;

            } else if (image.contentEquals("*")==true) {
                operation = VarBase.Operation.MUL;

            } else if (image.contentEquals("/")==true) {
                operation = VarBase.Operation.DIV;

            } else if (image.contentEquals("==")==true) {
                operation = VarBase.Operation.EQUAL;

            } else if (image.contentEquals(">")==true) {
                operation = VarBase.Operation.GT;

            } else if (image.contentEquals("<")==true) {
                operation = VarBase.Operation.LT;

            } else if (image.contentEquals(">=")==true) {
                operation = VarBase.Operation.GTE;

            } else if (image.contentEquals("<=")==true) {
                operation = VarBase.Operation.LTE;

            } else if (image.contentEquals("!=")==true) {
                operation = VarBase.Operation.NOTEQUAL;

            } else if (image.contentEquals("pre-")==true) {
                operation = VarBase.Operation.UMINUS;
                unaryOp = true;

            } else if (image.contentEquals("pre+")==true) {
                operation = VarBase.Operation.UPLUS;
                unaryOp = true;

            } else if (image.contentEquals("pre--")==true) {
                operation = VarBase.Operation.PREDECR;
                unaryOp = true;
                incDecOp = true;

            } else if (image.contentEquals("pre++")==true) {
                operation = VarBase.Operation.PREINCR;
                unaryOp = true;
                incDecOp = true;

            } else if (image.contentEquals("post--")==true) {
                operation = VarBase.Operation.POSTDECR;
                unaryOp = true;
                incDecOp = true;

            } else if (image.contentEquals("post++")==true) {
                operation = VarBase.Operation.POSTINCR;
                unaryOp = true;
                incDecOp = true;

            } else if (image.contentEquals("and")==true) {
                operation = VarBase.Operation.AND;

            } else if (image.contentEquals("or")==true) {
                operation = VarBase.Operation.OR;

            }
        }        
    }

    @Override
    public ExecResult execute(ExecuteContext ctx) throws ExecuteException {
        ExecResult ret;
        VarBase result;
    
        calcOperation();
        ret = executeChildren(ctx);
        VarContainer rightCont = ctx.popContainer(this);
        VarContainer leftCont = null;
 
        if (operation == null) {
            throw generateExecuteException("Operation <"+getName()+"> is not supported on Values");
        }        
        if (!unaryOp) {
            leftCont = ctx.popContainer(this);
            result = VarBase.doVarOperation(this, leftCont.getInst(), operation, rightCont.getInst());
        } else {
            if(!incDecOp) {
                result = VarBase.doVarUnaryOperation(this, operation, rightCont.getInst());
            } else {
                result = VarBase.doContainerUnaryOperation(this, operation, rightCont);
            }
        }
        ctx.pushContainer(new VarContainer(result));
        return ret;
    }
    
    


}
