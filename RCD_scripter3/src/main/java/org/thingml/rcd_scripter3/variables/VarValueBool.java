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
public class VarValueBool extends VarValueBase {
    
    private boolean boolValue = false;
    
    public VarValueBool(String image) {
        super(image);
        boolValue = Boolean.getBoolean(image.toLowerCase());
    }
    
    @Override
    public String getTypeString() {
        return "ValueBool";
    }
    
    @Override
    public VarType getType() {
        return VarType.BOOL;
    }
    
    public boolean getBool() {
        return boolValue;
    }
    
    @Override
    protected boolean compareVal(VarValueBase value_other) {
        return boolValue == ((VarValueBool)value_other).boolValue;
    }
    
    @Override
    public String printString() {
        String ret = "<"+getTypeString()+" Image:"+getString()+" Value:"+boolValue+" Operations:"+getOperationImage()+">";
        return ret;
    }

}
