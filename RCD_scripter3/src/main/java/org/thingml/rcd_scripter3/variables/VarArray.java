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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ASTRcdBase.ExecResult;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.proc.CallMethod;

/**
 *
 * @author steffend
 */
public class VarArray extends VarBase implements Cloneable{

    private HashMap<String, VarKeyContainer> hash = new HashMap<String, VarKeyContainer>();
    private HashMap<String, VarKeyContainer> defaultElemHash = new HashMap<String, VarKeyContainer>();

    public VarArray() {
        super("");
    }

    public VarArray(VarArray copyFromArray) {
        super("");
        copyArray(copyFromArray); 
    }

    public VarArray(VarBase convertToArray) {
        super("");
        addKeyContainer(new VarKeyContainer("0", new VarContainer(convertToArray)));
    }

    public VarArray(VarKeyContainer copyFromKc) {
        super("");
        addKeyContainer(copyFromKc);
    }

    //private static HashMap<String, CallMethod> callMethods = new HashMap<String, CallMethod>();
    
    public static void registerMethods()throws Exception{

        //callMethods.put("add", new CallMethod("add", VarArray.class, "addHash", new Class[] { VarArray.class }));
        //callMethods.put("has", new CallMethod("has", VarArray.class, "has", new Class[] { VarBase.class }));

    }

    @Override
    protected VarArray clone() throws CloneNotSupportedException {
        return new VarArray(this);
    }    
    
    
    private void copyArray(VarArray copyFromArray) {
        if (copyFromArray != null) {
            //defaultHash = new VarHash(copyFromArray.defaultElemHash.clo);
            Iterator i1 = copyFromArray.defaultElemHash.entrySet().iterator();
            while(i1.hasNext()) {
                HashMap.Entry pair  = (HashMap.Entry)i1.next();
                try {
                    VarKeyContainer clonedValue;
                    clonedValue = ((VarKeyContainer)pair.getValue()).clone();
                    defaultElemHash.put(clonedValue.getKey(), clonedValue);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(VarArray.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
            Iterator i2 = copyFromArray.hash.entrySet().iterator();
            while(i2.hasNext()) {
                HashMap.Entry pair  = (HashMap.Entry)i2.next();
                try {
                    VarKeyContainer clonedValue;
                    clonedValue = ((VarKeyContainer)pair.getValue()).clone();
                    hash.put(clonedValue.getKey(), clonedValue);
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(VarArray.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
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
    
    public void addKeyContainer(VarKeyContainer kc){
        hash.put(kc.getKey(), kc);
    }    
    
    public VarKeyContainer getKeyContainer(String key){
        return hash.get(key);
    }    
    
    public void addArray(VarArray array_add) {
        hash.putAll(array_add.hash);
    }

    public boolean has(VarBase key) {
        VarKeyContainer test = getKeyContainer(key.stringVal()); // Fetch key from HASH
        return test != null;
    }
    
    @Override
    public VarContainer fetchFromIndex(ASTRcdBase b, VarContainer idx) throws ExecuteException {
        VarKeyContainer kc = getKeyContainer(idx.stringVal());
        VarContainer ret;
        if (kc != null) {
            ret = kc.getContainer();
        } else {
            b.printMessage("ERROR "+b.getName()+"["+idx.stringVal()+"] is not defined");
            ret = new VarContainer();
        }
        return ret;
    }

    @Override
    public void storeToIndex(ASTRcdBase b, VarContainer idx, VarContainer expr) throws ExecuteException {
        addKeyContainer(new VarKeyContainer(idx.stringVal(), expr));
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
            VarKeyContainer kc = (VarKeyContainer)pair.getValue();
            ret += comma + kc.stringVal();
            comma = ", ";
        }

        ret += "}";
        return ret;
    }

}
