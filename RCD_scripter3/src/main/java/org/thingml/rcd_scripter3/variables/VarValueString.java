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

import java.util.HashMap;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ASTRcdBase.ExecResult;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.proc.CallMethod;

/**
 *
 * @author steffend
 */
public class VarValueString extends VarValueBase {
    
    public VarValueString(String image) {
        super(image);
    }
    
    private static HashMap<String, CallMethod> callMyMethods = new HashMap<String, CallMethod>();
    private static HashMap<String, CallMethod> callStringMethods = new HashMap<String, CallMethod>();
    
    public static void registerMethods()throws Exception{
        callStringMethods.put("length", new CallMethod("lenght", java.lang.String.class, "length", new Class[] {} ));
        callStringMethods.put("endswith", new CallMethod("endswith", java.lang.String.class, "endsWith", new Class[] {java.lang.String.class} ));
        callStringMethods.put("replace", new CallMethod("replace", java.lang.String.class, "replace", new Class[] {java.lang.CharSequence.class, java.lang.CharSequence.class} ));
        callStringMethods.put("startswith", new CallMethod("startswith", java.lang.String.class, "startsWith", new Class[] {java.lang.String.class} ));
        callStringMethods.put("tolowercase", new CallMethod("tolowercase", java.lang.String.class, "toLowerCase", new Class[] {} ));
        callStringMethods.put("touppercase", new CallMethod("touppercase", java.lang.String.class, "toUpperCase", new Class[] {} ));
        callStringMethods.put("trim", new CallMethod("trim", java.lang.String.class, "trim", new Class[] {} ));
    }

    @Override
    public String getTypeString() {
        return "ValueString";
    }

    @Override
    public VarType getType() {
        return VarType.STRING;
    }
    
    @Override
    public ExecResult executeProc(ExecuteContext ctx, ASTRcdBase callersBase, String methodId, VarBase[] args) throws ExecuteException {
  
        CallMethod cm = callMyMethods.get(methodId.toLowerCase());
        if (cm != null) {
            cm.Call(ctx, callersBase, this, args);
        } else {
            cm = callStringMethods.get(methodId.toLowerCase());
            if (cm != null) {
                cm.Call(ctx, callersBase, this.getString(), args);
            } else {
                callersBase.generateExecuteException("ERROR method <"+methodId+"> is not defined for type <"+getTypeString()+">");
            }
        }
        return ExecResult.NORMAL;
    }
    
}
