/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.old;

import org.thingml.rcd_scripter2.old.JobBase_String;
import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;

/**
 *
 * @author steffend
 */
public class JobCreateString extends JobBase_String {
    private final String image;
    

    public JobCreateString(Token t, String image) {
        super(t);
        this.image = image;
    }
    
    @Override
    public String getTypeString() {
        return "JobCreateString";
    }
    
    @Override
    public String executeInternal(ExecuteContext ctx) {
        return image;
    }
}
