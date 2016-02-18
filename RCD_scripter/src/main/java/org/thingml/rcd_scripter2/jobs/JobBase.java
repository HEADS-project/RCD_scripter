/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.jobs;

import org.thingml.rcd_scripter2.ExecuteContext;
import org.thingml.rcd_scripter2.parser.Token;

/**
 *
 * @author steffend
 */
abstract public class JobBase {
 
    protected Token t;
    private JobBase next = null;
    private int sequence  = 0;
    private String listName = "";
    
    public JobBase(Token t) {
        this.t = t;
    }
    
    public void setNext(JobBase next) {
        this.next = next;
    }
    
    public void setTraceInfo(String listName, int seq) {
        this.listName = listName;
        this.sequence = seq;
    }
    
    public JobBase getNext(ExecuteContext ctx) {
        if (ctx.getTrace()) {
            if (next != null) next.print();
        }
        
        return next;
    }
    
    public Token getToken() {
        return t;
    }
    
    abstract public String getType();
    abstract public Object execute(ExecuteContext ctx);

    public void print(){
        System.out.println("List("+listName+":"+sequence+") "+getType()+" Image:<"+t.image+"> beginline:"+t.beginLine+" beginColumn:"+t.beginColumn+" endLine:"+t.endLine+" endColumn:"+t.endColumn);
    }

}
