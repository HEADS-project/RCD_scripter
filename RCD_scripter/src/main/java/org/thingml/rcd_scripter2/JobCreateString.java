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
public class JobCreateString extends JobBase {
    private final String image;
    

    public JobCreateString(Token t, String image) {
        super(t);
        this.image = image;
    }
    
    @Override
    public String getType() {
        return "JobCreateString";
    }
    
    @Override
    public String execute(ExecuteContext ctx) {
        return image;
    }
}
