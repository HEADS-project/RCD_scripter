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
import org.thingml.rcd_scripter2.variables.VarValueInt;

/**
 *
 * @author steffend
 */
public class JobDefArray extends JobBase_Obj {

    private String varName;
    private String copyFromVarName;
    private JobList_VarValueBase jobListSizeValue;
    private JobList_VarValueBase jobListDefaultValue;
    
    public JobDefArray(Token t, String varName, String copyFromVarName, JobList_VarValueBase jobListSizeValue, JobList_VarValueBase jobListDefaultValue) {
        super(t);
        this.varName = varName;
        this.copyFromVarName = copyFromVarName;
        this.jobListSizeValue = jobListSizeValue;
        this.jobListDefaultValue = jobListDefaultValue;
    }
    
    public String getTypeString() {
        return "JobDefArray";
    }
    
    public Object execute(ExecuteContext ctx) {
        VarArray newArray = null;
        if (copyFromVarName != null) {
            VarArray copyFromArray = ctx.getArrayVar(copyFromVarName);
            newArray = new VarArray(copyFromArray);
            ctx.addVar(varName, newArray);
        }
        if (jobListSizeValue != null) {
            VarValueInt allocValue = (VarValueInt) jobListSizeValue.executeOneValue(ctx);
            VarValueBase    defaultValue = jobListDefaultValue.executeOneValue(ctx);
            newArray = new VarArray(allocValue.getInt(), defaultValue);
            ctx.addVar(varName, newArray);
        }
        
        return newArray;
    }

}
