/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author steffend
 */
public class JobList {

    private JobBase jobStart = null;
    private JobBase jobLast = null;
    private int sequence = 0;
    private String listName = "";

    public JobList(String listName) {
        this.listName = listName;
    }

    public JobBase getJobStart(ExecuteContext ctx) {
        if (ctx.getTrace()) {
            if (jobStart != null) jobStart.print();
        }
        return jobStart;
    }
    
    public void addJob(JobBase newJob) {
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
        JobBase currJob = getJobStart(ctx);
        int n1 = 0;
        while(currJob != null) {
            currJob.execute(ctx);
            
            currJob = currJob.getNext(ctx);
            n1++;
        }
    }
}
