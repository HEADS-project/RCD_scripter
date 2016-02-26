/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarCell;

/**
 *
 * @author steffend
 */
public class JobCreateCellVarId extends JobBase_VarCell {
    private String var;
    private String id;
    

    public JobCreateCellVarId(Token t, String var, String id) {
        super(t);
        this.var = var;
        this.id = id;
    }
    
    @Override
    public String getTypeString() {
        return "JobCreateCellVarId";
    }
    
    @Override
    protected VarCell executeInternal(ExecuteContext ctx) {
        return ctx.getVarCellId(var, id);
    }
}
