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
class JobDefTable extends JobBase {

    private String varName;
    private String copyFromName;
    
    public JobDefTable(Token t, String varName, String copyFromName) {
        super(t);
        this.varName = varName;
        this.copyFromName = copyFromName;
    }
    
    public String getType() {
        return "JobDefTable";
    }
    
    public Object execute(ExecuteContext ctx) {
        VarTable newTable = ctx.copyTableVar(copyFromName);
        ctx.addVar(varName, newTable);
        
        return newTable;
    }

}
