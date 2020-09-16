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
    ( '[' TYPE_INT_CONSTANT ']'
    )*
  ;

atribstat
  : lvalue ASSIGN
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
  : READ lvaule
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
      | '\'
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
    ( TYPE_INT_CONSTANT
    | TYPE_FLOAT_CONSTANT
    | TYPE_STRING_CONSTANT
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
IDENT: 'ident';

/*
 * Types
 */
TYPE_STRING: 'string';
TYPE_FLOAT: 'float';
TYPE_INT: 'int';
TYPE_INT_CONSTANT: 'int_constant';
TYPE_FLOAT_CONSTANT: 'float_constant';
TYPE_STRING_CONSTANT: 'string_constant';
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
