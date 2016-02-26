/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarFile;
import org.thingml.rcd_scripter2.variables.VarRowList;
import org.thingml.rcd_scripter2.variables.VarValueBase;

/**
 *
 * @author steffend
 */
public class JobDefFile extends JobBase_Obj {

    private String varName;
    private JobList_VarValueBase valueJobList;
    
    public JobDefFile(Token t, String varName, JobList_VarValueBase valueJobList) {
        super(t);
        this.varName = varName;
        this.valueJobList = valueJobList;
    }
    
    public String getTypeString() {
        return "JobDefFile";
    }
    
    protected Object executeInternal(ExecuteContext ctx) {
        VarValueBase varFileName = valueJobList.executeOneValue(ctx);
        VarFile newFile = new VarFile(varFileName.getString());
        ctx.addVar(varName, newFile);
        
        return newFile;
    }

}
