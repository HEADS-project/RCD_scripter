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
$tekst1 = "Heisan";
println("Tekst "+$tekst1);
$tall1 = 2;
println("Tall "+$tall1+" "+$tall1*$tall1);
$boolt = true;
$boolf = false;
println("Boolt "+$boolt+" "+($boolt AND $boolf)+" "+($boolt OR $boolf));
$real1 = 1.1;
println("Real "+$real1+" "+$real1*$tall1+" "+$tall1*$real1);
$array[11] = 1.1;
$array[2] = 2;
println("Real "+$array [11]+" Int "+$array [2]);
println("array "+$array.is_array());
println("array "+$array.is_int());
println("array[2] "+$array[2].is_array());
println("array[2] "+$array[2].is_int());

$tall = +2;
println("Tall +2 "+$tall);
$tall = -2;
println("Tall -2 "+$tall);
$tall = 2 --2;
println("Tall 2--2 "+$tall);
$tall = +2 - 2;
println("Tall +2-2 "+$tall);

$func_var = println;
$func_var("$func_var "+$func_var);
$var1 = 1;
$var2 = 2;
$var_use1 = true;
println("CondExpr "+$var_use1+" "+($var_use1 ? $var1 : $var2));
$var_use1 = false;
println("CondExpr "+$var_use1+" "+($var_use1 ? $var1 : $var2));




PROC my_func($in_val1, $in_val2, $in_string) {
    println("Input value1: " + $in_val1);
    println("Input value2: " + $in_val2);
    println("Input value3: " + $in_string);
    printf("Input int1:%d int2:%d string<%s>\n", $in_val1, $in_val2, $in_string);
    printf("Only text\n");
}

my_func(1 , 2, "Param tekst");


$GenFile = FILE();
$GenFile.OPEN("test.txt");
$GenFile.PRINT("Start på linje");
$GenFile.PRINTLN();
$GenFile.PRINTLN("Egen linje");
$GenFile.PRINTF("Int: %d  String <%s>\n", 42, "En tekst");
$GenFile.CLOSE();

PROC check_hash_func($in_hash, $key) {
    IF ($in_hash.HAS($key)) {
        PRINTLN("Key "+$key+" has value <"+$in_hash[$key]+"> Having size "+$in_hash[$key].length());
    } ELSE {
        PRINTLN("Key "+$key+" is not present in HASH");
    }
}

$test = {MSGID: MSGID_PING2 ,              ENUM_VAL: 0x31 ,  MSGC: MSGC_U16U16 ,  COMMENT: "ThingMl test message  "};
check_hash_func($test, COMMENT);
check_hash_func($test, TULL);

PROC check_string_func($txt, $start, $end) {
    PRINTLN("Check: <"+$txt+">");
    PRINTLN("Begin with: "+$start+" "+$txt.startswith($start));
    PRINTLN("End with: "+$end+" "+$txt.endswith($end));
    PRINTLN("Replace start to end: "+$txt.replace($start, $end));
    PRINTLN("Toupper: "+$txt.touppercase());
    PRINTLN("Tolower: "+$txt.tolowercase());
    PRINTLN("Trim: <"+$txt.trim()+">");
}

check_string_func("Start Tekst Slutt ", "Start", "Slutt ");


PROC addToArrayWithIndexFrom($to_array, $index_from, $array_to_add) {
    $to_array[$array_to_add[$index_from]].add($array_to_add);
}

PROC printArrayContent($name, $array) {
    IF ($array.is_array()) {
        println("Content of "+$name+" len "+($array.length()));
        FOR($pair : $array) {  
            printArrayContent($name+"["+$pair[key]+"]", $pair[val]);
        }
    } ELSE {
        println("  Elem "+$name+" : "+$array);
    }
}

PROC makeElemWithoutPrefix ($array, $new_key, $base_key, $prefix) {
    FOR ($pair : $array) {
        $this_elem = $pair[val];
        $this_elem[$new_key] = $this_elem[$base_key]-$prefix; 
    }
}



## This file is specifying how to generate c-code files with declarations messages with attributes and MSGC coders with attributes

## To run:
##    java -jar ./RCD_scripter.jar

##############  Message definitions ##############
## Small-message IDs (range 0x00 - 0xFF) 
## Data from this section is used to generate declarations for "enum msg_id_t", "_msgidName[]" and "_coderTypeArr[]"
## Data from this section is used to generate declarations for "APP_MSGC_CoderTypeArr[]" and inline coder 

$MsgIdPrefix = "MSGID_";
$MsgDef = Array() ;
$MsgDef.SetDef( {MSGC: MSGC_U16, MSGID: NONE, TRACE: yes,        COMMENT: "Default row"});

## A rowlist contains variable number of fields
## Following fields are in use for $MsgDef
## MSGID    - Message id used in emum msg_id_t and other related structures 
## ENUM_VAL - Value used for the message id
## COMMENT  - Text describing the message id
## TRACE    - Specifies whether the message shall be added to the trace buffer when sent
## MSGC     - Specifies the paramter set and coder to be used. This is a crosslink to $CoderDef

addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_TMR_SIGNAL ,         ENUM_VAL: 0x01 ,  TRACE: no ,          COMMENT: "Time signal                                  "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_TIME_TICK ,          ENUM_VAL: 0x02 ,  TRACE: no ,          COMMENT: "Time tick from OS                            "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_BELT_STATUS ,        ENUM_VAL: 0x03 ,                       COMMENT: "Chest belt status change                     "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_BATT_STATUS ,        ENUM_VAL: 0x04 ,                       COMMENT: "Battery status change                        "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_CHARGE_CURRENT ,     ENUM_VAL: 0x05 ,  TRACE: no ,          COMMENT: "Charge current (in mA)                       "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_SWITCH_EVENT ,       ENUM_VAL: 0x06 ,                       COMMENT: "Push-button (on/off switch) status change    "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_SET_DATA_MODE ,      ENUM_VAL: 0x07 ,                       COMMENT: "Set Data logging mode                        "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_GET_DATA_MODE ,      ENUM_VAL: 0x08 ,                       COMMENT: "Get Data logging mode                        "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_FULL_CLK ,           ENUM_VAL: 0x09 ,                       COMMENT: "Get full clock time                          "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_REFERENCE_CLK ,      ENUM_VAL: 0x0a ,                       COMMENT: "Get reference clock time                     "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_GET_SERNUM ,         ENUM_VAL: 0x0b ,                       COMMENT: "Get CU serial number                         "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_GET_FW_REV ,         ENUM_VAL: 0x0c ,                       COMMENT: "Get CU firmware revision                     "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_CTRL_STATE ,         ENUM_VAL: 0x0d ,                       COMMENT: "Control status change                        "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_UIND_CMD ,           ENUM_VAL: 0x0e ,  TRACE: no ,          COMMENT: "User Indicator (LED or vibrator) act. cmd    "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_CONN_STATUS ,        ENUM_VAL: 0x0f ,                       COMMENT: "Connection status change                     "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_ADC_DATA ,           ENUM_VAL: 0x10 ,  TRACE: no ,          COMMENT: "ADC sample                                   "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_GOTO_STANDBY ,       ENUM_VAL: 0x11 ,                       COMMENT: "Timout since last resync, go to standby      "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_UIND_OVR_CMD ,       ENUM_VAL: 0x12 ,                       COMMENT: "User Indicator LED act. override cmd         "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_CHARGER_CONNECT ,    ENUM_VAL: 0x13 ,  TRACE: no ,          COMMENT: "Charger connected or disconnected            "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_PWR_STATE ,          ENUM_VAL: 0x14 ,                       COMMENT: "Set or rep power state for uC and peripherals"});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_BATT_LEVEL_DATA ,    ENUM_VAL: 0x15 ,                       COMMENT: "Battery level data                           "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_BATT_LEVEL_FORCED ,  ENUM_VAL: 0x16 ,                       COMMENT: "Battery level forced                         "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_CTRL_STATE_FORCED ,  ENUM_VAL: 0x17 ,                       COMMENT: "Control status forced                        "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_CTRL_STATE_REQ ,     ENUM_VAL: 0x18 ,                       COMMENT: "Control status request                       "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_DEBUG_SET ,          ENUM_VAL: 0x19 ,                       COMMENT: "Control Debug                                "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_SET_IMM_ALERT ,      ENUM_VAL: 0x1a ,                       COMMENT: "Set immidiate alert                          "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_GET_CH ,             ENUM_VAL: 0x1b ,  TRACE: no ,          COMMENT: "Character from console in PC-app "} );
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_SENDER_TEST ,        ENUM_VAL: 0x1c ,  MSGC: MSGC_U16U16 ,  COMMENT: "Test output from task_sender_class "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_RESET_TEST ,         ENUM_VAL: 0x1d ,                       COMMENT: "Generic reset to processes  "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_PING0 ,              ENUM_VAL: 0x1e ,  MSGC: MSGC_NONE ,    COMMENT: "ThingMl test message  "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_PING1 ,              ENUM_VAL: 0x1f ,                       COMMENT: "ThingMl test message  "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_PONG1 ,              ENUM_VAL: 0x20 ,                       COMMENT: "ThingMl test message  "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_PONG2 ,              ENUM_VAL: 0x21 ,  MSGC: MSGC_U16U16 ,  COMMENT: "ThingMl test message  "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_TUNNEL ,             ENUM_VAL: 0x22 ,  MSGC: MSGC_U8ARR14 , COMMENT: "ThingMl tunneling message  "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_BT_ON_RDY ,          ENUM_VAL: 0x23 ,                       COMMENT: "Bluetooth rdy interrupt                      "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_BT_ADVERTISE ,       ENUM_VAL: 0x24 ,                       COMMENT: "BT start advertising                         "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_PRESSURE_DATA ,      ENUM_VAL: 0x25 ,                       COMMENT: "Pressure sensor level data                   "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_PRESSURE_TEMP ,      ENUM_VAL: 0x26 ,                       COMMENT: "Pressure sensor temperature data             "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_PRESSURE_PHASE ,     ENUM_VAL: 0x27 ,                       COMMENT: "Pressure update phase (point in time)        "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_PRESSURE_CHECK ,     ENUM_VAL: 0x28 ,                       COMMENT: "Pressure check                               "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_PB_FRAME_CHECKH ,    ENUM_VAL: 0x29 ,                       COMMENT: "Pressure Broadcast frame check high 16-bit   "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_PB_FRAME_CHECKL ,    ENUM_VAL: 0x2A ,                       COMMENT: "Pressure Broadcast frame check low 16-bit    "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_PB_FRAME_PHASE ,     ENUM_VAL: 0x2B ,                       COMMENT: "Pressure Broadcast frame update phase (point in time)"});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_PUT_CH ,             ENUM_VAL: 0x2C ,                       COMMENT: "haracter to console in PC-app                "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_ADT_TEMP_DATA ,      ENUM_VAL: 0x2D ,                       COMMENT: "ADT Temperature level data                   "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_ADT_TEMP_CHECK ,     ENUM_VAL: 0x2E ,                       COMMENT: "ADT Temperature check                        "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_ADT_TEMP_INTERVAL ,  ENUM_VAL: 0x2F ,                       COMMENT: "DT Temperature update interval               "});
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_BT_TUNNELMSG ,       ENUM_VAL: 0x30 ,                       COMMENT: "Debug ThingML BLE tunneling message"          });
addToArrayWithIndexFrom($MsgDef, MSGID, {MSGID: MSGID_PING2 ,              ENUM_VAL: 0x31 ,  MSGC: MSGC_U16U16 ,  COMMENT: "ThingMl test message  "});

makeElemWithoutPrefix($MsgDef, TRACE_NAME, MSGID, "MSGID_");

##printArrayContent("$MsgDef", $MsgDef);

println("Time now: "+ timenow());
timeformat("HH-mm-ss");
println("Time now: "+ timenow());


##################### Warning text ##########################

$AutogenWarning = "
// *********************************************************
//   This file is autogenerated by RCD_scripter DO NOT EDIT this file.
// *********************************************************
";

############## Create a look-up table to find coder based on message tag ##############
PROC addEnumToArray($array, $msg_entry) {
    $max = $array[MAX];
    $enum = $msg_entry[ENUM_VAL];
    if($max < $enum) {
        $array[MAX] = $enum;
    }
    $array[$enum] = $msg_entry[MSGC];
}


$MsgArray = Array();
$MsgArray.SetDef( MSGC_NONE );
$MsgArray[MAX] = -1;
FOR ($pair : $MsgDef) {
    addEnumToArray($MsgArray, $pair[val]);
}

##################### Generating file app_msgcarr_gen.c ##########################

$ArraySize = $MsgArray[MAX];

$GenFile = FILE();
$GenFile.OPEN("app_msgcarr_gen.c");
$GenFile.PRINTLN($AutogenWarning);

$GenFile.PRINTLN("#include \"includes.h\"");
$GenFile.PRINTLN("#include \"app_msgc_gen.h\"");
$GenFile.PRINTLN("");

$GenFile.PRINTLN("const uint8_t APP_MSGC_CoderTypeArr["+$ArraySize+"] = {");

$Counter = 0;
$LineCounter = 0;
$EntriesPerRow = 8;
$First = 0;
$Last = 0;

$idx = 0;
while($idx < $ArraySize) {
	$GenFile.PRINT($MsgArray[$idx]+",\t");
	$Counter = $Counter + 1;
	$LineCounter = $LineCounter + 1;
	IF($LineCounter == $EntriesPerRow) {
		$First = $Counter - $EntriesPerRow;
		$Last = $Counter - 1;
		$LineCounter = 0;
		$GenFile.PRINTLN(" /* "+$First+".."+$Last+" *"+"/");
	}
    $idx = $idx + 1;
}
$GenFile.PRINTLN("};");

$GenFile.PRINTLN("");
$GenFile.PRINTLN("/*********************************************************************"+"/");
$GenFile.PRINTLN("");

$GenFile.PRINTLN("// END OF FILE");
$GenFile.CLOSE();

RCD_SCRIPT_END

*/