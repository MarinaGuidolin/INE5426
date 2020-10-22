grammar ConvCC20201;

program:

 	(STATEMENT 
	| FUNCLIST)? 
;

FUNCLIST: 

	FUNCDEF FUNCLIST2 
;

fragment FUNCLIST2:  

	FUNCLIST?
;

FUNCDEF:
	 DEF IDENT '(' PARAMLIST ')' '{' STATELIST '}'
;

fragment PARAMLIST:

	(TYPE_INT IDENT PARAMLIST2 
	| TYPE_FLOAT IDENT PARAMLIST2
	| TYPE_STRING IDENT PARAMLIST2 
	)?
;

fragment PARAMLIST2:

	(',' PARAMLIST 
	)?

;

STATEMENT:

	VARDECL SEMI 
	| ATRIBSTAT SEMI 
	| PRINTSTAT SEMI
	| READSTAT SEMI 
	| RETURNSTAT SEMI
	| IFSTAT 
	| FORSTAT 
	| '{' STATELIST '}' 
	| BREAK SEMI | SEMI
;

VARDECL:

	TYPE_INT IDENT A 
	| TYPE_FLOAT IDENT A 
	| TYPE_STRING IDENT A
;

fragment A:
	(T1 A 
	)?
; 

T1: 
	'[' TYPE_INT ']'
;

ATRIBSTAT:
	
	LVALUE SEMI ATRIBSTAT1 
;

ATRIBSTAT1:

	IDENT  ATRIBSTAT2
	| ALLOCEXPRESSION 
	| '+' FACTOR 
	| '-' FACTOR 
	| TYPE_INT 
	| TYPE_FLOAT 
	| TYPE_STRING 
	| NULL
	| '(' NUMEXPRESSION ')'
;

 fragment ATRIBSTAT2: 

	B D C EXPRESSION2 
	|   '(' PARAMLISTCALL ')'
;

FUNCCALL:

	IDENT '(' PARAMLISTCALL ')'
;

fragment PARAMLISTCALL:

	(IDENT PARAMLISTCALL2
	)?
;

fragment PARAMLISTCALL2:

	(',' PARAMLISTCALL 
	)?
; 

PRINTSTAT:

	PRINT EXPRESSION
;

READSTAT:

	READ LVALUE
;

RETURNSTAT:

	RETURN
;

IFSTAT:

	IF (EXPRESSION) STATEMENT IFSTAT2
;

fragment IFSTAT2:

	(ELSE STATEMENT 
	)?
;

FORSTAT:

	FOR '(' ATRIBSTAT SEMI EXPRESSION SEMI ATRIBSTAT ')' STATEMENT
;

STATELIST: 

	STATEMENT STATELIST2
;

fragment STATELIST2:

	(STATELIST 
	)?
;	

ALLOCEXPRESSION:

	NEW ALLOCEXPRESSION2
;

ALLOCEXPRESSION2:

	TYPE_INT T2 B 
	| TYPE_FLOAT T2 B 
	| TYPE_STRING T2 B
;

fragment B:
	(T2 B 
	)?
;

T2:
	'[' NUMEXPRESSION ']'
;

EXPRESSION:

	NUMEXPRESSION EXPRESSION2
; 

fragment EXPRESSION2:

	('<' NUMEXPRESSION 
	| '>' NUMEXPRESSION 
	| '<=' NUMEXPRESSION 
	|'>=' NUMEXPRESSION 
	| '==' NUMEXPRESSION 
	|  '!=' NUMEXPRESSION 
	)?
; 

NUMEXPRESSION:

	TERM C
;

fragment C:
	(T3 C 
	)?
;

T3: 
	'+' TERM 
	| '-' TERM
;

TERM:
	 UNARYEXPR D
;

fragment D: 
	(T4 D 
	)?
;

T4: 
	'*' UNARYEXPR 
	| '/' UNARYEXPR
	| '%' UNARYEXPR
;

UNARYEXPR:

	'+' FACTOR 
	| '-' FACTOR 
	| FACTOR
;

FACTOR:
	TYPE_INT
	| TYPE_FLOAT
	| TYPE_STRING
	| NULL
	| LVALUE 
	| '(' NUMEXPRESSION ')'
;

LVALUE:
	 IDENT B
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

INT: DIGITS;
FLOAT: DIGITS '.' DIGITS | '.' DIGITS;

fragment DIGITS: [0-9]+;

STRING: '"' ~('"')* '"';
IDENT: [a-zA-Z_] ( [a-zA-Z0-9_] )*;

/*
 * Symbols and Punctuation
*/

LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
LBRACK : '[';
RBRACK : ']';
SEMI : ';';
COMMA : ',';
DOT : '.';
COLON : ':';

/*
 * Arithmetic Operators
*/

ASSIGN : '=';
ADD : '+';
SUB : '-';
MUL : '*';
DIV : '/';
MOD : '%';

/*
* Relational Operators
*/

GT : '>';
LT : '<';
EQUAL : '==';
LE : '<=';
GE : '>=';
NOTEQUAL : '!=';

WHITESPACE: [ \t\r\n]+ -> skip;
