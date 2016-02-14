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
public class VarCellString extends VarCellBase {
    
    @Override
    public String getType() {
        return "String";
    }
    
    @Override
    public void setImage(String image) {
        int len = image.length();
        this.image = image.substring(0, len-1).substring(1);
    }
    
    
}
