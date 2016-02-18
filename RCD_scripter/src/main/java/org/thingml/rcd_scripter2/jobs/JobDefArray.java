/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarArray;
import org.thingml.rcd_scripter2.variables.VarValueBase;

/**
 *
 * @author steffend
 */
public class JobDefArray extends JobBase_Obj {

    private String varName;
    private JobList_VarValueBase jobListVarValue;
    
    public JobDefArray(Token t, String varName, JobList_VarValueBase jobListVarValue) {
        super(t);
        this.varName = varName;
        this.jobListVarValue = jobListVarValue;
    }
    
    public String getTypeString() {
        return "JobDefArray";
    }
    
    public Object execute(ExecuteContext ctx) {
        VarArray newArray = null;
        if (jobListVarValue != null) {
            VarValueBase defaultValue = jobListVarValue.executeOneValue(ctx);
            newArray = new VarArray(defaultValue);
            ctx.addVar(varName, newArray);
        }
        
        return newArray;
    }

}
