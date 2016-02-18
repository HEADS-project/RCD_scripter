/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.variables.VarValueBase;

/**
 *
 * @author steffend
 */
public class JobList_VarValueBase {

    private JobBase_VarValueBase jobStart = null;
    private JobBase_VarValueBase jobLast = null;
    private int sequence = 0;
    private String listName = "";

    public JobList_VarValueBase(String listName) {
        this.listName = listName;
    }

    public JobBase_VarValueBase getJobStart(ExecuteContext ctx) {
        if (ctx.getTrace()) {
            if (jobStart != null) jobStart.print();
        }
        return jobStart;
    }
    
    public void addJob(JobBase_VarValueBase newJob) {
        if (newJob != null) {
            newJob.setTraceInfo(listName, sequence++);
            if (jobStart != null) {
                jobLast.setNext(newJob);
                jobLast = newJob;
                
            } else {
                jobStart = newJob;
                jobLast = newJob;
            }
        }
    }
    
    public VarValueBase executeOneValue(ExecuteContext ctx) {
        JobBase_VarValueBase nextJob = getJobStart(ctx);
        VarValueBase newValue = nextJob.execute(ctx);
        return newValue;
    }
    
}
