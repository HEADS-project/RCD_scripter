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
public class JobCreateCell extends JobBase {
    private final String id;
    private final JobBase jobCreateValue;
    

    public JobCreateCell(Token t, String id, JobBase jobCreateValue) {
        super(t);
        this.id = id;
        this.jobCreateValue = jobCreateValue;
    }
    
    @Override
    public String getType() {
        return "JobCreateCell";
    }
    
    @Override
    public VarCell execute(ExecuteContext ctx) {
        VarValueBase newValue = (VarValueBase)jobCreateValue.execute(ctx);
        VarCell newCell = new VarCell(id, newValue);

        return newCell;
    }
}
