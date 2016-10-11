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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.thingml.rcd_scripter3.parser.ASTRcdBase;
import org.thingml.rcd_scripter3.parser.ExecuteException;

/**
 *
 * @author steffend
 */
public class VarFile extends VarBase {
 
    private String fileName = "";
    private String keyword = "";
    private boolean modeInsert = false;
    private List<String> originalInsertFileLines = null;
    private String contentToInsert = "";
    private int keywordStartLine = -1;
    private int keywordEndLine = -1;
    private BufferedReader bufferedReader = null;
    private BufferedWriter bufferedWriter = null;
    private boolean isOpen = false;
    
    public VarFile() {
    }

    public void open(ASTRcdBase b, String fileName) throws ExecuteException {
        this.fileName = fileName;
        modeInsert = false;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(this.fileName));
        } catch (IOException ex) {
            b.generateExecuteException("ERROR opening file <"+fileName+">\n"+ex);
        }
        isOpen = true;
    }

    public void insert(ASTRcdBase b, String fileName, String keyword) throws ExecuteException {
        this.fileName = fileName;
        this.keyword = keyword;
        modeInsert = true;
        try {
            List<String> originalInsertFileLines = Files.readAllLines(Paths.get(this.fileName), Charset.defaultCharset());

            int bakNum = -1;
            try {
                // Find next unused backup filename
                File sFolder;
                do {
                    bakNum++;
                    sFolder = new File(this.fileName+"."+bakNum+".sbak");
                } while (sFolder.exists());
                
                // Make backup and search for keyword
                bufferedWriter = new BufferedWriter(new FileWriter(this.fileName+"."+bakNum+".sbak"));
                for (int i=0; i<originalInsertFileLines.size(); i++){
                    String line = originalInsertFileLines.get(i);
                    bufferedWriter.write(line+"\n");
                    if (line.contains(keyword+"_start")) {
                        if (keywordStartLine == -1) {
                            keywordStartLine = i;  
                        } else {
                            b.generateExecuteException("ERROR found multiple keywords <"+keyword+"_start"+"> at line "+keywordStartLine+" and "+i+"\n");
                        }
                    }
                    if (line.contains(keyword+"_end")) {
                        if (keywordEndLine == -1) {
                            keywordEndLine = i;  
                        } else {
                            b.generateExecuteException("ERROR found multiple keywords <"+keyword+"_end"+"> at line "+keywordEndLine+" and "+i+"\n");
                        }
                    }
                }
                bufferedWriter.close();
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(this.fileName));
                } catch (IOException ex) {
                    b.generateExecuteException("ERROR opening file <"+fileName+">\n"+ex);
                }
            } catch (IOException ex) {
                b.generateExecuteException("ERROR writing file <"+fileName+"."+bakNum+".sbak"+">\n"+ex);
            }

            bufferedWriter = new BufferedWriter(new FileWriter(this.fileName));
        } catch (IOException ex) {
            b.generateExecuteException("ERROR reading file <"+fileName+">\n"+ex);
        }
        isOpen = true;
    }

    public void close(ASTRcdBase b) throws ExecuteException {
        if (modeInsert) {
            if (isOpen) {
                try {
                    boolean contentToBeWritten = true;
                    for (int i=0; i<originalInsertFileLines.size(); i++){
                        String line = originalInsertFileLines.get(i);
                        if ((i <= keywordStartLine) || (i >= keywordEndLine)) {
                            bufferedWriter.write(line+"\n");
                        } else {
                            if (contentToBeWritten) {
                                contentToBeWritten = false;
                                bufferedWriter.write(contentToInsert);
                            }
                        }
                    }
                } catch (IOException ex) {
                    b.generateExecuteException("ERROR writing to file <"+fileName+">\n"+ex);
                }
            } else {
                b.generateExecuteException("ERROR writing to closed file <"+fileName+">\n");
            }
        }
        
        try {
            bufferedWriter.close();
        } catch (IOException ex) {
            b.generateExecuteException("ERROR closing file <"+fileName+">\n"+ex);
        }
        isOpen = false;
    }
    
    public void write(ASTRcdBase b, String txt) throws ExecuteException {
        if (isOpen) {
            if (modeInsert) {
                txt = contentToInsert+txt;
            } else {
                try {
                    bufferedWriter.write(txt);
                } catch (IOException ex) {
                    b.generateExecuteException("ERROR writing to file <"+fileName+">\n"+ex);
                }
            }
        } else {
            b.generateExecuteException("ERROR writing to closed file <"+fileName+">\n");
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
        return fileName;
    }

}
