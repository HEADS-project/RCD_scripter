/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarCell;
import org.thingml.rcd_scripter2.variables.VarRow;
import org.thingml.rcd_scripter2.variables.VarValueBase;

/**
 *
 * @author steffend
 */
public class JobPrint extends JobBase_Obj {

    private JobList_VarValueBase retValueJobList;
    
    public JobPrint(Token t, JobList_VarValueBase retValueJobList) {
        super(t);
        this.retValueJobList = retValueJobList;
    }
    
    @Override
    public String getTypeString() {
        return "JobPrint";
    }
    
    @Override
    public Object execute(ExecuteContext ctx) {
        VarValueBase text = retValueJobList.executeOneValue(ctx);
        System.out.println("Print("+text.getString()+")");
        
        return this;
    }

}
