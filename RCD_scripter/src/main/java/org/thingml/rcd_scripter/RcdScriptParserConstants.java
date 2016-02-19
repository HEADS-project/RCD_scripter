/* Generated By:JavaCC: Do not edit this line. RcdScriptParserConstants.java */
package org.thingml.rcd_scripter;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface RcdScriptParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SINGLE_LINE_COMMENT = 9;
  /** RegularExpression Id. */
  int CR_TAB = 11;
  /** RegularExpression Id. */
  int CR_TAB_IF_EQ = 12;
  /** RegularExpression Id. */
  int CR_TAB_JOIN = 13;
  /** RegularExpression Id. */
  int CR_COL_DEF = 14;
  /** RegularExpression Id. */
  int CR_COL_CAT = 15;
  /** RegularExpression Id. */
  int CR_ROW = 16;
  /** RegularExpression Id. */
  int PR_EACH_ROW = 17;
  /** RegularExpression Id. */
  int ID_LITERAL = 18;
  /** RegularExpression Id. */
  int DEC_LITERAL = 19;
  /** RegularExpression Id. */
  int HEX_LITERAL = 20;
  /** RegularExpression Id. */
  int STRING_LITERAL = 21;
  /** RegularExpression Id. */
  int LETTER = 22;
  /** RegularExpression Id. */
  int DIGIT = 23;
  /** RegularExpression Id. */
  int HEX_DIGIT = 24;
  /** RegularExpression Id. */
  int OBRA = 25;
  /** RegularExpression Id. */
  int CBRA = 26;
  /** RegularExpression Id. */
  int OCBRA = 27;
  /** RegularExpression Id. */
  int CCBRA = 28;
  /** RegularExpression Id. */
  int COMMA = 29;
  /** RegularExpression Id. */
  int PLUS = 30;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int IN_SINGLE_LINE_COMMENT = 1;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\r\\n\"",
    "\"\\\\\"",
    "\"\\t\"",
    "\" \"",
    "\"/\"",
    "\"##\"",
    "<SINGLE_LINE_COMMENT>",
    "<token of kind 10>",
    "\"CREATE_TAB\"",
    "\"CREATE_TAB_IF_EQ\"",
    "\"CREATE_TAB_JOIN\"",
    "\"CREATE_COL_DEF\"",
    "\"CREATE_COL_CAT\"",
    "\"CREATE_ROW\"",
    "\"PRINT_EACH_ROW\"",
    "<ID_LITERAL>",
    "<DEC_LITERAL>",
    "<HEX_LITERAL>",
    "<STRING_LITERAL>",
    "<LETTER>",
    "<DIGIT>",
    "<HEX_DIGIT>",
    "<OBRA>",
    "<CBRA>",
    "<OCBRA>",
    "<CCBRA>",
    "\",\"",
    "\"+\"",
  };

}