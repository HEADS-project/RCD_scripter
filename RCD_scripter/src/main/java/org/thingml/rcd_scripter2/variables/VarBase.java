/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.variables;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;

/**
 *
 * @author steffend
 */
abstract public class VarBase {
    abstract public String printString();
    abstract public String getTypeString();
    
    public void setDefault(ExecuteContext ctx, String idName) {
        System.out.println("ERROR method setDefault() not supported for "+ctx.getVarName(this)+" "+getTypeString());
    }
    
    public void add(ExecuteContext ctx, String idName) {
        System.out.println("ERROR method add() not supported for "+ctx.getVarName(this)+" "+getTypeString());
    }
    
    
}
