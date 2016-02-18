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
public class JobTableSetDefault extends JobBase_Obj {

    private String varName;
    private JobList_VarCell jobCreateCellList = null;
    
    public JobTableSetDefault(Token t, String varName, JobList_VarCell jobCreateCellList ) {
        super(t);
        this.varName = varName;
        this.jobCreateCellList = jobCreateCellList;
    }
    
    @Override
    public String getTypeString() {
        return "JobTableSetDefault";
    }
    
    @Override
    public Object execute(ExecuteContext ctx) {
        VarRow newRow = new VarRow();
        jobCreateCellList.executeCellsToRow(ctx, newRow);
        
        ctx.getTableVar(varName).setDefaultRowObj(newRow);
        
        return newRow;
    }

}
