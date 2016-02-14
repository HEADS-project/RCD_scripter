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
public class JobCreateVarCell extends JobCreateCellBase {
    private String var;
    private String id;
    

    public JobCreateVarCell(String var, String id) {
        this.var = var;
        this.id = id;
    }
    
    public VarCellBase execute(ExecuteContext ctx) {
        VarCellBase newCell = ctx.getVarId(var, id);
        return newCell;
    }
}
