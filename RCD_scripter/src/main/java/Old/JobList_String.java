/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Old;

import org.thingml.rcd_scripter2.ExecuteContext;
import java.util.ArrayList;
import java.util.Iterator;
import org.thingml.rcd_scripter2.variables.VarCell;
import org.thingml.rcd_scripter2.variables.VarRow;

/**
 *
 * @author steffend
 */
public class JobList_String {

    private JobBase_String jobStart = null;
    private JobBase_String jobLast = null;
    private int sequence = 0;
    private String listName = "";

    public JobList_String(String listName) {
        this.listName = listName;
    }

    public JobBase_String getJobStart(ExecuteContext ctx) {
        if (ctx.getTrace()) {
            if (jobStart != null) jobStart.print();
        }
        return jobStart;
    }
    
    public void addJob(JobBase_String newJob) {
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
    
    public String executeToString(ExecuteContext ctx) {
        JobBase_String nextJob = getJobStart(ctx);
        String newString = "";
        
        while (nextJob != null) {
            newString += nextJob.execute(ctx);
            nextJob = (JobBase_String) nextJob.getNext(ctx);
        }
        return newString;
    }
}
