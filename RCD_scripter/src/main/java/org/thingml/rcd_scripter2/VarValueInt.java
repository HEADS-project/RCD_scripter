/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2;


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
    public String getType() {
        return "VarValueInt";
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
        String ret = "<"+getType()+":"+getString()+":"+intValue+">";
        return ret;
    }

}
