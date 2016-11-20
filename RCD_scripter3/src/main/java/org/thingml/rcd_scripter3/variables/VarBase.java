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

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.proc.CallMethodRegHelper;

/**
 *
 * @author steffend
 */
abstract public class VarBase implements Cloneable{
    public enum VarType { ARRAY, INT, REAL, STRING, BOOL, FILE};
    public enum Operation { PLUS, MINUS, UPLUS, UMINUS, OR, AND, MUL, DIV, EQUAL, GT, LT, GTE, LTE, NOTEQUAL };

    private String image;
    private String operationImage;
    
    public VarBase(String image) {
        this.image = image;
        this.operationImage = image;
    }
    private static void registerForVarX(ExecuteContext ctx, String name, CallMethodRegHelper cmrh) throws Exception{
        ctx.declProc(null, VarType.ARRAY+":"+name, cmrh);
        ctx.declProc(null, VarType.INT+":"+name, cmrh);
        ctx.declProc(null, VarType.REAL+":"+name, cmrh);
        ctx.declProc(null, VarType.STRING+":"+name, cmrh);
        ctx.declProc(null, VarType.BOOL+":"+name, cmrh);
        ctx.declProc(null, VarType.FILE+":"+name, cmrh);
    }
    public static void registerMethods(ExecuteContext ctx) throws Exception{
        registerForVarX(ctx, "is_int", new CallMethodRegHelper("is_int", VarBase.class, CallMethodRegHelper.InstClass.VARINST, "isInt", new Class[] {}));
        registerForVarX(ctx, "is_real", new CallMethodRegHelper("is_real", VarBase.class, CallMethodRegHelper.InstClass.VARINST, "isReal", new Class[] {}));
        registerForVarX(ctx, "is_bool", new CallMethodRegHelper("is_bool", VarBase.class, CallMethodRegHelper.InstClass.VARINST, "isBool", new Class[] {}));
        registerForVarX(ctx, "is_string", new CallMethodRegHelper("is_string", VarBase.class, CallMethodRegHelper.InstClass.VARINST, "isString", new Class[] {}));
        registerForVarX(ctx, "is_array", new CallMethodRegHelper("is_array", VarBase.class, CallMethodRegHelper.InstClass.VARINST, "isArray", new Class[] {}));
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
    abstract public VarArray arrayVal();
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

    public static VarBase doOperation(ASTRcdBase b, VarBase varLeft, Operation op, VarBase varRight) throws ExecuteException {
        
        VarBase newValue = null;
        
        switch (op) {
            case PLUS:
                newValue = doOperationPLUS(varLeft, varRight);
                break;
            case MINUS:
                newValue = doOperationMINUS(varLeft, varRight);
                break;
            case UPLUS:
                newValue = doOperationUPLUS(varRight);
                break;
            case UMINUS:
                newValue = doOperationUMINUS(varRight);
                break;
            case EQUAL:
                newValue = doOperationEQUAL(varLeft, varRight);
                break;
            case NOTEQUAL:
                newValue = doOperationNOTEQUAL(varLeft, varRight);
                break;
            case MUL:
                newValue = doOperationMUL(varLeft, varRight);
                break;
            case DIV:
                newValue = doOperationDIV(varLeft, varRight);
                break;
            case GT:
                newValue = doOperationGT(varLeft, varRight);
                break;
            case LT:
                newValue = doOperationLT(varLeft, varRight);
                break;
            case GTE:
                newValue = doOperationGTE(varLeft, varRight);
                break;
            case LTE:
                newValue = doOperationLTE(varLeft, varRight);
                break;
            case AND:
                newValue = doOperationAND(varLeft, varRight);
                break;
            case OR:
                newValue = doOperationOR(varLeft, varRight);
                break;
            default:
                throw b.generateExecuteException("ERROR operation<"+op+"> is not supported");
        }
        if (newValue == null) {
            throw b.generateExecuteException("ERROR operation<"+op+"> cannot operate on <"+varLeft.getTypeString()+"> and <"+varRight.getTypeString()+">");
        }

        return newValue;
    }

    private static VarBase doOperationPLUS(VarBase varLeft, VarBase varRight) {
        VarBase newValue = null;

        switch (varLeft.getType()) {
            case INT:
                if (varRight.getType() != VarType.REAL) {
                    long resultInt = varLeft.intVal() + varRight.intVal();
                    newValue = new VarInt(""+resultInt);
                } else {
                    double resultReal = varLeft.realVal() + varRight.realVal();
                    newValue = new VarReal(""+resultReal);
                }
                break;
            case REAL: 
                double resultReal = varLeft.realVal() + varRight.realVal();
                newValue = new VarReal(""+resultReal);
                break;
            case STRING: 
                String resultString = varLeft.stringVal() + varRight.stringVal();
                newValue = new VarString(""+resultString);
                break;
            case BOOL:
                // Not supported
                break;
            case ARRAY: 
                newValue = new VarArray(varLeft.arrayVal());
                ((VarArray)newValue).addArray((VarArray)varRight);
                break;
                
        }
        if (newValue != null) {
            newValue.setOperationImage("("+varLeft.getOperationImage()+")+("+varRight.getOperationImage()+")");
        }
        return newValue;
    }

    private static VarBase doOperationUPLUS(VarBase varRight) {
        VarBase newValue = null;
        long resultInt;
        
        switch (varRight.getType()) {
            case INT:
                resultInt = varRight.intVal();
                newValue = new VarInt(""+resultInt);
                break;
            case REAL: 
                double resultReal = varRight.realVal();
                newValue = new VarReal(""+resultReal);
                break;
            default: 
                resultInt = varRight.intVal();
                newValue = new VarInt(""+resultInt);
                break;
                
        }
        if (newValue != null) {
            newValue.setOperationImage("+("+varRight.getOperationImage()+")");
        }
        return newValue;
    }

    private static VarBase doOperationMINUS(VarBase varLeft, VarBase varRight) {
        VarBase newValue = null;

        switch (varLeft.getType()) {
            case INT:
                if (varRight.getType() != VarType.REAL) {
                    long resultInt = varLeft.intVal() - varRight.intVal();
                    newValue = new VarInt(""+resultInt);
                } else {
                    double resultReal = varLeft.realVal() - varRight.realVal();
                    newValue = new VarReal(""+resultReal);
                }
                break;
            case REAL: 
                double resultReal = varLeft.realVal() - varRight.realVal();
                newValue = new VarReal(""+resultReal);
                break;
            case STRING: 
                String resultString = varLeft.stringVal().replaceAll(varRight.stringVal(), "");
                newValue = new VarString(""+resultString);
                break;
            case BOOL:
                // Not supported
                break;
            case ARRAY: 
                // Not supported
                break;
                
        }
        if (newValue != null) {
            newValue.setOperationImage("("+varLeft.getOperationImage()+")-("+varRight.getOperationImage()+")");
        }
        return newValue;
    }
    
    private static VarBase doOperationUMINUS(VarBase varRight) {
        VarBase newValue = null;
        long resultInt;
        
        switch (varRight.getType()) {
            case INT:
                resultInt = -varRight.intVal();
                newValue = new VarInt(""+resultInt);
                break;
            case REAL: 
                double resultReal = -varRight.realVal();
                newValue = new VarReal(""+resultReal);
                break;
            default: 
                resultInt = -varRight.intVal();
                newValue = new VarInt(""+resultInt);
                break;
                
        }
        if (newValue != null) {
            newValue.setOperationImage("-("+varRight.getOperationImage()+")");
        }
        return newValue;
    }

    private static VarBase doOperationEQUAL(VarBase varLeft, VarBase varRight) {
        VarBase newValue = null;
        boolean resultBool;

        switch (varLeft.getType()) {
            case INT:
                if (varRight.getType() != VarType.REAL) {
                    resultBool = varLeft.intVal() == varRight.intVal();
                    newValue = new VarBool(""+resultBool);
                } else {
                    resultBool = varLeft.realVal() == varRight.realVal();
                    newValue = new VarBool(""+resultBool);
                }
                break;
            case REAL: 
                resultBool = varLeft.realVal() == varRight.realVal();
                newValue = new VarBool(""+resultBool);
                break;
            case STRING: 
                resultBool = varLeft.stringVal().contentEquals(varRight.stringVal());
                newValue = new VarBool(""+resultBool);
                break;
            case BOOL:
                resultBool = varLeft.boolVal() == varRight.boolVal();
                newValue = new VarBool(""+resultBool);
                break;
            case ARRAY: 
                // Not supported
                break;
                
        }
        if (newValue != null) {
            newValue.setOperationImage("("+varLeft.getOperationImage()+")==("+varRight.getOperationImage()+")");
        }
        return newValue;
    }

    private static VarBase doOperationNOTEQUAL(VarBase varLeft, VarBase varRight) {
        VarBase equalValue = doOperationEQUAL(varLeft, varRight);
        boolean notEqual = !equalValue.boolVal();
        VarBase newValue = new VarBool(""+notEqual);
        newValue.setOperationImage("NOT("+equalValue.getOperationImage()+")");
        
        return newValue;
    }
    
    private static VarBase doOperationMUL(VarBase varLeft, VarBase varRight) {
        VarBase newValue = null;

        switch (varLeft.getType()) {
            case INT:
                if (varRight.getType() != VarType.REAL) {
                    long resultInt = varLeft.intVal() * varRight.intVal();
                    newValue = new VarInt(""+resultInt);
                } else {
                    double resultReal = varLeft.realVal() * varRight.realVal();
                    newValue = new VarReal(""+resultReal);
                }
                break;
            case REAL: 
                double resultReal = varLeft.realVal() * varRight.realVal();
                newValue = new VarReal(""+resultReal);
                break;
                
        }
        if (newValue != null) {
            newValue.setOperationImage("("+varLeft.getOperationImage()+")*("+varRight.getOperationImage()+")");
        }
        return newValue;
    }

    private static VarBase doOperationDIV(VarBase varLeft, VarBase varRight) {
        VarBase newValue = null;

        switch (varLeft.getType()) {
            case INT:
                if (varRight.getType() != VarType.REAL) {
                    long resultInt = varLeft.intVal() / varRight.intVal();
                    newValue = new VarInt(""+resultInt);
                } else {
                    double resultReal = varLeft.realVal() / varRight.realVal();
                    newValue = new VarReal(""+resultReal);
                }
                break;
            case REAL: 
                double resultReal = varLeft.realVal() / varRight.realVal();
                newValue = new VarReal(""+resultReal);
                break;
                
        }
        if (newValue != null) {
            newValue.setOperationImage("("+varLeft.getOperationImage()+")/("+varRight.getOperationImage()+")");
        }
        return newValue;
    }

    private static VarBase doOperationGT(VarBase varLeft, VarBase varRight) {
        VarBase newValue = null;
        boolean resultBool;

        switch (varLeft.getType()) {
            case INT:
                if (varRight.getType() != VarType.REAL) {
                    resultBool = varLeft.intVal() > varRight.intVal();
                    newValue = new VarBool(""+resultBool);
                } else {
                    resultBool = varLeft.realVal() > varRight.realVal();
                    newValue = new VarBool(""+resultBool);
                }
                break;
            case REAL: 
                resultBool = varLeft.realVal() > varRight.realVal();
                newValue = new VarBool(""+resultBool);
                break;
            case STRING: 
                resultBool = varLeft.stringVal().compareTo(varRight.stringVal()) > 0;
                newValue = new VarBool(""+resultBool);
                break;
                
        }
        if (newValue != null) {
            newValue.setOperationImage("("+varLeft.getOperationImage()+")>("+varRight.getOperationImage()+")");
        }
        return newValue;
    }

    private static VarBase doOperationLT(VarBase varLeft, VarBase varRight) {
        VarBase newValue = null;
        boolean resultBool;

        switch (varLeft.getType()) {
            case INT:
                if (varRight.getType() != VarType.REAL) {
                    resultBool = varLeft.intVal() < varRight.intVal();
                    newValue = new VarBool(""+resultBool);
                } else {
                    resultBool = varLeft.realVal() < varRight.realVal();
                    newValue = new VarBool(""+resultBool);
                }
                break;
            case REAL: 
                resultBool = varLeft.realVal() < varRight.realVal();
                newValue = new VarBool(""+resultBool);
                break;
            case STRING: 
                resultBool = varLeft.stringVal().compareTo(varRight.stringVal()) < 0;
                newValue = new VarBool(""+resultBool);
                break;
                
        }
        if (newValue != null) {
            newValue.setOperationImage("("+varLeft.getOperationImage()+")<("+varRight.getOperationImage()+")");
        }
        return newValue;
    }

    private static VarBase doOperationGTE(VarBase varLeft, VarBase varRight) {
        VarBase newValue = null;
        boolean resultBool;

        switch (varLeft.getType()) {
            case INT:
                if (varRight.getType() != VarType.REAL) {
                    resultBool = varLeft.intVal() >= varRight.intVal();
                    newValue = new VarBool(""+resultBool);
                } else {
                    resultBool = varLeft.realVal() >= varRight.realVal();
                    newValue = new VarBool(""+resultBool);
                }
                break;
            case REAL: 
                resultBool = varLeft.realVal() >= varRight.realVal();
                newValue = new VarBool(""+resultBool);
                break;
            case STRING: 
                resultBool = varLeft.stringVal().compareTo(varRight.stringVal()) >= 0;
                newValue = new VarBool(""+resultBool);
                break;
                
        }
        if (newValue != null) {
            newValue.setOperationImage("("+varLeft.getOperationImage()+")>=("+varRight.getOperationImage()+")");
        }
        return newValue;
    }

    private static VarBase doOperationLTE(VarBase varLeft, VarBase varRight) {
        VarBase newValue = null;
        boolean resultBool;

        switch (varLeft.getType()) {
            case INT:
                if (varRight.getType() != VarType.REAL) {
                    resultBool = varLeft.intVal() <= varRight.intVal();
                    newValue = new VarBool(""+resultBool);
                } else {
                    resultBool = varLeft.realVal() <= varRight.realVal();
                    newValue = new VarBool(""+resultBool);
                }
                break;
            case REAL: 
                resultBool = varLeft.realVal() <= varRight.realVal();
                newValue = new VarBool(""+resultBool);
                break;
            case STRING: 
                resultBool = varLeft.stringVal().compareTo(varRight.stringVal()) <= 0;
                newValue = new VarBool(""+resultBool);
                break;
                
        }
        if (newValue != null) {
            newValue.setOperationImage("("+varLeft.getOperationImage()+")<=("+varRight.getOperationImage()+")");
        }
        return newValue;
    }

    private static VarBase doOperationOR(VarBase varLeft, VarBase varRight) {
        VarBase newValue = null;
        boolean resultBool;

        switch (varLeft.getType()) {
            case INT:
            case REAL: 
            case BOOL: 
                resultBool = varLeft.boolVal() || varRight.boolVal();
                newValue = new VarBool(""+resultBool);
                break;
        }
        if (newValue != null) {
            newValue.setOperationImage("("+varLeft.getOperationImage()+") OR ("+varRight.getOperationImage()+")");
        }
        return newValue;
    }

    private static VarBase doOperationAND(VarBase varLeft, VarBase varRight) {
        VarBase newValue = null;
        boolean resultBool;

        switch (varLeft.getType()) {
            case INT:
            case REAL: 
            case BOOL: 
                resultBool = varLeft.boolVal() && varRight.boolVal();
                newValue = new VarBool(""+resultBool);
                break;
        }
        if (newValue != null) {
            newValue.setOperationImage("("+varLeft.getOperationImage()+") AND ("+varRight.getOperationImage()+")");
        }
        return newValue;
    }

}
