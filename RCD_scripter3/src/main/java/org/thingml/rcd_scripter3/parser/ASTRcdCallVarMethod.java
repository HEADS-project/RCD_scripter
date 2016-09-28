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

package org.thingml.rcd_scripter3.parser;

import org.thingml.rcd_scripter3.ExecuteContext;
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarFile;
import org.thingml.rcd_scripter3.variables.VarHash;
import org.thingml.rcd_scripter3.variables.VarHashList;
import org.thingml.rcd_scripter3.variables.VarId;
import org.thingml.rcd_scripter3.variables.VarValArray;
import org.thingml.rcd_scripter3.variables.VarValueBase;
import org.thingml.rcd_scripter3.variables.VarValueInt;
import org.thingml.rcd_scripter3.variables.VarValueString;

public class ASTRcdCallVarMethod extends ASTRcdBase {

  /**
   * Constructor.
   * @param id the id
   */
  public ASTRcdCallVarMethod(int id) {
    super(id);
  }

    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
        boolean found = false;
        int baseStackSize = ctx.getVarStackSize();
        
        executeChildren(ctx);
        int addedStackElems = ctx.getVarStackSize() - baseStackSize;
        int argNum = addedStackElems-2;
        VarBase[] args = new VarBase[argNum];
        for (int i = 0; i < argNum; i++) {
            args[argNum - i - 1] = ctx.popVar(this);
        }
        
        VarId id = ctx.popVarX(this, VarId.class);
        VarBase var = ctx.popVar(this);
        
        String method = id.getString();

        if (var instanceof VarHash) {
            VarHash varHash = (VarHash) var;

            if (method.equalsIgnoreCase("add")) {
                found = true;
                if (argNum == 1) {
                    VarBase arg = args[0];
                    if (arg instanceof VarHash) {
                        varHash.addHash((VarHash) arg);
                    } else {
                        throw generateExecuteException("ERROR method Hash.add() cannot add <"+arg.getType()+">");
                    }
                } else {
                    throw generateExecuteException("ERROR method Hash.add() accepts 1 arg given "+argNum+" arg(s)");
                }
            }
        }
        
        if (var instanceof VarHashList) {
            VarHashList varHashList = (VarHashList) var;
            
            if (method.equalsIgnoreCase("add")) {
                found = true;
                if (argNum == 1) {
                    VarBase arg = args[0];
                    if (arg instanceof VarHash) {
                        varHashList.addHash((VarHash) arg);
                    } else {
                        throw generateExecuteException("ERROR method HashList.add() cannot add <"+arg.getType()+">");
                    }
                } else {
                    throw generateExecuteException("ERROR method HashList.add() accepts 1 arg given "+argNum+" arg(s)");
                }
            }

            if (method.equalsIgnoreCase("setdef")) {
                found = true;
                if (argNum == 1) {
                    VarBase arg = args[0];
                    if (arg instanceof VarHash) {
                        varHashList.setDefaultHash((VarHash) arg);
                    } else {
                        throw generateExecuteException("ERROR method HashList.setdef() cannot use <"+arg.getType()+">");
                    }
                } else {
                    throw generateExecuteException("ERROR method HashList.setdef() accepts 1 arg given "+argNum+" arg(s)");
                }
            }
        }

        if (var instanceof VarFile) {
            VarFile varFile = (VarFile) var;
            
            if (method.equalsIgnoreCase("open")) {
                found = true;
                if (argNum == 1) {
                    VarBase arg = args[0];
                    if (arg instanceof VarValueString) {
                        varFile.open(this, arg.getString());
                    } else {
                        throw generateExecuteException("ERROR method File.open() cannot use <"+arg.getType()+">");
                    }
                } else {
                    throw generateExecuteException("ERROR method File.open() accepts 1 arg given "+argNum+" arg(s)");
                }
            }

            if (method.equalsIgnoreCase("print")) {
                found = true;
                if (argNum == 1) {
                    VarBase arg = args[0];
                    if (arg instanceof VarBase) {
                        varFile.write(this, arg.getString());
                    } else {
                        throw generateExecuteException("ERROR method File.print() cannot print <"+arg.getType()+">");
                    }
                } else {
                    throw generateExecuteException("ERROR method File.print() accepts 1 arg given "+argNum+" arg(s)");
                }
            }
            
            if (method.equalsIgnoreCase("println")) {
                found = true;
                if (argNum == 1) {
                    VarBase arg = args[0];
                    if (arg instanceof VarBase) {
                        varFile.write(this, arg.getString()+"\n");
                    } else {
                        throw generateExecuteException("ERROR method File.println() cannot print <"+arg.getType()+">");
                    }
                } else if (argNum == 0) {
                    varFile.write(this, "\n");
                } else {
                    throw generateExecuteException("ERROR method File.println() accepts 1 arg given "+argNum+" arg(s)");
                }
            }
            
            if (method.equalsIgnoreCase("close")) {
                found = true;
                if (argNum == 0) {
                    varFile.close(this);
                } else {
                    throw generateExecuteException("ERROR method File.close() accepts 0 arg given "+argNum+" arg(s)");
                }
            }
        }

        if (var instanceof VarValArray) {
            VarValArray varValArray = (VarValArray) var;

            if (method.equalsIgnoreCase("setsize_default")) {
                found = true;
                if (argNum == 2) {
                    VarBase arg1 = args[0];
                    VarBase arg2 = args[1];
                    if (arg1 instanceof VarValueInt) {
                        if (arg2 instanceof VarValueBase) {
                            varValArray.setSizeAndDefaultValue(((VarValueInt)arg1).getInt(), (VarValueBase)arg2);
                        } else {
                            throw generateExecuteException("ERROR method ValArray.setsize_default() cannot add <"+arg2.getType()+"> as second param");
                        }
                    } else {
                        throw generateExecuteException("ERROR method ValArray.setsize_default() cannot add <"+arg1.getType()+"> as first param");
                    }
                } else {
                    throw generateExecuteException("ERROR method ValArray.setsize_default() accepts 2 arg given "+argNum+" arg(s)");
                }
            }
        }
        

        if (!found) {
            throw generateExecuteException("ERROR method <"+method+"> is not defined for "+var.getTypeString());
        }
    }
}
