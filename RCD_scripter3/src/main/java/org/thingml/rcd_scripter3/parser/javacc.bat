@REM
@REM Copyright (C) 2016 SINTEF <steffen.dalgard@sintef.no>
@REM
@REM Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
@REM you may not use this file except in compliance with the License.
@REM You may obtain a copy of the License at
@REM
@REM 	http://www.gnu.org/licenses/lgpl-3.0.txt
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
@REM

java -cp ..\..\..\..\..\..\..\..\javacc-6.1.2\target\javacc-6.1.2.jar jjtree  RCD_scripter3.jjt
java -cp ..\..\..\..\..\..\..\..\javacc-6.1.2\target\javacc-6.1.2.jar javacc  RCD_scripter3.jj