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
import java.nio.file.StandardCopyOption;
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
    private String fileNameTmp = "";
    private String keyword = "";
    private boolean modeInsert = false;
    private List<String> originalInsertFileLines = null;
    private String contentToInsert = "";
    private int keywordStartLine = -1;
    private int keywordEndLine = -1;
    private BufferedWriter bufferedWriterTmp = null;
    private boolean isOpen = false;
    
    public VarFile() {
    }

    public void open(ASTRcdBase b, String fileName) throws ExecuteException {
        this.fileName = fileName;
        modeInsert = false;

        // Prepare writing to tmp file
        createTmpFile(b);

        // Read all lines for later comparison
        readAllLines(b);
        
        isOpen = true;
    }

    public void insert(ASTRcdBase b, String fileName, String keyword) throws ExecuteException {
        this.fileName = fileName;
        this.keyword = keyword;
        modeInsert = true;
        originalInsertFileLines = null;
        contentToInsert = "";
        keywordStartLine = -1;
        keywordEndLine = -1;
        bufferedWriterTmp = null;
        
        // Read all lines for later comparison
        readAllLines(b);

        // Prepare writing to tmp file
        createTmpFile(b);

        // Search for keywords
        for (int i=0; i<originalInsertFileLines.size(); i++){
            String line = originalInsertFileLines.get(i);
            if (line.contains(keyword+"_START")) {
                if (keywordStartLine == -1) {
                    keywordStartLine = i;  
                } else {
                    b.generateExecuteException("ERROR found multiple keywords <"+keyword+"_START"+"> at line "+keywordStartLine+" and "+i+" in file <"+this.fileName+">\n");
                }
            }
            if (line.contains(keyword+"_END")) {
                if (keywordEndLine == -1) {
                    keywordEndLine = i;  
                } else {
                    b.generateExecuteException("ERROR found multiple keywords <"+keyword+"_END"+"> at line "+keywordEndLine+" and "+i+" in file <"+this.fileName+">\n");
                }
            }
        }

        if (keywordStartLine == -1) {
            b.generateExecuteException("ERROR cannot find keyword <"+keyword+"_START"+"> in file <"+this.fileName+">\n");
        }
        if (keywordEndLine == -1) {
            b.generateExecuteException("ERROR cannot find keyword <"+keyword+"_END"+"> in file <"+this.fileName+">\n");
        }

        isOpen = true;
    }

    public void write(ASTRcdBase b, String txt) throws ExecuteException {
        if (isOpen) {
            if (modeInsert) {
                contentToInsert = contentToInsert+txt;
            } else {
                try {
                    bufferedWriterTmp.write(txt);
                } catch (IOException ex) {
                    b.generateExecuteException("ERROR writing to file <"+fileNameTmp+">\n"+ex);
                }
            }
        } else {
            b.generateExecuteException("ERROR writing to closed file <"+fileNameTmp+">\n");
        }
    }
    
    public void close(ASTRcdBase b) throws ExecuteException {
        if (isOpen) {
            if (modeInsert) {
                // Create tmp insert file
                try {
                    for (int i=0; i<originalInsertFileLines.size(); i++){
                        String line = originalInsertFileLines.get(i);
                        if ((i <= keywordStartLine) || (i >= keywordEndLine)) {
                            bufferedWriterTmp.write(line+"\n");
                        }
                        if (i == keywordStartLine) {
                            bufferedWriterTmp.write(contentToInsert);
                        }
                    }
                } catch (IOException ex) {
                    b.generateExecuteException("ERROR writing to file <"+fileNameTmp+">\n"+ex);
                }
            }
        
            // Close tmp file
            try {
                bufferedWriterTmp.close();
            } catch (IOException ex) {
                b.generateExecuteException("ERROR closing file <"+fileNameTmp+">\n"+ex);
            }

            // Compare tmp file and original file
            List<String> generatedTmpFileLines = null;
            try {
                generatedTmpFileLines = Files.readAllLines(Paths.get(this.fileNameTmp), Charset.defaultCharset());
            } catch (IOException ex) {
                b.generateExecuteException("ERROR reading file <"+fileNameTmp+">\n"+ex);
            }

            boolean sameContent = true; // Assume the same content as in the original

            if (originalInsertFileLines.size() != generatedTmpFileLines.size()) {
                sameContent = false;
            } else {
                for (int i = 0; i < originalInsertFileLines.size(); i++) {
                    if (originalInsertFileLines.get(i).contentEquals(generatedTmpFileLines.get(i)) == false) {
                        sameContent = false;
                    }
                }
            }

            // Replace if tmp file and original file is different
            if (sameContent == false) {
                if (modeInsert) {
                    // Make backup of original file
                    createBakFile(b);
                }

                try {
                    Files.copy(Paths.get(this.fileNameTmp), Paths.get(fileName), StandardCopyOption.valueOf("REPLACE_EXISTING"));                
                } catch (IOException ex) {
                    b.generateExecuteException("ERROR copying file <"+fileNameTmp+"> to <"+fileName+">\n"+ex);
                }

                try {
                    Files.delete(Paths.get(this.fileNameTmp));
                } catch (IOException ex) {
                    b.generateExecuteException("ERROR deleting file <"+fileNameTmp+">\n"+ex);
                }
            }

            isOpen = false;
        } else {
            b.generateExecuteException("ERROR writing to closed file <"+fileNameTmp+">\n");
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

    private void createTmpFile(ASTRcdBase b) {
        int tmpNum = -1;
        try {
            // Find next unused backup filename
            File sFolder;
            do {
                tmpNum++;
                fileNameTmp = fileName+"."+tmpNum+".stmp";
                sFolder = new File(fileNameTmp);
            } while (sFolder.exists());
            bufferedWriterTmp = new BufferedWriter(new FileWriter(fileNameTmp));
        } catch (IOException ex) {
            b.generateExecuteException("ERROR making tmp file <"+fileNameTmp+">\n"+ex);
        }
        
    }
    
    private void createBakFile(ASTRcdBase b) {
        int bakNum = -1;
        String fileNameBak = "";
        try {
            // Find next unused backup filename
            File sFolder;
            do {
                bakNum++;
                fileNameBak = fileName+"."+bakNum+".sbak";
                sFolder = new File(fileNameBak);
            } while (sFolder.exists());
            // Make backup 
            Files.copy(Paths.get(this.fileName), Paths.get(fileNameBak));                
        } catch (IOException ex) {
            b.generateExecuteException("ERROR making bak file <"+fileNameBak+">\n"+ex);
        }
        
    }

    private void readAllLines(ASTRcdBase b) {
        try {
            originalInsertFileLines = Files.readAllLines(Paths.get(this.fileName), Charset.defaultCharset());
        } catch (IOException ex) {
            b.generateExecuteException("ERROR reading file <"+fileName+">\n"+ex);
        }
        
    }
    
}
