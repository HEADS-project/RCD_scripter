/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarValueBase;

/**
 *
 * @author steffend
 */
public class JobCreateValueVar extends JobBase_VarValueBase {
    private String var;
    

    public JobCreateValueVar(Token t, String var) {
        super(t);
        this.var = var;
    }
    
    @Override
    public String getTypeString() {
        return "JobCreateValueVar";
    }
    
    @Override
    protected VarValueBase executeInternal(ExecuteContext ctx) {
        return ctx.getVarValue(var);
    }
}
