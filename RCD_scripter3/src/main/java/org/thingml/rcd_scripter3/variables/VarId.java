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
public class VarId extends VarBase {

    private String id;
    
    public VarId(String id) {
        this.id = id;
    }

    public String getString() {
        return id;
    }
    
    @Override
    public String getTypeString() {
        return "VarId";
    }

    @Override
    public VarType getType() {
        return VarType.ID;
    }
    
    @Override
    public String printString(){
        String ret = "Id: " + id;
        return ret;
    }
    
}
