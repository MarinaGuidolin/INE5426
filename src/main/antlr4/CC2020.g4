grammar CC2020;


program
  :
    ( statement 
    | funclist
    )?
  ;

funclist
  : funcdef funclist
  | funcdef
  ;

funcdef 
  : DEF IDENT '(' paramlist ')' '{' statelist '}'
  ;

paramlist
  :
    ( ( TYPE_INT
      | TYPE_FLOAT
      | TYPE_STRING
    ) IDENT ',' paramlist
    | ( TYPE_INT
      | TYPE_FLOAT
      | TYPE_STRING
    ) IDENT
    )?
  ;

statement
  :
    ( vardecl ';'
    | atribstat ';'
    | printstat ';'
    | readstat ';'
    | returnstat ';'
    | ifstat
    | forstat
    | '{' statelist '}'
    | BREAK ';'
    | ';'
    )
  ;

vardecl
  :
    ( TYPE_INT
    | TYPE_FLOAT
    | TYPE_STRING
    ) IDENT
    ( '[' INT ']'
    )*
  ;

atribstat
  : lvalue '='
    ( expression
    | allocexpression
    | funccall
    )
  ;

funccall
  : IDENT '(' paramlistcall ')'
  ;

paramlistcall
  :
    ( IDENT ',' paramlistcall
    | IDENT
    )?
  ;

printstat
  : PRINT expression
  ;

readstat
  : READ lvalue
  ;

returnstat
  : RETURN
  ;

ifstat
  : IF '(' expression ')' statement
    ( ELSE statement
    )?
  ;

forstat
  : FOR '(' atribstat ';' expression ';' atribstat ')' statement
  ;

statelist
  : statement
    ( statelist
    )?
  ;

allocexpression
  : NEW
    ( TYPE_INT
    | TYPE_FLOAT
    | TYPE_STRING
    ) (
      '[' numexpression ']'
    )+
  ;

expression
  : numexpression
    ( ( '<'
      | '>'
      | '<='
      | '>='
      | '=='
      | '!='
      ) numexpression
    )?
  ;

numexpression
  : term
    ( ( '+'
      | '-'
      ) term
    )*
  ;

/*
 * Operators
 * notice, DIV is a backward slash
 */
term
  : unaryexpr
    ( ( '*'
      | '/'
      | '%'
      ) unaryexpr
    )*
  ;

unaryexpr
  :
    ( ( '+'
      | '-' 
    ) )?
    factor
  ;

factor
  : 
    ( INT
    | FLOAT
    | STRING
    | NULL
    | lvalue
    | '(' numexpression ')'
    )
  ;

lvalue
  : IDENT
    ( '[' numexpression ']'
    )*
  ;

/*
 * Keywords
 */
IF: 'if';
FOR: 'for';
ELSE: 'else';
RETURN: 'return';
READ: 'read';
PRINT: 'print';
NEW: 'new';
BREAK: 'break';
DEF: 'def';

/*
 * Types
 */
TYPE_STRING: 'string';
TYPE_FLOAT: 'float';
TYPE_INT: 'int';
NULL: 'null';

/*
 * Literals (non-trivial)
 *
 * INT: [0-9]+;
 * FLOAT: [0-9]+ '.' [0-9]+ | '.' [0-9]+;
 * STRING: '"' ~('"')* '"';
 * ID: [a-zA-Z$_] ([a-zA-Z$_] | [0-9])*;
 * WHITESPACE: [ \t\r\n]+ -> skip;
 *
 */

INT: [0-9]+;
FLOAT: [0-9]+ '.' [0-9]+ | '.' [0-9]+;
STRING: '"' ~('"')* '"';
IDENT
	:	JavaLetter JavaLetterOrDigit*
	;

fragment
JavaLetter
	:	[a-zA-Z$_] // these are the "java letters" below 0x7F
	|	// covers all characters above 0x7F which are not a surrogate
		~[\u0000-\u007F\uD800-\uDBFF]
		{Character.isJavaIdentifierStart(_input.LA(-1))}?
	|	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
		[\uD800-\uDBFF] [\uDC00-\uDFFF]
		{Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
	;

fragment
JavaLetterOrDigit
	:	[a-zA-Z0-9$_] // these are the "java letters or digits" below 0x7F
	|	// covers all characters above 0x7F which are not a surrogate
		~[\u0000-\u007F\uD800-\uDBFF]
		{Character.isJavaIdentifierPart(_input.LA(-1))}?
	|	// covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
		[\uD800-\uDBFF] [\uDC00-\uDFFF]
		{Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
	;


LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
LBRACK : '[';
RBRACK : ']';
SEMI : ';';
COMMA : ',';
DOT : '.';

ASSIGN : '=';
GT : '>';
LT : '<';
COLON : ':';
EQUAL : '==';
LE : '<=';
GE : '>=';
NOTEQUAL : '!=';
ADD : '+';
SUB : '-';
MUL : '*';
DIV : '/';
MOD : '%';

WHITESPACE: [ \t\r\n]+ -> skip;
