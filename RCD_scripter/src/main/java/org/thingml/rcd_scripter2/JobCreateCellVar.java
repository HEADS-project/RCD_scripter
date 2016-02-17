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
public class JobCreateCellVar extends JobBase {
    private String var;
    

    public JobCreateCellVar(Token t, String var) {
        super(t);
        this.var = var;
    }
    
    @Override
    public String getType() {
        return "JobCreateCellVar";
    }
    
    @Override
    public VarCell execute(ExecuteContext ctx) {
        return ctx.getCellVar(var);
    }
}
