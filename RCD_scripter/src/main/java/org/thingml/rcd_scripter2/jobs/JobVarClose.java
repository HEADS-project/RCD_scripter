/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarBase;
import org.thingml.rcd_scripter2.variables.VarFile;
import org.thingml.rcd_scripter2.variables.VarRow;

/**
 *
 * @author steffend
 */
public class JobVarClose extends JobBase_Obj {

    private String varName;
    
    public JobVarClose(Token t, String varName ) {
        super(t);
        this.varName = varName;
    }

    @Override
    public String getTypeString() {
        return "JobVarClose";
    }
    
    @Override
    protected Object executeInternal(ExecuteContext ctx) {
        VarFile varFile = ctx.getVarFile(varName);
        if (varFile != null) {
            varFile.close();
        }
        return this;
    }

}
