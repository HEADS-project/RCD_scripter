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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ASTRcdBase.ExecResult;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.proc.CallMethod;

/**
 *
 * @author steffend
 */
public class VarHash extends VarBase {

    private HashMap<String, VarKeyValue> hash = null;

    public VarHash() {
        hash = new HashMap<String, VarKeyValue>();
    }

    public VarHash(VarHash copyFromHash) {
        hash = new HashMap<String, VarKeyValue>();
        hash.putAll(copyFromHash.hash);
    }

    private static HashMap<String, CallMethod> callMethods = new HashMap<String, CallMethod>();
    
    public static void registerMethods()throws Exception{

        callMethods.put("add", new CallMethod("add", VarHash.class, "addHash", new Class[] { VarHash.class }));
        callMethods.put("has", new CallMethod("has", VarHash.class, "has", new Class[] { VarBase.class }));

    }

    public void addKeyValue(VarKeyValue kv){
        hash.put(kv.getKey(), kv);
    }    
    
    public VarKeyValue getKeyValue(String key){
        return hash.get(key);
    }    
    
    public void addHash(VarHash hash_add) {
        hash.putAll(hash_add.hash);
    }

    public boolean has(VarBase key) {
        VarBase test = getKeyValue(key.getString()); // Fetch key from HASH
        return test != null;
    }
    
    @Override
    public VarBase fetchFromIndex(ASTRcdBase b, VarBase idx) throws ExecuteException {
        VarKeyValue kv = getKeyValue(idx.getString());
        VarBase ret = null;
        if (kv != null) {
            ret = kv.getValue();
        } else {
            throw b.generateExecuteException("ERROR "+b.getName()+"["+idx.getString()+"] is not defined");
        }
        return ret;
    }

    @Override
    public void storeToIndex(ASTRcdBase b, VarBase idx, VarBase expr) throws ExecuteException {
        if (expr instanceof VarValueBase) {
            VarKeyValue kv = new VarKeyValue(idx.getString(), (VarValueBase)expr);
            addKeyValue(kv);
        } else {
            throw b.generateExecuteException("ERROR value of type <"+expr.getTypeString()+"> cannot be added to a HASH");
        }
    }
    
    
    @Override
    public String printString(){
        String ret = "Hash: " + getString();
        return ret;
    }

    @Override
    public String getString(){
        String comma = "";
        String ret = "{";
        Iterator i = hash.entrySet().iterator();
        while(i.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)i.next();
            VarKeyValue kv = (VarKeyValue)pair.getValue();
            ret += comma + kv.getString();
            comma = ", ";
        }

        ret += "}";
        return ret;
    }

    @Override
    public String getTypeString() {
        return "Hash";
    }

    @Override
    public VarType getType() {
        return VarType.HASH;
    }
    
    @Override
    public ExecResult executeProc(ExecuteContext ctx, ASTRcdBase callersBase, String methodId, VarBase[] args) throws ExecuteException {
  
        CallMethod cm = callMethods.get(methodId.toLowerCase());
        if (cm != null) {
            cm.Call(ctx, callersBase, this, args);
        } else {
            callersBase.generateExecuteException("ERROR method <"+methodId+"> is not defined for type <"+getTypeString()+">");
        }
        return ExecResult.NORMAL;
/*        
        int argNum = args.length;
        
        // Fetch params and push into symtab
        if (methodId.equalsIgnoreCase("add")) {
            if (argNum == 1) {
                VarBase arg = args[0];
                if (arg instanceof VarHash) {
                    addHash((VarHash) arg);
                    ctx.pushVar(this); // Return value
                } else {
                    throw callersBase.generateExecuteException("ERROR method Hash.add() cannot add <"+arg.getType()+">");
                }
            } else {
                throw callersBase.generateExecuteException("ERROR method Hash.add() accepts 1 arg given "+argNum+" arg(s)");
            }
        } else if (methodId.equalsIgnoreCase("has")) {
            if (argNum == 1) {
                VarBase arg = args[0];
                VarBase test = getKeyValue(arg.getString()); // Fetch key from HASH
                boolean has = test != null;
                ctx.pushVar(new VarValueBool(""+has)); // Return value
            } else {
                throw callersBase.generateExecuteException("ERROR method Hash.has() accepts 1 arg given "+argNum+" arg(s)");
            }
        } else {
            callersBase.generateExecuteException("ERROR method <"+methodId+"> is not defined for type <"+getTypeString()+">");
        }
        return ExecResult.NORMAL;
*/        
    }
}
