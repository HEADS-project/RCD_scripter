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

    private ArrayList<JobBase> jobList = null;

    public JobList() {
        jobList = new ArrayList<JobBase>();
    }

    public void addJobObj(JobBase jobObj) {
        jobList.add(jobObj);
    }
    
    public void execute(ExecuteContext ctx) {
        Iterator i1 = jobList.iterator();
        int n1 = 0;
        while(i1.hasNext()) {
            JobBase  job  = (JobBase)i1.next();
            
            job.execute(ctx);
            n1++;
        }
    }
}
