/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarValueBase;
import org.thingml.rcd_scripter2.variables.VarValueInt;

/**
 *
 * @author steffend
 */
public class JobCreateValueOperation extends JobBase_VarValueBase {
    private final String image;
    private final JobList_VarValueBase leftValueJob;
    private final JobList_VarValueBase rigthValueJob;
    private VarValueBase.Operation operation;
    

    public JobCreateValueOperation(Token t, String image, JobList_VarValueBase leftValueJob, JobList_VarValueBase rightValueJob) {
        super(t);
        this.image = image;
        this.leftValueJob = leftValueJob;
        this.rigthValueJob = rightValueJob;
        
        if (image.contentEquals("S+")==true) {
            this.operation = VarValueBase.Operation.STRPLUS;
            
        } else if (image.contentEquals("+")==true) {
            this.operation = VarValueBase.Operation.PLUS;
            
        } else if (image.contentEquals("-")==true) {
            this.operation = VarValueBase.Operation.MINUS;
            
        } else if (image.contentEquals("*")==true) {
            this.operation = VarValueBase.Operation.MUL;
            
        } else if (image.contentEquals("/")==true) {
            this.operation = VarValueBase.Operation.DIV;
            
        } else {
            throw new Error("Operation <"+image+"> is not supported on Values");
        }
    }
    
    @Override
    public String getTypeString() {
        return "JobCreateValueOperation";
    }
    
    @Override
    public VarValueBase execute(ExecuteContext ctx) {
        VarValueBase leftValue = leftValueJob.executeOneValue(ctx);
        VarValueBase rightValue = rigthValueJob.executeOneValue(ctx);
        
        VarValueBase ret = VarValueBase.doOperation(leftValue, operation, rightValue);
        return ret;
    }
}
