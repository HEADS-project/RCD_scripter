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
import org.thingml.rcd_scripter3.variables.VarFile;
import org.thingml.rcd_scripter3.variables.VarHash;
import org.thingml.rcd_scripter3.variables.VarHashList;
import org.thingml.rcd_scripter3.variables.VarValArray;
import org.thingml.rcd_scripter3.variables.VarValueBase;

public class ASTRcdType extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    Class typeClass;
    boolean foundType = false;
    
    public ASTRcdType(int id) {
      super(id);
    }

    public Class getTypeClass() {
        
        if (!foundType) {
            String image = getName().toLowerCase();
            if (image.contentEquals("void")) {
                typeClass = null;
                foundType = true;
            } else if (image.contentEquals("hashlist")) {
                typeClass = VarHashList.class;
                foundType = true;
            } else if (image.contentEquals("hash")) {
                typeClass = VarHash.class;
                foundType = true;
            } else if (image.contentEquals("file")) {
                typeClass = VarFile.class;
                foundType = true;
            } else if (image.contentEquals("value")) {
                typeClass = VarValueBase.class;
                foundType = true;
            } else if (image.contentEquals("valarray")) {
                typeClass = VarValArray.class;
                foundType = true;
            }
        }
        return typeClass;
    }
    
    @Override
    public void execute(ExecuteContext ctx) throws ExecuteException {
    }

}
