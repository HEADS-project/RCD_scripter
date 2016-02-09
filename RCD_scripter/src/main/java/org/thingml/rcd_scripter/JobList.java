/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author steffend
 */
public class JobList {
    private ArrayList<JobObj> jobList = null;

    public JobList() {
        jobList = new ArrayList<JobObj>();
    }

    public void addJobObj(JobObj printObj){
        jobList.add(printObj);
    }
    
    public void executeList(RowObj row){
        Iterator i1 = jobList.iterator();
        int n1 = 0;
        while(i1.hasNext()) {
            JobObj  jobObj  = (JobObj)i1.next();

            jobObj.execute(row);
            n1++;
        }
        
    }
    
    
}
