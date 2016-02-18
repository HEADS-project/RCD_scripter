/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.variables;

/**
 *
 * @author steffend
 */
abstract public class VarValueBase extends VarBase {
 
    protected String image;
    
    public VarValueBase(String image) {
        this.image = image;
    }

    public String getString() {
        return image;
    }
    
    abstract public String getType();

    public String printString() {
        String ret = "<"+getType()+":"+getString()+">";
        return ret;
    }

    public boolean compareTypeAndVal(VarValueBase value_other) {
        boolean eq = false;
        if( getType().contentEquals(value_other.getType()) == true) {
            if(compareVal(value_other) == true) {
                eq = true; 
            }
        }
        return eq;
    }
    
    protected boolean compareVal(VarValueBase value_other) {
        return image.contentEquals(value_other.image);
    }
}
