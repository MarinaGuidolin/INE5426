grammar ConvCC20201;

program:

 	STATEMENT 
	| FUNCLIST 
	|   EPSILON
;

FUNCLIST: 

	FUNCDEF FUNCLIST2 
;

FUNCLIST2:  

	FUNCLIST 
	|   EPSILON
;

FUNCDEF:
	 DEF IDENT '(' PARAMLIST ')' '{' STATELIST '}'
;

PARAMLIST:

	TYPE_INT IDENT PARAMLIST2 
	| TYPE_FLOAT IDENT PARAMLIST2
	| TYPE_STRING IDENT PARAMLIST2 
	|   EPSILON
;

PARAMLIST2:

	 ',' PARAMLIST 
	|  EPSILON
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

A:
	T1 A 
	|   EPSILON
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

 ATRIBSTAT2: 

	B D C EXPRESSION2 
	|   '(' PARAMLISTCALL ')'
;

FUNCCALL:

	IDENT '(' PARAMLISTCALL ')'
;

PARAMLISTCALL:

	IDENT PARAMLISTCALL2
	|   EPSILON
;

PARAMLISTCALL2:

	',' PARAMLISTCALL 
	|   EPSILON
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

IFSTAT2:

	ELSE STATEMENT 
	|   EPSILON
;

FORSTAT:

	FOR '(' ATRIBSTAT SEMI EXPRESSION SEMI ATRIBSTAT ')' STATEMENT
;

STATELIST: 

	STATEMENT STATELIST2
;

STATELIST2:

	STATELIST 
	|   EPSILON
;

ALLOCEXPRESSION:

	NEW ALLOCEXPRESSION2
;

ALLOCEXPRESSION2:

	TYPE_INT T2 B 
	| TYPE_FLOAT T2 B 
	| TYPE_STRING T2 B
;

B:
	T2 B 
	|   EPSILON
;

T2:
	'[' NUMEXPRESSION ']'
;

EXPRESSION:

	NUMEXPRESSION EXPRESSION2
; 

EXPRESSION2:

	'<' NUMEXPRESSION 
	| '>' NUMEXPRESSION 
	| '<=' NUMEXPRESSION 
	|'>=' NUMEXPRESSION 
	| '==' NUMEXPRESSION 
	|  '!=' NUMEXPRESSION 
	|   EPSILON
; 

NUMEXPRESSION:

	TERM C
;

C:
	T3 C 
	|   EPSILON
;

T3: 
	'+' TERM 
	| '-' TERM
;

TERM:
	 UNARYEXPR D
;

D: 
	T4 D 
	|   EPSILON
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
EPSILON: '&';