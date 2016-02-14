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
    private JobCreateCellList jobCreateCellList = null;
    
    public JobDefRow(String varName, String copyFromName) {
        this.varName = varName;
        this.copyFromName = copyFromName;
    }
    
    public JobDefRow(String varName, JobCreateCellList jobCreateCellList ) {
        this.varName = varName;
        this.jobCreateCellList = jobCreateCellList;
    }
    
    @Override
    public String getType() {
        return "JobDefRow";
    }
    
    @Override
    public void execute(ExecuteContext ctx) {
        VarRow newRow = null;
        if (copyFromName != null) {
            VarBase copyFromBase = ctx.getVar(copyFromName);
            if (copyFromBase instanceof VarRow) {
                VarRow copyFromRow = (VarRow) copyFromBase;
                newRow = new VarRow(copyFromRow);
            } else {
                System.out.println("Warning variable <"+copyFromName+"> is not of type ROW");
            }
            
        } else if (jobCreateCellList != null) {
            newRow = new VarRow(jobCreateCellList.execute(ctx));
        }
        
        if (newRow == null) {
            newRow = new VarRow();  // Create if not copied from other table
            System.out.println("Warning variable <"+varName+"> failed to populate");
        }
        ctx.addVar(varName, newRow);
        
    }

}
