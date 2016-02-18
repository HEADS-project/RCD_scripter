/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarArray;

/**
 *
 * @author steffend
 */
class JobDefArray extends JobBase {

    private String varName;
    private String copyFromName;
    
    public JobDefArray(Token t, String varName, String copyFromName) {
        super(t);
        this.varName = varName;
        this.copyFromName = copyFromName;
    }
    
    public String getType() {
        return "JobDefArray";
    }
    
    public Object execute(ExecuteContext ctx) {
        VarArray newTable = ctx.copyArrayVar(copyFromName);
        ctx.addVar(varName, newTable);
        
        return newTable;
    }

}
