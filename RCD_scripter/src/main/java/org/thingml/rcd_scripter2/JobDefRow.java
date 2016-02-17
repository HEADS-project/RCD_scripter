/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2;

/**
 *
 * @author steffend
 */
class JobDefRow extends JobBase {

    private String varName;
    private String copyFromName = null;
    private JobBase jobCreateCellList = null;
    
    public JobDefRow(Token t, String varName, String copyFromName) {
        super(t);
        this.varName = varName;
        this.copyFromName = copyFromName;
    }
    
    public JobDefRow(Token t, String varName, JobBase jobCreateCellList ) {
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
        JobBase nextJob = jobCreateCellList;
        
        while (nextJob != null) {
            VarCell cell = (VarCell)nextJob.execute(ctx);
            newRow.addCell(cell);
            nextJob = nextJob.next;
        }
        ctx.addVar(varName, newRow);
        
        return newRow;
    }

}
