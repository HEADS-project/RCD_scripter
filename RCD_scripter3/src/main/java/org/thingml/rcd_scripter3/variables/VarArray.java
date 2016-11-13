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
public class VarArray extends VarBase {

    private HashMap<String, VarKeyValue> hash = null;

    public VarArray() {
        super("");
        hash = new HashMap<String, VarKeyValue>();
    }

    public VarArray(VarArray copyFromArray) {
        super("");
        hash = new HashMap<String, VarKeyValue>();
        hash.putAll(copyFromArray.hash);
    }

    private static HashMap<String, CallMethod> callMethods = new HashMap<String, CallMethod>();
    
    public static void registerMethods()throws Exception{

        callMethods.put("add", new CallMethod("add", VarArray.class, "addHash", new Class[] { VarArray.class }));
        callMethods.put("has", new CallMethod("has", VarArray.class, "has", new Class[] { VarBase.class }));

    }

    @Override
    public VarType getType() {
        return VarType.ARRAY;
    }
    
    @Override
    public long intVal() {
        if (hash.size() == 0) return 0;
        else return 1;
    }    
    
    @Override
    public double realVal() {
        return intVal();
    }
    
    @Override
    public boolean boolVal() {
        return intVal() != 0;
    }
    
    @Override
    public String stringVal(){
        return "Array";
    }

    @Override
    public Object getValObj(){
        Object ret;
        
        ret = intVal();
        return ret;
    }

    
    @Override
    public String getTypeString() {
        return "Hash";
    }

    public void addKeyValue(VarKeyValue kv){
        hash.put(kv.getKey(), kv);
    }    
    
    public VarKeyValue getKeyValue(String key){
        return hash.get(key);
    }    
    
    public void addHash(VarArray hash_add) {
        hash.putAll(hash_add.hash);
    }

    public boolean has(VarBase key) {
        VarBase test = getKeyValue(key.stringVal()); // Fetch key from HASH
        return test != null;
    }
    
    @Override
    public VarBase fetchFromIndex(ASTRcdBase b, VarBase idx) throws ExecuteException {
        VarKeyValue kv = getKeyValue(idx.stringVal());
        VarBase ret = null;
        if (kv != null) {
            ret = kv.getValue();
        } else {
            b.printMessage("ERROR "+b.getName()+"["+idx.stringVal()+"] is not defined");
            ret = new VarString("");
        }
        return ret;
    }

    @Override
    public void storeToIndex(ASTRcdBase b, VarBase idx, VarBase expr) throws ExecuteException {
        VarKeyValue kv = new VarKeyValue(idx.stringVal(), (VarValueBase)expr);
        addKeyValue(kv);
    }
    
    
    @Override
    public String printString(){
        String ret = "Array: " + dump();
        return ret;
    }

    private String dump(){
        String comma = "";
        String ret = "{";
        Iterator i = hash.entrySet().iterator();
        while(i.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)i.next();
            VarKeyValue kv = (VarKeyValue)pair.getValue();
            ret += comma + kv.stringVal();
            comma = ", ";
        }

        ret += "}";
        return ret;
    }

    public ExecResult executeProc(ExecuteContext ctx, ASTRcdBase callersBase, String methodId, VarBase[] args) throws ExecuteException {
  
        CallMethod cm = callMethods.get(methodId.toLowerCase());
        if (cm != null) {
            cm.Call(ctx, callersBase, this, args);
        } else {
            callersBase.generateExecuteException("ERROR method <"+methodId+"> is not defined for type <"+getTypeString()+">");
        }
        return ExecResult.NORMAL;
    }
}
