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
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.proc.CallMethodRegHelper;

/**
 *
 * @author steffend
 */
public class VarArray extends VarBase implements Cloneable{

    public HashMap<String, VarKeyContainer> hash = new HashMap<String, VarKeyContainer>();
    private HashMap<String, VarKeyContainer> defaultElemHash = null;

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

    public static void registerMethods(ExecuteContext ctx)throws Exception{
        ctx.declProc(null, VarType.ARRAY+":clone", new CallMethodRegHelper("clone", VarArray.class, CallMethodRegHelper.InstClass.VARINST, "myClone", new Class[] {} ));
        ctx.declProc(null, VarType.ARRAY+":has", new CallMethodRegHelper("has", VarArray.class, CallMethodRegHelper.InstClass.VARINST, "has", new Class[] { VarBase.class }));
        ctx.declProc(null, VarType.ARRAY+":setdef", new CallMethodRegHelper("setdef", VarArray.class, CallMethodRegHelper.InstClass.VARINST, "setDefaultArray", new Class[] { VarArray.class }));
        ctx.declProc(null, VarType.ARRAY+":add", new CallMethodRegHelper("add", VarArray.class, CallMethodRegHelper.InstClass.VARINST, "addArray", new Class[] { VarArray.class }));
        ctx.declProc(null, VarType.ARRAY+":length", new CallMethodRegHelper("length", VarArray.class, CallMethodRegHelper.InstClass.VARINST, "length", new Class[] {}));
    }


    public int length() {
        return hash.size();
    }
    
    public VarArray myClone() throws CloneNotSupportedException {
        return clone();
    }    
    
    @Override
    protected VarArray clone() throws CloneNotSupportedException {
        return new VarArray(this);
    }    
    
    
    private void copyArray(VarArray copyFromArray) {
        if (copyFromArray != null) {
            if (copyFromArray.defaultElemHash != null) {
                defaultElemHash = new HashMap<String, VarKeyContainer>();
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
    public VarArray arrayVal() {
        return this;
    }
    
    @Override
    public Object getValObj(){
        Object ret;
        
        ret = intVal();
        return ret;
    }
    
    private void addKeyContainer(VarKeyContainer kc){
        hash.put(kc.getKey(), kc);
    }    
    
    private VarKeyContainer getKeyContainer(String key){
        return hash.get(key);
    }    
    
    public void setDefaultArray(VarArray def_array) {
        defaultElemHash = new HashMap<String, VarKeyContainer>();
        defaultElemHash.putAll(def_array.hash);
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
            if (defaultElemHash == null) {
                ret = new VarContainer();
            } else {
                VarArray newArr = new VarArray();
                Iterator i1 = defaultElemHash.entrySet().iterator();
                while(i1.hasNext()) {
                    HashMap.Entry pair  = (HashMap.Entry)i1.next();
                    try {
                        VarKeyContainer clonedDefValue = ((VarKeyContainer)pair.getValue()).clone();
                        newArr.addKeyContainer(clonedDefValue);
                    } catch (CloneNotSupportedException ex) {
                        throw b.generateExecuteException("Error array failed to apply default elements\n"+ex);
                    }
                }
                ret = new VarContainer(newArr);
            }
            addKeyContainer(new VarKeyContainer(idx.stringVal(), ret));
        }
        return ret;
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

    @Override
    public boolean isArray() {
        return true;
    }
    
}
