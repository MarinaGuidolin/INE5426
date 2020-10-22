// Generated from /home/marina/Documents/Compiladores/INE5426/src/main/antlr4/ConvCC20201.g4 by ANTLR 4.8
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ConvCC20201Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FUNCLIST=1, FUNCDEF=2, STATEMENT=3, VARDECL=4, T1=5, ATRIBSTAT=6, ATRIBSTAT1=7, 
		FUNCCALL=8, PRINTSTAT=9, READSTAT=10, RETURNSTAT=11, IFSTAT=12, FORSTAT=13, 
		STATELIST=14, ALLOCEXPRESSION=15, ALLOCEXPRESSION2=16, T2=17, EXPRESSION=18, 
		NUMEXPRESSION=19, T3=20, TERM=21, T4=22, UNARYEXPR=23, FACTOR=24, LVALUE=25, 
		IF=26, FOR=27, ELSE=28, RETURN=29, READ=30, PRINT=31, NEW=32, BREAK=33, 
		DEF=34, TYPE_STRING=35, TYPE_FLOAT=36, TYPE_INT=37, NULL=38, INT=39, FLOAT=40, 
		STRING=41, IDENT=42, LPAREN=43, RPAREN=44, LBRACE=45, RBRACE=46, LBRACK=47, 
		RBRACK=48, SEMI=49, COMMA=50, DOT=51, COLON=52, ASSIGN=53, ADD=54, SUB=55, 
		MUL=56, DIV=57, MOD=58, GT=59, LT=60, EQUAL=61, LE=62, GE=63, NOTEQUAL=64, 
		WHITESPACE=65;
	public static final int
		RULE_program = 0;
	private static String[] makeRuleNames() {
		return new String[] {
			"program"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "'if'", "'for'", "'else'", "'return'", "'read'", "'print'", 
			"'new'", "'break'", "'def'", "'string'", "'float'", "'int'", "'null'", 
			null, null, null, null, "'('", "')'", "'{'", "'}'", "'['", "']'", "';'", 
			"','", "'.'", "':'", "'='", "'+'", "'-'", "'*'", "'/'", "'%'", "'>'", 
			"'<'", "'=='", "'<='", "'>='", "'!='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "FUNCLIST", "FUNCDEF", "STATEMENT", "VARDECL", "T1", "ATRIBSTAT", 
			"ATRIBSTAT1", "FUNCCALL", "PRINTSTAT", "READSTAT", "RETURNSTAT", "IFSTAT", 
			"FORSTAT", "STATELIST", "ALLOCEXPRESSION", "ALLOCEXPRESSION2", "T2", 
			"EXPRESSION", "NUMEXPRESSION", "T3", "TERM", "T4", "UNARYEXPR", "FACTOR", 
			"LVALUE", "IF", "FOR", "ELSE", "RETURN", "READ", "PRINT", "NEW", "BREAK", 
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
	public String getGrammarFileName() { return "ConvCC20201.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ConvCC20201Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode STATEMENT() { return getToken(ConvCC20201Parser.STATEMENT, 0); }
		public TerminalNode FUNCLIST() { return getToken(ConvCC20201Parser.FUNCLIST, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(3);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FUNCLIST || _la==STATEMENT) {
				{
				setState(2);
				_la = _input.LA(1);
				if ( !(_la==FUNCLIST || _la==STATEMENT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3C\b\4\2\t\2\3\2\5"+
		"\2\6\n\2\3\2\2\2\3\2\2\3\4\2\3\3\5\5\2\7\2\5\3\2\2\2\4\6\t\2\2\2\5\4\3"+
		"\2\2\2\5\6\3\2\2\2\6\3\3\2\2\2\3\5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}