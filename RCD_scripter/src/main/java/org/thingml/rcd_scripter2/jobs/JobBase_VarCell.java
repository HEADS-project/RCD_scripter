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
abstract public class JobBase_VarCell extends JobBase_Obj {
 
    public JobBase_VarCell(Token t) {
        super(t);
    }
    
    abstract public String getTypeString();
    
    public VarCell execute(ExecuteContext ctx) {
        VarCell ret;
        ctx.pushExecutingToken(t);
        ret = executeInternal(ctx);
        ctx.popExecutingToken();
        return ret;
    }

    abstract protected VarCell executeInternal(ExecuteContext ctx);

}
