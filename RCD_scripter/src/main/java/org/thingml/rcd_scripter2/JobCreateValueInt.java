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
public class JobCreateValueInt extends JobBase {
    private final String image;
    

    public JobCreateValueInt(Token t, String image) {
        super(t);
        this.image = image;
    }
    
    @Override
    public String getType() {
        return "JobCreateValueInt";
    }
    
    @Override
    public VarValueBase execute(ExecuteContext ctx) {
        VarValueBase ret = new VarValueInt(image);
        return ret;
    }
}
