/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter;

/**
 *
 * @author steffend
 */
abstract class CellObj {
 
    String id;
    String image;
    
    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    abstract public String getType();

    public void print(){
        System.out.print(getType() + "(" + id + ", " + image + ")");
    }
}
