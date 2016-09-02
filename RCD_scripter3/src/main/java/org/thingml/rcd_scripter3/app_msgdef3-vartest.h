//## Definition of coders with attributes separated by whitespace and terminated by 

DEF $MsgDef = HASHLIST();
DEF $MsgDef2 = HASHLIST($Input);

aflææaldfk jkajdf kja fkk kkfj 

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
RCD_SCRIPT_START
DEF $GenFile1 = FILE("app_msg_gen.h"+"mere"*4);
RCD_SCRIPT_STOP
DEF $GenFile2 = FILE(4 * 4 / 4 + 0x4 - -4 + +4 + $variname);
DEF $GenFile3 = FILE(1.1 + +2.2 + -3.3 + -4.4E-4 + 1.05E+5);
DEF $GenFile4 = FILE({$myId});
DEF $GenFile5 = FILE({Id:"txt", $myVar});

RCD_SCRIPT_START

DEF $GenFile6 = FILE({$moreVar, 12:"txt", "12":"txt", $myVar});
