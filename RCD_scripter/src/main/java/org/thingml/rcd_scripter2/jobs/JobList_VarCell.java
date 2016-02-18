/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import java.util.ArrayList;
import java.util.Iterator;
import org.thingml.rcd_scripter2.variables.VarCell;
import org.thingml.rcd_scripter2.variables.VarRow;

/**
 *
 * @author steffend
 */
public class JobList_VarCell {

    private JobBase_VarCell jobStart = null;
    private JobBase_VarCell jobLast = null;
    private int sequence = 0;
    private String listName = "";

    public JobList_VarCell(String listName) {
        this.listName = listName;
    }

    public JobBase_VarCell getJobStart(ExecuteContext ctx) {
        if (ctx.getTrace()) {
            if (jobStart != null) jobStart.print();
        }
        return jobStart;
    }
    
    public void addJob(JobBase_VarCell newJob) {
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
    
    public VarRow executeCellsToRow(ExecuteContext ctx, VarRow row) {
        JobBase_VarCell nextJob = getJobStart(ctx);
        
        while (nextJob != null) {
            VarCell cell = nextJob.execute(ctx);
            row.addCell(cell);
            nextJob = (JobBase_VarCell) nextJob.getNext(ctx);
        }
        return row;
    }
    
    public VarCell executeOneCell(ExecuteContext ctx) {
        JobBase_VarCell nextJob = getJobStart(ctx);
        VarCell cell = nextJob.execute(ctx);
        return cell;
    }
}
