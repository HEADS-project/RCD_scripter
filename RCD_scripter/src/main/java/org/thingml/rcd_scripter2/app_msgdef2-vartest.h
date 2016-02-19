//## Definition of coders with attributes separated by whitespace and terminated by 

//DEF $CoderDef = TABLE()
//$CoderDef.SetDef({MSGC, MSGC_NONE}, {TEST, TESTID}, 
//                 {PARAMS, ""},
//                 {COMP_SIGN, ""}, 
//                 {DECOMP_SIGN, ""}) 
//$CoderDef.Add({MSGC, MSGC_U16}, 
//              {PARAMS, ", p0"}, 
//              {COMP_SIGN, ", const uint16_t p0"}, 
//              {DECOMP_SIGN, ", uint16_t *p0"}) 
//$CoderDef.Add({MSGC, MSGC_U16U16}, 
//              {PARAMS, ", p0, p1"}, 
//              {COMP_SIGN, ", const uint16_t p0, const uint16_t p1"}, 
//              {DECOMP_SIGN, ", uint16_t *p0, uint16_t *p1"}) 

//## Definition of messages with attributes separated by whitespace and terminated by 
//DEF $MsgDef = TABLE()
//$MsgDef.SetDef({ENUM_PREFIX, MSGID_}, {MSGC, MSGC_U16},
//               {TRACE_NAME, NONE},              {ENUM_VAL, 0},    {TRACE, yes},        {COMMENT, ""})
//$MsgDef.Add( {TRACE_NAME, TMR_SIGNAL},        {ENUM_VAL, 0x01*40+100},                      {COMMENT, "Time signal                                  "})
//$MsgDef.Add( {TRACE_NAME, TIME_TICK},         {ENUM_VAL, 0x02}, {TRACE, no},         {COMMENT, "Time tick from OS                            "})
//DEF $MsgDefCopyWithTwoRows = TABLE(=$MsgDEF)
//$MsgDef.Add( {TRACE_NAME, SET_IMM_ALERT},     {ENUM_VAL, ID+0x58},                      {COMMENT, "Set immidiate alert - added by steffend 20130321  "})
//$MsgDef.Add( {TRACE_NAME, GET_CH},            {ENUM_VAL, "String space"+0x59}, {TRACE, no},         {COMMENT, "Character from console in PC-app "})
//$MsgDef.Add( {TRACE_NAME, SENDER_TEST},       {ENUM_VAL, 30-14/2*4},                      {COMMENT, "Test output from task_sender_class "})
//$MsgDef.Add( {TRACE_NAME, PING0},             {ENUM_VAL, 0x5c}, {MSGC, MSGC_NONE},   {COMMENT, "ThingMl test message  "})
//$MsgDef.Add( {TRACE_NAME, PING1},             {ENUM_VAL, 0x5d},                      {COMMENT, "ThingMl test message  "})
//$MsgDef.Add( {TRACE_NAME, PONG2},             {ENUM_VAL, 0x5f}, {MSGC, MSGC_U16U16}, {COMMENT, "ThingMl test message  "})



//DEF $MsgArray = ARRAY(200, MSGC_U16)
//DEF $CopyArray = ARRAY(=$MsgArray)

//DEF $OneRow = ROW({NAME, Garbage},        {Number, 1001})

//DEF $CopyRow = ROW(=$OneRow)
//$CopyRow.Add( {MORE_COMMENT, "Test of row"})

//DEF $OneCell = CELL({MY_CELL, "Hei"})

//DEF $Verdien = VALUE(42)
//DEF $CelleVerdien = CELL({Verdien, $Verdien})

//$CopyRow.Add( =$CelleVerdien)

//DEF $CopyCell = CELL(=$CelleVerdien)

//DEF $CopyVerdi = VALUE(=$Verdien)

//DEF $CopyValueId = VALUE($OneRow.NAME)

//DEF $Test = VALUE($OneRow.NAME)
//DEF $CopyCellId = CELL($OneRow.NAME) 

//##PRINT("$CopyCellId.NAME:"+$CopyCellId.NAME)
//PRINT("$CopyValueId:"+$CopyValueId)


