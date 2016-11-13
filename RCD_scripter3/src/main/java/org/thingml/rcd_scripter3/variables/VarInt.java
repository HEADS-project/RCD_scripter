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
public class VarInt extends VarBase {
    
    private int intValue = 0;
    
    public VarInt(String image) {
        super(image);
        intValue = Integer.decode(image);
    }
    
    //private static HashMap<String, CallMethod> callMethods = new HashMap<String, CallMethod>();
    
    public static void registerMethods()throws Exception{
        //callMethods.put("add", new CallMethod("add", VarHash.class, "addHash", new Class[] { VarHash.class }));
    }

    @Override
    public VarType getType() {
        return VarType.INT;
    }
    
    @Override
    public long intVal() {
        return intValue;
    }    
    
    @Override
    public double realVal() {
        return intVal();
    }
    
    @Override
    public boolean boolVal() {
        return intVal() == 0;
    }
    
    @Override
    public String stringVal() {
        return ""+intVal();
    }

    @Override
    public Object getValObj(){
        Object ret;
        
        ret = intVal();
        return ret;
    }

    @Override
    public boolean isInt() {
        return true;
    }
    
    @Override
    public String printString() {
        String ret = "<"+getTypeString()+" Image:"+stringVal()+" Value:"+intValue+" Operations:"+getOperationImage()+">";
        return ret;
    }

    
}
