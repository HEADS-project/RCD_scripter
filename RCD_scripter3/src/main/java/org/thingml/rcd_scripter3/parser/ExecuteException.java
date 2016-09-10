package org.thingml.rcd_scripter3.parser;

/**
 * This exception is thrown when execution errors are encountered.
 * You can explicitly create objects of this exception type by
 * calling the method generateExecuteException in the ASTRcdBase.
 *
 */
public class ExecuteException extends Exception {

  public ExecuteException(Token currentTokenVal, String message)
  {
    super(initialise(currentTokenVal, message));
    currentToken = currentTokenVal;
    message = message;
  }

  /**
   * This is the last token that has been consumed successfully.  If
   * this object has been created due to a parse error, the token
   * followng this token will (therefore) be the first error token.
   */
  public Token currentToken;

  private static String initialise(Token t, String message) {

    String ret = message;
    if (t != null) {
        ret += "\n Image:<"+t.image+"> beginline:"+t.beginLine+" beginColumn:"+t.beginColumn+" endLine:"+t.endLine+" endColumn:"+t.endColumn;
    } else {
        ret += "\n No token info available";
    }
    return ret;
  }


  /**
   * Used to convert raw characters to their escaped version
   * when these raw version cannot be used as part of an ASCII
   * string literal.
   */
  static String add_escapes(String str) {
      StringBuffer retval = new StringBuffer();
      char ch;
      for (int i = 0; i < str.length(); i++) {
        switch (str.charAt(i))
        {
           case '\b':
              retval.append("\\b");
              continue;
           case '\t':
              retval.append("\\t");
              continue;
           case '\n':
              retval.append("\\n");
              continue;
           case '\f':
              retval.append("\\f");
              continue;
           case '\r':
              retval.append("\\r");
              continue;
           case '\"':
              retval.append("\\\"");
              continue;
           case '\'':
              retval.append("\\\'");
              continue;
           case '\\':
              retval.append("\\\\");
              continue;
           default:
              if ((ch = str.charAt(i)) < 0x20 || ch > 0x7e) {
                 String s = "0000" + Integer.toString(ch, 16);
                 retval.append("\\u" + s.substring(s.length() - 4, s.length()));
              } else {
                 retval.append(ch);
              }
              continue;
        }
      }
      return retval.toString();
   }

}
