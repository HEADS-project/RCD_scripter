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
public class JobCreateStringVarId extends JobBase_String {
    private final String var;
    private final String id;
    

    public JobCreateStringVarId(Token t, String var, String id) {
        super(t);
        this.var = var;
        this.id = id;
    }
    
    @Override
    public String getTypeString() {
        return "JobCreateStringVarId";
    }
    
    @Override
    public String executeInternal(ExecuteContext ctx) {
        return ctx.getVarValueId(var, id).getString();
    }
}
