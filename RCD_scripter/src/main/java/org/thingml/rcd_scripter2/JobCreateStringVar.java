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
public class JobCreateStringVar extends JobBase {
    private String var;
    

    public JobCreateStringVar(Token t, String var) {
        super(t);
        this.var = var;
    }
    
    @Override
    public String getType() {
        return "JobCreateStringVar";
    }
    
    @Override
    public String execute(ExecuteContext ctx) {
        return ctx.getValueVar(var).getString();
    }
}
