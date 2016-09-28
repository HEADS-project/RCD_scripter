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
public class VarKeyValue extends VarBase {
 
    protected String key;
    protected VarValueBase value;
    
    public VarKeyValue(String id, VarValueBase value) {
        this.key = id;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    
    public VarValueBase getValue() {
        return value;
    }
    
    public String getString() {
        String ret = key + ":" + value.getString();
        return ret;
    }
    
    @Override
    public String getTypeString() {
        return "KeyValue:" + getString();
    }

    @Override
    public VarType getType() {
        return VarType.KEYVALUE;
    }
    
    @Override
    public String printString(){
        String ret = key+":"+value.printString();
        return ret;
    }

    public boolean compareTypeAndVal(VarKeyValue keyvalue_other) {
        return value.compareTypeAndVal(keyvalue_other.value);
    }
    
}
