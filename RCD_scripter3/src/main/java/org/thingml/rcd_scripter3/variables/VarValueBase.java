/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter3.variables;

/**
 *
 * @author steffend
 */
abstract public class VarValueBase extends VarBase {
 
    public enum Operation { PLUS, MINUS, MUL, DIV };
    public enum VarType { INT, STRING };
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
    
    abstract public String getTypeString();
    abstract public VarType getType();

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

    public static VarValueBase doOperation(VarBase varLeft, Operation op, VarBase varRight) {
        
        VarValueBase newValue = null;

        switch (op) {
            case PLUS:
                newValue = doOperationPlus(varLeft, varRight);
                break;
            case MINUS:
                newValue = doOperationMinus(varLeft, varRight);
                break;
            case MUL:
                newValue = doOperationMul(varLeft, varRight);
                break;
            case DIV:
                newValue = doOperationDiv(varLeft, varRight);
                break;
        }
        return newValue;
    }

    private static VarValueBase doOperationStringPlus(String valueLeft, String valueRight) {

        //Any + Any -> String
        String leftString = "??? doOperationStringPlus() leftString is null";
        if (valueLeft != null) leftString = valueLeft;

        String rightString = "??? doOperationStringPlus() rightString is null"; 
        if (valueRight != null) rightString = valueRight;

        String result = leftString + rightString;
        VarValueBase newValue = new VarValueString(result);
        
        return newValue;
    }

    private static VarValueBase doOperationStringMinus(String valueLeft, String valueRight) {

        //Any - Any -> String
        String leftString = "??? doOperationStringMinus() leftString is null";
        if (valueLeft != null) leftString = valueLeft;

        String rightString = "??? doOperationStringMinus() rightString is null"; 
        if (valueRight != null) rightString = valueRight;

        String result = leftString.replaceAll(rightString, "");
        VarValueBase newValue = new VarValueString(result);
        
        return newValue;
    }

    private static VarValueBase doOperationPlus(VarBase varLeft, VarBase varRight) {
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
                    newValue =  doOperationStringPlus(valueLeft.getString(), valueRight.getString());
                    //String result = valueLeft.getString() + valueRight.getString();
                    //newValue = new VarValueString(result);
                }
            }
        } catch (Exception x) {
            // Nothing to do 
        }
        
        if (newValue == null) {
            //Any - Any -> String
            newValue =  doOperationStringPlus(varLeft.getString(), varRight.getString());
        }
        return newValue;
    }

    private static VarValueBase doOperationMinus(VarBase varLeft, VarBase varRight) {
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
                    newValue =  doOperationStringMinus(valueLeft.getString(), valueRight.getString());
                }
            }
        } catch (Exception x) {
            // Nothing to do 
        }
        
        if (newValue == null) {
            //Any - Any -> String
            newValue =  doOperationStringMinus(varLeft.getString(), varRight.getString());
        }

        return newValue;
        
    }

    private static VarValueBase doOperationMul(VarBase varLeft, VarBase varRight) {
        VarValueBase newValue = new VarValueString("ERROR in operation MUL");

        try {
            VarValueBase valueLeft = (VarValueBase)varLeft; 
            VarValueBase valueRight = (VarValueBase)varRight;
            boolean sameType = valueLeft.getType() == valueRight.getType();

            if (sameType) {
                if (valueLeft.getType() == VarType.INT) {
                    int result = ((VarValueInt) valueLeft).getInt() * ((VarValueInt) valueRight).getInt();
                    newValue = new VarValueInt(""+result);
                    newValue.setOperationImage("("+valueLeft.getOperationImage()+")*("+valueRight.getOperationImage()+")");
                }
            }
        
        } catch (Exception x) {
            // Nothing to do 
        }
        return newValue;
    }

    private static VarValueBase doOperationDiv(VarBase varLeft, VarBase varRight) {
        VarValueBase newValue = new VarValueString("ERROR in operation DIV");

        try {
            VarValueBase valueLeft = (VarValueBase)varLeft; 
            VarValueBase valueRight = (VarValueBase)varRight;
            boolean sameType = valueLeft.getType() == valueRight.getType();

            if (sameType) {
                if (valueLeft.getType() == VarType.INT) {
                    int result = ((VarValueInt) valueLeft).getInt() / ((VarValueInt) valueRight).getInt();
                    newValue = new VarValueInt(""+result);
                    newValue.setOperationImage("("+valueLeft.getOperationImage()+")/("+valueRight.getOperationImage()+")");
                }
            }
        } catch (Exception x) {
            // Nothing to do 
        }
        return newValue;
    }
}
