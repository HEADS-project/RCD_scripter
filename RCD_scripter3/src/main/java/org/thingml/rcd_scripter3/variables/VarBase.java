/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3.variables;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.parser.Token;

/**
 *
 * @author steffend
 */
abstract public class VarBase {
    abstract public String getString();
    abstract public String printString();
    abstract public String getTypeString();
    
    public void setDefault(VarBase var) {
        System.out.println("ERROR method setDefault() not supported for "+getTypeString());
    }
    
    public void add(VarBase var) {
        System.out.println("ERROR method add() not supported for "+getTypeString());
    }
    
    public void print() {
        System.out.println("ERROR method print() not supported for "+getTypeString());
    }
    
    
}
