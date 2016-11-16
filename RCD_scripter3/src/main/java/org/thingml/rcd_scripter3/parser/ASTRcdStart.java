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
import org.thingml.rcd_scripter3.variables.VarContainer;
import org.thingml.rcd_scripter3.variables.VarInt;

public class ASTRcdStart extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdStart(int id) {
      super(id);
    }

    public ASTRcdStart(RcdScript3Parser p, int id) {
      super(p, id);
    }

    public int run (ExecuteContext ctx) throws ExecuteException {
        VarContainer exitCon = ctx.getContainer(this, "exitVal");
        exitCon.setInst(new VarInt("1"));

        
        ExecResult execRes = executeChildren(ctx);
        int ret = (int)exitCon.getInst().intVal();
        System.out.println("The script ended with exit code <"+ret+">");
        return ret;
    }
			
    public ExecResult execute (ExecuteContext ctx) throws ExecuteException {
         return executeChildren(ctx);
    }

}
