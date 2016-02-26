/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarArray;
import org.thingml.rcd_scripter2.variables.VarBase;
import org.thingml.rcd_scripter2.variables.VarCell;
import org.thingml.rcd_scripter2.variables.VarValueBase;
import org.thingml.rcd_scripter2.variables.VarValueInt;

/**
 *
 * @author steffend
 */
public class JobVarNameAssign extends JobBase_Obj {
    private final String varName;
    private final JobList_VarValueBase jobListCreateIndex;
    private final JobList_VarValueBase jobListCreateValue;
    

    public JobVarNameAssign(Token t, String varName, JobList_VarValueBase jobListCreateIndex, JobList_VarValueBase jobListCreateValue) {
        super(t);
        this.varName = varName;
        this.jobListCreateIndex = jobListCreateIndex;
        this.jobListCreateValue = jobListCreateValue;
    }
    
    @Override
    public String getTypeString() {
        return "JobVarNameAssign";
    }
    
    @Override
    protected Object executeInternal(ExecuteContext ctx) {
        VarValueBase newValue = jobListCreateValue.executeOneValue(ctx);

        if (jobListCreateIndex != null) {
            VarValueInt indexValue = (VarValueInt)jobListCreateIndex.executeOneValue(ctx);
            VarArray array = ctx.getVarArray(varName);
            array.setValue(indexValue.getInt(), newValue);
        } else {
            VarBase varBase = ctx.getVarBaseSilent(varName);
            if (varBase == null) {
                // Var name does not exist => Create
                ctx.addVar(varName, newValue);
            } else {
                if (varBase instanceof VarValueBase) {
                    // Var name is a Value var => Overwrite
                    ctx.addVar(varName, newValue);
                } else {
                    // Var name is NOT a Value var => Protect
                    System.out.println("ERROR variable <"+varName+"> is not instanceof VarValueBase");
                    ctx.printExecutingInfo();
                }
            }
        }
        return this;
    }
}
