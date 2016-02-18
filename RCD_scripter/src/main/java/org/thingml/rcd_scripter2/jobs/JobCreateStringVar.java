/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;

/**
 *
 * @author steffend
 */
public class JobCreateStringVar extends JobBase_String {
    private String var;
    

    public JobCreateStringVar(Token t, String var) {
        super(t);
        this.var = var;
    }
    
    @Override
    public String getTypeString() {
        return "JobCreateStringVar";
    }
    
    @Override
    public String execute(ExecuteContext ctx) {
        return ctx.getValueVar(var).getString();
    }
}
