/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarValueBase;
import org.thingml.rcd_scripter2.variables.VarValueString;

/**
 *
 * @author steffend
 */
public class JobCreateValueConcatString extends JobBase {
    private final JobList jobCreateStringList;
    

    public JobCreateValueConcatString(Token t, JobList jobCreateStringList) {
        super(t);
        this.jobCreateStringList = jobCreateStringList;
    }
    
    @Override
    public String getType() {
        return "JobCreateValueConcatString";
    }
    
    @Override
    public VarValueBase execute(ExecuteContext ctx) {
        String concatString = "";
        
        JobBase nextJob = jobCreateStringList.getJobStart(ctx);
        
        while (nextJob != null) {
            concatString += (String)nextJob.execute(ctx);
            nextJob = nextJob.getNext(ctx);
        }
        
        VarValueBase ret = new VarValueString(concatString);
        return ret;
    }
}
