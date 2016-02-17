//## Definition of coders with attributes separated by whitespace and terminated by 

//TABLE $CoderDef = TABLE()
//$CoderDef.SetDef({MSGC, MSGC_NONE}, 
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
//$CoderDef.Add({MSGC, MSGC_U16U16U16}, 
//              {PARAMS, ", p0, p1, p2"}, 
//              {COMP_SIGN, ", const uint16_t p0, const uint16_t p1, const uint16_t p2"}, 
//              {DECOMP_SIGN, ", uint16_t *p0, uint16_t *p1, uint16_t *p2"}) 
//$CoderDef.Add({MSGC, MSGC_U32}, 
//              {PARAMS, ", p0"}, 
//              {COMP_SIGN, ", const uint32_t p0"}, 
//              {DECOMP_SIGN, ", uint32_t *p0"}) 
//$CoderDef.Add({MSGC, MSGC_U32U32U16U8U8}, 
//              {PARAMS, ", p0, p1, p2, p3, p4"}, 
//              {COMP_SIGN, ", const uint32_t p0, const uint32_t p1, const uint16_t p2, const uint8_t p3, const uint8_t p4"}, 
//              {DECOMP_SIGN, ", uint32_t *p0, uint32_t *p1, uint16_t *p2, uint8_t *p3, uint8_t *p4"}) 

//## Definition of messages with attributes separated by whitespace and terminated by 
//TABLE $MsgDef = TABLE()
//$MsgDef.SetDef({ENUM_PREFIX, MSGID_}, {MSGC, MSGC_U16},
//               {TRACE_NAME, NONE},              {ENUM_VAL, 0},    {TRACE, yes},        {COMMENT, ""})
//$MsgDef.Add( {TRACE_NAME, TMR_SIGNAL},        {ENUM_VAL, 0x01},                      {COMMENT, "Time signal                                  "})
//$MsgDef.Add( {TRACE_NAME, TIME_TICK},         {ENUM_VAL, 0x02}, {TRACE, no},         {COMMENT, "Time tick from OS                            "})
//$MsgDef.Add( {TRACE_NAME, BELT_STATUS},       {ENUM_VAL, 0x13},                      {COMMENT, "Chest belt status change                     "})
//$MsgDef.Add( {TRACE_NAME, BATT_STATUS},       {ENUM_VAL, 0x14},                      {COMMENT, "Battery status change                        "})
//$MsgDef.Add( {TRACE_NAME, CHARGE_CURRENT},    {ENUM_VAL, 0x15},                      {COMMENT, "Charge current (in mA)                       "})
//$MsgDef.Add( {TRACE_NAME, SWITCH_EVENT},      {ENUM_VAL, 0x17},                      {COMMENT, "Push-button (on/off switch) status change    "})
//$MsgDef.Add( {TRACE_NAME, SET_DATA_MODE},     {ENUM_VAL, 0x1b},                      {COMMENT, "Set Data logging mode                        "})
//$MsgDef.Add( {TRACE_NAME, GET_DATA_MODE},     {ENUM_VAL, 0x1c},                      {COMMENT, "Get Data logging mode                        "})
//$MsgDef.Add( {TRACE_NAME, FULL_CLK},          {ENUM_VAL, 0x1d},                      {COMMENT, "Get full clock time                          "})
//$MsgDef.Add( {TRACE_NAME, REFERENCE_CLK},     {ENUM_VAL, 0x1e},                      {COMMENT, "Get reference clock time                     "})
//$MsgDef.Add( {TRACE_NAME, GET_SERNUM},        {ENUM_VAL, 0x1f},                      {COMMENT, "Get CU serial number                         "})
//$MsgDef.Add( {TRACE_NAME, GET_FW_REV},        {ENUM_VAL, 0x20},                      {COMMENT, "Get CU firmware revision                     "})
//$MsgDef.Add( {TRACE_NAME, CTRL_STATE},        {ENUM_VAL, 0x21},                      {COMMENT, "Control status change                        "})
//$MsgDef.Add( {TRACE_NAME, UIND_CMD},          {ENUM_VAL, 0x22},                      {COMMENT, "User Indicator (LED or vibrator) act. cmd    "})
//$MsgDef.Add( {TRACE_NAME, CHARGER_CONNECT},   {ENUM_VAL, 0x31}, {TRACE, no},         {COMMENT, "Charger connected or disconnected            "})
//$MsgDef.Add( {TRACE_NAME, PWR_STATE},         {ENUM_VAL, 0x41},                      {COMMENT, "Set or rep power state for uC and peripherals"})
//$MsgDef.Add( {TRACE_NAME, BATT_LEVEL_FORCED}, {ENUM_VAL, 0x4f},                      {COMMENT, "Battery level forced - added by steffend 20120809    "})
//$MsgDef.Add( {TRACE_NAME, CTRL_STATE_FORCED}, {ENUM_VAL, 0x50},                      {COMMENT, "Control status forced - added by steffend 20120809   "})
//$MsgDef.Add( {TRACE_NAME, CTRL_STATE_REQ},    {ENUM_VAL, 0x51},                      {COMMENT, "Control status request - added by steffend 20120809   "})
//$MsgDef.Add( {TRACE_NAME, DEBUG_SET},         {ENUM_VAL, 0x57},                      {COMMENT, "Control Debug                  "})
//$MsgDef.Add( {TRACE_NAME, SET_IMM_ALERT},     {ENUM_VAL, 0x58},                      {COMMENT, "Set immidiate alert - added by steffend 20130321  "})
//$MsgDef.Add( {TRACE_NAME, GET_CH},            {ENUM_VAL, 0x59}, {TRACE, no},         {COMMENT, "Character from console in PC-app "})
//$MsgDef.Add( {TRACE_NAME, SENDER_TEST},       {ENUM_VAL, 0x5a},                      {COMMENT, "Test output from task_sender_class "})
//$MsgDef.Add( {TRACE_NAME, RESET_TEST},        {ENUM_VAL, 0x5b},                      {COMMENT, "Generic reset to processes  "})
//$MsgDef.Add( {TRACE_NAME, PING0},             {ENUM_VAL, 0x5c}, {MSGC, MSGC_NONE},   {COMMENT, "ThingMl test message  "})
//$MsgDef.Add( {TRACE_NAME, PING1},             {ENUM_VAL, 0x5d},                      {COMMENT, "ThingMl test message  "})
//$MsgDef.Add( {TRACE_NAME, PONG1},             {ENUM_VAL, 0x5e},                      {COMMENT, "ThingMl test message  "})
//$MsgDef.Add( {TRACE_NAME, PONG2},             {ENUM_VAL, 0x5f}, {MSGC, MSGC_U16U16}, {COMMENT, "ThingMl test message  "})



