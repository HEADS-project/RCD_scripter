/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarValueBase;
import org.thingml.rcd_scripter2.variables.VarValueInt;

/**
 *
 * @author steffend
 */
public class JobCreateValueVarIndex extends JobBase_VarValueBase {
    private String var;
    private final JobList_VarValueBase indexValueJobList;


    public JobCreateValueVarIndex(Token t, String var, JobList_VarValueBase indexValueJobList) {
        super(t);
        this.var = var;
        this.indexValueJobList = indexValueJobList;
    }
    
    @Override
    public String getTypeString() {
        return "JobCreateValueVarIndex";
    }
    
    @Override
    protected VarValueBase executeInternal(ExecuteContext ctx) {
        VarValueBase index = indexValueJobList.executeOneValue(ctx);
        return ctx.getVarValueIndex(var, ((VarValueInt)index).getInt());
    }
}
