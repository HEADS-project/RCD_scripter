/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.old;

import org.thingml.rcd_scripter2.old.JobBase_String;
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
    public String executeInternal(ExecuteContext ctx) {
        return ctx.getVarValue(var).getString();
    }
}
