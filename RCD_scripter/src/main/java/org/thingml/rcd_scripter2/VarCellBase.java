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
abstract class VarCellBase extends VarBase {
 
    protected String id;
    protected String image;
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public String getImage() {
        return image;
    }
    
    abstract public String getType();

    public void print(){
        System.out.print(getType() + "(" + id + ", " + image + ")");
    }

    boolean compareTypeAndVal(VarCellBase cell_other) {
        boolean eq = false;
        if( getType().contentEquals(cell_other.getType()) == true) {
            if(image.contentEquals(cell_other.image) == true) {
                eq = true; 
            }
        }
        return eq;
    }
}
