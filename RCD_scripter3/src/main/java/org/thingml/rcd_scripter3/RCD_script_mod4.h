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
RCD_SCRIPT_START

## This file is specifying how to generate c-code files with declarations messages with attributes and MSGC coders with attributes

## To run:
##    java -jar ./RCD_scripter.jar

##############  Message definitions ##############
## Small-message IDs (range 0x00 - 0xFF) 
## Data from this section is used to generate declarations for "enum msg_id_t", "_msgidName[]" and "_coderTypeArr[]"
## Data from this section is used to generate declarations for "APP_MSGC_CoderTypeArr[]" and inline coder 

VALUE $MsgIdPrefix = MSGID_;
HASHLIST $MsgDef ;
$MsgDef.SetDef({ MSGC : MSGC_U16, MSGID : NONE, TRACE : yes,        COMMENT : "Default row"});
## A rowlist contains variable number of fields
## Following fields are in use for $MsgDef
## MSGID    - Message id used in emum msg_id_t and other related structures 
## ENUM_VAL - Value used for the message id
## COMMENT  - Text describing the message id
## TRACE    - Specifies whether the message shall be added to the trace buffer when sent
## MSGC     - Specifies the paramter set and coder to be used. This is a crosslink to $CoderDef


$MsgDef.Add({ MSGID : MSGID_TMR_SIGNAL,        ENUM_VAL : 0x01, TRACE : no,          COMMENT : "Time signal                                  "});
$MsgDef.Add({ MSGID : MSGID_TIME_TICK,         ENUM_VAL : 0x02, TRACE : no,          COMMENT : "Time tick from OS                            "});
$MsgDef.Add({ MSGID : MSGID_BELT_STATUS,       ENUM_VAL : 0x03,                      COMMENT : "Chest belt status change                     "});
$MsgDef.Add({ MSGID : MSGID_BATT_STATUS,       ENUM_VAL : 0x04,                      COMMENT : "Battery status change                        "});
$MsgDef.Add({ MSGID : MSGID_CHARGE_CURRENT,    ENUM_VAL : 0x05, TRACE : no,          COMMENT : "Charge current (in mA)                       "});
$MsgDef.Add({ MSGID : MSGID_SWITCH_EVENT,      ENUM_VAL : 0x06,                      COMMENT : "Push-button (on/off switch) status change    "});
$MsgDef.Add({ MSGID : MSGID_PONG2,             ENUM_VAL : 0x21, MSGC : MSGC_U16U16,  COMMENT : "ThingMl test message  "});
$MsgDef.Add({ MSGID : MSGID_TUNNEL,            ENUM_VAL : 0x22, MSGC : MSGC_U8ARR14, COMMENT : "ThingMl tunneling message  "});
$MsgDef.Add({ MSGID : MSGID_BT_ON_RDY,         ENUM_VAL : 0x23,                      COMMENT : "Bluetooth rdy interrupt                      "});
$MsgDef.Add({ MSGID : MSGID_PING2,             ENUM_VAL : 0x31, MSGC : MSGC_U16U16,  COMMENT : "ThingMl test message  " });

## Create TRACE_NAME entry based on content in MSGID entry
FOR (HASH $Row : $MsgDef) {
	$Row.ADD({ TRACE_NAME : $Row[MSGID]-$MsgIdPrefix });
}
PRINTLN($MsgDef);


############## CODER definitions ##############
## Data from this section is used to generate declarations for "enum msg_id_t", "_msgidName[]" and "APP_MSGC_CoderTypeArr[]"
## Data from this section is used to generate declarations for "APP_MSGC_CoderTypeArr[]" and inline coder *

VALUE $CoderPrefix = MSGC_;
HASHLIST $CoderDef;
$CoderDef.SetDef({MSGC : MSGC_NONE, PARAMS : "", COMP_SIGN : "", DECOMP_SIGN : "", IN_USE : no});
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
              ENUM_VAL : 0x00, 
              APARAM : "", 
              ENCODE_FPARAM : "", 
              DECODE_FPARAM : "",
              MSGC_STRUCT : $msgc_struct,
              MSGC_TYPENAME : $msgc_typename,
              MSGC_COMP : $msgc_comp,
              MSGC_DECOMP : $msgc_decomp});

##############  ##############

              
## Create MSGC_BASE entry based on content in MSGC entry
FOR (HASH $Row : $CoderDef) {
	$Row.ADD({MSGC_BASE : $Row[MSGC]-$CoderPrefix});
}

PRINTLN($CoderDef);

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
FOR (HASH $MsgRow : $MsgDef) {
    FOR (HASH $CoderRow : $CoderDef) {
        IF ($MsgRow[MSGC] == $CoderRow[MSGC]) {
            $CoderRow[IN_USE] = yes;
            HASH $NewRow;
            $NewRow.ADD($MsgRow);
            $NewRow.ADD($CoderRow);
            $CombinedDef.ADD($NewRow);
            PRINTLN("New combined row:" + $NewRow);
        }
    }
}

HASHLIST $CoderInUseDef;
FOR(HASH $CoderRow : $CoderDef) {
    IF($CoderRow[IN_USE] == yes) {
        $CoderInUseDef.ADD($CoderRow);
    }
}

PRINTLN("****** CoderInUseDef:\n"+$CoderInUseDef);


############## Create a look-up table to find coder based on message tag ##############
VALARRAY $MsgArray;
$MsgArray.SETSIZE_DEFAULT(256, MSGC_NONE);
FOR (HASH $LoopRow : $MsgDef) {
	$MsgArray[$LoopRow[ENUM_VAL]] = $LoopRow[MSGC];
}

##################### Generating file "app_msg_gen.h" ##########################

PRINTLN("****** MsgArray:\n"+$MsgArray);


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
FOR (HASH $Row : $MsgDef) {
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
$GenFile.PRINTLN(""+ $AutogenWarning);

$GenFile.PRINTLN("#include \"includes.h\"");
$GenFile.PRINTLN("");

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
    IF ($Row[TRACE] == no) {
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
$GenFile.PRINTLN(""+ $AutogenWarning);

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
    $GenFile.PRINTLN(""+$CoderRow[MSGC_STRUCT]);
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
PRINTLN("********** $ArraySize : " + $ArraySize);


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
$GenFile.PRINTLN(""+ $AutogenWarning);

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
$GenFile.PRINTLN(""+ $AutogenWarning);

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
   	$GenFile.PRINTLN("static error_t MsgcDecoder"+$Row["MSGC_BASE"]+$DecoderFparams+";");
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

RCD_SCRIPT_STOP
RCD_SCRIPT_START

*/