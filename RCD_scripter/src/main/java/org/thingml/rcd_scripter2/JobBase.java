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
abstract class JobBase {
 
    protected Token t;
    protected JobBase next = null;
    
    public JobBase(Token t) {
        this.t = t;
    }
    
    public void setNext(JobBase next) {
        this.next = next;
    }
    
    public JobBase getNext() {
        return next;
    }
    
    public Token getToken() {
        return t;
    }
    
    abstract public String getType();
    abstract public Object execute(ExecuteContext ctx);

    public void print(){
        System.out.println(getType()+"Image:<"+t.image+"> beginline:"+t.beginLine+" beginColumn:"+t.beginColumn+" endLine:"+t.endLine+" endColumn:"+t.endColumn);
    }

}
