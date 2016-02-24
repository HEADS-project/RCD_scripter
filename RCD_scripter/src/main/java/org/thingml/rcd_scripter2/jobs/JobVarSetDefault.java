/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarBase;
import org.thingml.rcd_scripter2.variables.VarCell;
import org.thingml.rcd_scripter2.variables.VarRow;

/**
 *
 * @author steffend
 */
public class JobVarSetDefault extends JobBase_Obj {

    private String varName;
    private String copyFromName;
    private JobList_VarCell jobCreateCellList;
    
    public JobVarSetDefault(Token t, String varName, String copyFromName, JobList_VarCell jobCreateCellList ) {
        super(t);
        this.varName = varName;
        this.copyFromName = copyFromName;
        this.jobCreateCellList = jobCreateCellList;
    }
    
    @Override
    public String getTypeString() {
        return "JobVarSetDefault";
    }
    
    @Override
    public Object execute(ExecuteContext ctx) {
        VarBase var = ctx.getVarBase(varName);
        if (jobCreateCellList != null) {
            VarRow tmpRow = new VarRow();
            jobCreateCellList.executeCellsToRow(ctx, tmpRow);
            ctx.addVar("__tmp__", tmpRow);
            var.setDefault(ctx, "__tmp__");
        }
        if (copyFromName != null) {
            var.setDefault(ctx, copyFromName);
        }
        return this;
    }

}
