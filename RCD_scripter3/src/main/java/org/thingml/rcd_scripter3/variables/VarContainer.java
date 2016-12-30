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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3.variables;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.proc.ProcBaseIf;
import org.thingml.rcd_scripter3.variables.VarBase.VarType;

/**
 *
 * @author steffend
 */
public class VarContainer implements Cloneable{

    private VarBase varInst;
    
    public VarContainer(VarBase inst) {
        this.varInst = inst;
    }

    public VarContainer() {
        this.varInst = new VarUndefined();
    }
    
    public VarBase getInst() {
        return varInst;
    }
    
    public void setInst(VarBase inst) {
        this.varInst = inst;
    }
    
    @Override
    protected VarContainer clone() throws CloneNotSupportedException {
        VarContainer cloned = (VarContainer)super.clone();
        cloned.setInst((VarBase)cloned.getInst().clone());
        return cloned;
    }    
    
    public String printString() {return varInst.printString(); }
    public VarType getType() { return varInst.getType(); }
    public String getTypeString() { return varInst.getTypeString(); }

    public long intVal() {return varInst.intVal(); }
    public double realVal() {return varInst.realVal(); }
    public boolean boolVal() {return varInst.boolVal(); }
    public String stringVal() {return varInst.stringVal(); }
    public Object getValObj() {return varInst.getValObj(); }
    
    public boolean isInt() {return varInst.isInt(); }
    public boolean isReal() {return varInst.isReal(); }
    public boolean isBool() {return varInst.isBool(); }
    public boolean isString() {return varInst.isString(); }
    public boolean isArray() {return varInst.isArray(); }
    public boolean isObject() {return varInst.isObject(); }
    public boolean isMethod() {return varInst.isMethod(); }
    public boolean isUndefined() {return varInst.isUndefined(); }
    
    public VarContainer fetchFromIndex(ExecuteContext ctx, ASTRcdBase b, VarContainer idx, boolean assign) throws ExecuteException  {
        VarContainer ret;
        String methodId = getType().toString() +":"+ idx.stringVal();
        boolean hasMethod = ctx.hasProcBase(methodId);
        if (hasMethod) {
            ret = new VarContainer (new VarMethod(this, methodId));
        } else {
            if (isArray()) {
                // Fetch from existing array
                ret = varInst.fetchFromIndex(b, idx); 
            } else {
                // Transform current into an array
                VarBase arrInst = new VarArray(varInst);
                ret = arrInst.fetchFromIndex(b, idx); 
                if (assign) varInst = arrInst;
            }
        }
        return ret;
    }
    
    //public void storeToIndex(ASTRcdBase b, VarContainer idx, VarContainer expr) throws ExecuteException {
    //    if (varInst.isArray()) {
    //        varInst.storeToIndex(b, idx, expr);
    //    } else {
    //        varInst = new VarArray(varInst);
    //        varInst.storeToIndex(b, idx, expr);
    //    }
    //}

}
