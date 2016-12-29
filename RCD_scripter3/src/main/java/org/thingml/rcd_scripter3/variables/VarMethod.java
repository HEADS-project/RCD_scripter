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
import org.thingml.rcd_scripter3.parser.ASTRcdBase.ExecResult;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.proc.ProcBaseIf;

/**
 *
 * @author steffend
 */
public class VarMethod extends VarBase {
    private VarContainer inst;
    
    public VarMethod(VarContainer inst, String image) {
        super(image.toLowerCase());
        this.inst = inst;
    }
    
    
    @Override
    public VarType getType() {
        return VarType.METHOD;
    }
    
    @Override
    public long intVal() {
        return 0;
    }    
    
    @Override
    public double realVal() {
        return intVal();
    }
    
    @Override
    public boolean boolVal() {
        return false;
    }
    
    @Override
    public String stringVal() {
        return "Method";
    }
    
    @Override
    public VarArray arrayVal() {
        return new VarArray(this);
    }
    
    @Override
    public Object getValObj(){
        Object ret = null;
        
        return ret;
    }

    @Override
    public boolean isMethod() {
        return true;
    }
    
    @Override
    public String printString() {
        String ret = "<"+getTypeString()+" Image:"+stringVal()+">";
        return ret;
    }

    public ExecResult executeMethod(ExecuteContext ctx, ASTRcdBase callersBase, VarContainer[] args) throws ExecuteException {
        ProcBaseIf method = ctx.getProcBase(callersBase, getImage(), args.length);
        ExecResult ret = method.executeMethod(ctx, callersBase, inst, args);
        return ret;
    }
}
