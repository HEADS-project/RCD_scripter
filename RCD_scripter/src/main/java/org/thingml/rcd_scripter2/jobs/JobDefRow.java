/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarCell;
import org.thingml.rcd_scripter2.variables.VarRow;

/**
 *
 * @author steffend
 */
class JobDefRow extends JobBase {

    private String varName;
    private String copyFromName = null;
    private JobList jobCreateCellList = null;
    
    public JobDefRow(Token t, String varName, String copyFromName) {
        super(t);
        this.varName = varName;
        this.copyFromName = copyFromName;
    }
    
    public JobDefRow(Token t, String varName, JobList jobCreateCellList ) {
        super(t);
        this.varName = varName;
        this.jobCreateCellList = jobCreateCellList;
    }
    
    @Override
    public String getType() {
        return "JobDefRow";
    }
    
    @Override
    public Object execute(ExecuteContext ctx) {
        VarRow newRow = ctx.copyRowVar(copyFromName);
        JobBase nextJob = jobCreateCellList.getJobStart(ctx);
        
        while (nextJob != null) {
            VarCell cell = (VarCell)nextJob.execute(ctx);
            newRow.addCell(cell);
            nextJob = nextJob.getNext(ctx);
        }
        ctx.addVar(varName, newRow);
        
        return newRow;
    }

}
