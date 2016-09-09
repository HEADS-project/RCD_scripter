/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3.variables;

/**
 *
 * @author steffend
 */
public class VarKeyValue extends VarBase {
 
    protected String key;
    protected VarValueBase value;
    
    public VarKeyValue(String id, VarValueBase value) {
        this.key = id;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    
    public VarValueBase getValue() {
        return value;
    }
    
    public String getString() {
        String ret = key + ":" + value.getString();
        return ret;
    }
    
    @Override
    public String getTypeString() {
        return "KeyValue:" + getString();
    }

    @Override
    public String printString(){
        String ret = key+":"+value.printString();
        return ret;
    }

    public boolean compareTypeAndVal(VarKeyValue keyvalue_other) {
        return value.compareTypeAndVal(keyvalue_other.value);
    }
    
}
