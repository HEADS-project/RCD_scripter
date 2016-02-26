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
abstract public class JobBase_Obj {
 
    protected Token t;
    private JobBase_Obj next = null;
    private int sequence  = 0;
    private String listName = "";
    
    public JobBase_Obj(Token t) {
        this.t = t;
    }
    
    public void setNext(JobBase_Obj next) {
        this.next = next;
    }
    
    public void setTraceInfo(String listName, int seq) {
        this.listName = listName;
        this.sequence = seq;
    }
    
    public JobBase_Obj getNext(ExecuteContext ctx) {
        if (ctx.getTrace()) {
            if (next != null) next.print();
        }
        
        return next;
    }
    
    private void enterExecute(ExecuteContext ctx) {
        ctx.pushExecutingToken(t);
    }
    
    private void exitExecute(ExecuteContext ctx) {
        ctx.popExecutingToken();
    }

    public Token getToken() {
        return t;
    }
    
    abstract public String getTypeString();
    
    public Object execute(ExecuteContext ctx) {
        Object ret;
        ctx.pushExecutingToken(t);
        ret = executeInternal(ctx);
        ctx.popExecutingToken();
        return ret;
    }
    abstract protected Object executeInternal(ExecuteContext ctx);

    public void print(){
        System.out.println("List("+listName+":"+sequence+") "+getTypeString()+" Image:<"+t.image+"> beginline:"+t.beginLine+" beginColumn:"+t.beginColumn+" endLine:"+t.endLine+" endColumn:"+t.endColumn);
    }

}
