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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.thingml.rcd_scripter3.ExecuteContext;

/**
 *
 * @author steffend
 */
public class ProcDateTime {
    private static SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    
    public static void registerProcs(ExecuteContext ctx)throws Exception{
        ctx.declProc(null, "timenow", new CallProcRegHelper("timenow", ProcDateTime.class, "getTimeNow", new Class[] {} ));
        ctx.declProc(null, "timeformat", new CallProcRegHelper("timeformat", ProcDateTime.class, "setTimeFormat", new Class[] {java.lang.String.class} ));
    }
    
    public static String getTimeNow() {
        return timestampFormat.format( Calendar.getInstance().getTime());
    }
    
    public static void setTimeFormat(String format) {
        timestampFormat = new SimpleDateFormat(format);
    }
    
}
