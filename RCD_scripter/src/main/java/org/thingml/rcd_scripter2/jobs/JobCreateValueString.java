/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;
import org.thingml.rcd_scripter2.variables.VarValueBase;
import org.thingml.rcd_scripter2.variables.VarValueString;

/**
 *
 * @author steffend
 */
public class JobCreateValueString extends JobBase_VarValueBase {
    private final String image;
    

    public JobCreateValueString(Token t, String image, boolean removeQuotes) {
        super(t);
        if (removeQuotes == true) {
            this.image = image.substring(0, image.length()-1).substring(1);
        } else {
            this.image = image;
        }
    }
    
    @Override
    public String getTypeString() {
        return "JobCreateValueString";
    }
    
    @Override
    public VarValueBase execute(ExecuteContext ctx) {
        VarValueBase ret = new VarValueString(image);
        return ret;
    }
}
