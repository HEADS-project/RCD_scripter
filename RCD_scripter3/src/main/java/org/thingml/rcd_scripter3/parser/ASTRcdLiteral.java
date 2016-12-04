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
import org.thingml.rcd_scripter3.variables.VarBool;
import org.thingml.rcd_scripter3.variables.VarContainer;
import org.thingml.rcd_scripter3.variables.VarInt;
import org.thingml.rcd_scripter3.variables.VarReal;
import org.thingml.rcd_scripter3.variables.VarString;

public class ASTRcdLiteral extends ASTRcdBase {

    public enum ValType {BOOL, INT, ID, STRING, REAL, NONE};
    private ValType valType = ValType.NONE;

    private final String CR = "\r";
    private final String NL = "\n";
    private final String TAB = "\t";
    private final String QUOTE = "\"";

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdLiteral(int id) {
      super(id);
    }

    public void setValType (ValType vt) {
        valType = vt;
    }
    
    @Override
    public ExecResult execute(ExecuteContext ctx) throws ExecuteException {
        String image = getName();
        VarBase thisBase = null;

        switch (valType) {
            case BOOL:
                thisBase = new VarBool(image);
                break;
            case INT:
                thisBase = new VarInt(image);
                break;
            case REAL:
                thisBase = new VarReal(image);
                break;
            case ID:
                thisBase = new VarString(image);
                break;
            case STRING:
                image = image.substring(0, image.length()-1).substring(1);
                image = image.replace("\\r", CR);
                image = image.replace("\\n", NL);
                image = image.replace("\\t", TAB);
                image = image.replace("\\\"", QUOTE);
                thisBase = new VarString(image);
                break;
            default:
                throw generateExecuteException("ERROR ValType <"+valType.toString()+"> is not supported");
        }
        ctx.pushContainer(new VarContainer (thisBase));
        return ExecResult.NORMAL;
    }
  
}
