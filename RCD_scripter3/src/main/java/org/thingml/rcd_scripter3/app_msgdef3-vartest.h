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
//## Definition of coders with attributes separated by whitespace and terminated by 

DEF $MsgDef = HASHLIST();
DEF $MsgDef2 = HASHLIST($Input);


DEF $Hash0 = HASH();
DEF $Hash1 = HASH($Input);
DEF $Hash2 = HASH({sd:"kl"});
DEF $VarArray0 = VARARRAY();
DEF $VarArray1 = VARARRAY($Input);
DEF $VarArray2 = VARARRAY(5);
DEF $VarArray3 = VARARRAY(5 + $var);
DEF $VarArray4 = VARARRAY(5 + $var[5]);
DEF $VarArray5 = VARARRAY(5 + $var[id]);
DEF $VarArray6 = VARARRAY(5 + $var[$var5]);
DEF $VarArray7 = VARARRAY(5 + $var["var5"]);
DEF $GenFile1 = FILE("app_msg_gen.h"+"mere"*4);
DEF $GenFile2 = FILE(4 * 4 / 4 + 0x4 - -4 + +4 + $variname);
DEF $GenFile3 = FILE(1.1 + +2.2 + -3.3 + -4.4E-4 + 1.05E+5);
DEF $GenFile4 = FILE({$myId});
DEF $GenFile5 = FILE({Id:"txt", $myVar});

if (2>3) {
    DEF $GenFileIf1 = FILE(1);
    DEF $GenFileIf2 = FILE(2);
} else {
    DEF $GenFileElse1 = FILE(1);
    DEF $GenFileElse2 = FILE(2);
}

for_each ($var1 IN $varList) {
    DEF $GenFileFor1 = FILE(1);
    DEF $GenFileFor2 = FILE(2);
}

INCLUDE("C:/javasrc/RCD_scripter/RCD_scripter3/src/main/java/org/thingml/rcd_scripter3/app_msgdef3-include.h");


DEF $GenFile6 = FILE({$moreVar, 12:"txt", "12":"txt", $myVar});


print("Hei\n");
print("Hei"+" igjen\n");
print(10+"\n");
print(10 + 20 + "\n");
print(10 + 2 * 20 * 3 * 4 + "\n");
print("Tekst:" + 10 + 20 + "\n");

RCD_SCRIPT_START


HASH $VarHash0 = { One:1, Two:2};
HASH $VarHash1 = $VarHash0;
$VarHash1.add({ Ny:"New"});

HASHLIST $VarHashList1;
$VarHashList1.add($VarHash0);
$VarHashList1.add($VarHash1);
$VarHashList1.add({ Fersk:"Fresh"});

HASHLIST $VarHashList2 = $VarHashList1;
$VarHashList2.add({ Nyest:"Newest"});
$VarHashList2.setdef({ Default:"Default"});
$VarHashList2.add({ NyRad:"NewRow"});
print("$VarHash0: " + $VarHash0 + "\n");
print("$VarHash0[Two]: " + $VarHash0[Two] + "\n");
print("$VarHash1: " + $VarHash1 + "\n");
print("$VarHashList1: " + $VarHashList1 + "\n");
print("$VarHashList2: " + $VarHashList2 + "\n");

print("Hash: " + { TmpOne:1, "TmpTwo":2 , 33:3 } + "\n");

