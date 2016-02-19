/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarCell;

/**
 *
 * @author steffend
 */
public class JobDefCell extends JobBase_Obj {

    private String varName;
    private String copyFromName = null;
    JobList_VarCell jobCell = null;
    
    
    public JobDefCell(Token t, String varName, String copyFromName, JobList_VarCell jobCell) {
        super(t);
        this.varName = varName;
        this.copyFromName = copyFromName;
        this.jobCell = jobCell;
    }
    
    @Override
    public String getTypeString() {
        return "JobDefCell";
    }
    
    @Override
    public Object execute(ExecuteContext ctx) {
        VarCell newCell = null;
        
        if (copyFromName != null) {
            newCell = ctx.getCellVar(copyFromName);
            ctx.addVar(varName, newCell);
        }
        
        if (jobCell != null) {
            newCell = jobCell.executeOneCell(ctx);
            ctx.addVar(varName, newCell);
        }
        
        return newCell;
    }

    
    
    
}
