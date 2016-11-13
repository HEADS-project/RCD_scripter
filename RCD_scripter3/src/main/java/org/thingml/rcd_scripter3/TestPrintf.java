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
package org.thingml.rcd_scripter3;

import java.lang.reflect.Method;

/**
 *
 * @author steffend
 */
public class TestPrintf {
    public static void main(String[] args) { 
        String format = "Test %d <%s>\n";
        int num = 5;
        String txt = "txt";
        System.out.printf(format, num, txt);
        
        try {
            Class<?> c = Class.forName("java.io.PrintStream");
            Method[] methods = c.getMethods();
            for (int i = 0; i < methods.length; i++) {
                //System.out.println(methods[i]);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } 

        System.out.println("*******************************");
        try {
            Class<?> c = Class.forName("java.io.PrintStream");
            Class[] cArg = new Class[2];
            cArg[0] = java.lang.String.class;
            cArg[1] = java.lang.Object[].class;
            Method method = c.getMethod("printf", cArg);
            System.out.println(method);

            Object[] arguments = new Object[] { new String("Mynum %d %d\n"),
                new Object[] { new Integer(10), new Integer(20) } };
            method.invoke(System.out, arguments);

            Object[] o0Arg = new Object[2];
            Object[] vArg = new Object[3];
            o0Arg[0] = "Mynum2 %d %d %f\n";
            o0Arg[1] = vArg;
            vArg[0] = 30;
            vArg[1] = 450;
            vArg[2] = 3.14;

            
            method.invoke(System.out, o0Arg);
        } catch (Exception ex) {
            System.out.println(ex);
        } 
    }
    
}
