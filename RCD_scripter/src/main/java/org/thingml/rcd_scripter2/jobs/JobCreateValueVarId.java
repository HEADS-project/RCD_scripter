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
public class JobCreateValueVarId extends JobBase {
    private String var;
    private String id;
    

    public JobCreateValueVarId(Token t, String var, String id) {
        super(t);
        this.var = var;
        this.id = id;
    }
    
    @Override
    public String getType() {
        return "JobCreateValueVarId";
    }
    
    @Override
    public VarValueBase execute(ExecuteContext ctx) {
        return ctx.getValueVarId(var, id);
    }
}
