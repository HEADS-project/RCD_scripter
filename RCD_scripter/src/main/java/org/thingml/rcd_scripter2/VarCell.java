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
class VarCell extends VarBase {
 
    protected String id;
    protected VarValueBase value;
    
    public VarCell(String id, VarValueBase value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }
    
    public VarValueBase getValue() {
        return value;
    }
    
    public String getString() {
        return value.getString();
    }
    
    @Override
    public String getType() {
        return "Cell:"+value.getType();
    }

    @Override
    public String printString(){
        String ret = "<"+getType()+" Id:"+id+" "+value.printString()+">\n";
        return ret;
    }

    public boolean compareTypeAndVal(VarCell cell_other) {
        return value.compareTypeAndVal(cell_other.value);
    }
    
}
