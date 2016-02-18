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
public class VarValueId extends VarValueBase {
    
    public VarValueId(String image) {
        super(image);
    }
    
    @Override
    public String getType() {
        return "VarValueId";
    }
    
}
