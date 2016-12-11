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
import org.thingml.rcd_scripter3.parser.*;
import java.io.FileReader;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.thingml.rcd_scripter3.proc.ProcBaseIf;
import org.thingml.rcd_scripter3.proc.ProcDateTime;
import org.thingml.rcd_scripter3.proc.ProcPrint;
import org.thingml.rcd_scripter3.proc.ProcPrintf;
import org.thingml.rcd_scripter3.proc.ProcType;
import org.thingml.rcd_scripter3.variables.VarArray;
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarFile;
import org.thingml.rcd_scripter3.variables.VarInt;
import org.thingml.rcd_scripter3.variables.VarString;

/**
 *
 * @author steffend
 */
/*for testing the parser class*/ 
public class RCD_scripterTest { 
    public static void main(String[] args) throws org.apache.commons.cli.ParseException { 
// create Options object
        Options cliOptions = new Options();

        // add t option
        cliOptions.addOption("a", "ast", false, "Print AST");
        cliOptions.addOption("i", "input", false, "Input file");
        cliOptions.addOption("d", "doc", false, "Print doc");
        CommandLineParser cliParser = new DefaultParser();
        CommandLine cmd = cliParser.parse( cliOptions, args);

        RcdScript3Parser parser;
        ExecuteContext jobContext = new ExecuteContext();
        ASTRcdStart ast;
        
        String inputfile = "C:/javasrc/RCD_scripter/RCD_scripter3/src/main/java/org/thingml/rcd_scripter3/RCD_script_1.h";
        //String inputfile = "C:/javasrc/RCD_scripter/RCD_scripter3/src/main/java/org/thingml/rcd_scripter3/RCD_script.h";
        //String inputfile = "C:/javasrc/RCD_scripter/RCD_scripter3/src/main/java/org/thingml/rcd_scripter3/RCD_script_mod4.h";
        //String inputfile = "C:/javasrc/RCD_scripter/RCD_scripter3/src/main/java/org/thingml/rcd_scripter3/app_msgdef3-vartest.h";
        //String inputfile = "RCD_script.h";
        
        // get input option value
        String inputOption = cmd.getOptionValue("i");
        if(inputOption != null) {
            inputfile = inputOption;
        }
        
        
        System.out.println("RCD scripter START");
        try{
            //jobContext.setTrace(true);
            ProcPrint.registerProcs(jobContext);
            ProcType.registerProcs(jobContext);
            ProcPrintf.registerProcs(jobContext);
            ProcDateTime.registerProcs(jobContext);

            VarFile.registerMethods(jobContext);
            VarBase.registerMethods(jobContext);
            VarString.registerMethods(jobContext);
            VarArray.registerMethods(jobContext);
             
            System.out.println("Parser init using file <"+inputfile+">");
            System.out.println("<");
            parser = new RcdScript3Parser(new FileReader(inputfile)); 
            System.out.println(">");

            System.out.println("Parse file and generate AST...");
            System.out.println("<");
            ast = parser.makeAst(); 
            System.out.println(">");
        
            if(cmd.hasOption("a")) {
                ast.dump("Main: ");
            }
            
            ast.run(jobContext);
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        System.out.println("\nRCD scripter END");
    }
}