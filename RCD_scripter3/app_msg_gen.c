
// *********************************************************
//   This file is autogenerated by RCD_scripter DO NOT EDIT this file.
// *********************************************************

#include "includes.h"

// Message ID name conversion
const msgid_name_t APP_MSG_GEN_MsgIdName[] = {
    { MSGID_TMR_SIGNAL,	"TMR_SIGNAL"	},
    { MSGID_TIME_TICK,	"TIME_TICK"	},
    { MSGID_BELT_STATUS,	"BELT_STATUS"	},
    { MSGID_BATT_STATUS,	"BATT_STATUS"	},
    { MSGID_CHARGE_CURRENT,	"CHARGE_CURRENT"	},
    { MSGID_SWITCH_EVENT,	"SWITCH_EVENT"	},
    { MSGID_SET_DATA_MODE,	"SET_DATA_MODE"	},
    { MSGID_GET_DATA_MODE,	"GET_DATA_MODE"	},
    { MSGID_FULL_CLK,	"FULL_CLK"	},
    { MSGID_REFERENCE_CLK,	"REFERENCE_CLK"	},
    { MSGID_GET_SERNUM,	"GET_SERNUM"	},
    { MSGID_GET_FW_REV,	"GET_FW_REV"	},
    { MSGID_CTRL_STATE,	"CTRL_STATE"	},
    { MSGID_UIND_CMD,	"UIND_CMD"	},
    { MSGID_CONN_STATUS,	"CONN_STATUS"	},
    { MSGID_ADC_DATA,	"ADC_DATA"	},
    { MSGID_GOTO_STANDBY,	"GOTO_STANDBY"	},
    { MSGID_UIND_OVR_CMD,	"UIND_OVR_CMD"	},
    { MSGID_CHARGER_CONNECT,	"CHARGER_CONNECT"	},
    { MSGID_PWR_STATE,	"PWR_STATE"	},
    { MSGID_BATT_LEVEL_DATA,	"BATT_LEVEL_DATA"	},
    { MSGID_BATT_LEVEL_FORCED,	"BATT_LEVEL_FORCED"	},
    { MSGID_CTRL_STATE_FORCED,	"CTRL_STATE_FORCED"	},
    { MSGID_CTRL_STATE_REQ,	"CTRL_STATE_REQ"	},
    { MSGID_DEBUG_SET,	"DEBUG_SET"	},
    { MSGID_SET_IMM_ALERT,	"SET_IMM_ALERT"	},
    { MSGID_GET_CH,	"GET_CH"	},
    { MSGID_SENDER_TEST,	"SENDER_TEST"	},
    { MSGID_RESET_TEST,	"RESET_TEST"	},
    { MSGID_PING0,	"PING0"	},
    { MSGID_PING1,	"PING1"	},
    { MSGID_PONG1,	"PONG1"	},
    { MSGID_PONG2,	"PONG2"	},
    { MSGID_TUNNEL,	"TUNNEL"	},
    { MSGID_BT_ON_RDY,	"BT_ON_RDY"	},
    { MSGID_BT_ADVERTISE,	"BT_ADVERTISE"	},
    { MSGID_PRESSURE_DATA,	"PRESSURE_DATA"	},
    { MSGID_PRESSURE_TEMP,	"PRESSURE_TEMP"	},
    { MSGID_PRESSURE_PHASE,	"PRESSURE_PHASE"	},
    { MSGID_PRESSURE_CHECK,	"PRESSURE_CHECK"	},
    { MSGID_PB_FRAME_CHECKH,	"PB_FRAME_CHECKH"	},
    { MSGID_PB_FRAME_CHECKL,	"PB_FRAME_CHECKL"	},
    { MSGID_PB_FRAME_PHASE,	"PB_FRAME_PHASE"	},
    { MSGID_PUT_CH,	"PUT_CH"	},
    { MSGID_ADT_TEMP_DATA,	"ADT_TEMP_DATA"	},
    { MSGID_ADT_TEMP_CHECK,	"ADT_TEMP_CHECK"	},
    { MSGID_ADT_TEMP_INTERVAL,	"ADT_TEMP_INTERVAL"	},
    { MSGID_BT_TUNNELMSG,	"BT_TUNNELMSG"	},
    { MSGID_PING2,	"PING2"	},

    { MSGID_TMR_SIGNAL,       "UNKNOWN_SIGNAL"     }  // Dummy entry, used if id not found
};

bool APP_MSG_GEN_DoTrace(msg_id_t msg_id){
    bool ret = true;
    switch (msg_id)
    {
        case MSGID_TMR_SIGNAL:
        case MSGID_TIME_TICK:
        case MSGID_CHARGE_CURRENT:
        case MSGID_UIND_CMD:
        case MSGID_ADC_DATA:
        case MSGID_CHARGER_CONNECT:
        case MSGID_GET_CH:

            ret = false;
            break;
    }
    return ret;
}

uint32_t APP_MSG_GEN_MsgIdNameSize(void){
    uint32_t table_size = sizeof(APP_MSG_GEN_MsgIdName)/sizeof(msgid_name_t);
    return table_size;
}
// END OF FILE