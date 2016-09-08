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
public class VarValueString extends VarValueBase {
    
    public VarValueString(String image) {
        super(image);
    }
    
    @Override
    public String getTypeString() {
        return "VarValueString";
    }

    @Override
    public VarType getType() {
        return VarType.STRING;
    }
}
