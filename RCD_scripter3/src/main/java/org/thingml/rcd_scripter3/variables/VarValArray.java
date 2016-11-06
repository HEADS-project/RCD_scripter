/**
 * Copyright (C) 2016 SINTEF <steffen.dalgard@sintef.no>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3.variables;

import java.util.ArrayList;
import java.util.Iterator;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ASTRcdBase.ExecResult;
import org.thingml.rcd_scripter3.parser.ExecuteException;

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
    public VarBase fetchFromIndex(ASTRcdBase b, VarBase idx) throws ExecuteException {
        if (!(idx instanceof VarValueInt)) throw b.generateExecuteException("ERROR index must be INT got <"+idx.getTypeString()+">");
        VarBase ret = getValue(((VarValueInt)idx).getInt());
        return ret;
    }

    @Override
    public void storeToIndex(ASTRcdBase b, VarBase idx, VarBase expr) throws ExecuteException {
        if (!(idx instanceof VarValueInt)) throw b.generateExecuteException("ERROR index must be INT got <"+idx.getTypeString()+">");
        if (!(expr instanceof VarValueBase)) throw b.generateExecuteException("ERROR value of type <"+expr.getTypeString()+"> cannot be added to VALARRAY");
        setValue(((VarValueInt)idx).getInt(), (VarValueBase)expr);
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

    @Override
    public ExecResult executeProc(ExecuteContext ctx, ASTRcdBase callersBase, String methodId, VarBase[] args) throws ExecuteException {
        int argNum = args.length;

        if (methodId.equalsIgnoreCase("setsize_default")) {
            if (argNum == 2) {
                VarBase arg1 = args[0];
                VarBase arg2 = args[1];
                if (arg1 instanceof VarValueInt) {
                    if (arg2 instanceof VarValueBase) {
                        setSizeAndDefaultValue(((VarValueInt)arg1).getInt(), (VarValueBase)arg2);
                    } else {
                        throw callersBase.generateExecuteException("ERROR method ValArray.setsize_default() cannot add <"+arg2.getType()+"> as second param");
                    }
                } else {
                    throw callersBase.generateExecuteException("ERROR method ValArray.setsize_default() cannot add <"+arg1.getType()+"> as first param");
                }
            } else {
                throw callersBase.generateExecuteException("ERROR method ValArray.setsize_default() accepts 2 arg given "+argNum+" arg(s)");
            }
        } else {
            callersBase.generateExecuteException("ERROR method <"+methodId+"> is not defined for type <"+getTypeString()+">");
        }
        return ExecResult.NORMAL;
    }
}
