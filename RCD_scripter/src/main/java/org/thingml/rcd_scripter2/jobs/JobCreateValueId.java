/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarValueBase;
import org.thingml.rcd_scripter2.variables.VarValueId;

/**
 *
 * @author steffend
 */
public class JobCreateValueId extends JobBase {
    private final String image;
    

    public JobCreateValueId(Token t, String image) {
        super(t);
        this.image = image;
    }
    
    @Override
    public String getType() {
        return "JobCreateValueId";
    }
    
    @Override
    public VarValueBase execute(ExecuteContext ctx) {
        VarValueBase ret = new VarValueId(image);
        return ret;
    }
}
