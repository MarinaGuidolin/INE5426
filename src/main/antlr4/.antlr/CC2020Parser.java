// Generated from /home/ljmotta/INE5426/src/main/antlr4/CC2020.g4 by ANTLR 4.8
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CC2020Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, FOR=2, ELSE=3, RETURN=4, READ=5, PRINT=6, NEW=7, BREAK=8, DEF=9, 
		TYPE_STRING=10, TYPE_FLOAT=11, TYPE_INT=12, NULL=13, INT=14, FLOAT=15, 
		STRING=16, IDENT=17, LPAREN=18, RPAREN=19, LBRACE=20, RBRACE=21, LBRACK=22, 
		RBRACK=23, SEMI=24, COMMA=25, DOT=26, COLON=27, ASSIGN=28, ADD=29, SUB=30, 
		MUL=31, DIV=32, MOD=33, GT=34, LT=35, EQUAL=36, LE=37, GE=38, NOTEQUAL=39, 
		WHITESPACE=40;
	public static final int
		RULE_program = 0, RULE_funclist = 1, RULE_funcdef = 2, RULE_paramlist = 3, 
		RULE_statement = 4, RULE_vardecl = 5, RULE_atribstat = 6, RULE_funccall = 7, 
		RULE_paramlistcall = 8, RULE_printstat = 9, RULE_readstat = 10, RULE_returnstat = 11, 
		RULE_ifstat = 12, RULE_forstat = 13, RULE_statelist = 14, RULE_allocexpression = 15, 
		RULE_expression = 16, RULE_numexpression = 17, RULE_term = 18, RULE_unaryexpr = 19, 
		RULE_factor = 20, RULE_lvalue = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "funclist", "funcdef", "paramlist", "statement", "vardecl", 
			"atribstat", "funccall", "paramlistcall", "printstat", "readstat", "returnstat", 
			"ifstat", "forstat", "statelist", "allocexpression", "expression", "numexpression", 
			"term", "unaryexpr", "factor", "lvalue"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'for'", "'else'", "'return'", "'read'", "'print'", "'new'", 
			"'break'", "'def'", "'string'", "'float'", "'int'", "'null'", null, null, 
			null, null, "'('", "')'", "'{'", "'}'", "'['", "']'", "';'", "','", "'.'", 
			"':'", "'='", "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", "'<'", "'=='", 
			"'<='", "'>='", "'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "FOR", "ELSE", "RETURN", "READ", "PRINT", "NEW", "BREAK", 
			"DEF", "TYPE_STRING", "TYPE_FLOAT", "TYPE_INT", "NULL", "INT", "FLOAT", 
			"STRING", "IDENT", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", 
			"RBRACK", "SEMI", "COMMA", "DOT", "COLON", "ASSIGN", "ADD", "SUB", "MUL", 
			"DIV", "MOD", "GT", "LT", "EQUAL", "LE", "GE", "NOTEQUAL", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CC2020.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CC2020Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public FunclistContext funclist() {
			return getRuleContext(FunclistContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
			case FOR:
			case RETURN:
			case READ:
			case PRINT:
			case BREAK:
			case TYPE_STRING:
			case TYPE_FLOAT:
			case TYPE_INT:
			case IDENT:
			case LBRACE:
			case SEMI:
				{
				setState(44);
				statement();
				}
				break;
			case DEF:
				{
				setState(45);
				funclist();
				}
				break;
			case EOF:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunclistContext extends ParserRuleContext {
		public FuncdefContext funcdef() {
			return getRuleContext(FuncdefContext.class,0);
		}
		public FunclistContext funclist() {
			return getRuleContext(FunclistContext.class,0);
		}
		public FunclistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funclist; }
	}

	public final FunclistContext funclist() throws RecognitionException {
		FunclistContext _localctx = new FunclistContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_funclist);
		try {
			setState(52);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				funcdef();
				setState(49);
				funclist();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				funcdef();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncdefContext extends ParserRuleContext {
		public TerminalNode DEF() { return getToken(CC2020Parser.DEF, 0); }
		public TerminalNode IDENT() { return getToken(CC2020Parser.IDENT, 0); }
		public TerminalNode LPAREN() { return getToken(CC2020Parser.LPAREN, 0); }
		public ParamlistContext paramlist() {
			return getRuleContext(ParamlistContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CC2020Parser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(CC2020Parser.LBRACE, 0); }
		public StatelistContext statelist() {
			return getRuleContext(StatelistContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(CC2020Parser.RBRACE, 0); }
		public FuncdefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcdef; }
	}

	public final FuncdefContext funcdef() throws RecognitionException {
		FuncdefContext _localctx = new FuncdefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_funcdef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(DEF);
			setState(55);
			match(IDENT);
			setState(56);
			match(LPAREN);
			setState(57);
			paramlist();
			setState(58);
			match(RPAREN);
			setState(59);
			match(LBRACE);
			setState(60);
			statelist();
			setState(61);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamlistContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(CC2020Parser.IDENT, 0); }
		public TerminalNode COMMA() { return getToken(CC2020Parser.COMMA, 0); }
		public ParamlistContext paramlist() {
			return getRuleContext(ParamlistContext.class,0);
		}
		public TerminalNode TYPE_INT() { return getToken(CC2020Parser.TYPE_INT, 0); }
		public TerminalNode TYPE_FLOAT() { return getToken(CC2020Parser.TYPE_FLOAT, 0); }
		public TerminalNode TYPE_STRING() { return getToken(CC2020Parser.TYPE_STRING, 0); }
		public ParamlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramlist; }
	}

	public final ParamlistContext paramlist() throws RecognitionException {
		ParamlistContext _localctx = new ParamlistContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_paramlist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(63);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TYPE_STRING) | (1L << TYPE_FLOAT) | (1L << TYPE_INT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(64);
				match(IDENT);
				setState(65);
				match(COMMA);
				setState(66);
				paramlist();
				}
				break;
			case 2:
				{
				setState(67);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TYPE_STRING) | (1L << TYPE_FLOAT) | (1L << TYPE_INT))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(68);
				match(IDENT);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public VardeclContext vardecl() {
			return getRuleContext(VardeclContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(CC2020Parser.SEMI, 0); }
		public AtribstatContext atribstat() {
			return getRuleContext(AtribstatContext.class,0);
		}
		public PrintstatContext printstat() {
			return getRuleContext(PrintstatContext.class,0);
		}
		public ReadstatContext readstat() {
			return getRuleContext(ReadstatContext.class,0);
		}
		public ReturnstatContext returnstat() {
			return getRuleContext(ReturnstatContext.class,0);
		}
		public IfstatContext ifstat() {
			return getRuleContext(IfstatContext.class,0);
		}
		public ForstatContext forstat() {
			return getRuleContext(ForstatContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(CC2020Parser.LBRACE, 0); }
		public StatelistContext statelist() {
			return getRuleContext(StatelistContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(CC2020Parser.RBRACE, 0); }
		public TerminalNode BREAK() { return getToken(CC2020Parser.BREAK, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE_STRING:
			case TYPE_FLOAT:
			case TYPE_INT:
				{
				setState(71);
				vardecl();
				setState(72);
				match(SEMI);
				}
				break;
			case IDENT:
				{
				setState(74);
				atribstat();
				setState(75);
				match(SEMI);
				}
				break;
			case PRINT:
				{
				setState(77);
				printstat();
				setState(78);
				match(SEMI);
				}
				break;
			case READ:
				{
				setState(80);
				readstat();
				setState(81);
				match(SEMI);
				}
				break;
			case RETURN:
				{
				setState(83);
				returnstat();
				setState(84);
				match(SEMI);
				}
				break;
			case IF:
				{
				setState(86);
				ifstat();
				}
				break;
			case FOR:
				{
				setState(87);
				forstat();
				}
				break;
			case LBRACE:
				{
				setState(88);
				match(LBRACE);
				setState(89);
				statelist();
				setState(90);
				match(RBRACE);
				}
				break;
			case BREAK:
				{
				setState(92);
				match(BREAK);
				setState(93);
				match(SEMI);
				}
				break;
			case SEMI:
				{
				setState(94);
				match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VardeclContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(CC2020Parser.IDENT, 0); }
		public TerminalNode TYPE_INT() { return getToken(CC2020Parser.TYPE_INT, 0); }
		public TerminalNode TYPE_FLOAT() { return getToken(CC2020Parser.TYPE_FLOAT, 0); }
		public TerminalNode TYPE_STRING() { return getToken(CC2020Parser.TYPE_STRING, 0); }
		public List<TerminalNode> LBRACK() { return getTokens(CC2020Parser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(CC2020Parser.LBRACK, i);
		}
		public List<TerminalNode> INT() { return getTokens(CC2020Parser.INT); }
		public TerminalNode INT(int i) {
			return getToken(CC2020Parser.INT, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(CC2020Parser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(CC2020Parser.RBRACK, i);
		}
		public VardeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vardecl; }
	}

	public final VardeclContext vardecl() throws RecognitionException {
		VardeclContext _localctx = new VardeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_vardecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TYPE_STRING) | (1L << TYPE_FLOAT) | (1L << TYPE_INT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(98);
			match(IDENT);
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(99);
				match(LBRACK);
				setState(100);
				match(INT);
				setState(101);
				match(RBRACK);
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtribstatContext extends ParserRuleContext {
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(CC2020Parser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AllocexpressionContext allocexpression() {
			return getRuleContext(AllocexpressionContext.class,0);
		}
		public FunccallContext funccall() {
			return getRuleContext(FunccallContext.class,0);
		}
		public AtribstatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atribstat; }
	}

	public final AtribstatContext atribstat() throws RecognitionException {
		AtribstatContext _localctx = new AtribstatContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_atribstat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			lvalue();
			setState(108);
			match(ASSIGN);
			setState(112);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(109);
				expression();
				}
				break;
			case 2:
				{
				setState(110);
				allocexpression();
				}
				break;
			case 3:
				{
				setState(111);
				funccall();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunccallContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(CC2020Parser.IDENT, 0); }
		public TerminalNode LPAREN() { return getToken(CC2020Parser.LPAREN, 0); }
		public ParamlistcallContext paramlistcall() {
			return getRuleContext(ParamlistcallContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CC2020Parser.RPAREN, 0); }
		public FunccallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funccall; }
	}

	public final FunccallContext funccall() throws RecognitionException {
		FunccallContext _localctx = new FunccallContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funccall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(IDENT);
			setState(115);
			match(LPAREN);
			setState(116);
			paramlistcall();
			setState(117);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamlistcallContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(CC2020Parser.IDENT, 0); }
		public TerminalNode COMMA() { return getToken(CC2020Parser.COMMA, 0); }
		public ParamlistcallContext paramlistcall() {
			return getRuleContext(ParamlistcallContext.class,0);
		}
		public ParamlistcallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramlistcall; }
	}

	public final ParamlistcallContext paramlistcall() throws RecognitionException {
		ParamlistcallContext _localctx = new ParamlistcallContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_paramlistcall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(119);
				match(IDENT);
				setState(120);
				match(COMMA);
				setState(121);
				paramlistcall();
				}
				break;
			case 2:
				{
				setState(122);
				match(IDENT);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintstatContext extends ParserRuleContext {
		public TerminalNode PRINT() { return getToken(CC2020Parser.PRINT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrintstatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printstat; }
	}

	public final PrintstatContext printstat() throws RecognitionException {
		PrintstatContext _localctx = new PrintstatContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_printstat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(PRINT);
			setState(126);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReadstatContext extends ParserRuleContext {
		public TerminalNode READ() { return getToken(CC2020Parser.READ, 0); }
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public ReadstatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_readstat; }
	}

	public final ReadstatContext readstat() throws RecognitionException {
		ReadstatContext _localctx = new ReadstatContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_readstat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(READ);
			setState(129);
			lvalue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnstatContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(CC2020Parser.RETURN, 0); }
		public ReturnstatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnstat; }
	}

	public final ReturnstatContext returnstat() throws RecognitionException {
		ReturnstatContext _localctx = new ReturnstatContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_returnstat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(RETURN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfstatContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(CC2020Parser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(CC2020Parser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CC2020Parser.RPAREN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(CC2020Parser.ELSE, 0); }
		public IfstatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifstat; }
	}

	public final IfstatContext ifstat() throws RecognitionException {
		IfstatContext _localctx = new IfstatContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_ifstat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			match(IF);
			setState(134);
			match(LPAREN);
			setState(135);
			expression();
			setState(136);
			match(RPAREN);
			setState(137);
			statement();
			setState(140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(138);
				match(ELSE);
				setState(139);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForstatContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(CC2020Parser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(CC2020Parser.LPAREN, 0); }
		public List<AtribstatContext> atribstat() {
			return getRuleContexts(AtribstatContext.class);
		}
		public AtribstatContext atribstat(int i) {
			return getRuleContext(AtribstatContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(CC2020Parser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(CC2020Parser.SEMI, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CC2020Parser.RPAREN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ForstatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forstat; }
	}

	public final ForstatContext forstat() throws RecognitionException {
		ForstatContext _localctx = new ForstatContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_forstat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(FOR);
			setState(143);
			match(LPAREN);
			setState(144);
			atribstat();
			setState(145);
			match(SEMI);
			setState(146);
			expression();
			setState(147);
			match(SEMI);
			setState(148);
			atribstat();
			setState(149);
			match(RPAREN);
			setState(150);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatelistContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatelistContext statelist() {
			return getRuleContext(StatelistContext.class,0);
		}
		public StatelistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statelist; }
	}

	public final StatelistContext statelist() throws RecognitionException {
		StatelistContext _localctx = new StatelistContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_statelist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			statement();
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << FOR) | (1L << RETURN) | (1L << READ) | (1L << PRINT) | (1L << BREAK) | (1L << TYPE_STRING) | (1L << TYPE_FLOAT) | (1L << TYPE_INT) | (1L << IDENT) | (1L << LBRACE) | (1L << SEMI))) != 0)) {
				{
				setState(153);
				statelist();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AllocexpressionContext extends ParserRuleContext {
		public TerminalNode NEW() { return getToken(CC2020Parser.NEW, 0); }
		public TerminalNode TYPE_INT() { return getToken(CC2020Parser.TYPE_INT, 0); }
		public TerminalNode TYPE_FLOAT() { return getToken(CC2020Parser.TYPE_FLOAT, 0); }
		public TerminalNode TYPE_STRING() { return getToken(CC2020Parser.TYPE_STRING, 0); }
		public List<TerminalNode> LBRACK() { return getTokens(CC2020Parser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(CC2020Parser.LBRACK, i);
		}
		public List<NumexpressionContext> numexpression() {
			return getRuleContexts(NumexpressionContext.class);
		}
		public NumexpressionContext numexpression(int i) {
			return getRuleContext(NumexpressionContext.class,i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(CC2020Parser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(CC2020Parser.RBRACK, i);
		}
		public AllocexpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allocexpression; }
	}

	public final AllocexpressionContext allocexpression() throws RecognitionException {
		AllocexpressionContext _localctx = new AllocexpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_allocexpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(NEW);
			setState(157);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TYPE_STRING) | (1L << TYPE_FLOAT) | (1L << TYPE_INT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(162); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(158);
				match(LBRACK);
				setState(159);
				numexpression();
				setState(160);
				match(RBRACK);
				}
				}
				setState(164); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==LBRACK );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public List<NumexpressionContext> numexpression() {
			return getRuleContexts(NumexpressionContext.class);
		}
		public NumexpressionContext numexpression(int i) {
			return getRuleContext(NumexpressionContext.class,i);
		}
		public TerminalNode LT() { return getToken(CC2020Parser.LT, 0); }
		public TerminalNode GT() { return getToken(CC2020Parser.GT, 0); }
		public TerminalNode LE() { return getToken(CC2020Parser.LE, 0); }
		public TerminalNode GE() { return getToken(CC2020Parser.GE, 0); }
		public TerminalNode EQUAL() { return getToken(CC2020Parser.EQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(CC2020Parser.NOTEQUAL, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			numexpression();
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << EQUAL) | (1L << LE) | (1L << GE) | (1L << NOTEQUAL))) != 0)) {
				{
				setState(167);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << EQUAL) | (1L << LE) | (1L << GE) | (1L << NOTEQUAL))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(168);
				numexpression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumexpressionContext extends ParserRuleContext {
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> ADD() { return getTokens(CC2020Parser.ADD); }
		public TerminalNode ADD(int i) {
			return getToken(CC2020Parser.ADD, i);
		}
		public List<TerminalNode> SUB() { return getTokens(CC2020Parser.SUB); }
		public TerminalNode SUB(int i) {
			return getToken(CC2020Parser.SUB, i);
		}
		public NumexpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numexpression; }
	}

	public final NumexpressionContext numexpression() throws RecognitionException {
		NumexpressionContext _localctx = new NumexpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_numexpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			term();
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ADD || _la==SUB) {
				{
				{
				setState(172);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUB) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(173);
				term();
				}
				}
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public List<UnaryexprContext> unaryexpr() {
			return getRuleContexts(UnaryexprContext.class);
		}
		public UnaryexprContext unaryexpr(int i) {
			return getRuleContext(UnaryexprContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(CC2020Parser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(CC2020Parser.MUL, i);
		}
		public List<TerminalNode> DIV() { return getTokens(CC2020Parser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(CC2020Parser.DIV, i);
		}
		public List<TerminalNode> MOD() { return getTokens(CC2020Parser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(CC2020Parser.MOD, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			unaryexpr();
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) {
				{
				{
				setState(180);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DIV) | (1L << MOD))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(181);
				unaryexpr();
				}
				}
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryexprContext extends ParserRuleContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TerminalNode ADD() { return getToken(CC2020Parser.ADD, 0); }
		public TerminalNode SUB() { return getToken(CC2020Parser.SUB, 0); }
		public UnaryexprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryexpr; }
	}

	public final UnaryexprContext unaryexpr() throws RecognitionException {
		UnaryexprContext _localctx = new UnaryexprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_unaryexpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ADD || _la==SUB) {
				{
				setState(187);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUB) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(190);
			factor();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(CC2020Parser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(CC2020Parser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(CC2020Parser.STRING, 0); }
		public TerminalNode NULL() { return getToken(CC2020Parser.NULL, 0); }
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(CC2020Parser.LPAREN, 0); }
		public NumexpressionContext numexpression() {
			return getRuleContext(NumexpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CC2020Parser.RPAREN, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_factor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				{
				setState(192);
				match(INT);
				}
				break;
			case FLOAT:
				{
				setState(193);
				match(FLOAT);
				}
				break;
			case STRING:
				{
				setState(194);
				match(STRING);
				}
				break;
			case NULL:
				{
				setState(195);
				match(NULL);
				}
				break;
			case IDENT:
				{
				setState(196);
				lvalue();
				}
				break;
			case LPAREN:
				{
				setState(197);
				match(LPAREN);
				setState(198);
				numexpression();
				setState(199);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LvalueContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(CC2020Parser.IDENT, 0); }
		public List<TerminalNode> LBRACK() { return getTokens(CC2020Parser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(CC2020Parser.LBRACK, i);
		}
		public List<NumexpressionContext> numexpression() {
			return getRuleContexts(NumexpressionContext.class);
		}
		public NumexpressionContext numexpression(int i) {
			return getRuleContext(NumexpressionContext.class,i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(CC2020Parser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(CC2020Parser.RBRACK, i);
		}
		public LvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lvalue; }
	}

	public final LvalueContext lvalue() throws RecognitionException {
		LvalueContext _localctx = new LvalueContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_lvalue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(IDENT);
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(204);
				match(LBRACK);
				setState(205);
				numexpression();
				setState(206);
				match(RBRACK);
				}
				}
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*\u00d8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\5\2\61\n\2"+
		"\3\3\3\3\3\3\3\3\5\3\67\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\5\5H\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6b\n\6\3\7\3"+
		"\7\3\7\3\7\3\7\7\7i\n\7\f\7\16\7l\13\7\3\b\3\b\3\b\3\b\3\b\5\bs\n\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\5\n~\n\n\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u008f\n\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\5\20\u009d\n\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\6\21\u00a5\n\21\r\21\16\21\u00a6\3\22\3\22\3"+
		"\22\5\22\u00ac\n\22\3\23\3\23\3\23\7\23\u00b1\n\23\f\23\16\23\u00b4\13"+
		"\23\3\24\3\24\3\24\7\24\u00b9\n\24\f\24\16\24\u00bc\13\24\3\25\5\25\u00bf"+
		"\n\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00cc"+
		"\n\26\3\27\3\27\3\27\3\27\3\27\7\27\u00d3\n\27\f\27\16\27\u00d6\13\27"+
		"\3\27\2\2\30\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,\2\6\3\2\f"+
		"\16\3\2$)\3\2\37 \3\2!#\2\u00e1\2\60\3\2\2\2\4\66\3\2\2\2\68\3\2\2\2\b"+
		"G\3\2\2\2\na\3\2\2\2\fc\3\2\2\2\16m\3\2\2\2\20t\3\2\2\2\22}\3\2\2\2\24"+
		"\177\3\2\2\2\26\u0082\3\2\2\2\30\u0085\3\2\2\2\32\u0087\3\2\2\2\34\u0090"+
		"\3\2\2\2\36\u009a\3\2\2\2 \u009e\3\2\2\2\"\u00a8\3\2\2\2$\u00ad\3\2\2"+
		"\2&\u00b5\3\2\2\2(\u00be\3\2\2\2*\u00cb\3\2\2\2,\u00cd\3\2\2\2.\61\5\n"+
		"\6\2/\61\5\4\3\2\60.\3\2\2\2\60/\3\2\2\2\60\61\3\2\2\2\61\3\3\2\2\2\62"+
		"\63\5\6\4\2\63\64\5\4\3\2\64\67\3\2\2\2\65\67\5\6\4\2\66\62\3\2\2\2\66"+
		"\65\3\2\2\2\67\5\3\2\2\289\7\13\2\29:\7\23\2\2:;\7\24\2\2;<\5\b\5\2<="+
		"\7\25\2\2=>\7\26\2\2>?\5\36\20\2?@\7\27\2\2@\7\3\2\2\2AB\t\2\2\2BC\7\23"+
		"\2\2CD\7\33\2\2DH\5\b\5\2EF\t\2\2\2FH\7\23\2\2GA\3\2\2\2GE\3\2\2\2GH\3"+
		"\2\2\2H\t\3\2\2\2IJ\5\f\7\2JK\7\32\2\2Kb\3\2\2\2LM\5\16\b\2MN\7\32\2\2"+
		"Nb\3\2\2\2OP\5\24\13\2PQ\7\32\2\2Qb\3\2\2\2RS\5\26\f\2ST\7\32\2\2Tb\3"+
		"\2\2\2UV\5\30\r\2VW\7\32\2\2Wb\3\2\2\2Xb\5\32\16\2Yb\5\34\17\2Z[\7\26"+
		"\2\2[\\\5\36\20\2\\]\7\27\2\2]b\3\2\2\2^_\7\n\2\2_b\7\32\2\2`b\7\32\2"+
		"\2aI\3\2\2\2aL\3\2\2\2aO\3\2\2\2aR\3\2\2\2aU\3\2\2\2aX\3\2\2\2aY\3\2\2"+
		"\2aZ\3\2\2\2a^\3\2\2\2a`\3\2\2\2b\13\3\2\2\2cd\t\2\2\2dj\7\23\2\2ef\7"+
		"\30\2\2fg\7\20\2\2gi\7\31\2\2he\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2"+
		"k\r\3\2\2\2lj\3\2\2\2mn\5,\27\2nr\7\36\2\2os\5\"\22\2ps\5 \21\2qs\5\20"+
		"\t\2ro\3\2\2\2rp\3\2\2\2rq\3\2\2\2s\17\3\2\2\2tu\7\23\2\2uv\7\24\2\2v"+
		"w\5\22\n\2wx\7\25\2\2x\21\3\2\2\2yz\7\23\2\2z{\7\33\2\2{~\5\22\n\2|~\7"+
		"\23\2\2}y\3\2\2\2}|\3\2\2\2}~\3\2\2\2~\23\3\2\2\2\177\u0080\7\b\2\2\u0080"+
		"\u0081\5\"\22\2\u0081\25\3\2\2\2\u0082\u0083\7\7\2\2\u0083\u0084\5,\27"+
		"\2\u0084\27\3\2\2\2\u0085\u0086\7\6\2\2\u0086\31\3\2\2\2\u0087\u0088\7"+
		"\3\2\2\u0088\u0089\7\24\2\2\u0089\u008a\5\"\22\2\u008a\u008b\7\25\2\2"+
		"\u008b\u008e\5\n\6\2\u008c\u008d\7\5\2\2\u008d\u008f\5\n\6\2\u008e\u008c"+
		"\3\2\2\2\u008e\u008f\3\2\2\2\u008f\33\3\2\2\2\u0090\u0091\7\4\2\2\u0091"+
		"\u0092\7\24\2\2\u0092\u0093\5\16\b\2\u0093\u0094\7\32\2\2\u0094\u0095"+
		"\5\"\22\2\u0095\u0096\7\32\2\2\u0096\u0097\5\16\b\2\u0097\u0098\7\25\2"+
		"\2\u0098\u0099\5\n\6\2\u0099\35\3\2\2\2\u009a\u009c\5\n\6\2\u009b\u009d"+
		"\5\36\20\2\u009c\u009b\3\2\2\2\u009c\u009d\3\2\2\2\u009d\37\3\2\2\2\u009e"+
		"\u009f\7\t\2\2\u009f\u00a4\t\2\2\2\u00a0\u00a1\7\30\2\2\u00a1\u00a2\5"+
		"$\23\2\u00a2\u00a3\7\31\2\2\u00a3\u00a5\3\2\2\2\u00a4\u00a0\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7!\3\2\2\2"+
		"\u00a8\u00ab\5$\23\2\u00a9\u00aa\t\3\2\2\u00aa\u00ac\5$\23\2\u00ab\u00a9"+
		"\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac#\3\2\2\2\u00ad\u00b2\5&\24\2\u00ae"+
		"\u00af\t\4\2\2\u00af\u00b1\5&\24\2\u00b0\u00ae\3\2\2\2\u00b1\u00b4\3\2"+
		"\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3%\3\2\2\2\u00b4\u00b2"+
		"\3\2\2\2\u00b5\u00ba\5(\25\2\u00b6\u00b7\t\5\2\2\u00b7\u00b9\5(\25\2\u00b8"+
		"\u00b6\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2"+
		"\2\2\u00bb\'\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00bf\t\4\2\2\u00be\u00bd"+
		"\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\5*\26\2\u00c1"+
		")\3\2\2\2\u00c2\u00cc\7\20\2\2\u00c3\u00cc\7\21\2\2\u00c4\u00cc\7\22\2"+
		"\2\u00c5\u00cc\7\17\2\2\u00c6\u00cc\5,\27\2\u00c7\u00c8\7\24\2\2\u00c8"+
		"\u00c9\5$\23\2\u00c9\u00ca\7\25\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00c2\3"+
		"\2\2\2\u00cb\u00c3\3\2\2\2\u00cb\u00c4\3\2\2\2\u00cb\u00c5\3\2\2\2\u00cb"+
		"\u00c6\3\2\2\2\u00cb\u00c7\3\2\2\2\u00cc+\3\2\2\2\u00cd\u00d4\7\23\2\2"+
		"\u00ce\u00cf\7\30\2\2\u00cf\u00d0\5$\23\2\u00d0\u00d1\7\31\2\2\u00d1\u00d3"+
		"\3\2\2\2\u00d2\u00ce\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4"+
		"\u00d5\3\2\2\2\u00d5-\3\2\2\2\u00d6\u00d4\3\2\2\2\22\60\66Gajr}\u008e"+
		"\u009c\u00a6\u00ab\u00b2\u00ba\u00be\u00cb\u00d4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}