/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.old;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.jobs.JobBase_Obj;
import org.thingml.rcd_scripter2.jobs.JobList_VarValueBase;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarCell;
import org.thingml.rcd_scripter2.variables.VarValueBase;

/**
 *
 * @author steffend
 */
public class JobDefValue extends JobBase_Obj {

    private String varName;
    private String copyFromName = null;
    JobList_VarValueBase jobValue = null;
    
    
    public JobDefValue(Token t, String varName, String copyFromName, JobList_VarValueBase jobValue) {
        super(t);
        this.varName = varName;
        this.copyFromName = copyFromName;
        this.jobValue = jobValue;
    }
    
    @Override
    public String getTypeString() {
        return "JobDefValue";
    }
    
    @Override
    public Object executeInternal(ExecuteContext ctx) {
        VarValueBase newValue = null;
        
        if (copyFromName != null) {
            newValue = ctx.getVarValue(copyFromName);
            ctx.addVar(varName, newValue);
        }
        
        if (jobValue != null) {
            newValue = jobValue.executeOneValue(ctx);
            ctx.addVar(varName, newValue);
        }
        
        return newValue;
    }

    
    
    
}
