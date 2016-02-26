/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.thingml.rcd_scripter2.variables;

/**
 *
 * @author steffend
 */
abstract public class VarValueBase extends VarBase {
 
    public enum Operation { STRPLUS, PLUS, MINUS, MUL, DIV };
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

    public static VarValueBase doOperation(VarValueBase valueLeft, Operation op, VarValueBase valueRight) {
        
        VarValueBase newValue = null;

        switch (op) {
            case STRPLUS:
                newValue = doOperationStringPlus(valueLeft, valueRight);
                break;
            case PLUS:
                newValue = doOperationPlus(valueLeft, valueRight);
                break;
            case MINUS:
                newValue = doOperationMinus(valueLeft, valueRight);
                break;
            case MUL:
                newValue = doOperationMul(valueLeft, valueRight);
                break;
            case DIV:
                newValue = doOperationDiv(valueLeft, valueRight);
                break;
        }
        return newValue;
    }

    private static VarValueBase doOperationStringPlus(VarValueBase valueLeft, VarValueBase valueRight) {

        //Any + Any -> String
        String leftString = "??? doOperationStringPlus() leftString is null";
        if (valueLeft != null) leftString = valueLeft.getString();

        String rightString = "??? doOperationStringPlus() rightString is null"; 
        if (valueRight != null) rightString = valueRight.getString();

        String result = leftString + rightString;
        VarValueBase newValue = new VarValueString(result);
        
        return newValue;
    }

    private static VarValueBase doOperationPlus(VarValueBase valueLeft, VarValueBase valueRight) {
        VarValueBase newValue = null;
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
                String result = valueLeft.getString() + valueRight.getString();
                newValue = new VarValueString(result);
            }
        } else {
            //Any + Any -> String
            String result = valueLeft.getString() + valueRight.getString();
            newValue = new VarValueString(result);
        }
        
        if (newValue == null) {
            newValue = new VarValueString("ERROR in operation PLUSS");
        }
        return newValue;
    }

    private static VarValueBase doOperationMinus(VarValueBase valueLeft, VarValueBase valueRight) {
        VarValueBase newValue = null;
        boolean sameType = valueLeft.getType() == valueRight.getType();

        if (sameType) {
            if (valueLeft.getType() == VarType.INT) {
                int result = ((VarValueInt) valueLeft).getInt() - ((VarValueInt) valueRight).getInt();
                newValue = new VarValueInt(""+result);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")-("+valueRight.getOperationImage()+")");
            }
        }
        
        if (newValue == null) {
            newValue = new VarValueString("ERROR in operation MINUS");
        }
        return newValue;
    }

    private static VarValueBase doOperationMul(VarValueBase valueLeft, VarValueBase valueRight) {
        VarValueBase newValue = null;
        boolean sameType = valueLeft.getType() == valueRight.getType();

        if (sameType) {
            if (valueLeft.getType() == VarType.INT) {
                int result = ((VarValueInt) valueLeft).getInt() * ((VarValueInt) valueRight).getInt();
                newValue = new VarValueInt(""+result);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")*("+valueRight.getOperationImage()+")");
            }
        }
        
        if (newValue == null) {
            newValue = new VarValueString("ERROR in operation MUL");
        }
        return newValue;
    }

    private static VarValueBase doOperationDiv(VarValueBase valueLeft, VarValueBase valueRight) {
        VarValueBase newValue = null;
        boolean sameType = valueLeft.getType() == valueRight.getType();

        if (sameType) {
            if (valueLeft.getType() == VarType.INT) {
                int result = ((VarValueInt) valueLeft).getInt() / ((VarValueInt) valueRight).getInt();
                newValue = new VarValueInt(""+result);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")/("+valueRight.getOperationImage()+")");
            }
        }
        
        if (newValue == null) {
            newValue = new VarValueString("ERROR in operation Div");
        }
        return newValue;
    }
}
