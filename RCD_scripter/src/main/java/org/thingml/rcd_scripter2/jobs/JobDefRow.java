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
public class JobDefRow extends JobBase_Obj {

    private String varName;
    private String copyFromName = null;
    JobList_VarCell jobCellList = null;
    
    public JobDefRow(Token t, String varName, String copyFromName, JobList_VarCell jobCellList) {
        super(t);
        this.varName = varName;
        this.copyFromName = copyFromName;
        this.jobCellList = jobCellList;
    }
    
    @Override
    public String getTypeString() {
        return "JobDefRow";
    }
    
    @Override
    public Object execute(ExecuteContext ctx) {
        VarRow newRow = new VarRow();
                
        if (copyFromName != null) {
            newRow = ctx.copyVarRow(copyFromName);
            ctx.addVar(varName, newRow);
        } 
        if (jobCellList != null) {
            //newRow = new VarRow();
            jobCellList.executeCellsToRow(ctx, newRow);
            ctx.addVar(varName, newRow);
        }

        return newRow;
    }

}
