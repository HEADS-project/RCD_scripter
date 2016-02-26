/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import java.util.Iterator;
import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarArray;
import org.thingml.rcd_scripter2.variables.VarBase;
import org.thingml.rcd_scripter2.variables.VarCell;
import org.thingml.rcd_scripter2.variables.VarRow;
import org.thingml.rcd_scripter2.variables.VarRowList;
import org.thingml.rcd_scripter2.variables.VarValueBase;
import org.thingml.rcd_scripter2.variables.VarValueInt;

/**
 *
 * @author steffend
 */
public class JobIfVarEq extends JobBase_Obj {

    private String rowVarName;
    private JobList_VarValueBase leftValueJobList;
    private JobList_VarValueBase rightValueJobList;
    private JobList_Obj ifJobList;
    private JobList_Obj elseJobList;
    
    public JobIfVarEq(Token t, JobList_VarValueBase leftValueJobList, JobList_VarValueBase rightValueJobList, JobList_Obj ifJobList, JobList_Obj elseJobList) {
        super(t);
        this.leftValueJobList = leftValueJobList;
        this.rightValueJobList = rightValueJobList;
        this.ifJobList = ifJobList;
        this.elseJobList = elseJobList;
    }
    
    @Override
    public String getTypeString() {
        return "JobIfVarEq";
    }
    
    @Override
    protected Object executeInternal(ExecuteContext ctx) {
        VarValueBase varValueLeft = leftValueJobList.executeOneValue(ctx);
        VarValueBase varValueRight = rightValueJobList.executeOneValue(ctx);
        boolean ifMatch = false;
        if (varValueLeft != null) {
            if (varValueRight != null) {
                if (varValueLeft.compareTypeAndVal(varValueRight) == true) {
                    // Value do match
                    ifJobList.executeList(ctx);
                    ifMatch = true;
                }
            } else {
                System.out.println("Warning rightValue is not defined");
                ctx.printExecutingInfo();
            }
        } else {
            System.out.println("Warning leftValue is not defined");
            ctx.printExecutingInfo();
        }
            
        if (ifMatch == false) {
            elseJobList.executeList(ctx);
        }
        
        return this;
    }

}
