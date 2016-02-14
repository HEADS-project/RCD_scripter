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
abstract class JobBase {
 
    protected String image;
    
    public void setImage(String image) {
        this.image = image;
    }
    
    public String getImage() {
        return image;
    }
    
    abstract public String getType();
    abstract public void execute(ExecuteContext ctx);

    public void print(){
        System.out.print(getType() + "(" + image + ")");
    }

}
