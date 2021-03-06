/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;

/**
 *
 * @author steffend
 */
public class JobList_Obj {

    private JobBase_Obj jobStart = null;
    private JobBase_Obj jobLast = null;
    private int sequence = 0;
    private String listName = "";

    public JobList_Obj(String listName) {
        this.listName = listName;
    }

    public JobBase_Obj getJobStart(ExecuteContext ctx) {
        if (ctx.getTrace()) {
            if (jobStart != null) jobStart.print();
        }
        return jobStart;
    }
    
    public void addJob(JobBase_Obj newJob) {
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
    
    public void executeList(ExecuteContext ctx) {
        JobBase_Obj currJob = getJobStart(ctx);
        int n1 = 0;
        while(currJob != null) {
            currJob.execute(ctx);
            
            currJob = currJob.getNext(ctx);
            n1++;
        }
    }
}
