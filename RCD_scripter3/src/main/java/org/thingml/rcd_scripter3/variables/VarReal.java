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

/**
 *
 * @author steffend
 */
public class VarReal extends VarBase {
    
    private double realValue = 0;
    
    public VarReal(String image) {
        super(image);
        realValue = Double.parseDouble(image);
    }
    
    //private static HashMap<String, CallMethod> callMethods = new HashMap<String, CallMethod>();
    
    public static void registerMethods()throws Exception{
        //callMethods.put("add", new CallMethod("add", VarHash.class, "addHash", new Class[] { VarHash.class }));
    }
    
    @Override
    public VarType getType() {
        return VarType.REAL;
    }
    
    @Override
    public long intVal() {
        return (long)realVal();
    }    
    
    @Override
    public double realVal() {
        return realValue;
    }
    
    @Override
    public boolean boolVal() {
        return realVal() == 0.0;
    }
    
    @Override
    public String stringVal() {
        return ""+realVal();
    }

    @Override
    public VarArray arrayVal() {
        return new VarArray(this);
    }
    
    @Override
    public Object getValObj(){
        Object ret;
        
        ret = intVal();
        return ret;
    }

    @Override
    public boolean isReal() {
        return true;
    }
    
    @Override
    public String printString() {
        String ret = "<"+getTypeString()+" Image:"+stringVal()+" Value:"+realValue+" Operations:"+getOperationImage()+">";
        return ret;
    }

    
}
