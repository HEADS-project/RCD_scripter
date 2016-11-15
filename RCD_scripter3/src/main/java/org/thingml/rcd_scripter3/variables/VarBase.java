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

import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ExecuteException;

/**
 *
 * @author steffend
 */
abstract public class VarBase implements Cloneable{
    public enum VarType { KEYCONTAINER, ARRAY, INT, REAL, STRING, BOOL, FILE, VOID };
    public enum Operation { PLUS, MINUS, MUL, DIV, EQUAL, GT, LT, GTE, LTE, NOTEQUAL };

    private String image;
    private String operationImage;
    
    public VarBase(String image) {
        this.image = image;
        this.operationImage = image;
    }

    public String getImage() {
        return image;
    }
    
    public void setOperationImage(String image) {
        this.operationImage = image;
    }
    
    public String getOperationImage() {
        return operationImage;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }    
    
    abstract public String printString();
    abstract public VarType getType();

    abstract public long intVal();
    abstract public double realVal();
    abstract public boolean boolVal();
    abstract public String stringVal();
    abstract public Object getValObj();
    
    public boolean isInt()    { return false; }
    public boolean isReal()   { return false; }
    public boolean isBool()   { return false; }
    public boolean isString() { return false; }
    public boolean isArray()  { return false; }
    public boolean isObject()  { return false; }
    

    public String getTypeString() {
        return getType().toString();
    }
    
    public VarContainer fetchFromIndex(ASTRcdBase b, VarContainer idx) throws ExecuteException {
        throw b.generateExecuteException("ERROR indexing not supported for "+getTypeString());
    }
    
    public void storeToIndex(ASTRcdBase b, VarContainer idx, VarContainer expr) throws ExecuteException {
        throw b.generateExecuteException("ERROR indexing not supported for "+getTypeString());
    }

}
