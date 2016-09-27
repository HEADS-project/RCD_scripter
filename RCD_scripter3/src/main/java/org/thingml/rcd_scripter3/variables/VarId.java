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
public class VarId extends VarValueBase {

    public VarId(String id) {
        super(id);
    }

    @Override
    public String getTypeString() {
        return "Id";
    }

    @Override
    public VarType getType() {
        return VarType.ID;
    }
    
}
