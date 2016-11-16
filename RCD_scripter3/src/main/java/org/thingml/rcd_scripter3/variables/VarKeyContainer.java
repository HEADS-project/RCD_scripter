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
public class VarKeyContainer implements Cloneable{
 
    protected String key;
    protected VarContainer container;
    
    public VarKeyContainer(String id, VarContainer value) {
        this.key = id;
        this.container = value;
    }

    public String getKey() {
        return key;
    }
    
    public VarContainer getContainer() {
        return container;
    }
    
    public String stringVal() {
        String ret = key + ":" + container.stringVal();
        return ret;
    }

    @Override
    protected VarKeyContainer clone() throws CloneNotSupportedException {
        return new VarKeyContainer(key, container.clone());
    }    
        
    public String getTypeString() {
        return "KeyValue:" + stringVal();
    }

    public String printString(){
        String ret = key+":"+container.printString();
        return ret;
    }
}
