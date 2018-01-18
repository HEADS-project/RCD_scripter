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

// *********************************************************
//   This file is autogenerated by RCD_scripter DO NOT EDIT this file.
// *********************************************************


#ifndef APP_MSG_GEN_H
#define APP_MSG_GEN_H    


#define ID_LEN       20 // Message ID length

typedef enum {
    MSGID_TMR_SIGNAL	= 0x01,	 /**< Time signal                                  */
    MSGID_TIME_TICK	= 0x02,	 /**< Time tick from OS                            */
    MSGID_BELT_STATUS	= 0x03,	 /**< Chest belt status change                     */
    MSGID_BATT_STATUS	= 0x04,	 /**< Battery status change                        */
    MSGID_CHARGE_CURRENT	= 0x05,	 /**< Charge current (in mA)                       */
    MSGID_SWITCH_EVENT	= 0x06,	 /**< Push-button (on/off switch) status change    */
    MSGID_SET_DATA_MODE	= 0x07,	 /**< Set Data logging mode                        */
    MSGID_GET_DATA_MODE	= 0x08,	 /**< Get Data logging mode                        */
    MSGID_FULL_CLK	= 0x09,	 /**< Get full clock time                          */
    MSGID_REFERENCE_CLK	= 0x0a,	 /**< Get reference clock time                     */
    MSGID_GET_SERNUM	= 0x0b,	 /**< Get CU serial number                         */
    MSGID_GET_FW_REV	= 0x0c,	 /**< Get CU firmware revision                     */
    MSGID_CTRL_STATE	= 0x0d,	 /**< Control status change                        */
    MSGID_UIND_CMD	= 0x0e,	 /**< User Indicator (LED or vibrator) act. cmd    */
    MSGID_CONN_STATUS	= 0x0f,	 /**< Connection status change                     */
    MSGID_ADC_DATA	= 0x10,	 /**< ADC sample                                   */
    MSGID_GOTO_STANDBY	= 0x11,	 /**< Timout since last resync, go to standby      */
    MSGID_UIND_OVR_CMD	= 0x12,	 /**< User Indicator LED act. override cmd         */
    MSGID_CHARGER_CONNECT	= 0x13,	 /**< Charger connected or disconnected            */
    MSGID_PWR_STATE	= 0x14,	 /**< Set or rep power state for uC and peripherals*/
    MSGID_BATT_LEVEL_DATA	= 0x15,	 /**< Battery level data                           */
    MSGID_BATT_LEVEL_FORCED	= 0x16,	 /**< Battery level forced                         */
    MSGID_CTRL_STATE_FORCED	= 0x17,	 /**< Control status forced                        */
    MSGID_CTRL_STATE_REQ	= 0x18,	 /**< Control status request                       */
    MSGID_DEBUG_SET	= 0x19,	 /**< Control Debug                                */
    MSGID_SET_IMM_ALERT	= 0x1a,	 /**< Set immidiate alert                          */
    MSGID_GET_CH	= 0x1b,	 /**< Character from console in PC-app */
    MSGID_SENDER_TEST	= 0x1c,	 /**< Test output from task_sender_class */
    MSGID_RESET_TEST	= 0x1d,	 /**< Generic reset to processes  */
    MSGID_PING0	= 0x1e,	 /**< ThingMl test message  */
    MSGID_PING1	= 0x1f,	 /**< ThingMl test message  */
    MSGID_PONG1	= 0x20,	 /**< ThingMl test message  */
    MSGID_PONG2	= 0x21,	 /**< ThingMl test message  */
    MSGID_TUNNEL	= 0x22,	 /**< ThingMl tunneling message  */
    MSGID_BT_ON_RDY	= 0x23,	 /**< Bluetooth rdy interrupt                      */
    MSGID_BT_ADVERTISE	= 0x24,	 /**< BT start advertising                         */
    MSGID_PRESSURE_DATA	= 0x25,	 /**< Pressure sensor level data                   */
    MSGID_PRESSURE_TEMP	= 0x26,	 /**< Pressure sensor temperature data             */
    MSGID_PRESSURE_PHASE	= 0x27,	 /**< Pressure update phase (point in time)        */
    MSGID_PRESSURE_CHECK	= 0x28,	 /**< Pressure check                               */
    MSGID_PB_FRAME_CHECKH	= 0x29,	 /**< Pressure Broadcast frame check high 16-bit   */
    MSGID_PB_FRAME_CHECKL	= 0x2A,	 /**< Pressure Broadcast frame check low 16-bit    */
    MSGID_PB_FRAME_PHASE	= 0x2B,	 /**< Pressure Broadcast frame update phase (point in time)*/
    MSGID_PUT_CH	= 0x2C,	 /**< haracter to console in PC-app                */
    MSGID_ADT_TEMP_DATA	= 0x2D,	 /**< ADT Temperature level data                   */
    MSGID_ADT_TEMP_CHECK	= 0x2E,	 /**< ADT Temperature check                        */
    MSGID_ADT_TEMP_INTERVAL	= 0x2F,	 /**< DT Temperature update interval               */
    MSGID_BT_TUNNELMSG	= 0x30,	 /**< Debug ThingML BLE tunneling message*/
    MSGID_PING2	= 0x31,	 /**< ThingMl test message  */
    MSGID_ID_SIZE      /**< End of ENUM ... must be place as last entry */
} msg_id_t;

typedef struct
{
    msg_id_t msgid;
    char msgid_name[ID_LEN];
} msgid_name_t;

extern const msgid_name_t APP_MSG_GEN_MsgIdName[];

bool APP_MSG_GEN_DoTrace(msg_id_t msg_id);
uint32_t APP_MSG_GEN_MsgIdNameSize(void);

#endif
// END OF FILE
