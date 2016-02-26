//## Definition of coders with attributes separated by whitespace and terminated by 

//DEF $CoderDef = ROWLIST();
//$CoderDef.SetDef({MSGC, MSGC_NONE}, {TEST, TESTID}, 
//                 {PARAMS, ""},
//                 {COMP_SIGN, ""}, 
//                 {DECOMP_SIGN, ""});
//$CoderDef.Add({MSGC, MSGC_U16}, 
//              {PARAMS, ", p0"}, 
//              {COMP_SIGN, ", const uint16_t p0"}, 
//              {DECOMP_SIGN, ", uint16_t *p0"});
//$CoderDef.Add({MSGC, MSGC_U16U16}, 
//              {PARAMS, ", p0, p1"}, 
//              {COMP_SIGN, ", const uint16_t p0, const uint16_t p1"}, 
//              {DECOMP_SIGN, ", uint16_t *p0, uint16_t *p1"});

//## Definition of messages with attributes separated by whitespace and terminated by 
//DEF $MsgDef = ROWLIST();
//$MsgDef.SetDef({ENUM_PREFIX, MSGID_}, {MSGC, MSGC_U16},
//               {TRACE_NAME, NONE},              {ENUM_VAL, 1},   {TRACE, yes},        {COMMENT, ""});
//$MsgDef.Add( {TRACE_NAME, TMR_SIGNAL},        {ENUM_VAL, 2},                      {COMMENT, "Time signal                                  "});
//$MsgDef.Add( {TRACE_NAME, TIME_TICK},         {ENUM_VAL, 4}, {TRACE, no},         {COMMENT, "Time tick from OS                            "});
//DEF $MsgDefCopyWithTwoRows = ROWLIST(=$MsgDef);
//$MsgDef.Add( {TRACE_NAME, SET_IMM_ALERT},     {ENUM_VAL, 6},                      {COMMENT, "Set immidiate alert - added by steffend 20130321  "});
//$MsgDef.Add( {TRACE_NAME, GET_CH},            {ENUM_VAL, 8}, {TRACE, no},         {COMMENT, "Character from console in PC-app "});
//$MsgDef.Add( {TRACE_NAME, SENDER_TEST},       {ENUM_VAL, 10},                      {COMMENT, "Test output from task_sender_class "});
//$MsgDef.Add( {TRACE_NAME, PING0},             {ENUM_VAL, 12}, {MSGC, MSGC_NONE},   {COMMENT, "ThingMl test message  "});
//$MsgDef.Add( {TRACE_NAME, PING1},             {ENUM_VAL, 14},                      {COMMENT, "ThingMl test message  "});
//$MsgDef.Add( {TRACE_NAME, PONG2},             {ENUM_VAL, 16}, {MSGC, MSGC_U16U16}, {COMMENT, "ThingMl test message  "});
//$MsgDef.Add( {TRACE_NAME, ZERO},             {ENUM_VAL, 0}, {MSGC, ZERO},   {COMMENT, "ZERO TEXT  "});



//DEF $MsgArray = VARARRAY(200, "<USING DEFAULT>");
//DEF $CopyArray = VARARRAY(=$MsgArray);

//DEF $OneRow = ROW({NAME, Garbage},        {Number, 1001});

//DEF $CopyRow = ROW(=$OneRow);
//$CopyRow.Add( {MORE_COMMENT, "Test of row"});

//DEF $OneCell = CELL({MY_CELL, "Hei"});

//$Verdien = 42;
//DEF $CelleVerdien = CELL({Verdien, $Verdien});

//$CopyRow.Add( =$CelleVerdien);

//DEF $CopyCell = CELL(=$CelleVerdien);

//$CopyVerdi = $Verdien;

//$CopyValueId = $OneRow.NAME;

//$Test = $OneRow.NAME;
//DEF $CopyCellId = CELL($OneRow.NAME);

//##PRINT("$CopyCellId.NAME:"+$CopyCellId.NAME);
//##PRINT("$CopyValueId:"+$CopyValueId);
//PRINT("$OneRow.NAME:" + $OneRow.NAME + " $OneRow.Number:" + $OneRow.Number);

//$Counter = 0;
//PRINT("Before loop: " + $Counter);
//FOR_EACH($LoopRow IN $MsgDef) {
//$Counter = $Counter + 1;
//PRINT("In loop: " + $Counter);
//PRINT("$LoopRow.TRACE_NAME:" + $LoopRow.TRACE_NAME + " $LoopRow.ENUM_VAL:" + $LoopRow.ENUM_VAL);
//$MsgArray[$LoopRow.ENUM_VAL] = $LoopRow.MSGC;
//}
//PRINT("After loop: " + $Counter);

//$Counter2 = 0;
//PRINT("Before loop2: " + $Counter2);
//FOR_EACH($Value IN $MsgArray) {
//PRINT("In loop2: " + $Counter2 + " Value:" + $Value + " Value["+$Counter2+"]:" + $MsgArray[$Counter2]);
//$Counter2 = $Counter2 + 1;
//}
//PRINT("After loop2: " + $Counter2);

