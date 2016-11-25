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
import org.thingml.rcd_scripter3.proc.CallMethodRegHelper;


/**
 *
 * @author steffend
 */
public class VarString extends VarBase {
    
    public VarString(String image) {
        super(image);
    }

    public static void registerMethods(ExecuteContext ctx)throws Exception{
        ctx.declProc(null, VarType.STRING+":length", new CallMethodRegHelper("length", java.lang.String.class, CallMethodRegHelper.InstClass.STRING, "length", new Class[] {} ));
        ctx.declProc(null, VarType.STRING+":endswith", new CallMethodRegHelper("endswith", java.lang.String.class, CallMethodRegHelper.InstClass.STRING, "endsWith", new Class[] {java.lang.String.class} ));
        ctx.declProc(null, VarType.STRING+":replace", new CallMethodRegHelper("replace", java.lang.String.class, CallMethodRegHelper.InstClass.STRING, "replace", new Class[] {java.lang.CharSequence.class, java.lang.CharSequence.class} ));
        ctx.declProc(null, VarType.STRING+":startswith", new CallMethodRegHelper("startswith", java.lang.String.class, CallMethodRegHelper.InstClass.STRING, "startsWith", new Class[] {java.lang.String.class} ));
        ctx.declProc(null, VarType.STRING+":tolowercase", new CallMethodRegHelper("tolowercase", java.lang.String.class, CallMethodRegHelper.InstClass.STRING, "toLowerCase", new Class[] {} ));
        ctx.declProc(null, VarType.STRING+":touppercase", new CallMethodRegHelper("touppercase", java.lang.String.class, CallMethodRegHelper.InstClass.STRING, "toUpperCase", new Class[] {} ));
        ctx.declProc(null, VarType.STRING+":trim", new CallMethodRegHelper("trim", java.lang.String.class, CallMethodRegHelper.InstClass.STRING, "trim", new Class[] {} ));
    }

    @Override
    public VarType getType() {
        return VarType.STRING;
    }
    
    @Override
    public long intVal() {
        return Integer.decode(getImage());
    }    
    
    @Override
    public double realVal() {
        return intVal();
    }
    
    @Override
    public boolean boolVal() {
        if (getImage().contentEquals("0")) return false;
        if (getImage().length() == 0) return false;
        return true;
    }
    @Override
    public String stringVal() {
        return getImage();
    }

    @Override
    public VarArray arrayVal() {
        return new VarArray(this);
    }
    
    @Override
    public Object getValObj(){
        Object ret;
        
        ret = getImage();
        return ret;
    }
    
    @Override
    public String printString() {
        String ret = "<"+getTypeString()+" Image:"+stringVal()+" Value:"+getImage()+" Operations:"+getOperationImage()+">";
        return ret;
    }

    @Override
    public boolean isString() {
        return true;
    }
    
  
}
