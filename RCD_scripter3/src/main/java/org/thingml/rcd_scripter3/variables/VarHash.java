/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3.variables;

import java.util.HashMap;
import java.util.Iterator;

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

    public void addKeyValue(VarKeyValue kv){
        hash.put(kv.getKey(), kv);
    }    
    
    public VarKeyValue getKeyValue(String key){
        return hash.get(key);
    }    
    
    void addHash(VarHash hash_add) {
        hash.putAll(hash_add.hash);
    }

    @Override
    public String printString(){
        String ret = "VarHash: " + getString();
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

}
