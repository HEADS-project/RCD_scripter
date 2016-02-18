/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarValueBase;

/**
 *
 * @author steffend
 */
abstract public class JobBase_VarValueBase extends JobBase_Obj {
 
    public JobBase_VarValueBase(Token t) {
        super(t);
    }
    
    abstract public String getTypeString();
    abstract public VarValueBase execute(ExecuteContext ctx);

}
