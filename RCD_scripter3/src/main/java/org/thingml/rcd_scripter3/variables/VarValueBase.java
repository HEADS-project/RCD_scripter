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
abstract public class VarValueBase extends VarBase {
 
    public enum Operation { PLUS, MINUS, MUL, DIV, EQUAL, GT, LT, GTE, LTE, NOTEQUAL };
    
    protected String image;
    protected String operationImage;
    
    public VarValueBase(String image) {
        this.image = image;
        this.operationImage = image;
    }

    public String getString() {
        return image;
    }

    public void setOperationImage(String image) {
        this.operationImage = image;
    }
    
    public String getOperationImage() {
        return operationImage;
    }
    

    public String printString() {
        String ret = "<"+getTypeString()+":"+getString()+">";
        return ret;
    }

    public boolean compareTypeAndVal(VarValueBase value_other) {
        boolean eq = false;
        if( getType() == value_other.getType() ) {
            if(compareVal(value_other) == true) {
                eq = true; 
            }
        }
        return eq;
    }
    
    protected boolean compareVal(VarValueBase value_other) {
        return image.contentEquals(value_other.image);
    }

    public static VarValueBase doOperation(ASTRcdBase b, VarBase varLeft, Operation op, VarBase varRight) throws ExecuteException {
        
        VarValueBase newValue = null;
        
        String leftTypeString = "NULL";
        if (varLeft != null) leftTypeString = varLeft.getTypeString();
        
        String rightTypeString = "NULL";
        if (varRight != null) rightTypeString = varRight.getTypeString();

        switch (op) {
            case PLUS:
                newValue = doOperationPLUS(b, varLeft, varRight);
                break;
            case MINUS:
                newValue = doOperationMINUS(b, varLeft, varRight);
                break;
            default:
                try {
                    VarValueBase valueLeft = (VarValueBase)varLeft; 
                    VarValueBase valueRight = (VarValueBase)varRight;
                    switch (op) {
                        case MUL:
                            newValue = doOperationMUL(b, valueLeft, valueRight);
                            break;
                        case DIV:
                            newValue = doOperationDIV(b, valueLeft, valueRight);
                            break;
                        case EQUAL:
                            newValue = doOperationEQUAL(b, valueLeft, valueRight);
                            break;
                        case GT:
                            newValue = doOperationGT(b, valueLeft, valueRight);
                            break;
                        case LT:
                            newValue = doOperationLT(b, valueLeft, valueRight);
                            break;
                        case GTE:
                            newValue = doOperationGTE(b, valueLeft, valueRight);
                            break;
                        case LTE:
                            newValue = doOperationLTE(b, valueLeft, valueRight);
                            break;
                        case NOTEQUAL:
                            newValue = doOperationNOTEQUAL(b, valueLeft, valueRight);
                            break;
                        default:
                            throw b.generateExecuteException("ERROR operation<"+op+"> is not supported");
                    }
                } catch (Exception x) {
                    throw b.generateExecuteException("ERROR operation<"+op+"> cannot operate on <"+leftTypeString+"> and <"+rightTypeString+">");
                }
        }
        if (newValue == null) {
            throw b.generateExecuteException("ERROR operation<"+op+"> cannot operate on <"+leftTypeString+"> and <"+rightTypeString+">");
        }

        return newValue;
    }

    private static VarValueBase doOperationStringPLUS(ASTRcdBase b, String valueLeft, String valueRight) throws ExecuteException {

        //Any + Any -> String
        if (valueLeft == null) return null;
        if (valueRight == null) return null;
        String result = valueLeft + valueRight;
        //String leftString = "??? doOperationStringPlus() leftString is null";
        //if (valueLeft != null) leftString = valueLeft;

        //String rightString = "??? doOperationStringPlus() rightString is null"; 
        //if (valueRight != null) rightString = valueRight;

        //String result = leftString + rightString;
        VarValueBase newValue = new VarValueString(result);
        
        return newValue;
    }

    private static VarValueBase doOperationStringMINUS(ASTRcdBase b, String valueLeft, String valueRight) throws ExecuteException {

        //Any - Any -> String
        if (valueLeft == null) return null;
        if (valueRight == null) return null;
        String result = valueLeft.replaceAll(valueRight, "");
        
        //String leftString = "??? doOperationStringMinus() leftString is null";
        //if (valueLeft != null) leftString = valueLeft;

        //String rightString = "??? doOperationStringMinus() rightString is null"; 
        //if (valueRight != null) rightString = valueRight;

        //String result = leftString.replaceAll(rightString, "");
        VarValueBase newValue = new VarValueString(result);
        
        return newValue;
    }

    private static VarValueBase doOperationPLUS(ASTRcdBase b, VarBase varLeft, VarBase varRight) throws ExecuteException {
        VarValueBase newValue = null;

        try {
            VarValueBase valueLeft = (VarValueBase)varLeft; 
            VarValueBase valueRight = (VarValueBase)varRight;
            boolean sameType = valueLeft.getType() == valueRight.getType();

            if (sameType) {
                if (valueLeft.getType() == VarType.INT) {
                    //INT + INT -> INT
                    int result = ((VarValueInt) valueLeft).getInt() + ((VarValueInt) valueRight).getInt();
                    newValue = new VarValueInt(""+result);
                    newValue.setOperationImage("("+valueLeft.getOperationImage()+")+("+valueRight.getOperationImage()+")");
                }
                if (valueLeft.getType() == VarType.STRING) {
                    //String + String -> String
                    newValue =  doOperationStringPLUS(b, valueLeft.getString(), valueRight.getString());
                    //String result = valueLeft.getString() + valueRight.getString();
                    //newValue = new VarValueString(result);
                }
            }
        } catch (Exception x) {
            // Nothing to do 
        }
        
        if (newValue == null) {
            //Any - Any -> String
            newValue =  doOperationStringPLUS(b, varLeft.getString(), varRight.getString());
        }
        return newValue;
    }

    private static VarValueBase doOperationMINUS(ASTRcdBase b, VarBase varLeft, VarBase varRight) throws ExecuteException {
        VarValueBase newValue = null;

        try {
            VarValueBase valueLeft = (VarValueBase)varLeft; 
            VarValueBase valueRight = (VarValueBase)varRight;
            boolean sameType = valueLeft.getType() == valueRight.getType();

            if (sameType) {
                if (valueLeft.getType() == VarType.INT) {
                    int result = ((VarValueInt) valueLeft).getInt() - ((VarValueInt) valueRight).getInt();
                    newValue = new VarValueInt(""+result);
                    newValue.setOperationImage("("+valueLeft.getOperationImage()+")-("+valueRight.getOperationImage()+")");
                }
                if (valueLeft.getType() == VarType.STRING) {
                    //String - String -> String
                    newValue =  doOperationStringMINUS(b, valueLeft.getString(), valueRight.getString());
                }
            }
        } catch (Exception x) {
            // Nothing to do 
        }
        
        if (newValue == null) {
            //Any - Any -> String
            newValue =  doOperationStringMINUS(b, varLeft.getString(), varRight.getString());
        }

        return newValue;
        
    }

    private static VarValueBase doOperationMUL(ASTRcdBase b, VarValueBase valueLeft, VarValueBase valueRight) throws ExecuteException {
        VarValueBase newValue = null;

        boolean sameType = valueLeft.getType() == valueRight.getType();

        if (sameType) {
            if (valueLeft.getType() == VarType.INT) {
                int result = ((VarValueInt) valueLeft).getInt() * ((VarValueInt) valueRight).getInt();
                newValue = new VarValueInt(""+result);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")*("+valueRight.getOperationImage()+")");
            }
        } else {
            
        }
        
        return newValue;
    }

    private static VarValueBase doOperationDIV(ASTRcdBase b, VarValueBase valueLeft, VarValueBase valueRight) throws ExecuteException {
        VarValueBase newValue = null;

        boolean sameType = valueLeft.getType() == valueRight.getType();

        if (sameType) {
            if (valueLeft.getType() == VarType.INT) {
                int result = ((VarValueInt) valueLeft).getInt() / ((VarValueInt) valueRight).getInt();
                newValue = new VarValueInt(""+result);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")/("+valueRight.getOperationImage()+")");
            }
        }
        return newValue;
    }
    
    private static VarValueBase doOperationEQUAL(ASTRcdBase b, VarValueBase valueLeft, VarValueBase valueRight) throws ExecuteException {
        VarValueBase newValue = null;
        boolean equal = false;

        boolean sameType = valueLeft.getType() == valueRight.getType();

        if (sameType) {
            if (valueLeft.getType() == VarType.INT) {
                equal = ((VarValueInt) valueLeft).getInt() == ((VarValueInt) valueRight).getInt();
                newValue = new VarValueBool(equal);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")==("+valueRight.getOperationImage()+")");
            }
            if (valueLeft.getType() == VarType.STRING) {
                equal = valueLeft.getString().contentEquals(valueRight.getString());
                newValue = new VarValueBool(equal);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")==("+valueRight.getOperationImage()+")");
            }
        }
        
        if (newValue == null) {
            newValue = new VarValueBool(false);
            newValue.setOperationImage("("+valueLeft.getOperationImage()+")==("+valueRight.getOperationImage()+")");
        }
        return newValue;
    }
}
