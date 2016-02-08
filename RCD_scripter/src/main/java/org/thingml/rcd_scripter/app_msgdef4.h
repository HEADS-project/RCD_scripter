//# Definition of coders with attributes separated by whitespace and terminated by #

//CREATE_TAB(CODER_DEF)
//CREATE_COL(CODER_DEF, {MSGC, MSGC_NONE}, 
//                      {PARAMS, ""},
//                      {COMP_SIGN, ""}, 
//                      {DECOMP_SIGN, ""}) 
//CREATE_ROW(CODER_DEF, {MSGC, MSGC_U16}, 
//                      {PARAMS, ", p0"}, 
//                      {COMP_SIGN, ", const uint16_t p0"}, 
//                      {DECOMP_SIGN, ", uint16_t *p0"}) 
//CREATE_ROW(CODER_DEF, 
//                      {MSGC, MSGC_U16U16}, 
//                      {PARAMS, ", p0, p1"}, 
//                      {COMP_SIGN, ", const uint16_t p0, const uint16_t p1"}, 
//                      {DECOMP_SIGN, ", uint16_t *p0, uint16_t *p1"}) 
//CREATE_ROW(CODER_DEF, 
//                      {MSGC, MSGC_U16U16U16}, 
//                      {PARAMS, ", p0, p1, p2"}, 
//                      {COMP_SIGN, ", const uint16_t p0, const uint16_t p1, const uint16_t p2"}, 
//                      {DECOMP_SIGN, ", uint16_t *p0, uint16_t *p1, uint16_t *p2"}) 
//CREATE_ROW(CODER_DEF, 
//                      {MSGC, MSGC_U32}, 
//                      {PARAMS, ", p0"}, 
//                      {COMP_SIGN, ", const uint32_t p0"}, 
//                      {DECOMP_SIGN, ", uint32_t *p0"}) 
//CREATE_ROW(CODER_DEF, 
//                      {MSGC, MSGC_U32U32U16U8U8}, 
//                      {PARAMS, ", p0, p1, p2, p3, p4"}, 
//                      {COMP_SIGN, ", const uint32_t p0, const uint32_t p1, const uint16_t p2, const uint8_t p3, const uint8_t p4"}, 
//                      {DECOMP_SIGN, ", uint32_t *p0, uint32_t *p1, uint16_t *p2, uint8_t *p3, uint8_t *p4"}) 

//# Definition of messages with attributes separated by whitespace and terminated by #
//CREATE_TAB(MSG_DEF)
//CREATE_COL(MSG_DEF, {ENUM_PREFIX, MSGID_}, {MSGC, MSGC_U16},
//                    {MSG_NAME, NONE},              {VALUE, 0},    {TRACE, yes},        {COMMENT, ""})
//CREATE_ROW(MSG_DEF, {MSG_NAME, TMR_SIGNAL},        {VALUE, 0x01},                      {COMMENT, "Time signal                                  "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, TIME_TICK},         {VALUE, 0x02}, {TRACE, no},         {COMMENT, "Time tick from OS                            "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, BELT_STATUS},       {VALUE, 0x13},                      {COMMENT, "Chest belt status change                     "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, BATT_STATUS},       {VALUE, 0x14},                      {COMMENT, "Battery status change                        "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, CHARGE_CURRENT},    {VALUE, 0x15},                      {COMMENT, "Charge current (in mA)                       "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, SWITCH_EVENT},      {VALUE, 0x17},                      {COMMENT, "Push-button (on/off switch) status change    "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, SET_DATA_MODE},     {VALUE, 0x1b},                      {COMMENT, "Set Data logging mode                        "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, GET_DATA_MODE},     {VALUE, 0x1c},                      {COMMENT, "Get Data logging mode                        "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, FULL_CLK},          {VALUE, 0x1d},                      {COMMENT, "Get full clock time                          "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, REFERENCE_CLK},     {VALUE, 0x1e},                      {COMMENT, "Get reference clock time                     "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, GET_SERNUM},        {VALUE, 0x1f},                      {COMMENT, "Get CU serial number                         "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, GET_FW_REV},        {VALUE, 0x20},                      {COMMENT, "Get CU firmware revision                     "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, CTRL_STATE},        {VALUE, 0x21},                      {COMMENT, "Control status change                        "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, UIND_CMD},          {VALUE, 0x22},                      {COMMENT, "User Indicator (LED or vibrator) act. cmd    "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, CHARGER_CONNECT},   {VALUE, 0x31}, {TRACE, no},         {COMMENT, "Charger connected or disconnected            "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, PWR_STATE},         {VALUE, 0x41},                      {COMMENT, "Set or rep power state for uC and peripherals"})
//CREATE_ROW(MSG_DEF, {MSG_NAME, BATT_LEVEL_FORCED}, {VALUE, 0x4f},                      {COMMENT, "Battery level forced - added by steffend 20120809    "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, CTRL_STATE_FORCED}, {VALUE, 0x50},                      {COMMENT, "Control status forced - added by steffend 20120809   "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, CTRL_STATE_REQ},    {VALUE, 0x51},                      {COMMENT, "Control status request - added by steffend 20120809   "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, DEBUG_SET},         {VALUE, 0x57},                      {COMMENT, "Control Debug                  "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, SET_IMM_ALERT},     {VALUE, 0x58},                      {COMMENT, "Set immidiate alert - added by steffend 20130321  "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, GET_CH},            {VALUE, 0x59}, {TRACE, no},         {COMMENT, "Character from console in PC-app "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, SENDER_TEST},       {VALUE, 0x5a},                      {COMMENT, "Test output from task_sender_class "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, RESET_TEST},        {VALUE, 0x5b},                      {COMMENT, "Generic reset to processes  "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, PING0},             {VALUE, 0x5c}, {MSGC, MSGC},        {COMMENT, "ThingMl test message  "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, PING1},             {VALUE, 0x5d},                      {COMMENT, "ThingMl test message  "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, PONG1},             {VALUE, 0x5e},                      {COMMENT, "ThingMl test message  "})
//CREATE_ROW(MSG_DEF, {MSG_NAME, PONG2},             {VALUE, 0x5f}, {MSGC, MSGC_U16U16}, {COMMENT, "ThingMl test message  "})

//## Joining the two tables and make entry if both have the matching cell
//CREATE_TAB_JOIN(MSG_JOIN, MSG_DEF, MSGC, CODER_DEF,  MSGC)




