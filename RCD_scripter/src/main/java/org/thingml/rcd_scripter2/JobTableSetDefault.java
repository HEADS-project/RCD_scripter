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
class JobTableSetDefault extends JobBase {

    private String varName;
    private JobList jobCreateCellList = null;
    
    public JobTableSetDefault(Token t, String varName, JobList jobCreateCellList ) {
        super(t);
        this.varName = varName;
        this.jobCreateCellList = jobCreateCellList;
    }
    
    @Override
    public String getType() {
        return "JobTableSetDefault";
    }
    
    @Override
    public Object execute(ExecuteContext ctx) {
        VarRow newRow = new VarRow();
        JobBase nextJob = jobCreateCellList.getJobStart();
        
        while (nextJob != null) {
            VarCell cell = (VarCell)nextJob.execute(ctx);
            newRow.addCell(cell);
            nextJob = nextJob.next;
        }
        
        ctx.getTableVar(varName).setDefaultRowObj(newRow);
        
        return newRow;
    }

}
