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


RCD_SCRIPT_END

*/