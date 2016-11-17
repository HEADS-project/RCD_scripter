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
import org.thingml.rcd_scripter3.variables.VarArray;
import org.thingml.rcd_scripter3.variables.VarBase;
import org.thingml.rcd_scripter3.variables.VarContainer;
import org.thingml.rcd_scripter3.variables.VarKeyContainer;

public class ASTRcdTupl extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdTupl(int id) {
      super(id);
    }

    @Override
    public ExecResult execute(ExecuteContext ctx) throws ExecuteException {
        ExecResult ret;
        
        ret = executeChildren(ctx);
        VarContainer val = ctx.popContainer(this);
        VarContainer key = ctx.popContainer(this);
        
        VarKeyContainer kv = new VarKeyContainer(key.stringVal(), val);
        VarContainer c = new VarContainer(new VarArray(kv));
        
        ctx.pushContainer(c);
        return ret;
    }
  
    

}
