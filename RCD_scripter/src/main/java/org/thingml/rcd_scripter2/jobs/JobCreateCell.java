/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarCell;
import org.thingml.rcd_scripter2.variables.VarValueBase;

/**
 *
 * @author steffend
 */
public class JobCreateCell extends JobBase_VarCell {
    private final String id;
    private final JobList_VarValueBase jobListCreateValue;
    

    public JobCreateCell(Token t, String id, JobList_VarValueBase jobListCreateValue) {
        super(t);
        this.id = id;
        this.jobListCreateValue = jobListCreateValue;
    }
    
    @Override
    public String getTypeString() {
        return "JobCreateCell";
    }
    
    @Override
    protected VarCell executeInternal(ExecuteContext ctx) {
        VarValueBase newValue = jobListCreateValue.executeOneValue(ctx);
        VarCell newCell = new VarCell(id, newValue);

        return newCell;
    }
}
