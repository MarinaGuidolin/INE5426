lexer grammar MyLexer;
/*
 * Lexer rules
 */

// Keywords
IF: 'if';
FOR: 'for';
ELSE: 'else';
RETURN: 'return';
READ: 'read';
PRINT: 'print';
NEW: 'new';
BREAK: 'break';
DEF: 'def';

// Types
TYPE_STRING: 'string';
TYPE_FLOAT: 'float';
TYPE_INT: 'int';
NULL: 'null';

// Literals (non-trivial)
INT: [0-9]+;
FLOAT: [0-9]+ '.' [0-9]+ | '.' [0-9]+;
STRING: '"' ~('"')* '"';
ID: [a-zA-Z$_] ([a-zA-Z$_] | [0-9])*;
WHITESPACE: [ \t\r\n]+ -> skip;

// Operators
PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
MOD: '%';
LT: '<';
GT: '>';
LTEQ: '<=';
GTEQ: '>=';
EQ: '==';
NTEQ: '!=';
ASSIGN: '=';

// Separators
LPAR: '(';
RPAR: ')';
LBRACE: '{';
RBRACE: '}';
LKEY: '[';
RKEY: ']';
SCOL: ';';
COL: ',';