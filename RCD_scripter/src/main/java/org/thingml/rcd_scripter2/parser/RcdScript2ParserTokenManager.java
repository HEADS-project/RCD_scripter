/* RcdScript2ParserTokenManager.java */
/* Generated By:JavaCC: Do not edit this line. RcdScript2ParserTokenManager.java */
package org.thingml.rcd_scripter2.parser;
import org.thingml.rcd_scripter2.jobs.*;

/** Token Manager. */
@SuppressWarnings("unused")public class RcdScript2ParserTokenManager implements RcdScript2ParserConstants {

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0(){
   switch(curChar)
   {
      case 9:
         jjmatchedKind = 5;
         return jjMoveNfa_0(0, 0);
      case 10:
         jjmatchedKind = 1;
         return jjMoveNfa_0(0, 0);
      case 13:
         jjmatchedKind = 2;
         return jjMoveStringLiteralDfa1_0(0x8L);
      case 32:
         jjmatchedKind = 6;
         return jjMoveNfa_0(0, 0);
      case 35:
         return jjMoveStringLiteralDfa1_0(0x100L);
      case 36:
         jjmatchedKind = 41;
         return jjMoveNfa_0(0, 0);
      case 42:
         jjmatchedKind = 44;
         return jjMoveNfa_0(0, 0);
      case 43:
         jjmatchedKind = 42;
         return jjMoveNfa_0(0, 0);
      case 44:
         jjmatchedKind = 38;
         return jjMoveNfa_0(0, 0);
      case 45:
         jjmatchedKind = 43;
         return jjMoveNfa_0(0, 0);
      case 46:
         jjmatchedKind = 39;
         return jjMoveNfa_0(0, 0);
      case 47:
         jjmatchedKind = 45;
         return jjMoveStringLiteralDfa1_0(0x80L);
      case 61:
         jjmatchedKind = 40;
         return jjMoveNfa_0(0, 0);
      case 65:
         return jjMoveStringLiteralDfa1_0(0x3000L);
      case 67:
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 68:
         return jjMoveStringLiteralDfa1_0(0x800L);
      case 70:
         return jjMoveStringLiteralDfa1_0(0x18000L);
      case 72:
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 73:
         return jjMoveStringLiteralDfa1_0(0x1c0000L);
      case 80:
         return jjMoveStringLiteralDfa1_0(0x200000L);
      case 82:
         return jjMoveStringLiteralDfa1_0(0x400000L);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x800000L);
      case 84:
         return jjMoveStringLiteralDfa1_0(0x1000000L);
      case 86:
         return jjMoveStringLiteralDfa1_0(0x2000000L);
      case 92:
         jjmatchedKind = 4;
         return jjMoveNfa_0(0, 0);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x3000L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x800L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x18000L);
      case 104:
         return jjMoveStringLiteralDfa1_0(0x20000L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x1c0000L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x200000L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x400000L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x800000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x1000000L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x2000000L);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0){
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 0);
   }
   switch(curChar)
   {
      case 10:
         if ((active0 & 0x8L) != 0L)
         {
            jjmatchedKind = 3;
            jjmatchedPos = 1;
         }
         break;
      case 35:
         if ((active0 & 0x100L) != 0L)
         {
            jjmatchedKind = 8;
            jjmatchedPos = 1;
         }
         break;
      case 47:
         if ((active0 & 0x80L) != 0L)
         {
            jjmatchedKind = 7;
            jjmatchedPos = 1;
         }
         break;
      case 65:
         return jjMoveStringLiteralDfa2_0(active0, 0x3020000L);
      case 68:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000L);
      case 69:
         return jjMoveStringLiteralDfa2_0(active0, 0x804800L);
      case 70:
         if ((active0 & 0x40000L) != 0L)
         {
            jjmatchedKind = 18;
            jjmatchedPos = 1;
         }
         break;
      case 73:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L);
      case 78:
         if ((active0 & 0x80000L) != 0L)
         {
            jjmatchedKind = 19;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x100000L);
      case 79:
         return jjMoveStringLiteralDfa2_0(active0, 0x410000L);
      case 82:
         return jjMoveStringLiteralDfa2_0(active0, 0x202000L);
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x3020000L);
      case 100:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x804800L);
      case 102:
         if ((active0 & 0x40000L) != 0L)
         {
            jjmatchedKind = 18;
            jjmatchedPos = 1;
         }
         break;
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L);
      case 110:
         if ((active0 & 0x80000L) != 0L)
         {
            jjmatchedKind = 19;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x100000L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x410000L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x202000L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 1);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(0, 1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 1);
   }
   switch(curChar)
   {
      case 66:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000L);
      case 68:
         if ((active0 & 0x1000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 2;
         }
         break;
      case 70:
         if ((active0 & 0x800L) != 0L)
         {
            jjmatchedKind = 11;
            jjmatchedPos = 2;
         }
         break;
      case 73:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000L);
      case 76:
         return jjMoveStringLiteralDfa3_0(active0, 0x200c000L);
      case 82:
         return jjMoveStringLiteralDfa3_0(active0, 0x12000L);
      case 83:
         if ((active0 & 0x20000L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 2;
         }
         break;
      case 84:
         if ((active0 & 0x100000L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active0, 0x800000L);
      case 87:
         if ((active0 & 0x400000L) != 0L)
         {
            jjmatchedKind = 22;
            jjmatchedPos = 2;
         }
         break;
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000L);
      case 100:
         if ((active0 & 0x1000L) != 0L)
         {
            jjmatchedKind = 12;
            jjmatchedPos = 2;
         }
         break;
      case 102:
         if ((active0 & 0x800L) != 0L)
         {
            jjmatchedKind = 11;
            jjmatchedPos = 2;
         }
         break;
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x200c000L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x12000L);
      case 115:
         if ((active0 & 0x20000L) != 0L)
         {
            jjmatchedKind = 17;
            jjmatchedPos = 2;
         }
         break;
      case 116:
         if ((active0 & 0x100000L) != 0L)
         {
            jjmatchedKind = 20;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active0, 0x800000L);
      case 119:
         if ((active0 & 0x400000L) != 0L)
         {
            jjmatchedKind = 22;
            jjmatchedPos = 2;
         }
         break;
      default :
         break;
   }
   return jjMoveNfa_0(0, 2);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(0, 2);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 2);
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000L);
      case 68:
         return jjMoveStringLiteralDfa4_0(active0, 0x800000L);
      case 69:
         if ((active0 & 0x8000L) != 0L)
         {
            jjmatchedKind = 15;
            jjmatchedPos = 3;
         }
         break;
      case 76:
         if ((active0 & 0x4000L) != 0L)
         {
            jjmatchedKind = 14;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active0, 0x1000000L);
      case 78:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000L);
      case 85:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000000L);
      case 95:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000L);
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000L);
      case 100:
         return jjMoveStringLiteralDfa4_0(active0, 0x800000L);
      case 101:
         if ((active0 & 0x8000L) != 0L)
         {
            jjmatchedKind = 15;
            jjmatchedPos = 3;
         }
         break;
      case 108:
         if ((active0 & 0x4000L) != 0L)
         {
            jjmatchedKind = 14;
            jjmatchedPos = 3;
         }
         return jjMoveStringLiteralDfa4_0(active0, 0x1000000L);
      case 110:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000000L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 3);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(0, 3);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 3);
   }
   switch(curChar)
   {
      case 69:
         if ((active0 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 24;
            jjmatchedPos = 4;
         }
         else if ((active0 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 25;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x810000L);
      case 84:
         if ((active0 & 0x200000L) != 0L)
         {
            jjmatchedKind = 21;
            jjmatchedPos = 4;
         }
         break;
      case 89:
         if ((active0 & 0x2000L) != 0L)
         {
            jjmatchedKind = 13;
            jjmatchedPos = 4;
         }
         break;
      case 101:
         if ((active0 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 24;
            jjmatchedPos = 4;
         }
         else if ((active0 & 0x2000000L) != 0L)
         {
            jjmatchedKind = 25;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x810000L);
      case 116:
         if ((active0 & 0x200000L) != 0L)
         {
            jjmatchedKind = 21;
            jjmatchedPos = 4;
         }
         break;
      case 121:
         if ((active0 & 0x2000L) != 0L)
         {
            jjmatchedKind = 13;
            jjmatchedPos = 4;
         }
         break;
      default :
         break;
   }
   return jjMoveNfa_0(0, 4);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(0, 4);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 4);
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa6_0(active0, 0x10000L);
      case 70:
         if ((active0 & 0x800000L) != 0L)
         {
            jjmatchedKind = 23;
            jjmatchedPos = 5;
         }
         break;
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0x10000L);
      case 102:
         if ((active0 & 0x800000L) != 0L)
         {
            jjmatchedKind = 23;
            jjmatchedPos = 5;
         }
         break;
      default :
         break;
   }
   return jjMoveNfa_0(0, 5);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(0, 5);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 5);
   }
   switch(curChar)
   {
      case 67:
         return jjMoveStringLiteralDfa7_0(active0, 0x10000L);
      case 99:
         return jjMoveStringLiteralDfa7_0(active0, 0x10000L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 6);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0){
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(0, 6);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 6);
   }
   switch(curChar)
   {
      case 72:
         if ((active0 & 0x10000L) != 0L)
         {
            jjmatchedKind = 16;
            jjmatchedPos = 7;
         }
         break;
      case 104:
         if ((active0 & 0x10000L) != 0L)
         {
            jjmatchedKind = 16;
            jjmatchedPos = 7;
         }
         break;
      default :
         break;
   }
   return jjMoveNfa_0(0, 7);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int strKind = jjmatchedKind;
   int strPos = jjmatchedPos;
   int seenUpto;
   input_stream.backup(seenUpto = curPos + 1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { throw new Error("Internal Error"); }
   curPos = 0;
   int startsAt = 0;
   jjnewStateCnt = 23;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 28)
                        kind = 28;
                     { jjCheckNAdd(22); }
                  }
                  else if (curChar == 41)
                  {
                     if (kind > 35)
                        kind = 35;
                     { jjCheckNAdd(17); }
                  }
                  else if (curChar == 40)
                  {
                     if (kind > 34)
                        kind = 34;
                     { jjCheckNAdd(16); }
                  }
                  else if (curChar == 34)
                     { jjCheckNAddStates(0, 2); }
                  else if (curChar == 36)
                     jjstateSet[jjnewStateCnt++] = 1;
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 33)
                        kind = 33;
                  }
                  if (curChar == 48)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 2:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 26)
                     kind = 26;
                  jjstateSet[jjnewStateCnt++] = 2;
                  break;
               case 3:
                  if (curChar == 48)
                     jjstateSet[jjnewStateCnt++] = 4;
                  break;
               case 5:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 29)
                     kind = 29;
                  jjstateSet[jjnewStateCnt++] = 5;
                  break;
               case 6:
                  if (curChar == 34)
                     { jjCheckNAddStates(0, 2); }
                  break;
               case 7:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     { jjCheckNAddStates(0, 2); }
                  break;
               case 9:
                  if ((0x8400000000L & l) != 0L)
                     { jjCheckNAddStates(0, 2); }
                  break;
               case 10:
                  if (curChar == 34 && kind > 30)
                     kind = 30;
                  break;
               case 11:
                  if ((0xff000000000000L & l) != 0L)
                     { jjCheckNAddStates(3, 6); }
                  break;
               case 12:
                  if ((0xff000000000000L & l) != 0L)
                     { jjCheckNAddStates(0, 2); }
                  break;
               case 13:
                  if ((0xf000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 14;
                  break;
               case 14:
                  if ((0xff000000000000L & l) != 0L)
                     { jjCheckNAdd(12); }
                  break;
               case 15:
                  if ((0x3ff000000000000L & l) != 0L && kind > 33)
                     kind = 33;
                  break;
               case 16:
                  if (curChar != 40)
                     break;
                  if (kind > 34)
                     kind = 34;
                  { jjCheckNAdd(16); }
                  break;
               case 17:
                  if (curChar != 41)
                     break;
                  if (kind > 35)
                     kind = 35;
                  { jjCheckNAdd(17); }
                  break;
               case 21:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 27)
                     kind = 27;
                  jjstateSet[jjnewStateCnt++] = 21;
                  break;
               case 22:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 28)
                     kind = 28;
                  { jjCheckNAdd(22); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 27)
                        kind = 27;
                     { jjCheckNAdd(21); }
                  }
                  else if (curChar == 125)
                  {
                     if (kind > 37)
                        kind = 37;
                     { jjCheckNAdd(19); }
                  }
                  else if (curChar == 123)
                  {
                     if (kind > 36)
                        kind = 36;
                     { jjCheckNAdd(18); }
                  }
                  if ((0x7e0000007eL & l) != 0L)
                  {
                     if (kind > 33)
                        kind = 33;
                  }
                  break;
               case 1:
               case 2:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 26)
                     kind = 26;
                  { jjCheckNAdd(2); }
                  break;
               case 4:
                  if ((0x100000001000000L & l) != 0L)
                     { jjCheckNAdd(5); }
                  break;
               case 5:
                  if ((0x7e0000007eL & l) == 0L)
                     break;
                  if (kind > 29)
                     kind = 29;
                  { jjCheckNAdd(5); }
                  break;
               case 7:
                  if ((0xffffffffefffffffL & l) != 0L)
                     { jjCheckNAddStates(0, 2); }
                  break;
               case 8:
                  if (curChar == 92)
                     { jjAddStates(7, 9); }
                  break;
               case 9:
                  if ((0x14404410000000L & l) != 0L)
                     { jjCheckNAddStates(0, 2); }
                  break;
               case 15:
                  if ((0x7e0000007eL & l) != 0L && kind > 33)
                     kind = 33;
                  break;
               case 18:
                  if (curChar != 123)
                     break;
                  if (kind > 36)
                     kind = 36;
                  { jjCheckNAdd(18); }
                  break;
               case 19:
                  if (curChar != 125)
                     break;
                  if (kind > 37)
                     kind = 37;
                  { jjCheckNAdd(19); }
                  break;
               case 20:
               case 21:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 27)
                     kind = 27;
                  { jjCheckNAdd(21); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 7:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     { jjAddStates(0, 2); }
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 23 - (jjnewStateCnt = startsAt)))
         break;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { break; }
   }
   if (jjmatchedPos > strPos)
      return curPos;

   int toRet = Math.max(curPos, seenUpto);

   if (curPos < toRet)
      for (i = toRet - Math.min(curPos, seenUpto); i-- > 0; )
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) { throw new Error("Internal Error : Please send a bug report."); }

   if (jjmatchedPos < strPos)
   {
      jjmatchedKind = strKind;
      jjmatchedPos = strPos;
   }
   else if (jjmatchedPos == strPos && jjmatchedKind > strKind)
      jjmatchedKind = strKind;

   return toRet;
}
private int jjMoveStringLiteralDfa0_1()
{
   return jjMoveNfa_1(0, 0);
}
private int jjMoveNfa_1(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 3;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x2400L & l) != 0L)
                  {
                     if (kind > 9)
                        kind = 9;
                  }
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               case 1:
                  if (curChar == 10 && kind > 9)
                     kind = 9;
                  break;
               case 2:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 1;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 3 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   7, 8, 10, 7, 8, 12, 10, 9, 11, 13, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, null, null, null, null, "\54", "\56", 
"\75", "\44", "\53", "\55", "\52", "\57", };
protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(Exception e)
   {
      jjmatchedKind = 0;
      jjmatchedPos = -1;
      matchedToken = jjFillToken();
      matchedToken.specialToken = specialToken;
      return matchedToken;
   }
   image = jjimage;
   image.setLength(0);
   jjimageLen = 0;

   for (;;)
   {
     switch(curLexState)
     {
       case 0:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_0();
         break;
       case 1:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_1();
         if (jjmatchedPos == 0 && jjmatchedKind > 10)
         {
            jjmatchedKind = 10;
         }
         break;
     }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
           matchedToken.specialToken = specialToken;
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           return matchedToken;
        }
        else if ((jjtoSkip[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
           {
              matchedToken = jjFillToken();
              if (specialToken == null)
                 specialToken = matchedToken;
              else
              {
                 matchedToken.specialToken = specialToken;
                 specialToken = (specialToken.next = matchedToken);
              }
              SkipLexicalActions(matchedToken);
           }
           else
              SkipLexicalActions(null);
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
        jjimageLen += jjmatchedPos + 1;
      if (jjnewLexState[jjmatchedKind] != -1)
        curLexState = jjnewLexState[jjmatchedKind];
        curPos = 0;
        jjmatchedKind = 0x7fffffff;
        try {
           curChar = input_stream.readChar();
           continue;
        }
        catch (java.io.IOException e1) { }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
   }
  }
}

void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

    /** Constructor. */
    public RcdScript2ParserTokenManager(SimpleCharStream stream){

      if (SimpleCharStream.staticFlag)
            throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");

    input_stream = stream;
  }

  /** Constructor. */
  public RcdScript2ParserTokenManager (SimpleCharStream stream, int lexState){
    ReInit(stream);
    SwitchTo(lexState);
  }

  /** Reinitialise parser. */
  public void ReInit(SimpleCharStream stream)
  {
	
    jjmatchedPos = jjnewStateCnt = 0;
    curLexState = defaultLexState;
    input_stream = stream;
    ReInitRounds();
  }

  private void ReInitRounds()
  {
    int i;
    jjround = 0x80000001;
    for (i = 23; i-- > 0;)
      jjrounds[i] = 0x80000000;
  }

  /** Reinitialise parser. */
  public void ReInit( SimpleCharStream stream, int lexState)
  {
  
    ReInit( stream);
    SwitchTo(lexState);
  }

  /** Switch to specified lex state. */
  public void SwitchTo(int lexState)
  {
    if (lexState >= 2 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
    else
      curLexState = lexState;
  }

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "IN_SINGLE_LINE_COMMENT",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, -1, -1, -1, 1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0x3ffffffff801L, 
};
static final long[] jjtoSkip = {
   0x2feL, 
};
static final long[] jjtoSpecial = {
   0x200L, 
};
static final long[] jjtoMore = {
   0x500L, 
};
    protected SimpleCharStream  input_stream;

    private final int[] jjrounds = new int[23];
    private final int[] jjstateSet = new int[2 * 23];

    private final StringBuilder jjimage = new StringBuilder();
    private StringBuilder image = jjimage;
    private int jjimageLen;
    private int lengthOfMatch;
    
    protected int curChar;
}
