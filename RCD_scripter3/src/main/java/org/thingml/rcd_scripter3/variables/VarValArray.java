/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3.variables;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author steffend
 */
public class VarValArray extends VarBase {

    private int allocSize;
    private int maxIndex = 0;
    private ArrayList<VarValueBase> arrayList = null;
    private VarValueBase defaultValue = null;

    public VarValArray() {
    }
    
    public VarValArray(VarValArray copyFromArray) {
        copyArray(copyFromArray); 
    }

    private void copyArray(VarValArray copyFromArray) {
        this.allocSize = copyFromArray.allocSize;
        this.defaultValue = copyFromArray.defaultValue;
        this.maxIndex = copyFromArray.maxIndex;
        this.arrayList = new ArrayList<VarValueBase>(allocSize);
        for (int i = 0; i < allocSize; i++) {
            arrayList.add(0, defaultValue);
        }
        for (int i = 0; i < allocSize; i++) {
            arrayList.set(i, copyFromArray.arrayList.get(i));
        }
    }

    public void setSizeAndDefaultValue(int allocSize, VarValueBase defaultValue) {
        this.allocSize = allocSize;
        this.defaultValue = defaultValue;
        this.arrayList = new ArrayList<VarValueBase>(allocSize);
        for (int i = 0; i < allocSize; i++) {
            arrayList.add(0, defaultValue);
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
        String ret = "<"+getTypeString()+" AllocSize:"+allocSize+" Default value:"+getDefaultValue().printString()+"\n";
        for (int i = 0; i < size(); i++) {
            ret += "Index #" + i + ":"+getValue(i).printString();
        }
        ret += ">\n";
        return ret;
    }

    @Override
    public String getTypeString() {
        return "ValArray";
    }

    @Override
    public String getString() {
        String ret = "[";
        for (int i = 0; i < size(); i++) {
            if  (i > 0)  ret += " ,";
            
            ret += getValue(i).printString();
        }
        ret += "]";
        return ret;
    }

    @Override
    public VarType getType() {
        return VarType.VALARRAY;
    }

}
