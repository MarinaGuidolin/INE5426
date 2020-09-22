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

fragment DIGIT: [0-9];
fragment DIGITS: DIGIT+;
fragment LETTER_: [a-zA-Z_];

INT: DIGITS;
FLOAT: DIGITS '.' DIGITS | '.' DIGITS;
STRING: '"' ~('"')* '"';
IDENT:	LETTER_(LETTER_|DIGIT)*;


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
