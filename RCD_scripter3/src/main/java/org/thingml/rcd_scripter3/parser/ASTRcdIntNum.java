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
import org.thingml.rcd_scripter3.variables.VarValueInt;

public class ASTRcdIntNum extends ASTRcdBase {

    /**
     * Constructor.
     * @param id the id
     */
    public ASTRcdIntNum(int id) {
      super(id);
    }

    @Override
    public boolean execute(ExecuteContext ctx) throws ExecuteException {
        boolean execContinue = true;
        String image = getName();

        ctx.pushVar(new VarValueInt(image));
        return execContinue;
    }
  
}
