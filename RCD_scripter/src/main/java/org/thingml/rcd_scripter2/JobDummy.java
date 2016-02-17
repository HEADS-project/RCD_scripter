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
class JobDummy extends JobBase {

    
    public JobDummy() {
        super(new Token());
    }
    
    public String getType() {
        return "JobDummy";
    }
    
    public Object execute(ExecuteContext ctx) {
        return this;
    }

}
