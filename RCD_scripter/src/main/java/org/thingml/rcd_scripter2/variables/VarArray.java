/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.variables;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author steffend
 */
public class VarArray extends VarBase {

    private final int startAlloc = 256;
    private int maxIndex = 0;
    private ArrayList<VarValueBase> arrayList = new ArrayList<VarValueBase>(startAlloc);
    private VarValueBase defaultValue = null;

    public VarArray(VarValueBase defaultValue) {
        this.defaultValue = defaultValue;
        for (int i = 0; i < startAlloc; i++) {
            arrayList.set(i, defaultValue);
        }
    }

    public VarArray(VarArray copyFromArray) {
        copyArray(copyFromArray); 
    }

    private void copyArray(VarArray copyFromArray) {
        this.defaultValue = copyFromArray.defaultValue;
        this.maxIndex = copyFromArray.maxIndex;
        for (int i = 0; i < startAlloc; i++) {
            arrayList.set(i, copyFromArray.arrayList.get(i));
        }
    }

    
    public VarValueBase getDefaultValue() {
        return defaultValue;
    }
    
    public VarValueBase getValue(int index) {
        return arrayList.get(index);
    }
    
    public void setValue(int index, VarValueBase value) {
        arrayList.set(index, value);
        if (maxIndex < index ) {
            maxIndex = index;
        }
    }
    
    public int size() {
        return (maxIndex+1);
    }
    
    
    @Override
    public String printString(){
        String ret = "<"+getType()+" Default value:"+getDefaultValue().printString()+"\n";
        for (int i = 0; i < maxIndex; i++) {
            ret += "Row #" + i + ":"+getValue(i).printString();
        }
        ret += ">\n";
        return ret;
    }

    @Override
    public String getType() {
        return "VarArray";
    }

}
