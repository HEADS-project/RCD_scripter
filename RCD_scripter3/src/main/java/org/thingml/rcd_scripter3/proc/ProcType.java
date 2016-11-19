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
package org.thingml.rcd_scripter3.proc;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ASTRcdBase.ExecResult;
import org.thingml.rcd_scripter3.parser.ExecuteException;
import org.thingml.rcd_scripter3.variables.VarArray;
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarBool;
import org.thingml.rcd_scripter3.variables.VarContainer;
import org.thingml.rcd_scripter3.variables.VarFile;
import org.thingml.rcd_scripter3.variables.VarInt;
import org.thingml.rcd_scripter3.variables.VarReal;
import org.thingml.rcd_scripter3.variables.VarString;

/**
 *
 * @author steffend
 */
public class ProcType {
    public static void registerProcs(ExecuteContext ctx)throws Exception{
        ctx.declProc(null, "gettype", new CallProcRegHelper("gettype", ProcType.class, "getType", new Class[] {VarContainer.class} ));
        ctx.declProc(null, "isint", new CallProcRegHelper("isint", ProcType.class, "isInt", new Class[] {VarContainer.class} ));
        ctx.declProc(null, "isreal", new CallProcRegHelper("isreal", ProcType.class, "isReal", new Class[] {VarContainer.class} ));
        ctx.declProc(null, "isbool", new CallProcRegHelper("isbool", ProcType.class, "isBool", new Class[] {VarContainer.class} ));
        ctx.declProc(null, "isstring", new CallProcRegHelper("isstring", ProcType.class, "isString", new Class[] {VarContainer.class} ));
        ctx.declProc(null, "isarray", new CallProcRegHelper("isarray", ProcType.class, "isArray", new Class[] {VarContainer.class} ));
        ctx.declProc(null, "intval", new CallProcRegHelper("intval", ProcType.class, "intVal", new Class[] {VarContainer.class} ));
        ctx.declProc(null, "realval", new CallProcRegHelper("realval", ProcType.class, "realVal", new Class[] {VarContainer.class} ));
        ctx.declProc(null, "strval", new CallProcRegHelper("strval", ProcType.class, "strVal", new Class[] {VarContainer.class} ));
        ctx.declProc(null, "boolval", new CallProcRegHelper("boolval", ProcType.class, "boolVal", new Class[] {VarContainer.class} ));
        ctx.declProc(null, "file", new CallProcRegHelper("file", ProcType.class, "file", new Class[] {} ));
        ctx.declProc(null, "array", new CallProcRegHelper("array", ProcType.class, "array", new Class[] {} ));
    }
    
    public static String getType(VarContainer vc) {
        return vc.getTypeString();
    }
    
    public static boolean isInt(VarContainer vc) {
        return vc.isInt();
    }
    
    public static boolean isReal(VarContainer vc) {
        return vc.isReal();
    }
    
    public static boolean isBool(VarContainer vc) {
        return vc.isBool();
    }
    
    public static boolean isString(VarContainer vc) {
        return vc.isString();
    }
    
    public static boolean isArray(VarContainer vc) {
        return vc.isArray();
    }
    
    public static VarContainer intVal(VarContainer vc) {
        return new VarContainer( new VarInt(""+vc.intVal()));
    }
    
    public static VarContainer intReal(VarContainer vc) {
        return new VarContainer( new VarReal(""+vc.realVal()));
    }
    
    public static VarContainer strVal(VarContainer vc) {
        return new VarContainer( new VarString(""+vc.stringVal()));
    }
    
    public static VarContainer boolVal(VarContainer vc) {
        return new VarContainer( new VarBool(""+vc.boolVal()));
    }
        
    public static VarContainer file() {
        return new VarContainer( new VarFile());
    }
        
    public static VarContainer array() {
        return new VarContainer( new VarArray());
    }
        
}
