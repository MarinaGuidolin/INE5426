grammar ConvCC20201;

@header {
import ExpressionTree.ExpressionTree;
import ExpressionTree.Node;
}

@parser::members {

    List<ExpressionTree> trees = new ArrayList<ExpressionTree>();

    void addTree(Node node) {
        ExpressionTree expTree = new ExpressionTree(node);
        trees.add(expTree);
    }
}

program
@after {
    Utils.exportExpressionTrees(trees);
}
:
 	(statement 
	| funclist)?
;

funclist: 

	funcdef funclist2 
;

funclist2:  

	funclist?
;

funcdef:
	 DEF IDENT LPAREN paramlist RPAREN LBRACE statelist RBRACE
;

paramlist:

	TYPE_INT IDENT paramlist2
	| TYPE_FLOAT IDENT paramlist2
	| TYPE_STRING IDENT paramlist2 
	|
;

paramlist2:

	COMMA paramlist
	|

;

statement:

	vardecl SEMI 
	| atribstat1 SEMI
	| printstat SEMI
	| readstat SEMI 
	| returnstat SEMI
	| ifstat 
	| forstat 
	| LBRACE statelist RBRACE 
	| BREAK SEMI | SEMI
;

vardecl:

	TYPE_INT IDENT a 
	| TYPE_FLOAT IDENT a 
	| TYPE_STRING IDENT a
;

a:
	t1 a
	|
; 

t1: 
	LBRACK INT RBRACK;

paramlistcall:

	IDENT paramlistcall2
	|
;

paramlistcall2:

	COMMA paramlistcall
	|
; 

printstat:

	PRINT expression
;

readstat:

	READ lvalue[' ']
;

returnstat:

	RETURN
;

ifstat:

	IF LPAREN expression RPAREN LBRACE statelist RBRACE ifstat1
;

ifstat1:

	ELSE statement
	|
;

forstat:

	FOR LPAREN atribstat1 SEMI expression SEMI atribstat1 RPAREN statement
;

statelist: 

	statement statelist2
;

statelist2:

	statelist
	|
;	

allocexpression:

	NEW allocexpression1
;

allocexpression1:

	TYPE_INT t2 b[null]
	| TYPE_FLOAT t2 b[null]
	| TYPE_STRING t2 b[null]
;

atribstat1:

	lvalue[' '] ASSIGN atribstat2
;


atribstat2:

	IDENT atribstat3[new Node($IDENT.text)]
	| allocexpression
	| ADD factor['+'] d[$factor.sin] c[$d.sin] expression2 {addTree($c.sin); }
	| SUB factor['-'] d[$factor.sin] c[$d.sin] expression2 {addTree($c.sin); }
	| INT d[new Node($INT.text)] c[$d.sin] expression2 {addTree($c.sin); }
  	| FLOAT d[new Node($FLOAT.text)] c[$d.sin] expression2 {addTree($c.sin);}
  	| STRING d[new Node($STRING.text)] c[$d.sin] expression2 {addTree($c.sin);}
	| NULL d[null] c[null] expression2
	| LPAREN numexpression RPAREN d[$numexpression.sin] c[$d.sin] expression2 {addTree($c.sin);}
;

 atribstat3[Node h]
 :
	b[h] d[$b.sin] c[$d.sin] expression2 {addTree($c.sin);}
	|   LPAREN paramlistcall RPAREN
;

b[Node h] returns [Node sin]
:
	t2 b[h] {$sin = new Node("array", $t2.sin, $b.sin);}
	| {$sin = h;}
;

t2 returns[Node sin]
:
	LBRACK numexpression RBRACK {$sin = $numexpression.sin;}
;

expression
:

	numexpression expression2
; 

expression2:

	GT numexpression
	| LT numexpression
	| LE numexpression
	| GE numexpression
	| EQUAL numexpression
	| NOTEQUAL numexpression
	|
; 

numexpression returns [Node sin]
:
	term c[$term.sin] {$sin = $c.sin;} {addTree($sin);}
;

c[Node h] returns [Node sin]
:
	t3[h] c[$t3.sin] {$sin = $c.sin;}
	| {$sin = h;}
;

t3[Node h] returns [Node sin]
:
	ADD term {$sin = new Node($ADD.text, h, $term.sin);}
	| SUB term {$sin = new Node($SUB.text, h, $term.sin);}
;

term returns [Node sin]
:
	 unaryexpr d[$unaryexpr.sin] {$sin = $d.sin;}
;

d[Node h] returns [Node sin]
:
	t4[h] d[$t4.sin] {$sin = $d.sin;}
	| {$sin = h;}
;

t4[Node h] returns [Node sin]
:
	MUL unaryexpr {$sin = new Node($MUL.text, h, $unaryexpr.sin);}
	| DIV unaryexpr {$sin = new Node($DIV.text, h, $unaryexpr.sin);}
	| MOD unaryexpr {$sin = new Node($MOD.text, h, $unaryexpr.sin);}
;

unaryexpr returns [Node sin]
:
	ADD factor[' '] {$sin = $factor.sin;}
	| SUB factor[' ']
	| factor[' '] {$sin = $factor.sin;}
;

factor[char h] returns [Node sin]
:
	INT {$sin = new Node(h + $INT.text);}
	| FLOAT {$sin = new Node(h + $FLOAT.text);}
	| STRING {$sin = new Node($STRING.text);}
	| NULL
	| lvalue[h] {$sin = $lvalue.node;}
	| LPAREN numexpression RPAREN {$sin = $numexpression.sin;}
;

lvalue[char h] returns [Node node]
:
	 IDENT b[new Node(h + $IDENT.text)] {$node = $b.sin;}
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
