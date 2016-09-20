/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3.variables;

import java.util.HashMap;
import java.util.Iterator;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ExecuteException;

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
    
    public void addHash(VarHash hash_add) {
        hash.putAll(hash_add.hash);
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
    
}
