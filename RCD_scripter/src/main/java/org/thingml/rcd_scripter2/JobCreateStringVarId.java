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
public class JobCreateStringVarId extends JobBase {
    private final String var;
    private final String id;
    

    public JobCreateStringVarId(Token t, String var, String id) {
        super(t);
        this.var = var;
        this.id = id;
    }
    
    @Override
    public String getType() {
        return "JobCreateStringVarId";
    }
    
    @Override
    public String execute(ExecuteContext ctx) {
        return ctx.getValueVarId(var, id).getString();
    }
}
