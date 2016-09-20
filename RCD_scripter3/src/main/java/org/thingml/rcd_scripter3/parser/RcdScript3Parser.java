/* RcdScript3Parser.java */
/* Generated By:JJTree&JavaCC: Do not edit this line. RcdScript3Parser.java */
   package org.thingml.rcd_scripter3.parser;
   import java.io.FileReader;
   public class RcdScript3Parser/*@bgen(jjtree)*/implements RcdScript3ParserTreeConstants, RcdScript3ParserConstants {/*@bgen(jjtree)*/
  protected JJTRcdScript3ParserState jjtree = new JJTRcdScript3ParserState();private String fileName;
        public ASTRcdStart makeAst()throws ParseException, TokenMgrError
        {
            token_source.defaultLexState = IN_RCD_SKIP_REGION;
            token_source.curLexState = IN_RCD_SKIP_REGION;
                return(Start());
        }

        public void setFileName(String name) {
            fileName = name;
        }

  final public ASTRcdStart Start() throws ParseException {/*@bgen(jjtree) RcdStart */
  ASTRcdStart jjtn000 = new ASTRcdStart(JJTRCDSTART);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      Script();
      jj_consume_token(0);
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
{if ("" != null) return jjtn000;}
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
  }

  final public void Script() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INCLUDE:
      case VALARRAY:
      case VALUE:
      case FILE:
      case FOR:
      case IF:
      case PRINT:
      case HASH:
      case HASHLIST:
      case VAR_LITERAL:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INCLUDE:{
        Include();
        break;
        }
      case VALARRAY:
      case VALUE:
      case FILE:
      case HASH:
      case HASHLIST:{
        Def();
        break;
        }
      case FOR:
      case IF:{
        Block();
        break;
        }
      case PRINT:
      case VAR_LITERAL:{
        Statement();
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  final public void Include() throws ParseException {Token t;
        String fileName;
    RcdScript3Parser subParser;
    ASTRcdStart subAst;
    jj_consume_token(INCLUDE);
    jj_consume_token(OBRA);
    t = jj_consume_token(STRING_LITERAL);
    jj_consume_token(CBRA);
    jj_consume_token(SEMI);
fileName = t.image.substring(0, t.image.length()-1).substring(1); // Remove quotes
                System.out.println("Starting to include " + fileName);
        try{
                        subParser = new RcdScript3Parser(new FileReader(fileName));
                        subAst = subParser.makeAst();
            subAst.dump("Include: ");

                        if (subAst.children != null) {
                                for (int i = 0; i < subAst.children.length; ++i) {
                                        jjtree.openNodeScope(subAst.children[i]);
                                        jjtree.closeNodeScope(subAst.children[i], true);
                                }
                        }
                }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
                System.out.println("End of include " + fileName);
  }

// ************* Def xxx ***************
  final public void Def() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case HASHLIST:{
      DefHashList();
      break;
      }
    case HASH:{
      DefHash();
      break;
      }
    case FILE:{
      DefFile();
      break;
      }
    case VALUE:{
      DefValue();
      break;
      }
    case VALARRAY:{
      DefValArray();
      break;
      }
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(SEMI);
  }

  final public void DefHashList() throws ParseException {/*@bgen(jjtree) RcdDefHashList */
    ASTRcdDeclHashList jjtn000 = new ASTRcdDeclHashList(JJTRCDDEFHASHLIST);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      jj_consume_token(HASHLIST);
      t = jj_consume_token(VAR_LITERAL);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ASSIGN:{
        jj_consume_token(ASSIGN);
        Var();
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        ;
      }
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
jjtn000.setName(t.image);
        jjtn000.setToken(token);
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void DefHash() throws ParseException {/*@bgen(jjtree) RcdDefHash */
    ASTRcdDeclHash jjtn000 = new ASTRcdDeclHash(JJTRCDDEFHASH);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      jj_consume_token(HASH);
      t = jj_consume_token(VAR_LITERAL);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ASSIGN:{
        jj_consume_token(ASSIGN);
        Expr();
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        ;
      }
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
jjtn000.setName(t.image);
        jjtn000.setToken(token);
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void DefFile() throws ParseException {/*@bgen(jjtree) RcdDefFile */
    ASTRcdDeclFile jjtn000 = new ASTRcdDeclFile(JJTRCDDEFFILE);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      jj_consume_token(FILE);
      t = jj_consume_token(VAR_LITERAL);
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
jjtn000.setName(t.image);
        jjtn000.setToken(token);
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void DefValue() throws ParseException {/*@bgen(jjtree) RcdDefValue */
    ASTRcdDeclValue jjtn000 = new ASTRcdDeclValue(JJTRCDDEFVALUE);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      jj_consume_token(VALUE);
      t = jj_consume_token(VAR_LITERAL);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ASSIGN:{
        jj_consume_token(ASSIGN);
        Expr();
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        ;
      }
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
jjtn000.setName(t.image);
        jjtn000.setToken(token);
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void DefValArray() throws ParseException {/*@bgen(jjtree) RcdDefValArray */
    ASTRcdDeclValArray jjtn000 = new ASTRcdDeclValArray(JJTRCDDEFVALARRAY);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      jj_consume_token(VALARRAY);
      t = jj_consume_token(VAR_LITERAL);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case VAR_LITERAL:{
        Var();
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        ;
      }
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
jjtn000.setName(t.image);
        jjtn000.setToken(token);
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

// ************* Block ***************
  final public 
void Block() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IF:{
      IfBlock();
      break;
      }
    case FOR:{
      ForBlock();
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void IfBlock() throws ParseException {/*@bgen(jjtree) RcdIfBlock */
  ASTRcdIfBlock jjtn000 = new ASTRcdIfBlock(JJTRCDIFBLOCK);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(IF);
jjtn000.setToken(token);
      jj_consume_token(OBRA);
      CompExpr();
      jj_consume_token(CBRA);
      jj_consume_token(OCBRA);
      TrueScript();
      jj_consume_token(CCBRA);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ELSE:{
        jj_consume_token(ELSE);
        jj_consume_token(OCBRA);
        FalseScript();
        jj_consume_token(CCBRA);
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        ;
      }
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void ForBlock() throws ParseException {/*@bgen(jjtree) RcdForBlock */
  ASTRcdForBlock jjtn000 = new ASTRcdForBlock(JJTRCDFORBLOCK);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(FOR);
jjtn000.setToken(token);
      jj_consume_token(OBRA);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case HASH:{
        jj_consume_token(HASH);
        break;
        }
      case VALUE:{
        jj_consume_token(VALUE);
        break;
        }
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      Var();
      jj_consume_token(COLON);
      Var();
      jj_consume_token(CBRA);
      jj_consume_token(OCBRA);
      TrueScript();
      jj_consume_token(CCBRA);
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void TrueScript() throws ParseException {/*@bgen(jjtree) RcdTrueScript */
  ASTRcdTrueScript jjtn000 = new ASTRcdTrueScript(JJTRCDTRUESCRIPT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      Script();
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void FalseScript() throws ParseException {/*@bgen(jjtree) RcdFalseScript */
  ASTRcdFalseScript jjtn000 = new ASTRcdFalseScript(JJTRCDFALSESCRIPT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      Script();
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

// ************* Statement ***************
  final public 
void Statement() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case PRINT:{
      Print();
      break;
      }
    case VAR_LITERAL:{
      VarMethod();
      break;
      }
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(SEMI);
  }

  final public void Print() throws ParseException {/*@bgen(jjtree) RcdPrint */
  ASTRcdCallProc jjtn000 = new ASTRcdCallProc(JJTRCDPRINT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(PRINT);
jjtn000.setToken(token);
      jj_consume_token(OBRA);
      Expr();
      jj_consume_token(CBRA);
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

  final public void VarMethod() throws ParseException {/*@bgen(jjtree) RcdVarMethod */
  ASTRcdCallVarMethod jjtn000 = new ASTRcdCallVarMethod(JJTRCDVARMETHOD);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      Var();
jjtn000.setToken(token);
      jj_consume_token(PERIOD);
      Id();
      jj_consume_token(OBRA);
      Expr();
      jj_consume_token(CBRA);
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
  }

// ************* Comparison grammar ***************
  final public 
void CompExpr() throws ParseException {/*@bgen(jjtree) RcdOpExpr */
    ASTRcdOpExpr jjtn000 = new ASTRcdOpExpr(JJTRCDOPEXPR);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      Expr();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQUAL:{
        t = jj_consume_token(EQUAL);
        break;
        }
      case GT:{
        t = jj_consume_token(GT);
        break;
        }
      case LT:{
        t = jj_consume_token(LT);
        break;
        }
      case GTE:{
        t = jj_consume_token(GTE);
        break;
        }
      case LTE:{
        t = jj_consume_token(LTE);
        break;
        }
      case NOTEQUAL:{
        t = jj_consume_token(NOTEQUAL);
        break;
        }
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      Expr();
jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
jjtn000.setName(t.image);
        jjtn000.setToken(t);
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

// ************* Expression grammar ***************
  final public 
void Expr() throws ParseException {
    TermExpr();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:
      case MINUS:{
        ;
        break;
        }
      default:
        jj_la1[12] = jj_gen;
        break label_2;
      }
      ExprFollow();
    }
  }

  final public void ExprFollow() throws ParseException {/*@bgen(jjtree) RcdOpExpr */
    ASTRcdOpExpr jjtn000 = new ASTRcdOpExpr(JJTRCDOPEXPR);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        t = jj_consume_token(PLUS);
        break;
        }
      case MINUS:{
        t = jj_consume_token(MINUS);
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      TermExpr();
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
jjtn000.setName(t.image);
        jjtn000.setToken(t);
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void TermExpr() throws ParseException {
    Factor();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MUL:
      case DIV:{
        ;
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        break label_3;
      }
      TermExprFollow();
    }
  }

  final public void TermExprFollow() throws ParseException {/*@bgen(jjtree) RcdOpExpr */
    ASTRcdOpExpr jjtn000 = new ASTRcdOpExpr(JJTRCDOPEXPR);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case MUL:{
        t = jj_consume_token(MUL);
        break;
        }
      case DIV:{
        t = jj_consume_token(DIV);
        break;
        }
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      Factor();
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
jjtn000.setName(t.image);
        jjtn000.setToken(t);
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void Factor() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case OBRA:{
      jj_consume_token(OBRA);
      Expr();
      jj_consume_token(CBRA);
      break;
      }
    case VAR_LITERAL:{
      Var();
      break;
      }
    case ID_LITERAL:{
      Id();
      break;
      }
    case STRING_LITERAL:{
      String();
      break;
      }
    case DEC_LITERAL:
    case HEX_LITERAL:{
      IntNum();
      break;
      }
    case FLOAT_LITERAL:{
      FloatNum();
      break;
      }
    case OCBRA:{
      Hash();
      break;
      }
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void Var() throws ParseException {/*@bgen(jjtree) RcdVar */
    ASTRcdVar jjtn000 = new ASTRcdVar(JJTRCDVAR);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(VAR_LITERAL);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case OABRA:{
        jj_consume_token(OABRA);
        Expr();
        jj_consume_token(CABRA);
        break;
        }
      default:
        jj_la1[17] = jj_gen;
        ;
      }
jjtree.closeNodeScope(jjtn000, true);
          jjtc000 = false;
jjtn000.setName(t.image);
            jjtn000.setToken(t);
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void Id() throws ParseException {/*@bgen(jjtree) RcdId */
    ASTRcdId jjtn000 = new ASTRcdId(JJTRCDID);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(ID_LITERAL);
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
jjtn000.setName(t.image);
        jjtn000.setToken(t);
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void String() throws ParseException {/*@bgen(jjtree) RcdString */
    ASTRcdString jjtn000 = new ASTRcdString(JJTRCDSTRING);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(STRING_LITERAL);
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
jjtn000.setName(t.image);
        jjtn000.setToken(t);
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void IntNum() throws ParseException {/*@bgen(jjtree) RcdIntNum */
    ASTRcdIntNum jjtn000 = new ASTRcdIntNum(JJTRCDINTNUM);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case DEC_LITERAL:{
        t = jj_consume_token(DEC_LITERAL);
        break;
        }
      case HEX_LITERAL:{
        t = jj_consume_token(HEX_LITERAL);
        break;
        }
      default:
        jj_la1[18] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
jjtn000.setName(t.image);
        jjtn000.setToken(t);
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  final public void FloatNum() throws ParseException {/*@bgen(jjtree) RcdFloatNum */
    ASTRcdFloatNum jjtn000 = new ASTRcdFloatNum(JJTRCDFLOATNUM);
    boolean jjtc000 = true;
    jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(FLOAT_LITERAL);
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
jjtn000.setName(t.image);
        jjtn000.setToken(t);
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

// ************* HASH grammar ***************
  final public 
void Hash() throws ParseException {/*@bgen(jjtree) RcdHash */
  ASTRcdHash jjtn000 = new ASTRcdHash(JJTRCDHASH);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(OCBRA);
jjtn000.setToken(token);
      Tupl();
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[19] = jj_gen;
          break label_4;
        }
        jj_consume_token(COMMA);
        Tupl();
      }
      jj_consume_token(CCBRA);
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void Tupl() throws ParseException {
ASTRcdTupl jjtn001 = new ASTRcdTupl(JJTRCDTUPL);
      boolean jjtc001 = true;
      jjtree.openNodeScope(jjtn001);
    try {
      Expr();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COLON:{
        jj_consume_token(COLON);
jjtn001.setToken(token);
        Expr();
        break;
        }
      default:
        jj_la1[20] = jj_gen;
        ;
      }
    } catch (Throwable jjte001) {
if (jjtc001) {
        jjtree.clearNodeScope(jjtn001);
        jjtc001 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte001 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte001;}
      }
      if (jjte001 instanceof ParseException) {
        {if (true) throw (ParseException)jjte001;}
      }
      {if (true) throw (Error)jjte001;}
    } finally {
if (jjtc001) {
        jjtree.closeNodeScope(jjtn001, jjtree.nodeArity() > 1);
      }
    }
  }

  /** Generated Token Manager. */
  public RcdScript3ParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[21];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1d2bc000,0x1d2bc000,0xc038000,0x0,0x0,0x0,0x10000000,0x280000,0x400000,0x4010000,0x11000000,0x0,0x0,0x0,0x0,0x0,0xf0000000,0x0,0x40000000,0x0,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x10000,0x10000,0x10000,0x0,0x0,0x0,0x0,0x0,0x7e0000,0x3000000,0x3000000,0xc000000,0xc000000,0x146,0x400,0x2,0x1000,0x8000,};
   }

  /** Constructor with InputStream. */
  public RcdScript3Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public RcdScript3Parser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new RcdScript3ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public RcdScript3Parser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new RcdScript3ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
      jj_input_stream = new SimpleCharStream(stream, 1, 1);
   } else {
      jj_input_stream.ReInit(stream, 1, 1);
   }
   if (token_source == null) {
      token_source = new RcdScript3ParserTokenManager(jj_input_stream);
   }

    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public RcdScript3Parser(RcdScript3ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(RcdScript3ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[60];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 21; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 60; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

   }
