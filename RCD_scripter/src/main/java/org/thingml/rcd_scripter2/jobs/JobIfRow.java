/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import java.util.Iterator;
import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarArray;
import org.thingml.rcd_scripter2.variables.VarBase;
import org.thingml.rcd_scripter2.variables.VarCell;
import org.thingml.rcd_scripter2.variables.VarRow;
import org.thingml.rcd_scripter2.variables.VarRowList;
import org.thingml.rcd_scripter2.variables.VarValueBase;
import org.thingml.rcd_scripter2.variables.VarValueInt;

/**
 *
 * @author steffend
 */
public class JobIfRow extends JobBase_Obj {

    private String rowVarName;
    private JobList_VarCell jobCellList;
    private JobList_Obj ifJobList;
    private JobList_Obj elseJobList;
    
    public JobIfRow(Token t, String rowVarName, JobList_VarCell jobCellList, JobList_Obj ifJobList, JobList_Obj elseJobList) {
        super(t);
        this.rowVarName = rowVarName;
        this.jobCellList = jobCellList;
        this.ifJobList = ifJobList;
        this.elseJobList = elseJobList;
    }
    
    @Override
    public String getTypeString() {
        return "JobIfRow";
    }
    
    @Override
    protected Object executeInternal(ExecuteContext ctx) {
        VarRow varRow = ctx.getVarRow(rowVarName);
        VarCell varCell = jobCellList.executeOneCell(ctx);
        boolean ifMatch = false;
        if (varRow != null) {
            VarCell cellFromRow = varRow.getCell(varCell.getId());
            if (cellFromRow != null) {
                // Found cell with same ID in row
                if (varCell.compareTypeAndVal(cellFromRow) == true) {
                    // Value of cells do match
                    ifJobList.executeList(ctx);
                    ifMatch = true;
                }
            } 
        } else {
            System.out.println("Warning row variable <"+rowVarName+"> is not defined");
        }
            
        if (ifMatch == false) {
            elseJobList.executeList(ctx);
        }
        
        return this;
    }

}
