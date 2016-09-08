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
public class VarValueInt extends VarValueBase {
    
    private int intValue = 0;
    
    public VarValueInt(String image) {
        super(image);
        intValue = Integer.decode(image);
    }
    
    @Override
    public String getTypeString() {
        return "VarValueInt";
    }
    
    @Override
    public VarType getType() {
        return VarType.INT;
    }
    
    public int getInt() {
        return intValue;
    }
    
    @Override
    protected boolean compareVal(VarValueBase value_other) {
        return intValue == ((VarValueInt)value_other).intValue;
    }
    
    @Override
    public String printString() {
        String ret = "<"+getTypeString()+" Image:"+getString()+" Value:"+intValue+" Operations:"+getOperationImage()+">";
        return ret;
    }

}
