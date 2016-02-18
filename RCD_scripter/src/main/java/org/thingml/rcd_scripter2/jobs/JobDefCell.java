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
class JobDefCell extends JobBase_Obj {

    private String varName;
    private String copyFromName = null;
    private JobBase_Obj jobCreateCell = null;
    
    public JobDefCell(Token t, String varName, String copyFromName) {
        super(t);
        this.varName = varName;
        this.copyFromName = copyFromName;
    }
    
    public JobDefCell(Token t, String varName, JobBase_Obj jobCreateCell ) {
        super(t);
        this.varName = varName;
        this.jobCreateCell = jobCreateCell;
    }
    
    @Override
    public String getTypeString() {
        return "JobDefCell";
    }
    
    @Override
    public Object execute(ExecuteContext ctx) {
        VarCell newCell = ctx.getCellVar(copyFromName);
        
        if (jobCreateCell != null) {
            newCell = (VarCell)jobCreateCell.execute(ctx);
        }
        ctx.addVar(varName, newCell);
        
        return newCell;
    }

}
