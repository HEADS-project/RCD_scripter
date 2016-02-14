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
class JobDefTable extends JobBase {

    private String varName;
    private String copyFromName;
    
    public JobDefTable(String varName, String copyFromName) {
        this.varName = varName;
        this.copyFromName = copyFromName;
    }
    
    public String getType() {
        return "JobDefTable";
    }
    
    public void execute(ExecuteContext ctx) {
        VarTable newTable = null;
        if (copyFromName != null) {
            VarBase copyFromBase = ctx.getVar(copyFromName);
            if (copyFromBase instanceof VarTable) {
                VarTable copyFromTable = (VarTable) copyFromBase;
                newTable = new VarTable(copyFromTable);
            } else {
                System.out.println("Warning variable <"+copyFromName+"> is not of type TABLE");
            }
            
        }
        if (newTable == null) {
            newTable = new VarTable();  // Create if not copied from other table
        }
        ctx.addVar(varName, newTable);
        
    }

}
