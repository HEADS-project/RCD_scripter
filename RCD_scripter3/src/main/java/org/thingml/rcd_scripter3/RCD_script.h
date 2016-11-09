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
/*
INSERT_START
Denne teksten skal fjernes
INSERT_END

RCD_SCRIPT_START

void my_func(value $in_val1, value $in_val2, value $in_string) {
    println("Input value1: " + $in_val1);
    println("Input value2: " + $in_val2);
    println("Input value3: " + $in_string);
    printf("Input int1:%d int2:%d string<%s>\n", $in_val1, $in_val2, $in_string);
    printf("Only text\n");
}

my_func(1 , 2, "Param tekst");

void check_hash_func(HASH $in_hash, value $key) {
    IF ($in_hash.HAS($key)) {
        PRINTLN("Key "+$key+" has value "+$in_hash[$key]);
    } ELSE {
        PRINTLN("Key "+$key+" is not present in HASH");
    }
}

HASH $test = {MSGID: MSGID_PING2 ,              ENUM_VAL: 0x31 ,  MSGC: MSGC_U16U16 ,  COMMENT: "ThingMl test message  "};
check_hash_func($test, ENUM_VAL);
check_hash_func($test, TULL);


## This file is specifying how to generate c-code files with declarations messages with attributes and MSGC coders with attributes

## To run:
##    java -jar ./RCD_scripter.jar

##############  Message definitions ##############
## Small-message IDs (range 0x00 - 0xFF) 
## Data from this section is used to generate declarations for "enum msg_id_t", "_msgidName[]" and "_coderTypeArr[]"
## Data from this section is used to generate declarations for "APP_MSGC_CoderTypeArr[]" and inline coder 

VALUE $MsgIdPrefix = "MSGID_";
HASHLIST $MsgDef ;
$MsgDef.SetDef( {MSGC: MSGC_U16, MSGID: NONE, TRACE: yes,        COMMENT: "Default row"});
## A rowlist contains variable number of fields
## Following fields are in use for $MsgDef
## MSGID    - Message id used in emum msg_id_t and other related structures 
## ENUM_VAL - Value used for the message id
## COMMENT  - Text describing the message id
## TRACE    - Specifies whether the message shall be added to the trace buffer when sent
## MSGC     - Specifies the paramter set and coder to be used. This is a crosslink to $CoderDef


$MsgDef.Add( {MSGID: MSGID_TMR_SIGNAL ,         ENUM_VAL: 0x01 ,  TRACE: no ,          COMMENT: "Time signal                                  "});
$MsgDef.Add( {MSGID: MSGID_TIME_TICK ,          ENUM_VAL: 0x02 ,  TRACE: no ,          COMMENT: "Time tick from OS                            "});
$MsgDef.Add( {MSGID: MSGID_BELT_STATUS ,        ENUM_VAL: 0x03 ,                       COMMENT: "Chest belt status change                     "});
$MsgDef.Add( {MSGID: MSGID_BATT_STATUS ,        ENUM_VAL: 0x04 ,                       COMMENT: "Battery status change                        "});
$MsgDef.Add( {MSGID: MSGID_CHARGE_CURRENT ,     ENUM_VAL: 0x05 ,  TRACE: no ,          COMMENT: "Charge current (in mA)                       "});
$MsgDef.Add( {MSGID: MSGID_SWITCH_EVENT ,       ENUM_VAL: 0x06 ,                       COMMENT: "Push-button (on/off switch) status change    "});
$MsgDef.Add( {MSGID: MSGID_SET_DATA_MODE ,      ENUM_VAL: 0x07 ,                       COMMENT: "Set Data logging mode                        "});
$MsgDef.Add( {MSGID: MSGID_GET_DATA_MODE ,      ENUM_VAL: 0x08 ,                       COMMENT: "Get Data logging mode                        "});
$MsgDef.Add( {MSGID: MSGID_FULL_CLK ,           ENUM_VAL: 0x09 ,                       COMMENT: "Get full clock time                          "});
$MsgDef.Add( {MSGID: MSGID_REFERENCE_CLK ,      ENUM_VAL: 0x0a ,                       COMMENT: "Get reference clock time                     "});
$MsgDef.Add( {MSGID: MSGID_GET_SERNUM ,         ENUM_VAL: 0x0b ,                       COMMENT: "Get CU serial number                         "});
$MsgDef.Add( {MSGID: MSGID_GET_FW_REV ,         ENUM_VAL: 0x0c ,                       COMMENT: "Get CU firmware revision                     "});
$MsgDef.Add( {MSGID: MSGID_CTRL_STATE ,         ENUM_VAL: 0x0d ,                       COMMENT: "Control status change                        "});
$MsgDef.Add( {MSGID: MSGID_UIND_CMD ,           ENUM_VAL: 0x0e ,  TRACE: no ,          COMMENT: "User Indicator (LED or vibrator) act. cmd    "});
$MsgDef.Add( {MSGID: MSGID_CONN_STATUS ,        ENUM_VAL: 0x0f ,                       COMMENT: "Connection status change                     "});
$MsgDef.Add( {MSGID: MSGID_ADC_DATA ,           ENUM_VAL: 0x10 ,  TRACE: no ,          COMMENT: "ADC sample                                   "});
$MsgDef.Add( {MSGID: MSGID_GOTO_STANDBY ,       ENUM_VAL: 0x11 ,                       COMMENT: "Timout since last resync, go to standby      "});
$MsgDef.Add( {MSGID: MSGID_UIND_OVR_CMD ,       ENUM_VAL: 0x12 ,                       COMMENT: "User Indicator LED act. override cmd         "});
$MsgDef.Add( {MSGID: MSGID_CHARGER_CONNECT ,    ENUM_VAL: 0x13 ,  TRACE: no ,          COMMENT: "Charger connected or disconnected            "});
$MsgDef.Add( {MSGID: MSGID_PWR_STATE ,          ENUM_VAL: 0x14 ,                       COMMENT: "Set or rep power state for uC and peripherals"});
$MsgDef.Add( {MSGID: MSGID_BATT_LEVEL_DATA ,    ENUM_VAL: 0x15 ,                       COMMENT: "Battery level data                           "});
$MsgDef.Add( {MSGID: MSGID_BATT_LEVEL_FORCED ,  ENUM_VAL: 0x16 ,                       COMMENT: "Battery level forced                         "});
$MsgDef.Add( {MSGID: MSGID_CTRL_STATE_FORCED ,  ENUM_VAL: 0x17 ,                       COMMENT: "Control status forced                        "});
$MsgDef.Add( {MSGID: MSGID_CTRL_STATE_REQ ,     ENUM_VAL: 0x18 ,                       COMMENT: "Control status request                       "});
$MsgDef.Add( {MSGID: MSGID_DEBUG_SET ,          ENUM_VAL: 0x19 ,                       COMMENT: "Control Debug                                "});
$MsgDef.Add( {MSGID: MSGID_SET_IMM_ALERT ,      ENUM_VAL: 0x1a ,                       COMMENT: "Set immidiate alert                          "});
$MsgDef.Add( {MSGID: MSGID_GET_CH ,             ENUM_VAL: 0x1b ,  TRACE: no ,          COMMENT: "Character from console in PC-app "} );
$MsgDef.Add( {MSGID: MSGID_SENDER_TEST ,        ENUM_VAL: 0x1c ,  MSGC: MSGC_U16U16 ,  COMMENT: "Test output from task_sender_class "});
$MsgDef.Add( {MSGID: MSGID_RESET_TEST ,         ENUM_VAL: 0x1d ,                       COMMENT: "Generic reset to processes  "});
$MsgDef.Add( {MSGID: MSGID_PING0 ,              ENUM_VAL: 0x1e ,  MSGC: MSGC_NONE ,    COMMENT: "ThingMl test message  "});
$MsgDef.Add( {MSGID: MSGID_PING1 ,              ENUM_VAL: 0x1f ,                       COMMENT: "ThingMl test message  "});
$MsgDef.Add( {MSGID: MSGID_PONG1 ,              ENUM_VAL: 0x20 ,                       COMMENT: "ThingMl test message  "});
$MsgDef.Add( {MSGID: MSGID_PONG2 ,              ENUM_VAL: 0x21 ,  MSGC: MSGC_U16U16 ,  COMMENT: "ThingMl test message  "});
$MsgDef.Add( {MSGID: MSGID_TUNNEL ,             ENUM_VAL: 0x22 ,  MSGC: MSGC_U8ARR14 , COMMENT: "ThingMl tunneling message  "});
$MsgDef.Add( {MSGID: MSGID_BT_ON_RDY ,          ENUM_VAL: 0x23 ,                       COMMENT: "Bluetooth rdy interrupt                      "});
$MsgDef.Add( {MSGID: MSGID_BT_ADVERTISE ,       ENUM_VAL: 0x24 ,                       COMMENT: "BT start advertising                         "});
$MsgDef.Add( {MSGID: MSGID_PRESSURE_DATA ,      ENUM_VAL: 0x25 ,                       COMMENT: "Pressure sensor level data                   "});
$MsgDef.Add( {MSGID: MSGID_PRESSURE_TEMP ,      ENUM_VAL: 0x26 ,                       COMMENT: "Pressure sensor temperature data             "});
$MsgDef.Add( {MSGID: MSGID_PRESSURE_PHASE ,     ENUM_VAL: 0x27 ,                       COMMENT: "Pressure update phase (point in time)        "});
$MsgDef.Add( {MSGID: MSGID_PRESSURE_CHECK ,     ENUM_VAL: 0x28 ,                       COMMENT: "Pressure check                               "});
$MsgDef.Add( {MSGID: MSGID_PB_FRAME_CHECKH ,    ENUM_VAL: 0x29 ,                       COMMENT: "Pressure Broadcast frame check high 16-bit   "});
$MsgDef.Add( {MSGID: MSGID_PB_FRAME_CHECKL ,    ENUM_VAL: 0x2A ,                       COMMENT: "Pressure Broadcast frame check low 16-bit    "});
$MsgDef.Add( {MSGID: MSGID_PB_FRAME_PHASE ,     ENUM_VAL: 0x2B ,                       COMMENT: "Pressure Broadcast frame update phase (point in time)"});
$MsgDef.Add( {MSGID: MSGID_PUT_CH ,             ENUM_VAL: 0x2C ,                       COMMENT: "haracter to console in PC-app                "});
$MsgDef.Add( {MSGID: MSGID_ADT_TEMP_DATA ,      ENUM_VAL: 0x2D ,                       COMMENT: "ADT Temperature level data                   "});
$MsgDef.Add( {MSGID: MSGID_ADT_TEMP_CHECK ,     ENUM_VAL: 0x2E ,                       COMMENT: "ADT Temperature check                        "});
$MsgDef.Add( {MSGID: MSGID_ADT_TEMP_INTERVAL ,  ENUM_VAL: 0x2F ,                       COMMENT: "DT Temperature update interval               "});
$MsgDef.Add( {MSGID: MSGID_BT_TUNNELMSG ,       ENUM_VAL: 0x30 ,                       COMMENT: "Debug ThingML BLE tunneling message"          });
$MsgDef.Add( {MSGID: MSGID_PING2 ,              ENUM_VAL: 0x31 ,  MSGC: MSGC_U16U16 ,  COMMENT: "ThingMl test message  "});

## Create TRACE_NAME entry based on content in MSGID entry
FOR(HASH $Row : $MsgDef) {
	$Row.ADD({TRACE_NAME: $Row[MSGID]-$MsgIdPrefix});
}

##PRINTLN($MsgDef);
##PRINTLN($MsgDef);



############## CODER definitions ##############
## Data from this section is used to generate declarations for "enum msg_id_t", "_msgidName[]" and "APP_MSGC_CoderTypeArr[]"
## Data from this section is used to generate declarations for "APP_MSGC_CoderTypeArr[]" and inline coder *

VALUE $CoderPrefix = "MSGC_";
HASHLIST $CoderDef;
$CoderDef.SetDef({MSGC: MSGC_NONE, PARAMS: "", COMP_SIGN: "", DECOMP_SIGN: "", IN_USE: no});
## A rowlist contains variable number of fields
## Following fields are in use for $CoderDef
## MSGC          - Name describing the parameter set. Used for identifying different coders
## APARAM        - Actual parameter list fragment used when calling coders
## ENCODE_FPARAM - Formal parameter list fragment used when specifying encoders
## DECODE_FPARAM - Formal parameter list fragment used when specifying decoders
## IN_USE        - Some message is using this coder
## MSGC_STRUCT   - Typedef of struct for this coder

VALUE $msgc_typename = "msgc_param_none_t";
VALUE $msgc_struct = "
typedef struct {
    uint8_t Dummy; // No params
} " + $msgc_typename +";
";

VALUE $msgc_comp = "
static inline void APP_MSGC_comp_NONE (msgc_t *mc_ptr,
                                      const msg_id_t msgid)
{
    mc_ptr->MsgId  = msgid;
    mc_ptr->ParamType = MSGC_NONE;
}
";

VALUE $msgc_decomp = "
static inline void APP_MSGC_decomp_NONE (msgc_t *mc_ptr)
{
    // No params to handle
}
";

$CoderDef.Add({MSGC: MSGC_NONE, 
               ENUM_VAL: 0x00, 
               APARAM: "", 
               ENCODE_FPARAM: "", 
               DECODE_FPARAM: "",
               MSGC_STRUCT: $msgc_struct,
               MSGC_TYPENAME: $msgc_typename,
               MSGC_COMP: $msgc_comp,
               MSGC_DECOMP: $msgc_decomp});

##############  ##############

$msgc_typename = "msgc_param_u16_t";
$msgc_struct = "
typedef struct {
    uint16_t U161;
} " + $msgc_typename +";
";

$msgc_comp = "
static inline void APP_MSGC_comp_U16 (msgc_t *mc_ptr,
                                      const msg_id_t msgid,
                                      const uint16_t u161)
{
    mc_ptr->MsgId  = msgid;
    mc_ptr->ParamType = MSGC_U16;
    mc_ptr->ParamUnion.U16.U161 = u161;
}
";

$msgc_decomp = "
static inline void APP_MSGC_decomp_U16 (msgc_t *mc_ptr,
                                        uint16_t *u161_ptr)
{
    if (mc_ptr->ParamType == MSGC_U16) {
        if (u161_ptr != NULL) {
            *u161_ptr = mc_ptr->ParamUnion.U16.U161;
        }
    }
}
";

$CoderDef.Add({MSGC: MSGC_U16, 
               ENUM_VAL: 0x01, 
               APARAM: ", p0", 
               ENCODE_FPARAM: ", const uint16_t p0", 
               DECODE_FPARAM: ", uint16_t *p0",
               MSGC_STRUCT: $msgc_struct,
               MSGC_TYPENAME: $msgc_typename,
               MSGC_COMP: $msgc_comp,
               MSGC_DECOMP: $msgc_decomp});

##############  ##############
              
$msgc_typename = "msgc_param_u16u16_t";
$msgc_struct = "
typedef struct {
    uint16_t U161;
    uint16_t U162;
} " + $msgc_typename +";
";

$msgc_comp = "
static inline void APP_MSGC_comp_U16U16 (msgc_t *mc_ptr,
                                           const msg_id_t msgid,
                                           const uint16_t u161,
                                           const uint16_t u162)
{
    mc_ptr->MsgId  = msgid;
    mc_ptr->ParamType = MSGC_U16U16;
    mc_ptr->ParamUnion.U16U16.U161 = u161;
    mc_ptr->ParamUnion.U16U16.U162 = u162;
}
";

$msgc_decomp = "
static inline void APP_MSGC_decomp_U16U16 (msgc_t *mc_ptr,
                                           uint16_t *u161_ptr,
                                           uint16_t *u162_ptr)
{
    if (mc_ptr->ParamType == MSGC_U16U16) {
        if (u161_ptr != NULL) {
            *u161_ptr = mc_ptr->ParamUnion.U16U16.U161;
        }
        if (u162_ptr != NULL) {
            *u162_ptr = mc_ptr->ParamUnion.U16U16.U162;
        }
    }
}
";

$CoderDef.Add({MSGC: MSGC_U16U16, 
               ENUM_VAL: 0x02, 
               APARAM: ", p0, p1", 
               ENCODE_FPARAM: ", const uint16_t p0, const uint16_t p1", 
               DECODE_FPARAM: ", uint16_t *p0, uint16_t *p1",
               MSGC_STRUCT: $msgc_struct,
               MSGC_TYPENAME: $msgc_typename,
               MSGC_COMP: $msgc_comp,
               MSGC_DECOMP: $msgc_decomp});

##############  ##############
              
$msgc_typename = "msgc_param_u16u16u16_t";
$msgc_struct ="
typedef struct {
    uint16_t U161;
    uint16_t U162;
    uint16_t U163;
} " + $msgc_typename +";
";

$msgc_comp = "
static inline void APP_MSGC_comp_U16U16U16 (msgc_t *mc_ptr,
                                           const msg_id_t msgid,
                                           const uint16_t u161,
                                           const uint16_t u162,
                                           const uint16_t u163)
{
    mc_ptr->MsgId  = msgid;
    mc_ptr->ParamType = MSGC_U16U16U16;
    mc_ptr->ParamUnion.U16U16U16.U161 = u161;
    mc_ptr->ParamUnion.U16U16U16.U162 = u162;
    mc_ptr->ParamUnion.U16U16U16.U163 = u163;
}
";

$msgc_decomp = "
static inline void APP_MSGC_decomp_U16U16U16 (msgc_t *mc_ptr,
                                           uint16_t *u161_ptr,
                                           uint16_t *u162_ptr,
                                           uint16_t *u163_ptr)
{
    if (mc_ptr->ParamType == MSGC_U16U16U16) {
        if (u161_ptr != NULL) {
            *u161_ptr = mc_ptr->ParamUnion.U16U16U16.U161;
        }
        if (u162_ptr != NULL) {
            *u162_ptr = mc_ptr->ParamUnion.U16U16U16.U162;
        }
        if (u163_ptr != NULL) {
            *u163_ptr = mc_ptr->ParamUnion.U16U16U16.U163;
        }
    }
}
";

$CoderDef.Add({MSGC: MSGC_U16U16U16, 
               ENUM_VAL: 0x03, 
               APARAM: ", p0, p1, p2", 
               ENCODE_FPARAM: ", const uint16_t p0, const uint16_t p1, const uint16_t p2", 
               DECODE_FPARAM: ", uint16_t *p0, uint16_t *p1, uint16_t *p2",
               MSGC_STRUCT: $msgc_struct,
               MSGC_TYPENAME: $msgc_typename,
               MSGC_COMP: $msgc_comp,
               MSGC_DECOMP: $msgc_decomp});

##############  ##############
              
$msgc_typename = "msgc_param_u32_t";
$msgc_struct ="
typedef struct {
    uint32_t U321;
} " + $msgc_typename +";
";

$msgc_comp = "
static inline void APP_MSGC_comp_U32 (msgc_t *mc_ptr,
                                      const msg_id_t msgid,
                                      const uint32_t u321)
{
    mc_ptr->MsgId  = msgid;
    mc_ptr->ParamType = MSGC_U32;
    mc_ptr->ParamUnion.U32.U321 = u321;
}
";

$msgc_decomp = "
static inline void APP_MSGC_decomp_U32 (msgc_t *mc_ptr,
                                      uint32_t *u321_ptr)
{
    if (mc_ptr->ParamType == MSGC_U32) {
        if (u321_ptr != NULL) {
            *u321_ptr = mc_ptr->ParamUnion.U32.U321;
        }
    }
}
";

$CoderDef.Add({MSGC: MSGC_U32, 
               ENUM_VAL: 0x04, 
               APARAM: ", p0", 
               ENCODE_FPARAM: ", const uint32_t p0", 
               DECODE_FPARAM: ", uint32_t *p0",
               MSGC_STRUCT: $msgc_struct,
               MSGC_TYPENAME: $msgc_typename,
               MSGC_COMP: $msgc_comp,
               MSGC_DECOMP: $msgc_decomp});

##############  ##############
              
$msgc_typename = "msgc_param_u32u32u16u8u8_t";
$msgc_struct ="

typedef struct {
    uint32_t U321;
    uint32_t U322;
    uint16_t U161;
    uint8_t  U81;
    uint8_t  U82;
} " + $msgc_typename +";
";

$msgc_comp = "
static inline void APP_MSGC_comp_U32U32U16U8U8 (msgc_t *mc_ptr,
                                      const msg_id_t msgid,
                                      const uint32_t u321,
                                      const uint32_t u322,
                                      const uint16_t u161,
                                      const uint8_t  u81,
                                      const uint8_t  u82)
{
    mc_ptr->MsgId  = msgid;
    mc_ptr->ParamType = MSGC_U32U32U16U8U8;
    mc_ptr->ParamUnion.U32U32U16U8U8.U321 = u321;
    mc_ptr->ParamUnion.U32U32U16U8U8.U322 = u322;
    mc_ptr->ParamUnion.U32U32U16U8U8.U161 = u161;
    mc_ptr->ParamUnion.U32U32U16U8U8.U81 = u81;
    mc_ptr->ParamUnion.U32U32U16U8U8.U82 = u82;
}
";

$msgc_decomp = "
static inline void APP_MSGC_decomp_U32U32U16U8U8 (msgc_t *mc_ptr,
                                      uint32_t *u321_ptr,
                                      uint32_t *u322_ptr,
                                      uint16_t *u161_ptr,
                                      uint8_t  *u81_ptr,
                                      uint8_t  *u82_ptr)
{
    if (mc_ptr->ParamType == MSGC_U32U32U16U8U8) {
        if (u321_ptr != NULL) {
            *u321_ptr = mc_ptr->ParamUnion.U32U32U16U8U8.U321;
        }
        if (u322_ptr != NULL) {
            *u322_ptr = mc_ptr->ParamUnion.U32U32U16U8U8.U322;
        }
        if (u161_ptr != NULL) {
            *u161_ptr = mc_ptr->ParamUnion.U32U32U16U8U8.U161;
        }
        if (u81_ptr != NULL) {
            *u81_ptr = mc_ptr->ParamUnion.U32U32U16U8U8.U81;
        }
        if (u82_ptr != NULL) {
            *u82_ptr = mc_ptr->ParamUnion.U32U32U16U8U8.U82;
        }
    }
}
";

$CoderDef.Add({MSGC: MSGC_U32U32U16U8U8, 
               APARAM: ", p0, p1, p2, p3, p4", 
               ENCODE_FPARAM: ", const uint32_t p0, const uint32_t p1, const uint16_t p2, const uint8_t p3, const uint8_t p4", 
               DECODE_FPARAM: ", uint32_t *p0, uint32_t *p1, uint16_t *p2, uint8_t *p3, uint8_t *p4",
               MSGC_STRUCT: $msgc_struct,
               MSGC_TYPENAME: $msgc_typename,
               MSGC_COMP: $msgc_comp,
               MSGC_DECOMP: $msgc_decomp});

##############  ##############
              
$msgc_typename = "msgc_param_u8arr14_t";
$msgc_struct ="

//typedef uint8_t u8Arr14_t[14];
typedef struct {
//    u8Arr14_t Array14;
    uint8_t Array14[14];
    uint8_t   Len;
} " + $msgc_typename +";
";

$msgc_comp = "
static inline void APP_MSGC_comp_U8ARR14 (msgc_t *mc_ptr,
                                      const msg_id_t msgid,
                                      const uint8_t *u8Arr_ptr,
                                      uint8_t len)
{
    if(len > 14) len = 14;
    mc_ptr->MsgId  = msgid;
    mc_ptr->ParamType = MSGC_U8ARR14;
    memcpy(mc_ptr->ParamUnion.U8ARR14.Array14, u8Arr_ptr, len);
    mc_ptr->ParamUnion.U8ARR14.Len = len;
}
";

$msgc_decomp = "
static inline void APP_MSGC_decomp_U8ARR14 (msgc_t *mc_ptr,
                                      uint8_t  **u8Arr_ptr_ptr,
                                      uint8_t   *len_ptr)
{
    if (mc_ptr->ParamType == MSGC_U8ARR14) {
        if (u8Arr_ptr_ptr != NULL) {
            *u8Arr_ptr_ptr = mc_ptr->ParamUnion.U8ARR14.Array14;
        }
        if (len_ptr != NULL) {
            *len_ptr = mc_ptr->ParamUnion.U8ARR14.Len;
        }
    }
}
";

$CoderDef.Add({MSGC: MSGC_U8ARR14, 
               APARAM: ", arr, len", 
               ENCODE_FPARAM: ", const uint8_t *arr, const uint8_t len", 
               DECODE_FPARAM: ", uint8_t **arr, uint8_t *len",
               MSGC_STRUCT: $msgc_struct,
               MSGC_TYPENAME: $msgc_typename,
               MSGC_COMP: $msgc_comp,
               MSGC_DECOMP: $msgc_decomp});

##############  ##############
              
## Create MSGC_BASE entry based on content in MSGC entry
FOR(HASH $Row : $CoderDef) {
	$Row.ADD({MSGC_BASE: $Row[MSGC]-$CoderPrefix});
}
##PRINTLN($CoderDef);

VALUE $EncoderFparams = "(msg_t *msg_ptr, const uint32_t portnum, const msgc_t *mc_ptr)";
VALUE $DecoderFparams = "(const msg_t *msg_ptr, msgc_t *mc_ptr)";

##################### Warning text ##########################

VALUE $AutogenWarning = "
// *********************************************************
//   This file is autogenerated by RCD_scripter DO NOT EDIT this file.
// *********************************************************
";

##################### Script code for generation - DO NOT EDIT ##########################

############## Joint coder and message information into a new rowlist named "$CombinedDef" ##############
##############      Create a cell "IN_USE" indicating that some message uses this coder ##############
##############      Collect coders in use in a new ROWLIST $CoderInUseDef ##############
HASHLIST $CombinedDef;
FOR(HASH $MsgRow : $MsgDef) {
    FOR(HASH $CoderRow : $CoderDef) {
        IF ($MsgRow[MSGC] == $CoderRow[MSGC]) {
            $CoderRow.ADD({IN_USE: yes});
            HASH $NewRow;
            $NewRow.ADD($MsgRow);
            $NewRow.ADD($CoderRow);
            $CombinedDef.ADD($NewRow);
            ##PRINTLN("New combined row:" + $NewRow);
        }
    }
}

HASHLIST $CoderInUseDef;
FOR(HASH $CoderRow : $CoderDef) {
    IF($CoderRow[IN_USE] == yes) {
        $CoderInUseDef.ADD($CoderRow);
    }
}

##PRINTLN("****** CoderInUseDef:\n"+$CoderInUseDef);

############## Create a look-up table to find coder based on message tag ##############
VALARRAY $MsgArray;
$MsgArray.SETSIZE_DEFAULT(256, MSGC_NONE);
FOR(HASH $LoopRow : $MsgDef) {
	$MsgArray[$LoopRow[ENUM_VAL]] = $LoopRow[MSGC];
}

##################### Generating file "app_msg_gen.h" ##########################


FILE $GenFile;
$GenFile.OPEN("app_msg_gen.h");
$GenFile.PRINTLN($AutogenWarning);

$GenFile.PRINTLN("
#ifndef APP_MSG_GEN_H
#define APP_MSG_GEN_H    
");

$GenFile.PRINTLN("
#define ID_LEN       20 // Message ID length
");

$GenFile.PRINTLN("typedef enum {");
FOR(HASH $Row : $MsgDef) {
	$GenFile.PRINTLN("    " + $Row[MSGID] + "\t= " + $Row[ENUM_VAL] + ",\t /**< " + $Row[COMMENT] + "*"+"/");
}
$GenFile.PRINTLN("    " + "MSGID_ID_SIZE      /**< End of ENUM ... must be place as last entry *"+"/");
$GenFile.PRINTLN("} msg_id_t;");


$GenFile.PRINTLN("
typedef struct
{
    msg_id_t msgid;
    char msgid_name[ID_LEN];
} msgid_name_t;
");

$GenFile.PRINTLN("extern const msgid_name_t APP_MSG_GEN_MsgIdName[];");
$GenFile.PRINTLN("");

$GenFile.PRINTLN("bool APP_MSG_GEN_DoTrace(msg_id_t msg_id);");
$GenFile.PRINTLN("uint32_t APP_MSG_GEN_MsgIdNameSize(void);");
$GenFile.PRINTLN("");


$GenFile.PRINTLN("#endif");
$GenFile.PRINTLN("// END OF FILE");
$GenFile.CLOSE();

##################### Generating file "app_msg_gen.c" ##########################


$GenFile.OPEN("app_msg_gen.c");
$GenFile.PRINTLN($AutogenWarning);

$GenFile.PRINTLN("#include \"includes.h\"");
$GenFile.PRINTLN();

$GenFile.PRINTLN("// Message ID name conversion");
$GenFile.PRINTLN("const msgid_name_t APP_MSG_GEN_MsgIdName[] = {");

FOR(HASH $Row : $MsgDef) {
	$GenFile.PRINTLN("    { " + $Row[MSGID] + ",\t\"" + $Row[TRACE_NAME] + "\"\t},");
}
$GenFile.PRINT("
    { MSGID_TMR_SIGNAL,       \"UNKNOWN_SIGNAL\"     }  // Dummy entry, used if id not found
};
");

$GenFile.PRINT("
bool APP_MSG_GEN_DoTrace(msg_id_t msg_id){
    bool ret = true;
    switch (msg_id)
    {
");

FOR(HASH $Row : $MsgDef) {
    IF ($Row[TRACE] == "no") {
      $GenFile.PRINTLN("        case "+ $Row[MSGID] + ":");
    }
}

$GenFile.PRINT("
            ret = false;
            break;
    }
    return ret;
}
");

$GenFile.PRINT("
uint32_t APP_MSG_GEN_MsgIdNameSize(void){
    uint32_t table_size = sizeof(APP_MSG_GEN_MsgIdName)/sizeof(msgid_name_t);
    return table_size;
}
");


$GenFile.PRINTLN("// END OF FILE");
$GenFile.CLOSE();



##################### Generating file "app_msgc_gen.h" ##########################


$GenFile.OPEN("app_msgc_gen.h");
$GenFile.PRINTLN($AutogenWarning);

$GenFile.PRINTLN("
#include \"app_msg.h\"
");

$GenFile.PRINTLN("
#ifndef APP_MSGC_GEN_H
#define APP_MSGC_GEN_H    
");

$GenFile.PRINTLN("typedef enum {");
FOR(HASH $CoderRow : $CoderInUseDef) {
    $GenFile.PRINTLN("    "+$CoderRow[MSGC]+",");
}
$GenFile.PRINTLN("    MSGC_TYPE_MAX");
$GenFile.PRINTLN("} msgc_type_t;");

$GenFile.PRINTLN("");
$GenFile.PRINTLN("/*********************************************************************"+"/");
$GenFile.PRINTLN("");

FOR(HASH $CoderRow : $CoderInUseDef) {
    $GenFile.PRINTLN($CoderRow[MSGC_STRUCT]);
}
$GenFile.PRINTLN("");

$GenFile.PRINTLN("typedef union {");
FOR(HASH $CoderRow : $CoderInUseDef) {
    $GenFile.PRINTLN("    "+$CoderRow[MSGC_TYPENAME]+"\t"+$CoderRow[MSGC_BASE]+";");
}
$GenFile.PRINTLN("    "+"msg_t   MsgTypeStruct;");
$GenFile.PRINTLN("} msgc_param_union_t;");
$GenFile.PRINTLN("");


$GenFile.PRINTLN("
typedef struct {
    uint32_t   MsgId;
    uint32_t   ParamType;
    msgc_param_union_t ParamUnion;
} msgc_t;
");

VALUE $ArraySize = 0;
FOR(VALUE $Value : $MsgArray) {
	$ArraySize = $ArraySize + 1;
}

$GenFile.PRINTLN("");
$GenFile.PRINTLN("/*********************************************************************"+"/");
$GenFile.PRINTLN("");

$GenFile.PRINTLN("extern const uint8_t APP_MSGC_CoderTypeArr["+$ArraySize+"];");

$GenFile.PRINTLN("");
$GenFile.PRINTLN("/*********************************************************************"+"/");
$GenFile.PRINTLN("");

FOR(HASH $Row : $CoderInUseDef) {
	$GenFile.PRINTLN($Row[MSGC_COMP]);
	$GenFile.PRINTLN($Row[MSGC_DECOMP]);
    $GenFile.PRINTLN("");
    $GenFile.PRINTLN("/*********************************************************************"+"/");
    $GenFile.PRINTLN("");
}
FOR(HASH $Row : $CombinedDef) {
	$GenFile.PRINTLN("static inline void APP_MSGC_comp_" + $Row[MSGID] + "( msgc_t *mc_ptr" + $Row[ENCODE_FPARAM] + " ) {");
	$GenFile.PRINTLN("APP_MSGC_comp" +  "_" + $Row[MSGC_BASE] + "( mc_ptr, " + $Row[MSGID] +$Row[APARAM] + ");");
	$GenFile.PRINTLN("}");
    $GenFile.PRINTLN("");
	$GenFile.PRINTLN("static inline void APP_MSGC_decomp_" + $Row[MSGID] + "( msgc_t *mc_ptr" + $Row[DECODE_FPARAM] + " ) {");
	$GenFile.PRINTLN("APP_MSGC_decomp" +  "_" + $Row[MSGC_BASE] + "( mc_ptr" + $Row[APARAM] + ");");
	$GenFile.PRINTLN("}");
    $GenFile.PRINTLN("");

    $GenFile.PRINTLN("");
    $GenFile.PRINTLN("/*********************************************************************"+"/");
    $GenFile.PRINTLN("");
}


$GenFile.PRINTLN("#endif");
$GenFile.PRINTLN("// END OF FILE");
$GenFile.CLOSE();

##################### Generating file app_msgcarr_gen.c ##########################

$GenFile.OPEN("app_msgcarr_gen.c");
$GenFile.PRINTLN($AutogenWarning);

$GenFile.PRINTLN("#include \"includes.h\"");
$GenFile.PRINTLN("#include \"app_msgc_gen.h\"");
$GenFile.PRINTLN("");

$GenFile.PRINTLN("const uint8_t APP_MSGC_CoderTypeArr["+$ArraySize+"] = {");

VALUE $Counter = 0;
VALUE $LineCounter = 0;
VALUE $EntriesPerRow = 8;
VALUE $First = 0;
VALUE $Last = 0;
FOR(VALUE $Value : $MsgArray) {
	$GenFile.PRINT($Value+",\t");
	$Counter = $Counter + 1;
	$LineCounter = $LineCounter + 1;
	IF($LineCounter == $EntriesPerRow) {
		$First = $Counter - $EntriesPerRow;
		$Last = $Counter - 1;
		$LineCounter = 0;
		$GenFile.PRINTLN(" /* "+$First+".."+$Last+" *"+"/");
	}
}
$GenFile.PRINTLN("};");

$GenFile.PRINTLN("");
$GenFile.PRINTLN("/*********************************************************************"+"/");
$GenFile.PRINTLN("");

$GenFile.PRINTLN("// END OF FILE");
$GenFile.CLOSE();

##################### Generating file app_msgc_coderdecl_gen.c ##########################

$GenFile.OPEN("app_msgc_coderdecl_gen.h");
$GenFile.PRINTLN($AutogenWarning);

$GenFile.PRINTLN("
#ifndef APP_MSGC_CODERDECL_GEN_C
#define APP_MSGC_CODERDECL_GEN_C    
");

FOR(HASH $Row : $CoderInUseDef) {
  $GenFile.PRINTLN("#define DECODER_IN_USE_"+$Row[MSGC_BASE]);
}
$GenFile.PRINTLN("");



$GenFile.PRINTLN("typedef error_t (*encoder_fptr_t) (msg_t *msg_ptr, const uint32_t portnum, const msgc_t *mc_ptr);");
$GenFile.PRINTLN("");

FOR(HASH $Row : $CoderInUseDef) {
    $GenFile.PRINTLN("static error_t MsgcEncoder"+$Row[MSGC_BASE]+$EncoderFparams+";");
}
$GenFile.PRINTLN("");

$GenFile.PRINTLN("static const encoder_fptr_t _encoderTypeFptrArr[MSGC_TYPE_MAX] = {");
FOR(HASH $Row : $CoderInUseDef) {
  	 $GenFile.PRINTLN("    &MsgcEncoder"+$Row[MSGC_BASE]+",");
}
$GenFile.PRINTLN("};");

$GenFile.PRINTLN("");
$GenFile.PRINTLN("/*********************************************************************"+"/");
$GenFile.PRINTLN("");

$GenFile.PRINTLN("typedef error_t (*decoder_fptr_t) (const msg_t *msg_ptr, msgc_t *mc_ptr);");
$GenFile.PRINTLN("");

FOR(HASH $Row : $CoderInUseDef) {
   	$GenFile.PRINTLN("static error_t MsgcDecoder"+$Row[MSGC_BASE]+$DecoderFparams+";");
}
$GenFile.PRINTLN("");

$GenFile.PRINTLN("static const decoder_fptr_t _decoderTypeFptrArr[MSGC_TYPE_MAX] = {");
FOR(HASH $Row : $CoderInUseDef) {
   	$GenFile.PRINTLN("    &MsgcDecoder"+$Row[MSGC_BASE]+",");
}
$GenFile.PRINTLN("};");


$GenFile.PRINTLN("#endif");
$GenFile.PRINTLN("// END OF FILE");
$GenFile.CLOSE();


$GenFile.INSERT("insert_test.c", "INSERT");
$GenFile.PRINTLN("Another text 2");
$GenFile.PRINTF("Int %d String <%s>\n", 42, "Min tekst");
$GenFile.PRINTF("Only text\n");
$GenFile.CLOSE();

$GenFile.OPEN("test.c");
$GenFile.PRINTLN("Another text 2");
$GenFile.PRINTF("Int %d String <%s>\n", 42, "Min tekst");
$GenFile.PRINTF("Only text\n");
$GenFile.CLOSE();

RCD_SCRIPT_END

*/