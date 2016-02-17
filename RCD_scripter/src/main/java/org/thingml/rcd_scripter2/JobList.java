/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author steffend
 */
class JobList {

    private JobBase jobStart = null;
    private JobBase jobLast = null;

    public JobList() {
    }

    public JobBase getJobStart() {
        return jobStart;
    }
    
    public void addJob(JobBase newJob) {
        if (newJob != null) {
            if (jobStart != null) {
                jobLast.setNext(newJob);
                jobLast = newJob;
                
            } else {
                jobStart = newJob;
                jobLast = newJob;
            }
            
        }
    }
    
    public void execute(ExecuteContext ctx) {
        JobBase currJob = jobStart;
        int n1 = 0;
        while(currJob != null) {
            currJob.execute(ctx);
            
            currJob = currJob.getNext();
            n1++;
        }
    }
}
