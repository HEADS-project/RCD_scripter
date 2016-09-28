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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ExecuteException;

/**
 *
 * @author steffend
 */
public class VarFile extends VarBase {
 
    private String name = "";
    private BufferedWriter bufferedWriter = null;
    private boolean isOpen = false;
    
    public VarFile() {
    }

    public void open(ASTRcdBase b, String name) throws ExecuteException {
        this.name = name;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(this.name));
        } catch (IOException ex) {
            b.generateExecuteException("ERROR opening file <"+name+">\n"+ex);
        }
        isOpen = true;
    }

    public void close(ASTRcdBase b) throws ExecuteException {
        try {
            bufferedWriter.close();
        } catch (IOException ex) {
            b.generateExecuteException("ERROR closing file <"+name+">\n"+ex);
        }
        isOpen = false;
    }
    
    public void write(ASTRcdBase b, String txt) throws ExecuteException {
        if (isOpen) {
            try {
                bufferedWriter.write(txt);
            } catch (IOException ex) {
                b.generateExecuteException("ERROR writing to file <"+name+">\n"+ex);
            }
        } else {
            b.generateExecuteException("ERROR writing to closed file <"+name+">\n");
        }
    }
    
    @Override
    public String getTypeString() {
        return "File";
    }

    @Override
    public VarType getType() {
        return VarType.FILE;
    }

    @Override
    public String printString() {
        String ret = "File: " + getString();
        return ret;
    }
    
    @Override
    public String getString() {
        return name;
    }

}
