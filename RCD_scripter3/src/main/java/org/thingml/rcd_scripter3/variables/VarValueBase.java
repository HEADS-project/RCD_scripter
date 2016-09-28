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
                newValue = doOperationVarPLUS(varLeft, varRight);
                break;
            case MINUS:
                newValue = doOperationVarMINUS(varLeft, varRight);
                break;
            case EQUAL:
                newValue = doOperationVarEQUAL(varLeft, varRight);
                break;
            case NOTEQUAL:
                newValue = doOperationVarNOTEQUAL(varLeft, varRight);
                break;
            default:
                try {
                    VarValueBase valueLeft = (VarValueBase)varLeft; 
                    VarValueBase valueRight = (VarValueBase)varRight;
                    switch (op) {
                        case MUL:
                            newValue = doOperationValMUL(valueLeft, valueRight);
                            break;
                        case DIV:
                            newValue = doOperationValDIV(valueLeft, valueRight);
                            break;
                        case GT:
                            newValue = doOperationValGT(valueLeft, valueRight);
                            break;
                        case LT:
                            newValue = doOperationValLT(valueLeft, valueRight);
                            break;
                        case GTE:
                            newValue = doOperationValGTE(valueLeft, valueRight);
                            break;
                        case LTE:
                            newValue = doOperationValLTE(valueLeft, valueRight);
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

    private static VarValueBase doOperationStringPLUS(String valueLeft, String valueRight) {

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

    private static VarValueBase doOperationStringMINUS(String valueLeft, String valueRight) {

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

    private static VarValueBase doOperationStringEQUAL(String valueLeft, String valueRight) {

        //Any == Any -> Bool
        if (valueLeft == null) return null;
        if (valueRight == null) return null;
        Boolean result = valueLeft.contentEquals(valueRight);
        
        VarValueBase newValue = new VarValueBool(""+result);
        
        return newValue;
    }

    private static VarValueBase doOperationVarPLUS(VarBase varLeft, VarBase varRight) {
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
                    newValue =  doOperationStringPLUS(valueLeft.getString(), valueRight.getString());
                    newValue.setOperationImage("("+valueLeft.getOperationImage()+")+("+valueRight.getOperationImage()+")");
                    //String result = valueLeft.getString() + valueRight.getString();
                    //newValue = new VarValueString(result);
                }
            }
        } catch (Exception x) {
            // Nothing to do 
        }
        
        if (newValue == null) {
            //Any - Any -> String
            newValue =  doOperationStringPLUS(varLeft.getString(), varRight.getString());
        }
        return newValue;
    }

    private static VarValueBase doOperationVarMINUS(VarBase varLeft, VarBase varRight) {
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
                    newValue =  doOperationStringMINUS(valueLeft.getString(), valueRight.getString());
                    newValue.setOperationImage("("+valueLeft.getOperationImage()+")-("+valueRight.getOperationImage()+")");
                }
            }
        } catch (Exception x) {
            // Nothing to do 
        }
        
        if (newValue == null) {
            //Any - Any -> String
            newValue =  doOperationStringMINUS(varLeft.getString(), varRight.getString());
        }

        return newValue;
        
    }

    private static VarValueBase doOperationVarEQUAL(VarBase varLeft, VarBase varRight) {
        VarValueBase newValue = null;
        boolean equal;

        try {
            VarValueBase valueLeft = (VarValueBase)varLeft; 
            VarValueBase valueRight = (VarValueBase)varRight;
            boolean sameType = valueLeft.getType() == valueRight.getType();

            if (sameType) {
                if (valueLeft.getType() == VarType.INT) {
                    equal = ((VarValueInt) valueLeft).getInt() == ((VarValueInt) valueRight).getInt();
                    newValue = new VarValueBool(""+equal);
                    newValue.setOperationImage("("+valueLeft.getOperationImage()+")==("+valueRight.getOperationImage()+")");
                }
                if (valueLeft.getType() == VarType.STRING) {
                    equal = valueLeft.getString().contentEquals(valueRight.getString());
                    newValue = new VarValueBool(""+equal);
                    newValue.setOperationImage("("+valueLeft.getOperationImage()+")==("+valueRight.getOperationImage()+")");
                }
                if (valueLeft.getType() == VarType.ID) {
                    equal = valueLeft.getString().contentEquals(valueRight.getString());
                    newValue = new VarValueBool(""+equal);
                    newValue.setOperationImage("("+valueLeft.getOperationImage()+")==("+valueRight.getOperationImage()+")");
                }
                if (valueLeft.getType() == VarType.BOOL) {
                    equal = ((VarValueBool) valueLeft).getBool() == ((VarValueBool) valueRight).getBool();
                    newValue = new VarValueBool(""+equal);
                    newValue.setOperationImage("("+valueLeft.getOperationImage()+")==("+valueRight.getOperationImage()+")");
                }
            }
        } catch (Exception x) {
            // Nothing to do 
        }
        
        if (newValue == null) {
            //Any == Any -> Bool
            newValue =  doOperationStringEQUAL(varLeft.getString(), varRight.getString());
        }

        return newValue;
    }
    
    private static VarValueBase doOperationVarNOTEQUAL(VarBase varLeft, VarBase varRight) {
        VarValueBase equalValue = doOperationVarEQUAL(varLeft, varRight);
        boolean notEqual = !((VarValueBool)equalValue).getBool();
        VarValueBase newValue = new VarValueBool(""+notEqual);
        newValue.setOperationImage("NOT("+equalValue.getOperationImage()+")");
        
        return newValue;
    }
    
    private static VarValueBase doOperationValMUL(VarValueBase valueLeft, VarValueBase valueRight) {
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

    private static VarValueBase doOperationValDIV(VarValueBase valueLeft, VarValueBase valueRight) {
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
    
    private static VarValueBase doOperationValGT(VarValueBase valueLeft, VarValueBase valueRight) {
        VarValueBase newValue = null;
        boolean gt = false;

        boolean sameType = valueLeft.getType() == valueRight.getType();

        if (sameType) {
            if (valueLeft.getType() == VarType.INT) {
                gt = ((VarValueInt) valueLeft).getInt() > ((VarValueInt) valueRight).getInt();
                newValue = new VarValueBool(""+gt);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")>("+valueRight.getOperationImage()+")");
            }
            if (valueLeft.getType() == VarType.STRING) {
                int relation = valueLeft.getString().compareTo(valueRight.getString());
                gt = relation > 0;
                newValue = new VarValueBool(""+gt);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")>("+valueRight.getOperationImage()+")");
            }
        }
        
        return newValue;
    }
    
    private static VarValueBase doOperationValGTE(VarValueBase valueLeft, VarValueBase valueRight) {
        VarValueBase newValue = null;
        boolean gte = false;

        boolean sameType = valueLeft.getType() == valueRight.getType();

        if (sameType) {
            if (valueLeft.getType() == VarType.INT) {
                gte = ((VarValueInt) valueLeft).getInt() >= ((VarValueInt) valueRight).getInt();
                newValue = new VarValueBool(""+gte);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")>=("+valueRight.getOperationImage()+")");
            }
            if (valueLeft.getType() == VarType.STRING) {
                int relation = valueLeft.getString().compareTo(valueRight.getString());
                gte = relation >= 0;
                newValue = new VarValueBool(""+gte);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")>=("+valueRight.getOperationImage()+")");
            }
        }
        
        return newValue;
    }

    private static VarValueBase doOperationValLT(VarValueBase valueLeft, VarValueBase valueRight) {
        VarValueBase newValue = null;
        boolean lt = false;

        boolean sameType = valueLeft.getType() == valueRight.getType();

        if (sameType) {
            if (valueLeft.getType() == VarType.INT) {
                lt = ((VarValueInt) valueLeft).getInt() < ((VarValueInt) valueRight).getInt();
                newValue = new VarValueBool(""+lt);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")<("+valueRight.getOperationImage()+")");
            }
            if (valueLeft.getType() == VarType.STRING) {
                int relation = valueLeft.getString().compareTo(valueRight.getString());
                lt = relation < 0;
                newValue = new VarValueBool(""+lt);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")<("+valueRight.getOperationImage()+")");
            }
        }
        
        return newValue;
    }
    
    private static VarValueBase doOperationValLTE(VarValueBase valueLeft, VarValueBase valueRight) {
        VarValueBase newValue = null;
        boolean lte = false;

        boolean sameType = valueLeft.getType() == valueRight.getType();

        if (sameType) {
            if (valueLeft.getType() == VarType.INT) {
                lte = ((VarValueInt) valueLeft).getInt() <= ((VarValueInt) valueRight).getInt();
                newValue = new VarValueBool(""+lte);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")<=("+valueRight.getOperationImage()+")");
            }
            if (valueLeft.getType() == VarType.STRING) {
                int relation = valueLeft.getString().compareTo(valueRight.getString());
                lte = relation <= 0;
                newValue = new VarValueBool(""+lte);
                newValue.setOperationImage("("+valueLeft.getOperationImage()+")<=("+valueRight.getOperationImage()+")");
            }
        }
        
        return newValue;
    }
}
