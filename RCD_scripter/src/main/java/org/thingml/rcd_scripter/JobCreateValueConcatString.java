/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.jobs.JobBase_VarValueBase;
import Old.JobList_String;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarValueBase;
import org.thingml.rcd_scripter2.variables.VarValueString;

/**
 *
 * @author steffend
 */
public class JobCreateValueConcatString extends JobBase_VarValueBase {
    private final JobList_String jobCreateStringList;
    

    public JobCreateValueConcatString(Token t, JobList_String jobCreateStringList) {
        super(t);
        this.jobCreateStringList = jobCreateStringList;
    }
    
    @Override
    public String getTypeString() {
        return "JobCreateValueConcatString";
    }
    
    @Override
    public VarValueBase execute(ExecuteContext ctx) {
        String concatString = jobCreateStringList.executeToString(ctx);
        
        VarValueBase ret = new VarValueString(concatString);
        return ret;
    }
}
