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
public class JobForEach extends JobBase_Obj {

    private String loopVarName;
    private String inVarName;
    private JobList_Obj forEachJobList;
    
    public JobForEach(Token t, String loopVarName, String inVarName, JobList_Obj forEachJobList) {
        super(t);
        this.loopVarName = loopVarName;
        this.inVarName = inVarName;
        this.forEachJobList = forEachJobList;
    }
    
    @Override
    public String getTypeString() {
        return "JobForEach";
    }
    
    @Override
    protected Object executeInternal(ExecuteContext ctx) {
        VarBase var = ctx.getVarBase(inVarName);
        if (var != null) {
            if (var instanceof VarArray) {
                VarArray varArray = (VarArray) var;
                for (int i = 0; i < varArray.size(); i++) {
                    VarValueBase valueElem = varArray.getValue(i);
                    //VarCell cellElem = new VarCell("Elem", valueElem);
                    //VarValueInt valueIndex = new VarValueInt(""+i);
                    //VarCell cellIndex = new VarCell("Index", valueIndex);
                    //VarRow row = new VarRow();
                    //row.addCell(cellElem);
                    //row.addCell(cellIndex);
                    //ctx.addVar(loopVarName, row);
                    ctx.addVar(loopVarName, valueElem);
                    forEachJobList.executeList(ctx);
                }
            } else if (var instanceof VarRowList) {
                VarRowList varTable = (VarRowList) var;
                Iterator i = varTable.rowList.iterator();
                int n = 0;
                while(i.hasNext()) {
                    VarRow row = (VarRow)i.next();
                    ctx.addVar(loopVarName, row);
                    forEachJobList.executeList(ctx);
                    n++;
                }
            } else  {
                System.out.println("Warning variable <"+inVarName+"> type "+var.getTypeString()+" is not supported in FOR_EACH");
            }
        } else {
            System.out.println("Warning variable <"+inVarName+"> is not defined");
        }
        return this;
    }

}
